package com.pearl.normandy.calendar;

import java.util.Date;
import java.util.List;

import com.pearl.normandy.utils.Constants;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class CalendarDao extends SqlMapClientDaoSupport {

	//==============================
	//Get methods
	//==============================
	@SuppressWarnings("unchecked")
	public List<Date> getHolidays() throws DataAccessException{
		List<Date> result = getSqlMapClientTemplate().queryForList("Calendar.selectCalendarByType", Constants.CALENDAR_TYPE_HOLIDAY);
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<Date> getWorkingDays() throws DataAccessException{
		List<Date> result = getSqlMapClientTemplate().queryForList("Calendar.selectCalendarByType", Constants.CALENDAR_TYPE_WORKINGDAY);
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<CalendarTo> getAllHolidays() throws DataAccessException{
		List<CalendarTo> result = getSqlMapClientTemplate().queryForList("Calendar.selectAllHolidays");
		return result;
	}
	
	public CalendarTo create(CalendarTo calendarTo) throws DataAccessException {
		Integer id = (Integer)this.getSqlMapClientTemplate().insert("Calendar.insert", calendarTo);
		calendarTo.setId(id);
		return calendarTo;
	}
	
	public void delete(Integer id) throws DataAccessException {
		this.getSqlMapClientTemplate().delete("Calendar.delete", id);
	}
}