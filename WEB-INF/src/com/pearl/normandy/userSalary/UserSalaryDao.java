package com.pearl.normandy.userSalary;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class UserSalaryDao extends SqlMapClientDaoSupport {

	static Logger log = Logger.getLogger(UserSalaryDao.class.getName());
	
	@SuppressWarnings("unchecked")
	public List<UserSalary> getUserSalarysByUserId(int userId) throws DataAccessException{
		return getSqlMapClientTemplate().queryForList("UserSalary.selectUserSalaryByUserId", userId);
	}
	
	public Integer createUserSalary(UserSalary us) throws DataAccessException{
		return (Integer)getSqlMapClientTemplate().insert("UserSalary.create", us);
	}
	
	public void updateUserSalary(UserSalary us) throws DataAccessException{
		getSqlMapClientTemplate().update("UserSalary.update", us);
	}
	
	public void deleteUserSalary(UserSalary us) throws DataAccessException{
		getSqlMapClientTemplate().delete("UserSalary.delete", us);
	}
}
