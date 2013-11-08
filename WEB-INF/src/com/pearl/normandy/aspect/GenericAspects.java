package com.pearl.normandy.aspect;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.commons.lang.exception.ExceptionUtils;

import com.pearl.normandy.checkitem.CheckItemTo;
import com.pearl.normandy.core.mail.NormandyMail;
import com.pearl.normandy.core.mail.NormandyNotification;
import com.pearl.normandy.user.UserTo;
import com.pearl.normandy.utils.NormandyConfiguration;

import flex.messaging.MessageException;


public class GenericAspects {
	static Logger log = Logger.getLogger(GenericAspects.class.getName());
	private NormandyConfiguration config = NormandyConfiguration.getInstance();
	private NormandyMail mail = new NormandyMail();
	
	public void exceptionLogging(Exception ex) {		
		if(!(ex instanceof MessageException)){
			log.error("Exception: ", ex);			
			mail.sendMail(config.ERROR_MAIL_TO_ADDR, config.ERROR_MAIL_TO_NAME, "Normandy Exception", ExceptionUtils.getFullStackTrace(ex));
		}		
	}	 
	
	public void updateCheckItemStatusNotification(CheckItemTo checkItem, UserTo updator) throws Exception {
		notification.sendNotification(checkItem, updator);
	}
	
	public void updateCheckItemFixNotification(List<CheckItemTo> checkItems, UserTo updator) throws Exception{
		notification.sendNotification(checkItems, updator);
	}
	  
	// ==============================
	// Injected Variables
	// ==============================
	private NormandyNotification notification;

	/**
	 * @param notificatiion the notification to set
	 */
	public void setNotification(NormandyNotification notification) {
		this.notification = notification;
	}
}
