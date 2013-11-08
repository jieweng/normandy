package com.pearl.normandy.statuslog;

import java.util.Date;

import org.apache.log4j.Logger;

public class StatusLogService {
	static Logger log = Logger.getLogger(StatusLogService.class.getName());

	
	//==============================
	//Create, Update, Delete
	//==============================
	
	public void create(Integer taskId, String status) throws Exception{		
		StatusLogTo statusLogTo = new StatusLogTo();
		statusLogTo.setTaskId(taskId);
		statusLogTo.setStatus(status);
		statusLogTo.setDate(new Date());
		
		statusLogDao.create(statusLogTo);
	}
	
	//==============================
	//Injected Variables
	//==============================
	private StatusLogDao statusLogDao;

	public void setStatusLogDao(StatusLogDao statusLogDao) {
		this.statusLogDao = statusLogDao;
	}
}
