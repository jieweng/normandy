package com.pearl.normandy.userPrivilege;

import java.util.List;

import org.apache.log4j.Logger;

public class UserPrivilegeService {

	static Logger log = Logger.getLogger(UserPrivilegeService.class.getName());
	
	public UserPrivilege getUserPrivilegeByUserId(int userId) throws Exception{
		return userPrivilegeDao.getUserPrivilegesByUserId(userId);
	}
	
	private UserPrivilegeDao userPrivilegeDao;

	public UserPrivilegeDao getUserPrivilegeDao() {
		return userPrivilegeDao;
	}

	public void setUserPrivilegeDao(UserPrivilegeDao userPrivilegeDao) {
		this.userPrivilegeDao = userPrivilegeDao;
	}
	
	public int createUserPrivilege(UserPrivilege us) throws Exception{			
		return userPrivilegeDao.createUserPrivilege(us);
	}
	
	public void updateUserPrivilege(UserPrivilege us) throws Exception{			
		userPrivilegeDao.updateUserPrivilege(us);
	}
	
	public void deleteUserPrivilege(UserPrivilege us) throws Exception{
		userPrivilegeDao.deleteUserPrivilege(us);
	}
}
