package com.pearl.normandy.checkitemlog;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class CheckItemLogDao extends SqlMapClientDaoSupport {
	// ==============================
	// Get methods
	// ==============================
	@SuppressWarnings("unchecked")
	public List<CheckItemLogTo> getCheckItemLogByUserName(String userName)throws DataAccessException{
		List<CheckItemLogTo> result = this.getSqlMapClientTemplate().queryForList("CheckItemLog.selectCheckItemLogByUserName",userName);
		return result;
	}		
	
	// ==============================
	// Create, Update, Delete
	// ==============================
	public CheckItemLogTo create(CheckItemLogTo checkItemLog) throws DataAccessException{
		Integer id = (Integer) this.getSqlMapClientTemplate().insert("CheckItemLog.insert",checkItemLog);
		checkItemLog.setId(id);
		return checkItemLog;
	}
}