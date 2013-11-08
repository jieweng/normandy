package com.pearl.normandy.checkitem;

import java.util.Date;
import java.util.List;

import com.pearl.normandy.enumerator.CheckItemEnum;
import com.pearl.normandy.user.UserTo;

public class CheckItemFacade {

	public CheckItemTo createCheckItem(CheckItemTo checkItem, UserTo updator) throws Exception{
		return checkItemDao.create(checkItem);
	}
	
	public CheckItemTo updateCheckItemStatusNext(CheckItemTo checkItem, UserTo updator) throws Exception{
		checkItem.setStatus(CheckItemEnum.getNextStatus(checkItem.getStatus()));
		checkItem.setUpdatedDate(new Date());
		checkItem.setUpdatedBy(updator.getUserName());
		checkItemDao.update(checkItem);
		return checkItem;
	}
	
	public CheckItemTo updateCheckItemStatusFix(CheckItemTo checkItem, UserTo updator) throws Exception{
		checkItem.setStatus(CheckItemEnum.getFixStatus());
		checkItem.setUpdatedDate(new Date());
		checkItem.setUpdatedBy(updator.getUserName());
		checkItemDao.update(checkItem);
		return checkItem;
	}
	
	public void updateCheckItemStatusNext(List<CheckItemTo> checkItems, UserTo updator) throws Exception{
		for(int i = 0; i < checkItems.size(); i++){
			CheckItemTo checkItem = checkItems.get(i);
			checkItem.setStatus(CheckItemEnum.getNextStatus(checkItem.getStatus()));
			checkItem.setUpdatedDate(new Date());
			checkItem.setUpdatedBy(updator.getUserName());
			checkItemDao.update(checkItem);
		}
	}
	
	public void updateCheckItemStatusFix(List<CheckItemTo> checkItems, UserTo updator) throws Exception{
		for(int i = 0; i < checkItems.size(); i++){
			CheckItemTo checkItem = checkItems.get(i);
			checkItem.setStatus(CheckItemEnum.getFixStatus());
			checkItem.setUpdatedDate(new Date());
			checkItem.setUpdatedBy(updator.getUserName());
			checkItemDao.update(checkItem);
		}
	}
	
	//==============================
	//Injected Variables
	//==============================	
	private CheckItemDao checkItemDao;

	public void setCheckItemDao(CheckItemDao checkItemDao) {
		this.checkItemDao = checkItemDao;
	}
}
