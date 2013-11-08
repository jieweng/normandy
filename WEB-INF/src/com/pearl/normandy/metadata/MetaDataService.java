package com.pearl.normandy.metadata;

import java.util.Date;

import org.apache.log4j.Logger;

import com.adobe.agl.util.Calendar;
import com.pearl.normandy.usergroup.UserGroupDao;
import com.pearl.normandy.calendar.CalendarDao;
import com.pearl.normandy.listofvalue.ListOfValueDao;
import com.pearl.normandy.projectuser.ProjectUserDao;
import com.pearl.normandy.user.UserTo;
import com.pearl.normandy.utils.Constants;
import com.pearl.normandy.utils.NormandyConfiguration;


public class MetaDataService {

	static Logger log = Logger.getLogger(MetaDataService.class.getName());
	
	
	//==============================
	//Get methods
	//==============================	
	public MetaData getMetaData(UserTo user)throws Exception{
		MetaData metaData = new MetaData();

		if(user != null){
			NormandyConfiguration config = NormandyConfiguration.getInstance();
			metaData.setReferenceUrl(config.REFERENCE_URL);
			metaData.setReferenceTempUrl(config.REFERENCE_TEMP_URL);
			metaData.setReportUrl(config.REPORT_URL);
			metaData.setMpxjUrl(config.MPXJ_URL);
			metaData.setThumbnailUrl(config.THUMBNAIL_URL);
			
			metaData.setActivityLockedTime(getDateBefore(new Date(),config.ACTIVITY_LOCKED_TIME));
			
			metaData.setHolidays(calendarDao.getHolidays());
			metaData.setUserGroups(userGroupDao.getUserGroups());
			metaData.setProductionGroup(userGroupDao.getProductionGroup());
			metaData.setProjectUsers(projectUserDao.getDistinctProjectUserRole(user.getId()));
			metaData.setTaskCategories(listOfValueDao.getListOfValueByType(Constants.LOV_TYPE_TASK_CATEGORY));
			metaData.setPriorities(listOfValueDao.getListOfValueByType(Constants.LOV_TYPE_PRIORITY));
			metaData.setSeverities(listOfValueDao.getListOfValueByType(Constants.LOV_TYPE_SEVERITY));
			metaData.setTaskStatuses(listOfValueDao.getListOfValueByType(Constants.LOV_TYPE_SUBTASK_STATUS));
			metaData.setRootCauses(listOfValueDao.getListOfValueByType(Constants.LOV_TYPE_ROOT_CAUSE));
			metaData.setModifyStatues(listOfValueDao.getListOfValueByType(Constants.LOV_TYPE_MODIFY_STATUS));				
		}
		
		return metaData;
	}
	
	public static Date getDateBefore(Date d,int day){
		Calendar now=Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE,now.get(Calendar.DATE)-day);
		now.set(Calendar.HOUR_OF_DAY, 9);
		now.set(Calendar.MINUTE, 0);
		now.set(Calendar.SECOND, 0);
		now.set(Calendar.MILLISECOND, 0);
		return now.getTime();
	}

	//==============================
	//Injected Variables
	//==============================
	private CalendarDao			calendarDao;
	private UserGroupDao 		userGroupDao;	
	private ListOfValueDao 		listOfValueDao;
	private ProjectUserDao		projectUserDao;

	public void setCalendarDao(CalendarDao calendarDao) {
		this.calendarDao = calendarDao;
	}	
	
	public void setUserGroupDao(UserGroupDao userGroupDao){		
		this.userGroupDao = userGroupDao;
	}
	
	public void setListOfValueDao(ListOfValueDao listOfValueDao) {
		this.listOfValueDao = listOfValueDao;
	}		
	
	public void setProjectUserDao(ProjectUserDao projectUserDao){
		this.projectUserDao = projectUserDao;
	}	
}