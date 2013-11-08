package com.pearl.normandy.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarConfig {
	
	public static final int WORKING_TIME_START_HOUR		= 9;
	public static final int WORKING_TIME_START_MINUTE	= 0;
	public static final int WORKING_TIME_END_HOUR		= 18;
	public static final int WORKING_TIME_END_MINUTE		= 0;
	
	static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	static Calendar c = Calendar.getInstance();
	
	public CalendarConfig(){}
	
	public static String getStartTime(){
		Calendar c = Calendar.getInstance();
		Calendar startTime = new GregorianCalendar(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), WORKING_TIME_START_HOUR,WORKING_TIME_START_MINUTE);
		return format.format(startTime.getTime());
	}
	
	public static String getEndTime(){
		Calendar c = Calendar.getInstance();
		Calendar endTime = new GregorianCalendar(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), WORKING_TIME_END_HOUR,WORKING_TIME_END_MINUTE);
		return format.format(endTime.getTime());
	}
}