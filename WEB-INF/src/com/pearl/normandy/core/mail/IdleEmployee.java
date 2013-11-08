package com.pearl.normandy.core.mail;

import java.io.Serializable;

public class IdleEmployee implements Serializable {

	private static final long serialVersionUID = -8462585642801232816L;
	
	private int userId;
	private String userName;
	private double workdays;
	private String projects;
	private String recipients;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public double getWorkdays() {
		return workdays;
	}
	public void setWorkdays(double workdays) {
		this.workdays = workdays;
	}
	public String getProjects() {
		return projects;
	}
	public void setProjects(String projects) {
		this.projects = projects;
	}
	public String getRecipients() {
		return recipients;
	}
	public void setRecipients(String recipients) {
		this.recipients = recipients;
	}
}
