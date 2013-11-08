package com.pearl.normandy.checkitem;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.pearl.normandy.user.UserTo;
import com.pearl.normandy.activity.ActivityTo;
import com.pearl.normandy.checkitemlog.CheckItemLogService;
import com.pearl.normandy.core.mail.NormandyNotification;
import com.pearl.normandy.enumerator.CheckItemEnum;

public class CheckItemService {
	
	static Logger log = Logger.getLogger(CheckItemService.class.getName());
	
	// ==============================
	// Get methods
	// ==============================
	@Transactional
	public List<CheckItemTo> getCheckItemsPending(UserTo userTo) throws Exception{
		return checkItemDao.getCheckItemsPending(userTo.getId());
	}	
	
	@Transactional
	public List<CheckItemTo> getCheckItemsReviewed(UserTo userTo) throws Exception{
		return checkItemDao.getCheckItemsReviewed(userTo.getId());
	}
	
	@Transactional
	public List<CheckItemTo> getCheckItemsComplete(UserTo userTo) throws Exception{
		return checkItemDao.getCheckItemsComplete(userTo.getId());
	}		
	
	@Transactional
	public List<CheckItemTo> getCheckItemsByActivity(ActivityTo activity) throws Exception{
		return checkItemDao.getCheckItemsByActivityId(activity.getId());
	}		

	// ==============================
	// Create, Update, Delete
	// ==============================
	public CheckItemTo createCheckItem(Integer activityId, String comment, UserTo creator) throws Exception {
		CheckItemTo checkItemTo = new CheckItemTo();
		checkItemTo.setActivityId(activityId);
		checkItemTo.setComment(comment);
		checkItemTo.setStatus(CheckItemEnum.getInitialStatus());
		checkItemTo.setCreatedDate(new Date());
		checkItemTo.setCreatedBy(creator.getUserName());		
		return checkItemFacade.createCheckItem(checkItemTo, creator);		
	}
	
	@Transactional
	public boolean updateCheckItemComment(CheckItemTo checkItem) throws Exception{
		checkItemDao.updateComment(checkItem);
		return true;
	}	
	
/*	public boolean updateCheckItemsStatusNext(List<CheckItemTo> checkItems, UserTo updator) throws Exception{
		for(int i=0;i<checkItems.size();i++){
			this.updateCheckItemStatusNext(checkItems.get(i), updator);
		}
		
		return true;
	}	
	
	@Transactional
	public CheckItemTo updateCheckItemStatusNext(CheckItemTo checkItem, UserTo updator) throws Exception{
		checkItem = checkItemFacade.updateCheckItemStatusNext(checkItem, updator);
		checkItemLogService.createCheckItemLog(checkItem, updator);
		return checkItem;
	}	

	
	public boolean updateCheckItemsStatusFix(List<CheckItemTo> checkItems, UserTo updator) throws Exception{
		for(int i=0;i<checkItems.size();i++){
			this.updateCheckItemStatusFix(checkItems.get(i), updator);
		}		
		return true;		
	}	
	
	@Transactional	
	public CheckItemTo updateCheckItemStatusFix(CheckItemTo checkItem, UserTo updator) throws Exception{
		checkItem = checkItemFacade.updateCheckItemStatusFix(checkItem, updator);
		checkItemLogService.createCheckItemLog(checkItem, updator);
		return checkItem;
	}*/		
	
	@Transactional
	public boolean updateCheckItemsStatusNext(List<CheckItemTo> checkItems, UserTo updator) throws Exception{
		checkItemFacade.updateCheckItemStatusNext(checkItems, updator);
		for(int i = 0; i < checkItems.size(); i++){
			checkItemLogService.createCheckItemLog(checkItems.get(i), updator);
		}
		return true;
	}
	
	@Transactional
	public boolean updateCheckItemsStatusFix(List<CheckItemTo> checkItems, UserTo updator) throws Exception{
		checkItemFacade.updateCheckItemStatusFix(checkItems, updator);
		for(int i = 0; i < checkItems.size(); i++){
			checkItemLogService.createCheckItemLog(checkItems.get(i), updator);
		}
		return true;
	}
	
	public boolean sendStatusNotification(CheckItemTo checkItem, UserTo from) throws Exception{
		notification.sendNotification(checkItem, from);		
		return true;
	}
	
	@Transactional
	public boolean deleteCheckItem(CheckItemTo checkItem, UserTo updator) throws Exception{
		checkItem.setUpdatedBy(updator.getName());
		checkItem.setUpdatedDate(new Date());
		checkItemDao.update(checkItem);
		return true;
	}
	
	//==============================
	//Injected Variables
	//==============================	
	private CheckItemDao checkItemDao;
	private CheckItemLogService checkItemLogService;
	private CheckItemFacade checkItemFacade;
	
	private NormandyNotification notification;
	

	public void setCheckItemDao(CheckItemDao checkItemDao) {
		this.checkItemDao = checkItemDao;
	}
	
	public void setCheckItemLogService(CheckItemLogService checkItemLogService) {
		this.checkItemLogService = checkItemLogService;
	}	
	
	public void setCheckItemFacade(CheckItemFacade checkItemFacade) {
		this.checkItemFacade = checkItemFacade;
	}
	
	public void setNotification(NormandyNotification notification) {
		this.notification = notification;
	}	
}
