package com.pearl.normandy.core.mail;

import java.util.List;

import org.apache.commons.mail.HtmlEmail;
import org.apache.log4j.Logger;

import com.pearl.normandy.user.UserTo;

public class NormandyMail extends NormandyMailBase {	
	static Logger log = Logger.getLogger(NormandyMail.class.getName());	
	
	
	public void sendMail(String to, String toName, String subject, String msg){
		try{
			HtmlEmail email = new HtmlEmail();
			email.addTo(to, toName);
			email.setSubject(subject);
			email.setHtmlMsg(msg);
			super.sendMail(email);
			
		}
		catch(Exception e){
			log.error("Email Exception: ", e);
		}
	}
	
	public void sendMail(List<UserTo> users, String subject, String msg){
		try{
			HtmlEmail email = new HtmlEmail();
			
			for(int i=0; i<users.size(); i++){
				UserTo user = users.get(i);
				email.addTo(user.getEmail(), user.getName());				
			}
			
			email.setSubject(subject);
			email.setHtmlMsg(msg);
			super.sendMail(email);
		}
		catch(Exception e){
			log.error("Email Exception: ", e);
		}
	}	
}
