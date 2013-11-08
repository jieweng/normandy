package com.pearl.normandy.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.pearl.normandy.task.TaskTo;

import net.sf.mpxj.MPXJException;
import net.sf.mpxj.ProjectFile;
import net.sf.mpxj.Task;
import net.sf.mpxj.mpp.MPPReader;
import net.sf.mpxj.reader.ProjectReader;

public class MppFileUtil {
	static Logger log = Logger.getLogger(MppFileUtil.class.getName());
	
	private static MppFileUtil mppFileUtil;
	
	private int projectId;
	
	private String createdBy;
	
	private CalendarUtil calendarUtil;
	
	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public CalendarUtil getCalendarUtil() {
		return calendarUtil;
	}

	public void setCalendarUtil(CalendarUtil calendarUtil) {
		this.calendarUtil = calendarUtil;
	}

	public static MppFileUtil getInstance(){
		if(mppFileUtil == null){
			return new MppFileUtil();
		}else{
			return mppFileUtil;
		}
	}
	
	public List<TaskTo> getParentTasks(String filePath){
		ProjectFile file = readMppFile(filePath);
		List<TaskTo> tasks = new ArrayList<TaskTo>();
		
		if(file != null){		
			List<Task> mppTasks = getParents(file);
			if(mppTasks.size() > 0){
				for(Task t : mppTasks){
					TaskTo parentTask = taskConvertor(t);
					List<Task> children = t.getChildTasks();
					List<TaskTo> list = new ArrayList<TaskTo>();
					for(Task child : children){
						TaskTo subTask = taskConvertor(child);
						list.add(subTask);
//						parentTask.getChildren().add(subTask);
					}
					parentTask.setChildren(list);
					tasks.add(parentTask);
				}
			}
		}		
		
		return tasks;
	}
	
	private ProjectFile readMppFile(String filePath){
		ProjectFile pf = null;
		try{
			ProjectReader pr = new MPPReader();
			pf = pr.read(filePath);
		}catch(MPXJException e){
			log.error("Read Mpp File Error", e);
		}
		return pf;
	}
	
	private List<Task> getParents(ProjectFile file){
		List<Task> parents = new ArrayList<Task>();
		Task topTask = file.getTaskByUniqueID(0);
		
		for(Task mppTask : topTask.getChildTasks()){
			getParents(mppTask, parents);
		}
		
		return parents;
	}
	
	private void getParents(Task task, List<Task> parents){
		List<Task> pTasks = task.getChildTasks();
		if(pTasks.size() > 0){
			for(Task pTask : pTasks){
				List<Task> cTasks = pTask.getChildTasks();
				if(cTasks.size() > 0){
					for(Task cTask : cTasks){
						List<Task> lTasks =cTask.getChildTasks();
						if(lTasks.size() > 0){
							getParents(cTask, parents);
						}else{
							parents.add(pTask);
							break;
						}
					}
				}else{
					parents.add(task);
					break;
				}				
			}
		}else{
			parents.add(task);
		}
	}
	
	private TaskTo taskConvertor(Task t){
		TaskTo tt = new TaskTo();
		double plannedStaffDays = t.getWork().getDuration()/Constants.WORK_HOURS_PER_DAY;
		
		tt.setProjectId(projectId);
		tt.setName(t.getName());
		tt.setType(Constants.ACTIVITY_TYPE_PRODUCTION);
		tt.setStartTime(t.getStart());
		tt.setEndTime(t.getFinish());
		tt.setPlannedStaffDays(plannedStaffDays);
		tt.setCreatedDate(new Date());
		tt.setCreatedBy(createdBy);
		tt.setPlannedFeedbackDays(0.0);
		tt.setProgress(0);
		tt.setTaskPriorityId(4);
		tt.setVersion(0);
		tt.setStatusId(Constants.SUBTASK_STATUS_NOTSTARTED_NUM);
		
		return tt;
	}

}
