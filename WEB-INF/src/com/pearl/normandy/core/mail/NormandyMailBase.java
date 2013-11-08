package com.pearl.normandy.core.mail;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.Email;
import org.apache.log4j.Logger;

import com.pearl.normandy.utils.NormandyConfiguration;

public class NormandyMailBase {
	
	static Logger log = Logger.getLogger(NormandyMailBase.class.getName());	
	
	protected void sendMail(Email email){
		try{
			NormandyConfiguration config = NormandyConfiguration.getInstance();
			email.setHostName(config.MAIL_SMTP);
			email.setSmtpPort(config.MAIL_PORT);
			email.setAuthentication(config.MAIL_USERNAME, config.MAIL_PASSWORD);
			email.setFrom(config.MAIL_FROM_ADDR, config.MAIL_FROM_NAME);
			email.setCharset("UTF-8");
			email.send();
		}
		catch(EmailException e){
			log.error("Email Exception: ", e);
		}
	}
}
