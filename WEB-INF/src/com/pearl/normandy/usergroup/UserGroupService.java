package com.pearl.normandy.usergroup;

import java.util.List;

import org.apache.log4j.Logger;

public class UserGroupService {
	static Logger log = Logger.getLogger(UserGroupService.class.getName());
	
	//==============================
	//Get methods
	//==============================
	public List<UserGroupTo> getUserGroups()throws Exception{
		List<UserGroupTo> result = null;
		try{
			result = userGroupDao.getUserGroups();
		}catch (Exception e) {
			log.error("Error in getUserGroups",e);
			throw e;
		}
		return result;
	}
	
	public List<UserGroupTo> getUserGroups(Integer projectId,Integer groupId)throws Exception{
		List<UserGroupTo> result = null;
		try{
			result = userGroupDao.getUserGroups(projectId,groupId);
		}catch (Exception e) {
			log.error("Error in getUserGroupsByProjectId",e);
			throw e;
		}
		return result;
	}
	
	
	//==============================
	//Injected Variables
	//==============================	
	private UserGroupDao userGroupDao;
	
	public void setUserGroupDao(UserGroupDao userGroupDao){
		
		this.userGroupDao = userGroupDao;
	}
	
	public List<UserGroupTo> getUserGroupAs()throws Exception{
		List<UserGroupTo> result = null;
		try{
			result = userGroupDao.getUserGroupAs();
		}catch (Exception e) {
			log.error("Error in getUserGroupsByProjectId",e);
			throw e;
		}
		return result;
	}
	
	public UserGroupTo createUserGroup(UserGroupTo group)throws Exception{
		UserGroupTo result = null;
		try{
			result = userGroupDao.createUserGroup(group);
		}catch (Exception e) {
			log.error("Error in createUserGroup",e);
			throw e;
		}
		return result;
	}
	
	public void updateUserGroup(UserGroupTo group) throws Exception{
		try{
			userGroupDao.updateUserGroup(group);
		}catch (Exception e) {
			log.error("Error in updateUserGroup",e);
			throw e;
		}
	}
}
