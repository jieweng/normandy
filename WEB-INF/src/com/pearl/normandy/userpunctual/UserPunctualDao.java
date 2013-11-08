package com.pearl.normandy.userpunctual;

import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class UserPunctualDao extends SqlMapClientDaoSupport {
	static Logger log = Logger.getLogger(UserPunctualDao.class.getName());

	@SuppressWarnings("unchecked")
	public List<UserPunctual> getUserPunctual(Integer year,Integer month) throws DataAccessException {
		HashMap param = new HashMap();
		param.put("year", year);
		param.put("month", month);
		List<UserPunctual> result = null;
		result = this.getSqlMapClientTemplate().queryForList("UserPunctual.select",param);
		return result;
	}		
	
	public UserPunctual create(UserPunctual userPunctual) throws DataAccessException {
		userPunctual.setId((Integer) this.getSqlMapClientTemplate().insert("UserPunctual.create",userPunctual));
		return userPunctual;
	}	
	
	public void update(UserPunctual userPunctual) throws DataAccessException {
		this.getSqlMapClientTemplate().update("UserPunctual.update",userPunctual);
	}
}
