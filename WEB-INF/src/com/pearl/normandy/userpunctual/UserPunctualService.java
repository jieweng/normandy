package com.pearl.normandy.userpunctual;

import java.util.List;

import org.apache.log4j.Logger;

public class UserPunctualService {

	static Logger log = Logger.getLogger(UserPunctualService.class.getName());
	
	
	
	public List<UserPunctual> getUserPunctual(Integer year,Integer month) throws Exception {
		return userPunctualDao.getUserPunctual(year, month);
	}
	
	public UserPunctual createUserPunctual(UserPunctual up) throws Exception{
		userPunctualDao.create(up);
		
		return up;
	}
	
	public void updateUserPunctual(UserPunctual up) throws Exception{
		userPunctualDao.update(up);
	}
	
	private UserPunctualDao userPunctualDao;

	public UserPunctualDao getUserPunctualDao() {
		return userPunctualDao;
	}

	public void setUserPunctualDao(UserPunctualDao userPunctualDao) {
		this.userPunctualDao = userPunctualDao;
	}
}