package com.pearl.normandy.statuslog;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class StatusLogDao extends SqlMapClientDaoSupport{
	
	static Logger log = Logger.getLogger(StatusLog.class.getName());

	
	@SuppressWarnings("unchecked")
	public List<StatusLogTo> getStatusLog(StatusLogTo statusLogTo) throws DataAccessException{
		return this.getSqlMapClientTemplate().queryForList("StatusLog.selectStatusLog", statusLogTo);
	}
	//==============================
	//Create, Update, Delete
	//==============================
	
	public StatusLogTo create(StatusLogTo statusLogTo) throws DataAccessException{		
		Integer id = (Integer)this.getSqlMapClientTemplate().insert("StatusLog.insert", statusLogTo);
		statusLogTo.setId(id);
		return statusLogTo;
	}
}
