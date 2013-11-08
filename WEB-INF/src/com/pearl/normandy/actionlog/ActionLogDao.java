package com.pearl.normandy.actionlog;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class ActionLogDao extends SqlMapClientDaoSupport{
	
	static Logger log = Logger.getLogger(ActionLogDao.class.getName());

	
	//==============================
	//Get methods
	//==============================	

	
	//==============================
	//Create, Update, Delete
	//==============================
	public ActionLogTo create(ActionLogTo actionLogTo) throws DataAccessException {
		Integer id = (Integer) this.getSqlMapClientTemplate().insert("ActionLog.insert", actionLogTo);
		actionLogTo.setId(id);
		return actionLogTo;
	}
}
