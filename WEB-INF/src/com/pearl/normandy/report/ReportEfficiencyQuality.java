package com.pearl.normandy.report;

import java.io.Serializable;
import java.util.Date;

public class ReportEfficiencyQuality implements Serializable {
	
	private static final long serialVersionUID = 5571115499366412107L;
	
	private int userId;
	private String employeeNo;
	private String userName;
	private int groupId;
	private String groupName;
	private Date startTime;
	private Date endTime;
	private Double actualStaffDays;
	private Double actualStaffDaysF;
	private Double assignedEffort;
	private Double assignedEffortF;
	private int delay;
	private int delayF;
	private int ahead;
	private int aheadF;
	private Double sun;
	private Double sunF;
	private Double luna;
	private Double lunaF;
	private Double star;
	private Double starF;
	private int medal;
	private String activityType;
	
	public int getUserId(){
		return this.userId;
	}
	
	public void setUserId(int userId){
		this.userId = userId;
	}
	
	public String getEmployeeNo(){
		return this.employeeNo;
	}
	
	public void setEmployeeNo(String employeeNo){
		this.employeeNo = employeeNo;
	}
	
	public String getUserName(){
		return this.userName;
	}
	
	public void setUserName(String userName){
		this.userName = userName;
	}
	
	public int getGroupId(){
		return this.groupId;
	}
	
	public void setGroupId(int groupId){
		this.groupId = groupId;
	}
	
	public String getGroupName(){
		return this.groupName;
	}
	
	public void setGroupName(String groupName){
		this.groupName = groupName;
	}
	
	public Date getStartTime(){
		return this.startTime;
	}
	
	public void setStartTime(Date startTime){
		this.startTime = startTime;
	}
	
	public Date getEndTime(){
		return this.endTime;
	}
	
	public void setEndTime(Date endTime){
		this.endTime = endTime;
	}
	
	public Double getActualStaffDays(){
		return this.actualStaffDays;
	}
	
	public void setActualStaffDays(Double actualStaffDays){
		this.actualStaffDays = actualStaffDays;
	}
	
	public Double getActualStaffDaysF(){
		return this.actualStaffDaysF;
	}
	
	public void setActualStaffDaysF(Double actualStaffDaysF){
		this.actualStaffDaysF = actualStaffDaysF;
	}
	
	public Double getAssignedEffort(){
		return this.assignedEffort;
	}
	
	public void setAssignedEffort(Double assignedEffort){
		this.assignedEffort = assignedEffort;
	}
	
	public Double getAssignedEffortF(){
		return this.assignedEffortF;
	}
	
	public void setAssignedEffortF(Double assignedEffortF){
		this.assignedEffortF = assignedEffortF;
	}
	
	public int getDelay(){
		return this.delay;
	}
	
	public void setDelay(int delay){
		this.delay = delay;
	}
	
	public int getDelayF(){
		return this.delayF;
	}
	
	public void setDelayF(int delayF){
		this.delayF = delayF;
	}
	
	public int getAhead(){
		return this.ahead;
	}
	
	public void setAhead(int ahead){
		this.ahead = ahead;
	}
	
	public int getAheadF(){
		return this.aheadF;
	}
	
	public void setAheadF(int aheadF){
		this.aheadF = aheadF;
	}
	
	public Double getSun(){
		return this.sun;
	}
	
	public void setSun(Double sun){
		this.sun = sun;
	}
	
	public Double getSunF(){
		return this.sunF;
	}
	
	public void setSunF(Double sunF){
		this.sunF = sunF;
	}
	
	public Double getLuna(){
		return this.luna;
	}
	
	public void setLuna(Double luna){
		this.luna = luna;
	}
	
	public Double getLunaF(){
		return this.lunaF;
	}
	
	public void setLunaF(Double lunaF){
		this.lunaF = lunaF;
	}
	
	public Double getStar(){
		return this.star;
	}
	
	public void setStar(Double star){
		this.star = star;
	}
	
	public Double getStarF(){
		return this.starF;
	}
	
	public void setStarF(Double starF){
		this.starF = starF;
	}
	
	public int getMedal(){
		return this.medal;
	}
	
	public void setMedal(int medal){
		this.medal = medal;
	}
	
	public String getActivityType(){
		return this.activityType;
	}
	
	public void setActivityType(String activityType){
		this.activityType = activityType;
	}
}
