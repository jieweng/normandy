package com.pearl.normandy.timesheet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class TimesheetDao extends SqlMapClientDaoSupport{

	//==============================
	//Get methods
	//==============================	
	@SuppressWarnings("unchecked")
	public List<TimesheetTo> getTimeSheet(int userId,String activityId) throws DataAccessException{
		Map<String,String> map = new HashMap<String, String>();
		map.put("userId", String.valueOf(userId));
		map.put("activityId", activityId);
		return this.getSqlMapClientTemplate().queryForList("Timesheet.selectTimeSheet",map);
	}
	
	//==============================
	//Create, Update, Delete
	//==============================
	public int insert(TimesheetTo timeSheet) throws DataAccessException{
		Integer id = (Integer) this.getSqlMapClientTemplate().insert("Timesheet.insert",timeSheet);
		return id;
	}
	
	public void update(TimesheetTo timeSheet)throws DataAccessException{
		this.getSqlMapClientTemplate().update("Timesheet.update",timeSheet);
	}
	
	public boolean delete(Integer id) throws DataAccessException{
		this.getSqlMapClientTemplate().delete("Timesheet.delete", id);
		return true;
	}
}
