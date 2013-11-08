package com.pearl.normandy.userContract;

import java.util.List;

import org.apache.log4j.Logger;

public class UserContractService {

	static Logger log = Logger.getLogger(UserContractService.class.getName());
	
	public List<UserContract> getUserContractsByUserId(int userId) throws Exception{
		return userContractDao.getUserContractsByUserId(userId);
	}
	
	public int createUserContract(UserContract uc) throws Exception{
		return userContractDao.createUserContract(uc);
	}
	
	public void updateUserContract(UserContract uc) throws Exception{
		userContractDao.updateUserContract(uc);
	}
	
	public void deleteUserContract(UserContract uc) throws Exception{
		userContractDao.deleteUserContract(uc);
	}
	
	private UserContractDao userContractDao;

	public void setUserContractDao(UserContractDao userContractDao) {
		this.userContractDao = userContractDao;
	}
}
