package com.pearl.normandy.userPrivilege;

import java.io.Serializable;

public class UserPrivilege implements Serializable{

	private static final long serialVersionUID = 334002359107512157L;

	private int id;
	private int userId;
	private String project;
	private String resource;
	private String report;
	private String user;
	private String userGroup;
	private String holiday;
	private String userPunctual;
	private String loginPicture;
	private String levelShow;
	public String getLevelShow() {
		return levelShow;
	}
	public void setLevelShow(String levelShow) {
		this.levelShow = levelShow;
	}
	public String getLoginPicture() {
		return loginPicture;
	}
	public void setLoginPicture(String loginPicture) {
		this.loginPicture = loginPicture;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getResource() {
		return resource;
	}
	public void setResource(String resource) {
		this.resource = resource;
	}
	public String getReport() {
		return report;
	}
	public void setReport(String report) {
		this.report = report;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getUserGroup() {
		return userGroup;
	}
	public void setUserGroup(String userGroup) {
		this.userGroup = userGroup;
	}
	public String getHoliday() {
		return holiday;
	}
	public void setHoliday(String holiday) {
		this.holiday = holiday;
	}
	public String getUserPunctual() {
		return userPunctual;
	}
	public void setUserPunctual(String userPunctual) {
		this.userPunctual = userPunctual;
	}

}
