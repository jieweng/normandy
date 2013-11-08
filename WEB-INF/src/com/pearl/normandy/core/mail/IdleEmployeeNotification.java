package com.pearl.normandy.core.mail;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.pearl.normandy.activity.ActivityDao;
import com.pearl.normandy.calendar.CalendarDao;
import com.pearl.normandy.core.velocity.DefaultVelocityManager;
import com.pearl.normandy.user.UserDao;
import com.pearl.normandy.user.UserTo;
import com.pearl.normandy.utils.CalendarUtil;
import com.pearl.normandy.utils.NormandyConfiguration;

public class IdleEmployeeNotification extends TimerTask implements ServletContextListener{
	
	private static final int START_HOUR = 9;
	private static final long MILLISECOND_DAY = 24*60*60*1000;
	private static final long MILLISECOND_HOUR = 60*60*1000;
	
	private ActivityDao activityDao;
	private CalendarDao calendarDao;
	private UserDao userDao;
	
	public void contextInitialized(ServletContextEvent arg0){
		BeanFactory context = WebApplicationContextUtils.getWebApplicationContext(arg0.getServletContext());
		
		IdleEmployeeNotification notification = new IdleEmployeeNotification();
		notification.setActivityDao((ActivityDao)context.getBean("activityDao"));
		notification.setCalendarDao((CalendarDao)context.getBean("calendarDao"));
		notification.setUserDao((UserDao)context.getBean("userDao"));
		
		Calendar now = new GregorianCalendar();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH);
		int dayOfMonth = now.get(Calendar.DAY_OF_MONTH);
		
		Calendar next = Calendar.getInstance();
		if(now.get(Calendar.HOUR_OF_DAY) < START_HOUR){
			next.set(year, month, dayOfMonth, START_HOUR, 0, 0);
		}else{
			next.set(year, month, dayOfMonth, 0, 0, 0);
			next.setTimeInMillis(next.getTimeInMillis() + MILLISECOND_DAY);
			next.set(Calendar.HOUR_OF_DAY, START_HOUR);
		}
		long delay = next.getTimeInMillis() - now.getTimeInMillis();
		Timer timer = new Timer();
		timer.schedule(notification, delay, MILLISECOND_DAY);
	}
	
	public void contextDestroyed(ServletContextEvent arg0){
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void run() {
		Calendar now = new GregorianCalendar();
		now.set(Calendar.HOUR_OF_DAY, 0);
		now.set(Calendar.MINUTE, 0);
		now.set(Calendar.SECOND, 0);
		now.set(Calendar.MILLISECOND, 0);
		
		if(now.get(Calendar.DAY_OF_WEEK) != 1 && now.get(Calendar.DAY_OF_WEEK) != 7){
			List<Date> holidays = calendarDao.getHolidays();
			boolean isHoliday = false;
			for(Date d : holidays){
				if(now.getTimeInMillis() == d.getTime()){
					isHoliday = true;
					break;
				}
			}
			
			if(!isHoliday){
				Date start = new Date(now.getTimeInMillis() - MILLISECOND_DAY*7 + MILLISECOND_HOUR*9);
				Date end = new Date(now.getTimeInMillis() - MILLISECOND_DAY + MILLISECOND_HOUR*18);
				CalendarUtil cu = CalendarUtil.getInstance();
				cu.setHolidays(holidays);
				double workdays = cu.getWorkDays(start, end);
				List<IdleEmployee> list = activityDao.getIdleEmployee(start, end, workdays);
				List<UserTo> users = userDao.getRecipients();
				
				DefaultVelocityManager vm = new DefaultVelocityManager();
				HashMap ctxParam = new HashMap();
				String startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(start);
				String endTime 	 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(end);
				ctxParam.put("startTime", startTime);
				ctxParam.put("endTime", endTime);
				ctxParam.put("workdays", workdays);
				ctxParam.put("list", list);
				
				NormandyMail mail = new NormandyMail(); 
				mail.sendMail(users, NormandyConfiguration.getNotificationTitle("IDLE_EMPLOYEE"), 
						vm.getEncodedBody(NormandyConfiguration.getNotificationTemplate("IDLE_EMPLOYEE"), 
								NormandyConfiguration.getInstance().ENCODING, ctxParam));
			}
		}
	}

	protected ActivityDao getActivityDao() {
		return activityDao;
	}

	protected void setActivityDao(ActivityDao activityDao) {
		this.activityDao = activityDao;
	}

	protected CalendarDao getCalendarDao() {
		return calendarDao;
	}

	protected void setCalendarDao(CalendarDao calendarDao) {
		this.calendarDao = calendarDao;
	}

	protected UserDao getUserDao() {
		return userDao;
	}

	protected void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
}
