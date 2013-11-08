package com.pearl.normandy.projectmemberability;

import java.io.Serializable;
import java.util.Date;

public class Performance implements Serializable {
	private static final long serialVersionUID = 5571115499366412107L;
	private int userId;
	private String userName;
	private int groupId;
	private String groupName;
	private int projectId;
	private Date startTime;
	private Date endTime;
	private Double actualStaffDays;
	private Double assignedEffort;
	private Double sun;
	private Double luna;
	private Double star;
	private Double starlight;
	private Double stardust;
	private int medal;
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
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Double getActualStaffDays() {
		return actualStaffDays;
	}
	public void setActualStaffDays(Double actualStaffDays) {
		this.actualStaffDays = actualStaffDays;
	}
	public Double getAssignedEffort() {
		return assignedEffort;
	}
	public void setAssignedEffort(Double assignedEffort) {
		this.assignedEffort = assignedEffort;
	}
	public Double getSun() {
		return sun;
	}
	public void setSun(Double sun) {
		this.sun = sun;
	}
	public Double getLuna() {
		return luna;
	}
	public void setLuna(Double luna) {
		this.luna = luna;
	}
	public Double getStar() {
		return star;
	}
	public void setStar(Double star) {
		this.star = star;
	}
	public Double getStarlight() {
		return starlight;
	}
	public void setStarlight(Double starlight) {
		this.starlight = starlight;
	}
	public Double getStardust() {
		return stardust;
	}
	public void setStardust(Double stardust) {
		this.stardust = stardust;
	}
	public int getMedal() {
		return medal;
	}
	public void setMedal(int medal) {
		this.medal = medal;
	}
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	
}
