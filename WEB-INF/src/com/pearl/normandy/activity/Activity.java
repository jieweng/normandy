package com.pearl.normandy.activity;

import java.util.Date;

public class Activity implements java.io.Serializable {

	private static final long serialVersionUID = -3104034728172703855L;
	
	private Integer id;
	private Integer version;
	private Integer resourceId;
	private Integer taskId;
	private Integer projectId;
	private String 	name;	
	private String activityType;
	private Date startTime;
	private Date endTime;
	private Date actualStartTime;
	private Date actualEndTime;
	private Double actualStaffDays;
	private Double assignedEffort;
	private Double remainingEffort;
	private Integer progress;
	private String trainingFlag = "N";
	private String deleted;
	private String paused;
	private String description;
	private Date createdDate;
	private String createdBy;
	private Date updatedDate;
	private String updatedBy;
	private Integer medal;
	private String proved;
	private Date provedDate;
	private String provedBy;
	private String medalLocked;
	private String medalEdited;
	
	
	public String getMedalEdited() {
		return medalEdited;
	}
	public void setMedalEdited(String medalEdited) {
		this.medalEdited = medalEdited;
	}
	public String getProved() {
		return proved;
	}
	public void setProved(String proved) {
		this.proved = proved;
	}
	public Date getProvedDate() {
		return provedDate;
	}
	public void setProvedDate(Date provedDate) {
		this.provedDate = provedDate;
	}
	public String getProvedBy() {
		return provedBy;
	}
	public void setProvedBy(String provedBy) {
		this.provedBy = provedBy;
	}
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the version
	 */
	public Integer getVersion() {
		return version;
	}
	/**
	 * @param version the version to set
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}
	/**
	 * @return the resourceId
	 */
	public Integer getResourceId() {
		return resourceId;
	}
	/**
	 * @param resourceId the resourceId to set
	 */
	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}
	/**
	 * @return the taskId
	 */
	public Integer getTaskId() {
		return taskId;
	}
	/**
	 * @param taskId the taskId to set
	 */
	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}
	/**
	 * @return the projectId
	 */
	public Integer getProjectId() {
		return projectId;
	}
	/**
	 * @param projectId the projectId to set
	 */
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the activityType
	 */
	public String getActivityType() {
		return activityType;
	}
	/**
	 * @param activityType the activityType to set
	 */
	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}
	/**
	 * @return the startTime
	 */
	public Date getStartTime() {
		return startTime;
	}
	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	/**
	 * @return the endTime
	 */
	public Date getEndTime() {
		return endTime;
	}
	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	/**
	 * @return the actualStartTime
	 */
	public Date getActualStartTime() {
		return actualStartTime;
	}
	/**
	 * @param actualStartTime the actualStartTime to set
	 */
	public void setActualStartTime(Date actualStartTime) {
		this.actualStartTime = actualStartTime;
	}
	/**
	 * @return the actualEndTime
	 */
	public Date getActualEndTime() {
		return actualEndTime;
	}
	/**
	 * @param actualEndTime the actualEndTime to set
	 */
	public void setActualEndTime(Date actualEndTime) {
		this.actualEndTime = actualEndTime;
	}
	/**
	 * @return the actualStaffDays
	 */
	public Double getActualStaffDays() {
		return actualStaffDays;
	}
	/**
	 * @param actualStaffDays the actualStaffDays to set
	 */
	public void setActualStaffDays(Double actualStaffDays) {
		this.actualStaffDays = actualStaffDays;
	}
	/**
	 * @return the assignedEffort
	 */
	public Double getAssignedEffort() {
		return assignedEffort;
	}
	/**
	 * @param assignedEffort the assignedEffort to set
	 */
	public void setAssignedEffort(Double assignedEffort) {
		this.assignedEffort = assignedEffort;
	}
	/**
	 * @return the remainingEffort
	 */
	public Double getRemainingEffort() {
		return remainingEffort;
	}
	/**
	 * @param remainingEffort the remainingEffort to set
	 */
	public void setRemainingEffort(Double remainingEffort) {
		this.remainingEffort = remainingEffort;
	}
	/**
	 * @return the progress
	 */
	public Integer getProgress() {
		return progress;
	}
	/**
	 * @param progress the progress to set
	 */
	public void setProgress(Integer progress) {
		this.progress = progress;
	}
	/**
	 * @return the trainingFlag
	 */
	public String getTrainingFlag() {
		return trainingFlag;
	}
	/**
	 * @param trainingFlag the trainingFlag to set
	 */
	public void setTrainingFlag(String trainingFlag) {
		this.trainingFlag = trainingFlag;
	}
	/**
	 * @return the deleted
	 */
	public String getDeleted() {
		return deleted;
	}
	/**
	 * @param deleted the deleted to set
	 */
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	/**
	 * @return the paused
	 */
	public String getPaused() {
		return paused;
	}
	/**
	 * @param paused the paused to set
	 */
	public void setPaused(String paused) {
		this.paused = paused;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}
	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}
	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	/**
	 * @return the updatedDate
	 */
	public Date getUpdatedDate() {
		return updatedDate;
	}
	/**
	 * @param updatedDate the updatedDate to set
	 */
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	/**
	 * @return the updatedBy
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}
	/**
	 * @param updatedBy the updatedBy to set
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Integer getMedal(){
		return medal;
	}
	public void setMedal(Integer medal){
		this.medal=medal;
	}
	public String getMedalLocked() {
		return medalLocked;
	}
	public void setMedalLocked(String medalLocked) {
		this.medalLocked = medalLocked;
	}
}