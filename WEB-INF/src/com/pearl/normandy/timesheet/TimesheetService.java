package com.pearl.normandy.timesheet;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.pearl.normandy.activity.ActivityTo;
import com.pearl.normandy.activity.ActivityDao;
import com.pearl.normandy.task.TaskDao;

public class TimesheetService{
	
	static Logger log = Logger.getLogger(TimesheetService.class.getName());
	
	//==============================
	//Get methods
	//==============================
	public List<TimesheetTo> getTimeSheet(int userId,String activityId)throws Exception{
		List<TimesheetTo> result = timeSheetDao.getTimeSheet(userId, activityId);
		return result;
	}
	
	//==============================
	//Create, Update, Delete
	//==============================
	@Transactional
	public TimesheetTo createEntry(TimesheetTo timeSheet,ActivityTo activity)throws Exception{
		timeSheet.setId(timeSheetDao.insert(timeSheet));
		activityDao.update(activity);
		taskDao.updateTaskProgress(activity.getTaskId());		
		taskDao.updateSummaryTaskProgress(activity.getTaskId());
		
		return timeSheet;
	}
	
	public void updateEntry(TimesheetTo timeSheet)throws Exception{
		timeSheetDao.update(timeSheet);
	}
	
	public boolean deleteEntry(Integer id) throws Exception{
		return timeSheetDao.delete(id);
	}
	

	//==============================
	//Injected Variables
	//==============================	
	private TimesheetDao timeSheetDao;
	private ActivityDao  activityDao;
	private TaskDao		 taskDao;

	public void setTimeSheetDao(TimesheetDao timeSheetDao) {
		this.timeSheetDao = timeSheetDao;
	}

	public void setActivityDao(ActivityDao activityDao) {
		this.activityDao = activityDao;
	}
	
	public void setTaskDao(TaskDao taskDao) {
		this.taskDao = taskDao;
	}	
}
