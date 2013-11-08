package com.pearl.normandy.userContract;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class UserContractDao extends SqlMapClientDaoSupport {

	static Logger log = Logger.getLogger(UserContractDao.class.getName());
	
	@SuppressWarnings("unchecked")
	public List<UserContract> getUserContractsByUserId(int userId) throws DataAccessException{
		return getSqlMapClientTemplate().queryForList("UserContract.selectUserContractByUserId", userId);
	}
	
	public Integer createUserContract(UserContract uc) throws DataAccessException{
		return (Integer)getSqlMapClientTemplate().insert("UserContract.create", uc);
	}
	
	public void updateUserContract(UserContract uc) throws DataAccessException{
		getSqlMapClientTemplate().update("UserContract.update", uc);
	}
	
	public void deleteUserContract(UserContract uc) throws DataAccessException{
		getSqlMapClientTemplate().delete("UserContract.delete", uc);
	}
}
