package com.pearl.normandy.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CalendarUtil {
	
	private static final int MILLISECOND_DAY 	= 24*60*60*1000;
	private static final int MILLISECOND_MINUTE	= 60*1000;
	private static final int MILLISECOND_HOUR	= 60*60*1000;
	private static final int WORK_MINUTES_DAY 	= 8*60;
	private static final int WORK_START_HOUR	= 9;
	private static final int WORK_START_MINUTE	= 0;
	private static final int WORK_END_HOUR		= 18; 
	private static final int WORK_END_MINUTE	= 0;	
	
	private List<Date> holidays;
	private static CalendarUtil calendarUtil;
	
	public void setHolidays(List<Date> holidays){
		this.holidays = holidays;
	}
	
	public static CalendarUtil getInstance(){
		if(calendarUtil == null){
			return new CalendarUtil();
		}else{
			return calendarUtil;
		}		
	}
	
	/*得到从最晚开始时间到最早结束时间的工作天数，精确到分钟。结果四舍五入，保留3小数*/
	public double getWorkDays(Date s, Date e, Date f, Date t){
		Date start, end;
		
		/*取最晚的开始时间和最早的结束时间*/
		if(s.compareTo(f) >= 0){
			start = s;
		}else{
			start = f;
		}
		
		if(e.compareTo(t) >= 0){
			end	= t;
		}else{
			end = e;
		}
		
		if(start.compareTo(end) >= 0){
			return 0;
		}else{
			return Math.floor(getWorkDays(start, end) * 1000 + 0.5) / 1000;
		}		
	}
	
/*	得到两个日期之间的工作天数，从0时0分0秒开始，精确到分钟。*/
	public  double getWorkDays(Date s, Date e){
		double workMinute = 0;
		Calendar start = dateToCalendar(s); 
		Calendar end   = dateToCalendar(e);
				
		if (isSameDay(start, end)){
			if (isWorkDay(start)){
				workMinute = getWorkMinutesInDay(start, end);
			}			
		}else{			
			Calendar sTime = (Calendar)start.clone();
			Calendar eTime = (Calendar)start.clone();
			setWorkEndTime(eTime);
			
			if (isWorkDay(start)){
				workMinute = getWorkMinutesInDay(sTime, eTime);
			}
			
			Calendar temp = getNextDay(sTime);
			setWorkStartTime(temp);
			
			while(temp.before(end)){
				if (isWorkDay(temp)){
					if (isSameDay(temp, end)){
						sTime = temp;
						eTime = end;
					}else{
						sTime = temp;
						eTime = (Calendar)temp.clone();
						setWorkEndTime(eTime);
					}
					workMinute += getWorkMinutesInDay(sTime, eTime);
				}
				temp = getNextDay(temp);
			}
		}
		
		return workMinute / WORK_MINUTES_DAY;
	}
	
	/*根据给定的Calendar得到下一天的Calendar*/
	public Calendar getNextDay(Calendar c){
		Calendar nextDay = Calendar.getInstance();
		nextDay.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), 0, 0, 0);		
		nextDay.setTimeInMillis(c.getTimeInMillis() + MILLISECOND_DAY);		
		return nextDay;
	}
	
	/*得到一天的工作分钟数*/
	public int getWorkMinutesInDay(Calendar s, Calendar e){
		int result = 0;
		Calendar start = (Calendar)s.clone();
		Calendar end   = (Calendar)e.clone();
		int sHour	= start.get(Calendar.HOUR_OF_DAY);
		int eHour	= end.get(Calendar.HOUR_OF_DAY);

		if (sHour < WORK_START_HOUR)
			start.set(s.get(Calendar.YEAR), s.get(Calendar.MONTH), s.get(Calendar.DATE), WORK_START_HOUR, WORK_START_MINUTE, 0);

		if (eHour >= WORK_END_HOUR)
			end.set(e.get(Calendar.YEAR), e.get(Calendar.MONTH), e.get(Calendar.DATE), WORK_END_HOUR, WORK_END_MINUTE, 0);
		
		if (sHour == 12){
			start.set(s.get(Calendar.YEAR), s.get(Calendar.MONTH), s.get(Calendar.DATE), 13, 0, 0);
			result = (int)(end.getTimeInMillis() - start.getTimeInMillis()) / MILLISECOND_MINUTE;
		}			
		
		if (eHour == 12){
			end.set(e.get(Calendar.YEAR), e.get(Calendar.MONTH), e.get(Calendar.DATE), 12, 0, 0);
			result = (int)(end.getTimeInMillis() - start.getTimeInMillis()) / MILLISECOND_MINUTE;
		}
		
		if (sHour < 12 && eHour >12){
			result = (int)(end.getTimeInMillis() - start.getTimeInMillis() - MILLISECOND_HOUR) / MILLISECOND_MINUTE;
		}
		
		if (sHour < 12 && eHour < 12){
			result = (int)(end.getTimeInMillis() - start.getTimeInMillis()) / MILLISECOND_MINUTE;
		}
		
		if (sHour > 12 && eHour > 12){
			result = (int)(end.getTimeInMillis() - start.getTimeInMillis()) / MILLISECOND_MINUTE;
		}
		
		if (result < 0)
			result = 0;
		
		return result;
	}
	
	/*两个日期是否在同一天*/
	public boolean isSameDay(Calendar a, Calendar b){
		Calendar c1 = (Calendar)a.clone();
		c1.set(c1.get(Calendar.YEAR), c1.get(Calendar.MONTH), c1.get(Calendar.DATE), 0, 0, 0);
		Calendar c2 = (Calendar)b.clone();
		c2.set(c2.get(Calendar.YEAR), c2.get(Calendar.MONTH), c2.get(Calendar.DATE), 0, 0, 0);
		if (c1.equals(c2)){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean isWeekend(Calendar c){
		if (c.get(Calendar.DAY_OF_WEEK) == 1 || c.get(Calendar.DAY_OF_WEEK) == 7){
			return true;
		}else{
			return false;
		}						
	}
	
	public boolean isHoliday(Calendar c){		
		Calendar cal = (Calendar)c.clone();
		cal.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), 0, 0, 0);
		boolean result = false;
		if (holidays.size() > 0){
			for (int i = 0; i < holidays.size(); i++){
				Date date = holidays.get(i);
				Calendar calendar = dateToCalendar(date);
				if (cal.compareTo(calendar) == 0){				
					result = true;
					break;
				}
			}
		}		
		return result;
	}
	
	public boolean isWorkDay(Calendar c){
		if (isWeekend(c) || isHoliday(c)){
			return false;
		}else{
			return true;
		}
	}
	
	public Calendar dateToCalendar(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}
	
	public void setWorkStartTime(Calendar c){
		c.set(Calendar.HOUR_OF_DAY, WORK_START_HOUR);
		c.set(Calendar.MINUTE, WORK_START_MINUTE);
		c.set(Calendar.SECOND, 0);
	}
	
	public void setWorkEndTime(Calendar c){
		c.set(Calendar.HOUR_OF_DAY, WORK_END_HOUR);
		c.set(Calendar.MINUTE, WORK_END_MINUTE);
		c.set(Calendar.SECOND, 0);
	}
	
	public String getQuarter(int month){
		String quarter = "";
		if(month == 1  || month == 2  || month == 3)  quarter = "Q" + 1;
		if(month == 4  || month == 5  || month == 6)  quarter = "Q" + 2;
		if(month == 7  || month == 8  || month == 9)  quarter = "Q" + 3;
		if(month == 10 || month == 11 || month == 12) quarter = "Q" + 4;
		
		return quarter;
	}
	
	@SuppressWarnings("deprecation")
	public Date getFirstDayOfMonth(int year, int month){
		return new Date(year - 1900, month-1, 1, WORK_START_HOUR, WORK_START_MINUTE);
	}
	
	@SuppressWarnings("deprecation")
	public Date getLastDayOfMonth(int year, int month){
		if(month == 12){
			year++;
			month = 0;
		}
		Date d = new Date(year - 1900, month, 1, WORK_END_HOUR, WORK_END_MINUTE);
		return new Date(d.getTime() - MILLISECOND_DAY);
	}
}