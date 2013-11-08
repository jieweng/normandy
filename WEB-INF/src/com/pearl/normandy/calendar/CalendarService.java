package com.pearl.normandy.calendar;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.pearl.normandy.activity.ActivityService;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.List;

public class CalendarService {
	static Logger log = Logger.getLogger(CalendarService.class.getName());
	
	private CalendarDao calendarDao;
//	private ActivityDao activityDao;
	private ActivityService activityService;
	
	public void setCalendarDao(CalendarDao calendarDao){
		this.calendarDao = calendarDao;
	}
	
	public void setActivityService(ActivityService activityService){
		this.activityService = activityService;
	}
	
	public List<CalendarTo> getAllHolidays() throws Exception{
		return calendarDao.getAllHolidays();
	}
	
	@Transactional
	public CalendarTo createHoliday(CalendarTo calendarTo) throws Exception{
		CalendarTo newCalendarTo = calendarDao.create(calendarTo);
		activityService.updateActualStaffDays(calendarTo.getDate());
		return newCalendarTo;
	}
	
	@Transactional
	public void deleteHoliday(CalendarTo calendarTo) throws Exception{
		calendarDao.delete(calendarTo.getId());
		activityService.updateActualStaffDays(calendarTo.getDate());
	}
	
	@Transactional
	public void updateHoliday(List<CalendarTo> createArr,List<CalendarTo> deleteArr) throws Exception{
		Iterator<CalendarTo> it=createArr.iterator();
		while(it.hasNext()){
			createHoliday(it.next());
		}
		Iterator<CalendarTo> it2=deleteArr.iterator();
		while(it2.hasNext()){
			deleteHoliday(it2.next());
		}
		
	}
}
