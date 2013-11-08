package com.pearl.normandy.activity;

import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.pearl.normandy.actionlog.ActionLogService;
import com.pearl.normandy.calendar.CalendarDao;
import com.pearl.normandy.task.TaskDao;
import com.pearl.normandy.task.TaskService;
import com.pearl.normandy.user.UserTo;
import com.pearl.normandy.utils.CalendarUtil;
import com.pearl.normandy.utils.Constants;

public class ActivityService {
	static Logger log = Logger.getLogger(ActivityService.class.getName());
	static String ENTITY = Constants.ENTITY_ACTIVITY;

	// ==============================
	// Get methods
	// ==============================
	// Get user related activities with specified start date and end date
	public List<ActivityTo> getActivitiesByUsers(List<UserTo> users, Date startDate, Date endDate, String deleted) throws Exception {
		List<ActivityTo> result = null;

		if(startDate == null || endDate == null){
			result = activityDao.getActivitiesByUsers(users, deleted);
		}
		else{
			result = activityDao.getActivitiesByUsers(users, startDate, endDate, deleted);
		}

		return result;
	}	
	
	public List<ActivityTo> getActivitiesByUsers(List<UserTo> users) throws Exception{
		return activityDao.getActivitiesByUsers(users);
	}		
	
	
	public List<ActivityTo> getNotDeletedActivitiesByUsers(List<UserTo> users) throws Exception{
		return activityDao.getActivitiesByUsers(users, Constants.DELETE_NO);
	}
	
	public List<ActivityTo> getActivitiesByTaskId(Integer taskId)throws Exception {
		return activityDao.getActivitiesByTaskId(taskId);
	}
	
	public double  getTotalStaffDaysByTaskId(Integer taskId)throws Exception{
		double returnValue=0;
		List<ActivityTo> list=getActivitiesByTaskId(taskId);
		for(ActivityTo ac:list){
			returnValue+=ac.getAssignedEffort().doubleValue();
		}
		return returnValue;
	}
	
	public Double getAssignedEffortByProjectId(Integer projectId)throws Exception {
		return activityDao.getAssignedEffortByProjectId(projectId);
	}
	
	public Double getAssignedEffortByTaskId(Integer taskId)throws Exception {
		return activityDao.getAssignedEffortByTaskId(taskId);
	}

	public List<ActivityTo> getDeletedActivitiesByTaskId(Integer taskId) throws Exception{
		return activityDao.getDeletedActivitiesByTaskId(taskId);
	}
	
	//Ready to delete, Frank, 2009/7/17
/*	public int getActivityFeedbackStatus(ActivityTo activityTo)throws Exception{
		return activityDao.getActivityFeedbackStatus(activityTo);
	}*/
	
	public List<ActivityTo> getNotCompleteActivityByTaskId(Integer taskId, Integer userId, Date endTime)throws Exception{
		List<ActivityTo> result = activityDao.getNotCompleteActivityByTaskId(taskId, userId, endTime);
		return result;
	}
	
	public List<ActivityTo> getActivityByTime(Date date) throws Exception{
		List<ActivityTo> result = activityDao.getActivityByTime(date);
		return result;
	}
	
	public List<ActivityTo> getNonProductionActivity(Integer projectId, List<String> typeList) throws Exception{
		List<ActivityTo> result = activityDao.getNonProductionActivity(projectId, typeList);
		return result;
	}
	
	public List<ActivityTo>	getOtherActivityForProjectProgress(Integer projectId, String milestone) throws Exception{
		List<ActivityTo> result = activityDao.getOtherActivityForProjectProgress(projectId, milestone);
		return result;
	}

	// ==============================
	// Create, Update, Delete
	// ==============================
	@SuppressWarnings("static-access")
	@Transactional
	public ActivityTo createActivity(ActivityTo activityTo, UserTo creator) throws Exception{		
		activityTo.setAssignedEffort(activityTo.getAssignedEffort());
		activityTo.setRemainingEffort(activityTo.getActualStaffDays());		
		ActivityTo result = activityDao.create(activityTo);
		
		if(activityTo.getTaskId()!=0){		
			taskDao.updateTaskProgress(activityTo.getTaskId());	
			taskDao.updateSummaryTaskProgress(activityTo.getTaskId());
		}
		
		//Action Log
		actionLogService.create(activityTo.getProjectId(), this.ENTITY, activityTo.getId().toString(), activityTo.getName(), null,
				Constants.ACTION_CREATE, null, null, creator.getName());			

		return result;
	}

	@SuppressWarnings("static-access")
	public boolean updateActivity(ActivityTo activityTo, UserTo updator) throws Exception{		
		ActivityTo oldActivityTo = activityDao.getActivityById(activityTo.getId());
		
		if(oldActivityTo != null){
			Activity oldValue = new Activity();
			Activity newValue = new Activity();
			BeanUtils.copyProperties(oldValue, oldActivityTo);
			BeanUtils.copyProperties(newValue, activityTo);						
			
			activityDao.update(activityTo);
			
			//if plannedStaffDays updated, re-calculate subtask and task progress
			if(activityTo.getAssignedEffort().compareTo(oldActivityTo.getAssignedEffort())!=0){
				taskDao.updateTaskProgress(activityTo.getTaskId());	
				taskDao.updateSummaryTaskProgress(activityTo.getTaskId());				
			}		
			
			//Log Action
			actionLogService.createObject(activityTo.getProjectId(), this.ENTITY, activityTo.getId().toString(), activityTo.getName(),
					Constants.ACTION_UPDATE, oldValue, newValue, updator.getName());	
		}
		
		return true;
	}

	@Transactional
	public List<ActivityTo> updateActivities(List<ActivityTo> activities, UserTo updator) throws Exception{
		ActivityTo activityTo = null;
		
		if(activities.size() > 0){
			for (int i = 0; i < activities.size(); i++) {
				activityTo = (ActivityTo) activities.get(i);
				updateActivity(activityTo, updator);
			}
			
			taskDao.updateTaskProgress(activityTo.getTaskId());			
			taskDao.updateSummaryTaskProgress(activityTo.getTaskId());
		}
		
		return activities;
	}
	
	@Transactional
	public void updateActualStaffDays(Date date) throws Exception{
		List<ActivityTo> actList = getActivityByTime(date);
		if (actList.size() > 0){
			CalendarUtil cu = CalendarUtil.getInstance();
			List<Date> holidays = calendarDao.getHolidays();
			cu.setHolidays(holidays);
			for (int i=0; i < actList.size(); i++){
				ActivityTo act = (ActivityTo)actList.get(i);
				Date s = act.getStartTime();
				Date e = act.getEndTime();
				double actualStaffDays = cu.getWorkDays(s, e);
				activityDao.modifyActualStaffDays(act.getId(), actualStaffDays);
			}
		}
	}
	
	@Transactional
	public boolean startActivity(ActivityTo activity)throws Exception{
		if(activity.getStatusId() == Constants.SUBTASK_STATUS_NOTSTARTED_NUM){
			taskService.updateTaskStatus(activity.getTaskId(), Constants.SUBTASK_STATUS_WIP_NUM);
		}
		activity.setActualStartTime(new Date());
		activityDao.update(activity);
		return true;
	}
	
	@Transactional
	public boolean completeActivity(ActivityTo activity, UserTo currUser)throws Exception{
		activity.setActualEndTime(new Date());
		activity.setRemainingEffort(Double.valueOf(0));
		activity.setProgress(100);
		activityDao.update(activity);					
		
		//If all activities complete, change subtask status to complete
		if(activity.getStatusId() < Constants.SUBTASK_STATUS_COMPLETE_NUM){
			int num = activityDao.getCountActivityForWIP(activity.getTaskId());
			if(num == 0){
				taskService.updateTaskStatus(activity.getTaskId(), Constants.SUBTASK_STATUS_COMPLETE_NUM);
			}
		}
		

		//Update subtask and task progress
		taskDao.updateTaskProgress(activity.getTaskId());			
		taskDao.updateSummaryTaskProgress(activity.getTaskId());	
		
		return true;
	}
	
	@Transactional
	public boolean completeActivities(List<ActivityTo> activities,UserTo currUser)throws Exception{
		for (ActivityTo activityTo : activities) {
			if(activityTo.getActualStartTime() == null){
				this.startActivity(activityTo);
			}
			
			this.completeActivity(activityTo, currUser);
		}
		return true;
	}

	@SuppressWarnings("static-access")
	@Transactional
	public void cancelActivity(ActivityTo activityTo, UserTo updator) throws Exception{
		// Cancel Activity		
		activityDao.cancel(activityTo.getId());	
		
		//Action Log
		actionLogService.create(activityTo.getProjectId(), this.ENTITY, activityTo.getId().toString(), activityTo.getName(), null,
				Constants.ACTION_CANCEL, null, null, updator.getName());		
	}

	@SuppressWarnings("static-access")
	@Transactional
	public void deleteActivity(ActivityTo activityTo, UserTo updator) throws Exception{
		activityDao.delete(activityTo.getId());
		
		if(activityTo.getTaskId()!=0){
			taskDao.updateTaskProgress(activityTo.getTaskId());		
			taskDao.updateSummaryTaskProgress(activityTo.getTaskId());			
		}
		
		//Action Log
		actionLogService.create(activityTo.getProjectId(), this.ENTITY, activityTo.getId().toString(), activityTo.getName(), null,
				Constants.ACTION_DELETE, null, null, updator.getName());			
	}

	// ==============================
	// Injected Variables
	// ==============================
	private ActivityDao activityDao;
	private TaskDao	taskDao;
	private TaskService taskService;
	private ActionLogService actionLogService;	
	private CalendarDao calendarDao;
	
	public void setActivityDao(ActivityDao activityDao) {
		this.activityDao = activityDao;
	}	
	
	public void setTaskDao(TaskDao taskDao) {
		this.taskDao = taskDao;
	}		

	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}
	
	public void setActionLogService(ActionLogService actionLogService){
		this.actionLogService = actionLogService;
	}	
	
	public void setCalendarDao(CalendarDao calendarDao){
		this.calendarDao = calendarDao;
	}
}
