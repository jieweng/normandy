package com.pearl.normandy.utils;


import java.util.List;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.ConfigurationFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.pearl.normandy.sysConfig.SysConfigDao;

public class NormandyConfiguration implements ServletContextListener {
	static Logger log = Logger.getLogger(NormandyConfiguration.class.getName());
	
	private static NormandyConfiguration normandyConfig;
	private ConfigurationFactory factory;
	private Configuration config;	
	
	//==============================
	//Configuration Properties
	//==============================
	public String ENCODING;
	
	public String MAIL_SMTP;
	public int 	  MAIL_PORT;
	public String MAIL_USERNAME;
	public String MAIL_PASSWORD;
	public String MAIL_FROM_ADDR;
	public String MAIL_FROM_NAME;
	
	public String ERROR_MAIL_TO_ADDR;
	public String ERROR_MAIL_TO_NAME;

	public String APPLICATION_FOLDER;
	public String REFERENCE_FOLDER;
	public String REFERENCE_TEMP_FOLDER;	
	public String REPORT_FOLDER;
	public String TEMPLATE_JXLS_FOLDER;
	public String TEMPLATE_MPXJ_FOLDER;
	public String THUMBNAIL_FOLDER;
	
	public String APPLICATION_URL;
	public String REFERENCE_URL;
	public String REFERENCE_TEMP_URL;
	public String REPORT_URL;
	public String MPXJ_URL;
	public String THUMBNAIL_URL;
	
	public int ACTIVITY_LOCKED_TIME;
	
	private SysConfigDao sysConfigDao;
	private Map<String, String> sysConfig;
	
	//==============================
	//Methods
	//==============================		
	
	public SysConfigDao getSysConfigDao() {
		return sysConfigDao;
	}

	public void setSysConfigDao(SysConfigDao sysConfigDao) {
		this.sysConfigDao = sysConfigDao;
	}
	
	public static NormandyConfiguration  getInstance(){
		if(normandyConfig==null){
			normandyConfig = new NormandyConfiguration();
		}
		
		return normandyConfig;
	}
	
	@SuppressWarnings("unchecked")
	public static List getNotificationList(String type){
		return NormandyConfiguration.getInstance().config.getList("notifications."+type+".role.name");
	}
	
	public static String getNotificationManagerFlag(String type){
		return NormandyConfiguration.getInstance().config.getString("notifications."+type+".manager");
	}	
	
	public static String getNotificationTitle(String type){
		return NormandyConfiguration.getInstance().config.getString("notifications."+type+".title");
	}
	
	public static String getNotificationTemplate(String type){
		return NormandyConfiguration.getInstance().config.getString("notifications."+type+".template");
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		BeanFactory context = WebApplicationContextUtils.getWebApplicationContext(arg0.getServletContext());
		sysConfigDao = (SysConfigDao)context.getBean("sysConfigDao");
		
		normandyConfig =  NormandyConfiguration.getInstance();
		
		try{
			factory = new ConfigurationFactory("propertyConfig.xml");
			config = factory.getConfiguration();
			normandyConfig.config = factory.getConfiguration();

			sysConfig = sysConfigDao.getSysConfig();
			
			normandyConfig.sysConfigDao = sysConfigDao;
			normandyConfig.sysConfig = sysConfig;
			
			//Initialize mail server properties
			normandyConfig.ENCODING				= config.getString("encoding");
			
			normandyConfig.MAIL_SMTP 			= sysConfig.get("mail.smtp");
			normandyConfig.MAIL_PORT 			= Integer.valueOf(sysConfig.get("mail.port"));
			normandyConfig.MAIL_USERNAME  		= sysConfig.get("mail.username");
			normandyConfig.MAIL_PASSWORD  		= sysConfig.get("mail.password");
			normandyConfig.MAIL_FROM_ADDR 		= sysConfig.get("mail.from.addr");
			normandyConfig.MAIL_FROM_NAME 		= sysConfig.get("mail.from.name");	
			
			normandyConfig.ERROR_MAIL_TO_ADDR	= sysConfig.get("error.mail.to.addr");
			normandyConfig.ERROR_MAIL_TO_NAME	= sysConfig.get("error.mail.to.name");
			
			normandyConfig.APPLICATION_FOLDER	= config.getString("application.folder");
			normandyConfig.REFERENCE_FOLDER		= config.getString("reference.folder");
			normandyConfig.REFERENCE_TEMP_FOLDER=config.getString("reference.temp.folder");		
			normandyConfig.REPORT_FOLDER		= config.getString("report.folder");
			normandyConfig.TEMPLATE_JXLS_FOLDER	= config.getString("template.jxls.folder");
			normandyConfig.TEMPLATE_MPXJ_FOLDER	= config.getString("template.mpxj.folder");
			normandyConfig.THUMBNAIL_FOLDER    	= config.getString("thumbnail.folder");
			
			normandyConfig.APPLICATION_URL		= config.getString("application.url");	
			
			if(config.getString("reference.url")!=null){
				normandyConfig.REFERENCE_URL  	= normandyConfig.APPLICATION_URL + config.getString("reference.url");
			}else{
				normandyConfig.REFERENCE_URL  	= normandyConfig.APPLICATION_URL + config.getString("reference.folder");
			}
			
			if(config.getString("reference.temp.url")!=null){
				normandyConfig.REFERENCE_TEMP_URL	= normandyConfig.APPLICATION_URL + config.getString("reference.temp.url");
			}else{
				normandyConfig.REFERENCE_TEMP_URL	= normandyConfig.APPLICATION_URL + config.getString("reference.temp.folder");
			}		
			
			if(config.getString("report.url")!=null){
				normandyConfig.REPORT_URL			= normandyConfig.APPLICATION_URL + config.getString("report.url");
			}else{
				normandyConfig.REPORT_URL			= normandyConfig.APPLICATION_URL + config.getString("report.folder");
			}
			
			if(config.getString("mpxj.url")!=null){
				normandyConfig.MPXJ_URL			= normandyConfig.APPLICATION_URL + config.getString("mpxj.url");
			}else{
				normandyConfig.MPXJ_URL			= normandyConfig.APPLICATION_URL + config.getString("template.mpxj.folder");
			}
			
			if(config.getString("thumbnail.url")!=null){
				normandyConfig.THUMBNAIL_URL       = normandyConfig.APPLICATION_URL + config.getString("thumbnail.url");
			}else{
				normandyConfig.THUMBNAIL_URL       = normandyConfig.APPLICATION_URL + config.getString("thumbnail.folder");
			}
			normandyConfig.ACTIVITY_LOCKED_TIME    = Integer.valueOf(sysConfig.get("activity.locked.time"));
		}catch(ConfigurationException e){
			log.error("Can not initialize configuration.\n" +
					"Please check propertyConfig.xml under WEB-INF, and make sure configuration files are in proper place", e);
		}
	}		
}
