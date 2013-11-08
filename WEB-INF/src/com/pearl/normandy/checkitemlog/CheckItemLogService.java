package com.pearl.normandy.checkitemlog;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.pearl.normandy.user.UserTo;
import com.pearl.normandy.checkitem.CheckItemTo;

public class CheckItemLogService {
	
	static Logger log = Logger.getLogger(CheckItemLogService.class.getName());
	
	// ==============================
	// Get methods
	// ==============================
	@Transactional
	public List<CheckItemLogTo> getCheckItemLogByUserName(UserTo userTo) throws Exception{
		return checkItemLogDao.getCheckItemLogByUserName(userTo.getUserName());
	}	
		

	// ==============================
	// Create, Update, Delete
	// ==============================
	public CheckItemLogTo createCheckItemLog(CheckItemTo checkItem, UserTo creator) throws Exception {
		CheckItemLogTo checkItemLog = new CheckItemLogTo();
		checkItemLog.setCheckItemId(checkItem.getId());
		checkItemLog.setComment(checkItem.getComment());
		checkItemLog.setStatus(checkItem.getStatus());
		checkItemLog.setCreatedDate(new Date());
		checkItemLog.setCreatedBy(creator.getUserName());		
		return checkItemLogDao.create(checkItemLog);		
	}
	
	
	//==============================
	//Injected Variables
	//==============================	
	private CheckItemLogDao checkItemLogDao;
	
	public void setCheckItemLogDao(CheckItemLogDao checkItemLogDao) {
		this.checkItemLogDao = checkItemLogDao;
	}
}
