package com.pearl.normandy.task;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.pearl.normandy.actionlog.ActionLogService;
import com.pearl.normandy.activity.ActivityDao;
import com.pearl.normandy.activity.ActivityTo;
import com.pearl.normandy.calendar.CalendarDao;
import com.pearl.normandy.core.mail.NormandyMail;
import com.pearl.normandy.productionprocess.ProductionProcessTo;
import com.pearl.normandy.project.ProjectTo;
import com.pearl.normandy.projectuser.ProjectUserService;
import com.pearl.normandy.projectuser.ProjectUserTo;
import com.pearl.normandy.statuslog.StatusLogService;
import com.pearl.normandy.taskdetail.TaskDetailDao;
import com.pearl.normandy.user.UserTo;
import com.pearl.normandy.utils.CalendarUtil;
import com.pearl.normandy.utils.Constants;
import com.pearl.normandy.utils.MppFileUtil;
import com.pearl.normandy.utils.NormandyConfiguration;
import com.pearl.normandy.utils.comparator.TaskComparator;


public class TaskService {
	static Logger log = Logger.getLogger(TaskService.class.getName());
	//static NormandyConfiguration config = NormandyConfiguration.getInstance();
	static String ENTITY = Constants.ENTITY_TASK;

	
	//==============================
	//Get methods
	//==============================	
	public List<String> getMilestone(Integer projectId, boolean getDefault) throws Exception{
		List<String> result = null;
		
		result = taskDao.getMilestone(projectId);
		if (getDefault){
			result.add(0, Constants.SELECT_ITEM_ALL);
		}

		return result;
	}	
	
	public List<String> getTaskCategory(Integer projectId, boolean getDefault) throws Exception{
		List<String> result = null;
		
		result = taskDao.getTaskCategory(projectId);
		if (getDefault){
			result.add(0, Constants.SELECT_ITEM_ALL);
		}

		return result;
	}		
	

	@SuppressWarnings("unchecked")
	public TaskTo getTaskById(Integer taskId) throws Exception {
		TaskTo task = taskDao.getTaskById(taskId);
		
		if(task != null){
			ArrayList param = new ArrayList(1);
			param.add(task);
			List<TaskTo> leaf = taskDao.getTaskLeafByIds(param);		
			task.setChildren(leaf);			
		}
		
		return task;
	}			
	
	
	@SuppressWarnings("unchecked")
	public List<TaskTo> getTaskByIds(List ids) throws Exception {
		return taskDao.getTaskByIds(ids);
	}		

	

	@SuppressWarnings("unchecked")
	public List<TaskTo> getTaskHierarchicalByIds(List<TaskTo> ids) throws Exception {
		List<TaskTo> result = null;		
		Map root = taskDao.getTaskRootMapByIds(ids);		
		if(root != null && root.size()>0){			
			List<TaskTo> leaf = taskDao.getTaskLeafByIds(new ArrayList(root.values()));			
			result = assembleTask(root, leaf);			
		}
		return result;
	}		
		
	
	@SuppressWarnings("unchecked")
	public List<TaskTo> getTaskByProjectId(Integer projectId) throws Exception {
		return getTask(projectId, null, null);
	}	
	
	@SuppressWarnings("unchecked")
	public List<TaskTo> getTask(Integer projectId, String milestone, String taskCategory) throws Exception {	
		List<TaskTo> result = null;	
		Map root = taskDao.getTaskRootMap(projectId, milestone, taskCategory, null);		
		
		if(root != null && root.size()>0){	
			List<TaskTo> leaf = taskDao.getTaskLeaf(projectId, milestone, taskCategory);
			result = assembleTask(root, leaf);
		}			
			
		return result;
	}	
	
	@SuppressWarnings("unchecked")
	public List<TaskTo> getTaskByStatus(Integer projectId, String milestone, String taskCategory, String status) throws Exception {	
		List<TaskTo> result = taskDao.getTaskByStatus(projectId, milestone, taskCategory, status);							
		return result;
	}		
	
	
	
	@SuppressWarnings("unchecked")
	public List<TaskTo> getTask(Integer projectId, String milestone, String taskCategory, Integer ownerId) throws Exception {				
		List<TaskTo> result = null;			
		Map root = taskDao.getTaskRootMap(projectId, milestone, taskCategory, ownerId);
		
		if(root != null && root.size()>0){			
			List<TaskTo> leaf = taskDao.getTaskLeafByIds(new ArrayList(root.values()));			
			result = assembleTask(root, leaf);			
		}

		return result;
	}	
	
	@SuppressWarnings("unchecked")
	public List<TaskTo> getTaskByProjectUser(Integer projectId, Integer userId) throws Exception {
		List<ProjectUserTo> projectUsers = projectUserService.getProjectUserByUserId(userId, projectId);
		
		String privilege = "";
		ArrayList adPrivilege = new ArrayList();
		ArrayList leadPrivilege = new ArrayList();
    	
		for(int i=0; i<projectUsers.size(); i++){
			ProjectUserTo pu = (ProjectUserTo)projectUsers.get(i);
			
    		if(pu.getProjectRoleId() == Constants.PROJECT_ROLE_AD_NUM){
    			adPrivilege.add(pu);
    		}
    		else if(pu.getProjectRoleId() == Constants.PROJECT_ROLE_LEAD_NUM && pu.getStatus() != Constants.RESOURCE_PROJECT_STATUS_RELEASED){
    			leadPrivilege.add(pu);
    		}			
		}

    	
    	//Set privilege for this project
    	if(adPrivilege.size() > 0){
    		privilege = Constants.PROJECT_ROLE_AD;
    	}
    	else if(leadPrivilege.size() > 0){
    		privilege = Constants.PROJECT_ROLE_LEAD;
    	}
	
		
		if(Constants.PROJECT_ROLE_LEAD.equals(privilege)){
			return getTask(projectId, null, null, userId); //return lead's tasks
		}
		else{
			return getTask(projectId, null, null); //return all tasks
		}
	}		


	public List<TaskTo> getTaskByProjectIdAndOwnerId(Integer projectId, Integer ownerId) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("projectId", projectId.toString());
		map.put("ownerId", ownerId.toString());
		List<TaskTo> result = null;
		result = taskDao.getTask(map);
		return result;
	}

	@SuppressWarnings("unchecked")
	public List getTaskByProductionProcessId(int productionProcessId) throws Exception {
		List result = new ArrayList<TaskTo>();
		List<TaskTo> list = taskDao.getTaskByProductionProcessId(productionProcessId);
		List<TaskTo> taskWithOutActivity = new ArrayList<TaskTo>();
		List<TaskTo> taskWithNotStartedActivity = new ArrayList<TaskTo>();
		List<TaskTo> taskWithStartedActivity = new ArrayList<TaskTo>();
		for (int i = 0; i < list.size(); i++) {
			TaskTo task = (TaskTo) list.get(i);
			List<ActivityTo> activityList = activityDao
					.getActivityByTaskId(task.getId());

			if (activityList.size() < 1) {
				taskWithOutActivity.add(task);
			} else {
				for (int j = 0; j < activityList.size(); j++) {
					ActivityTo activity = activityList.get(j);
					if (activity.getActualStartTime() != null) {
						taskWithStartedActivity.add(task);
						j = activityList.size() - 1;
					} else if (j == activityList.size() - 1) {
						taskWithNotStartedActivity.add(task);
					}
				}
			}
		}
		result.add(taskWithOutActivity);
		result.add(taskWithNotStartedActivity);
		result.add(taskWithStartedActivity);
		return result;
	}
	
	
	public List<String> getTaskGroupByProjectId(Integer projectId)throws Exception{
		return taskDao.getTaskGroupByProjectId(projectId);
	}	

	public List<String> getTaskGroupByTaskId(Integer projectId, Integer taskId)throws Exception{
		return taskDao.getTaskGroupByTaskId(projectId, taskId);
	}	
	
	
	//==============================
	//Create, Update, Delete
	//==============================
	public TaskTo createAndGetTask(TaskTo task, UserTo creator) throws Exception{			
		return getTaskById(createTask(task, creator).getId());
	}	
	
	@SuppressWarnings("static-access")
	@Transactional
	public TaskTo createTask(TaskTo task, UserTo creator) throws Exception{
		
		String tempUrl=task.getReferenceUrl();
		TaskTo newTask = taskDao.create(task);
		
		//Move reference file from temp folder to reference folder and create record
		if(tempUrl != null){
			String url = moveFile(tempUrl, task.getProjectId().toString(),
					newTask.getId().toString());
			task.setReferenceUrl(url);
		}	
		
		taskDao.update(newTask);
				
		if(task.getProductionProcessId() != 0){
			taskDao.createSubtask(task);
		}
		
		//Create Task Detail
		taskDetailDao.create(task.getDetails(), task.getId(), task.getCreatedBy());	
		
		
		//Action Log
		actionLogService.create(task.getProjectId(), this.ENTITY, task.getId().toString(), task.getName(), null,
				Constants.ACTION_CREATE, null, null, creator.getName());
		
		return newTask;
	}
	
	@Transactional
	public boolean createTaskFromMpp(String fileName, ProjectTo project, UserTo user) throws Exception{
		boolean isSucceeded = false;
		
		CalendarUtil calendar = CalendarUtil.getInstance();
		List<Date> holidays = calendarDao.getHolidays();
		calendar.setHolidays(holidays);
		
		MppFileUtil mpu = MppFileUtil.getInstance();
		mpu.setProjectId(project.getId());
		mpu.setCreatedBy(user.getUserName());
		mpu.setCalendarUtil(calendar);
		
		NormandyConfiguration config = NormandyConfiguration.getInstance();
		String filePath = config.REFERENCE_TEMP_FOLDER + fileName;
		List<TaskTo> tasks = mpu.getParentTasks(filePath);
		
		if(tasks.size() > 0){
			for(int i = 0; i < tasks.size(); i++){
				TaskTo task = tasks.get(i);
				int id = taskDao.create(task).getId();
				for(TaskTo subTask : task.getChildren()){
					subTask.setParentId(id);
					subTask.setParentName(task.getName());
					taskDao.create(subTask);
				}
			}
			isSucceeded = true;
		}
		
		return isSucceeded;
	}
	
	@SuppressWarnings("static-access")
	@Transactional
	public TaskTo addSubtask(TaskTo task, UserTo creator) throws Exception{
		task.setParentId(task.getId());
		task.setParentName(task.getName());
		task.setName("[No Name]");
		task.setReferenceUrl(null);
		task.setStatusId(Constants.SUBTASK_STATUS_NOTSTARTED_NUM);
		task.setStatus(Constants.SUBTASK_STATUS_NOTSTARTED);
		task.setProgress(0);
		task.setPlannedStaffDays(0.0);
		task.setPlannedFeedbackDays(0.0);
		
		//Create Summary Task
		TaskTo newTask = taskDao.create(task);			
		
		//Action Log
		actionLogService.create(task.getProjectId(), this.ENTITY, task.getId().toString(), task.getName(), null,
				Constants.ACTION_CREATE, null, null, creator.getName());
		
		return newTask;
	}	
	
	@Transactional
	public List<TaskTo> copyTask(TaskTo task, Integer copyNum, boolean copyDetail, UserTo operator)throws Exception{
		List<TaskTo> result = new ArrayList<TaskTo>();
		
		Integer oldId = task.getId();
		for(int i=0; i<copyNum; i++){			
//			log.info("################ Copying Task: "+taskId+" ###############");
			task.setStatusId(Constants.SUBTASK_STATUS_NOTSTARTED_NUM);
			TaskTo newTask = taskDao.create(task);
			taskDao.copySubtask(oldId, newTask.getId(), newTask.getName(), Constants.SUBTASK_STATUS_NOTSTARTED_NUM, 0);
			result.add((TaskTo)BeanUtils.cloneBean(newTask));			
			
			if(newTask.getReferenceUrl() !=null){
				@SuppressWarnings("unused")
				String url = copyFile(newTask.getReferenceUrl(), newTask.getProjectId().toString(), newTask.getId());
			}
			
			if(copyDetail){
//				log.info("################ Copying Task Detail Of: "+taskId+" ###############");
				taskDetailDao.copyTaskDetail(oldId, newTask.getId(), operator.getUserName());
			}
		}
		
		return getTaskHierarchicalByIds(result);
	}
	
	@SuppressWarnings("static-access")
	@Transactional
	public boolean updateTask(TaskTo task, String field, UserTo updator) throws Exception {
				
		TaskTo oldTaskTo = taskDao.getTaskById(task.getId());
		Task oldValue = new Task();
		Task newValue = new Task();
		BeanUtils.copyProperties(oldValue, oldTaskTo);
		
		//if plannedStaffDays updated, re-calculate subtask and task progress
		boolean isProgressChanged = false;
		if(task.getPlannedStaffDays().compareTo(oldTaskTo.getPlannedStaffDays())!=0
			|| task.getPlannedFeedbackDays().compareTo(oldTaskTo.getPlannedFeedbackDays())!=0){
			isProgressChanged = true;
		}					
		
		BeanUtils.copyProperties(newValue, task);
		
		//Update referenceUrl
		if(field.equals("referenceUrl") && newValue.getReferenceUrl()!=null 
				&&!newValue.getReferenceUrl().equals(oldValue.getReferenceUrl())){
			if(task.getReferenceUrl() != null && !task.getReferenceUrl().equals("")){
				String url = moveFile(task.getReferenceUrl(), task.getProjectId().toString(), task.getId().toString());
				task.setReferenceUrl(url);
			}			
		}		
		
		log.info("################ Updating Task: "+task.getId()+"; Field: "+field+" ###############");
		taskDao.update(task);
		
		
		if(!oldValue.getName().equals(newValue.getName())){
			taskDao.updateParentName(task.getId());
			activityDao.updateActivityName(task.getId());
		}
		
		if(!oldValue.getType().equals(newValue.getType())){
			//update activity type(training/production) based on task type
			if(Constants.PROJECT_TYPE_PRODUCTION.equals(task.getType())){
				activityDao.updateTrainingFlagByTaskId(false, task.getId());				
			}
			else if(Constants.PROJECT_TYPE_TRAINING.equals(task.getType())){
				activityDao.updateTrainingFlagByTaskId(true, task.getId());
			}
		}

		if(isProgressChanged && task.getParentId()!=0){
			log.info("################ Updating Task & Summary Task Progress ###############");
			taskDao.updateTaskProgress(task.getId());
			taskDao.updateSummaryTaskProgress(task.getId());				
		}						
		
		//Log Action
		actionLogService.createObject(task.getProjectId(), this.ENTITY, task.getId().toString(), task.getName(),
				Constants.ACTION_UPDATE, oldValue, newValue, updator.getName());

		return true;
	}
	
	@Transactional
	public boolean updateTaskStatus(Integer taskId, Integer statusId) throws Exception {
		taskDao.updateTaskStatus(taskId, statusId);
		TaskTo	task = getTaskById(taskId);
		if(task == null){
			return false;
		}
		Double statusAvg = taskDao.getTaskStatusAvg(task.getParentId());

		if(statusAvg != null){
			if(Double.compare(statusAvg, Constants.SUBTASK_STATUS_NOTSTARTED_NUM) == 0){
				taskDao.updateTaskStatus(task.getParentId(), Constants.SUBTASK_STATUS_NOTSTARTED_NUM);				
			}
			else if(Double.compare(statusAvg, Constants.SUBTASK_STATUS_COMPLETE_NUM) < 0){
				taskDao.updateTaskStatus(task.getParentId(), Constants.SUBTASK_STATUS_WIP_NUM);
			}
			else if(Double.compare(statusAvg, Constants.SUBTASK_STATUS_APPROVED_NUM) < 0){
				taskDao.updateTaskStatus(task.getParentId(), Constants.SUBTASK_STATUS_COMPLETE_NUM);				
			}			
			else if(Double.compare(statusAvg, Constants.SUBTASK_STATUS_APPROVED_NUM) == 0){
				taskDao.updateTaskStatus(task.getParentId(), Constants.SUBTASK_STATUS_APPROVED_NUM);
			}
		}
		
		//Task Log
		statusLogService.create(task.getId(), task.getStatus());
		return true;
	}		
	
	@Transactional
	public boolean setStatusNotStarted(Integer taskId) throws Exception {
		this.updateTaskStatus(taskId, Constants.SUBTASK_STATUS_NOTSTARTED_NUM);
		activityDao.resetActivityByTaskId(taskId);
		taskDao.updateTaskProgress(taskId);		
		taskDao.updateSummaryTaskProgress(taskId);
		
		return true;
	}
	
	@Transactional
	public boolean setStatusWIP(Integer taskId, ArrayList<ActivityTo> activities) throws Exception {
		this.updateTaskStatus(taskId, Constants.SUBTASK_STATUS_WIP_NUM);
		
		if(activities.size() > 0){
			ArrayList<Integer> ids = new ArrayList<Integer>();
			
			for(int i=0; i< activities.size(); i++){
				ActivityTo activity = activities.get(i);				
				ids.add(activity.getId());
			}
			
			activityDao.resetActualEndTimeByIds(ids);
		}
		
		return true;
	}	
	
	@Transactional
	public boolean setStatusAfterComplete(Integer taskId, Integer statusId) throws Exception {
		this.updateTaskStatus(taskId, statusId);
		activityDao.completeProductionActivityByTaskId(taskId);
		taskDao.updateTaskProgress(taskId);		
		taskDao.updateSummaryTaskProgress(taskId);
		
		return true;
	}	
	
	@Transactional
	public boolean setStatusApproved(Integer taskId) throws Exception {
		this.updateTaskStatus(taskId, Constants.SUBTASK_STATUS_APPROVED_NUM);
		activityDao.completeProductionActivityByTaskId(taskId);
		taskDao.updateTaskProgress(taskId);		
		taskDao.updateSummaryTaskProgress(taskId);
		
		return true;
	}			
	
	public void assignTasks(List<TaskTo> taskList, ProjectUserTo owner, UserTo updator) throws Exception{	
		TaskTo taskTo;
		for(int i = 0; i < taskList.size(); i++){
			taskTo = taskList.get(i);
			assignTask(taskTo, owner, updator);
		}
	}
	
	@SuppressWarnings("static-access")
	@Transactional
	public boolean assignTask(TaskTo taskTo, ProjectUserTo owner, UserTo updator) throws Exception {
		//Update Task Owner ID
		TaskTo task = taskDao.getTaskById(taskTo.getId());
		String oldValue = task.getOwner();
		String newValue = owner.getName();
		
		task.setOwnerId(owner.getUserId());
		task.setOwner(owner.getName());
		taskDao.update(task);
		
		//Log Action
		actionLogService.create(taskTo.getProjectId(), this.ENTITY, taskTo.getId().toString(), taskTo.getName(), null,
				Constants.ACTION_REASSIGNED, oldValue, newValue, updator.getName());			
		
		return true;
	}
	
	@SuppressWarnings({ "unchecked", "static-access" })
	@Transactional	
	public boolean cancelTask(TaskTo task, UserTo updator) throws Exception {
		ArrayList param = new ArrayList(1);
		param.add(task);		
		List<TaskTo> ids = taskDao.getTaskLeafByIds(param);
		ids.add(task);		
		
		taskDao.cancel(task.getId());				
		activityDao.cancelActivityByTaskIds(ids);
		
		//Log Action
		actionLogService.create(task.getProjectId(), this.ENTITY, task.getId().toString(), task.getName(), null,
				Constants.ACTION_CANCEL, null, null, updator.getName());		
		return true;	
	}	
	
	//No Transaction needed for migrating process
	public boolean migrateProductionProcess(ProductionProcessTo process, List<TaskTo> list) throws Exception {
		for (int i = 0; i < list.size(); i++) {
			TaskTo taskTo = (TaskTo) list.get(i);			
			migrateTask(process.getId(), taskTo);
		}		
		
		return true;
	}	
	
	
	//Need to refactor
	@Transactional	
	public void migrateTask(Integer processId, TaskTo taskTo) throws Exception {
		//Update Task Process and Revision
		TaskTo task = taskDao.getTaskById(taskTo.getId());			
		task.setProductionProcessId(processId);
		task.setRevision(task.getRevision() + 1);
		taskDao.update(task);
		
		//Delete All Not Started Activities
//		activityDao.cancelActivityByTaskId(taskTo.getId());
	}
	
	
	//==============================
	//Private methods
	//==============================	
	@SuppressWarnings("unchecked")
	private List<TaskTo> assembleTask(Map root, List<TaskTo> leaf) throws Exception{
		Map tmp = new HashMap();

		TaskTo task;
		TaskTo parent;
		for(int i=0; i< leaf.size();){
			task = (TaskTo)leaf.get(i);
			
			if(root.containsKey(task.getParentId())){
				parent = (TaskTo)root.get(task.getParentId());
				
				List<TaskTo> children = parent.getChildren();
				if(children == null){
					children = new ArrayList<TaskTo>();
				}
				
				children.add(task);
				tmp.put(task.getParentId(), task);
				parent.setChildren(children);
				leaf.remove(i);
			}
			else{
				i++;
			}
		}
		
		if(leaf.size() > 0){
			NormandyConfiguration config = NormandyConfiguration.getInstance();
			NormandyMail mail = new NormandyMail(); 
			mail.sendMail(config.ERROR_MAIL_TO_ADDR, config.ERROR_MAIL_TO_NAME, "Task Exception", "taskId:" + leaf.get(0).getId() + "  项目名称:" + leaf.get(0).getProjectName());		
		}		
		
		 ArrayList result = new ArrayList(root.values());
		 Collections.sort(result, new TaskComparator());
		 return result;
	}			

	private String moveFile(String name, String folder, String newName){
		NormandyConfiguration config = NormandyConfiguration.getInstance();
		String suffix = name.substring(name.indexOf('.'));
		String url = newName + suffix;
		
		try{	
			File path 		= new File(config.REFERENCE_FOLDER+ folder);
			File minPath    = new File(config.THUMBNAIL_FOLDER+ folder);
			File srcFile 	= new File(config.REFERENCE_TEMP_FOLDER+name);			
			File destFile 	= new File(path + "/" + url);
			File minFile    = new File(minPath + "/" + url);
			
			
			if(!path.exists()){
				path.mkdir();
			}
			if(!minPath.exists()){
				minPath.mkdir();
			}
			if(destFile.exists() && srcFile.exists()){
				destFile.delete();
				if(minFile.exists()){
					minFile.delete();
				}
			}	
			
			BufferedImage bis = ImageIO.read(srcFile);
			int newWidth  = 150;
			int newHeight = 150;
			BufferedImage bid = new BufferedImage(newWidth,newHeight,BufferedImage.TYPE_INT_RGB);
			bid.getGraphics().drawImage(bis,0,0,newWidth, newHeight,null); 
			ImageIO.write(bid, "jpeg", minFile);
			//这里需要改进
//			FileUtils.moveFile(srcFile, destFile);
			FileUtils.copyFile(srcFile, destFile);
//			FileUtil.deletefile("webapps\\normandy\\temp\\");
		}
		catch(Exception e){
			log.error("Error in moveFile: ", e);			
		}
		return url;
	}
	
	
	private String copyFile(String name, String folder, Integer newName) throws Exception{
		NormandyConfiguration config = NormandyConfiguration.getInstance();
		String suffix = name.substring(name.indexOf("."));
		String url = newName + suffix;

		File srcFile = new File(config.REFERENCE_FOLDER+folder+"/"+name);
		File destFile = new File(config.REFERENCE_FOLDER+folder+"/"+url);
		File srcMinFile = new File(config.THUMBNAIL_FOLDER+folder+"/"+name);
		File destMinFile = new File(config.THUMBNAIL_FOLDER+folder+"/"+url);

		FileUtils.copyFile(srcFile, destFile);
		if(srcMinFile.exists()){
			FileUtils.copyFile(srcMinFile, destMinFile);
		}
		return url;
	}
	
	//==============================
	//Injected Variables
	//==============================
	private TaskDao			taskDao;
	private TaskDetailDao 	taskDetailDao;
	private ActivityDao		activityDao;
	private ActionLogService actionLogService;	
	private ProjectUserService projectUserService;
	private StatusLogService statusLogService;	
	private CalendarDao calendarDao;

	public void setTaskDao(TaskDao taskDao) {
		this.taskDao = taskDao;
	}
	
	public void setTaskDetailDao (TaskDetailDao taskDetailDao){
		this.taskDetailDao = taskDetailDao;
	}
	
	public void setActivityDao(ActivityDao activityDao){
		this.activityDao = activityDao;
	}
	
	public void setActionLogService(ActionLogService actionLogService){
		this.actionLogService = actionLogService;
	}	
	
	public void setProjectUserService(ProjectUserService projectUserService){
		this.projectUserService = projectUserService;
	}	
	
	public void setStatusLogService(StatusLogService statusLogService) {
		this.statusLogService = statusLogService;
	}
	
	public void setCalendarDao(CalendarDao calendarDao){
		this.calendarDao =calendarDao;
	}
}