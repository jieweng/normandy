package com.pearl.normandy.userPrivilege;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class UserPrivilegeDao extends SqlMapClientDaoSupport {

	static Logger log = Logger.getLogger(UserPrivilegeDao.class.getName());
	
	@SuppressWarnings("unchecked")
	public UserPrivilege getUserPrivilegesByUserId(int userId) throws DataAccessException{
		return (UserPrivilege)getSqlMapClientTemplate().queryForObject("UserPrivilege.selectUserPrivilegeByUserId", userId);
	}
	
	public Integer createUserPrivilege(UserPrivilege us) throws DataAccessException{
		return (Integer)getSqlMapClientTemplate().insert("UserPrivilege.create", us);
	}
	
	public void updateUserPrivilege(UserPrivilege us) throws DataAccessException{
		getSqlMapClientTemplate().update("UserPrivilege.update", us);
	}
	
	public void deleteUserPrivilege(UserPrivilege us) throws DataAccessException{
		getSqlMapClientTemplate().delete("UserPrivilege.delete", us);
	}
}
