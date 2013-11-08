package com.pearl.normandy.metadata;

import java.util.Date;
import java.util.List;

import com.pearl.normandy.listofvalue.ListOfValueTo;
import com.pearl.normandy.usergroup.UserGroupTo;

public class MetaData implements java.io.Serializable {

	private static final long serialVersionUID = 4261142780135210270L;

	private	String referenceUrl;
	private String referenceTempUrl;
	private String reportUrl;
	private String mpxjUrl;
	private String thumbnailUrl;
	
	private Date activityLockedTime;
	
	private List<UserGroupTo> userGroups;
	private List<UserGroupTo> productionGroup;
	private List<String> projectUsers;
	private List<Date> holidays;
	private List<ListOfValueTo> taskCategories;
	private List<ListOfValueTo> priorities;
	private List<ListOfValueTo> severities;
	private List<ListOfValueTo> taskStatuses;
	private List<ListOfValueTo> rootCauses;
	private List<ListOfValueTo> modifyStatues;
	
	/**
	 * @return the activityLockedTime
	 */
	public Date getActivityLockedTime(){
		return activityLockedTime;
	}
	/**
	 * @param activityLockedTime the activityLockedTime to set
	 */
	public void setActivityLockedTime(Date activityLockedTime){
		this.activityLockedTime=activityLockedTime;
	}
	/**
	 * @return the referenceUrl
	 */
	public String getReferenceUrl() {
		return referenceUrl;
	}
	/**
	 * @param referenceUrl the referenceUrl to set
	 */
	public void setReferenceUrl(String referenceUrl) {
		this.referenceUrl = referenceUrl;
	}
	/**
	 * @return the referenceTempUrl
	 */
	public String getReferenceTempUrl() {
		return referenceTempUrl;
	}
	/**
	 * @param referenceTempUrl the referenceTempUrl to set
	 */
	public void setReferenceTempUrl(String referenceTempUrl) {
		this.referenceTempUrl = referenceTempUrl;
	}
	/**
	 * @return the reportUrl
	 */
	public String getReportUrl() {
		return reportUrl;
	}
	/**
	 * @param reportUrl the reportUrl to set
	 */
	public void setReportUrl(String reportUrl) {
		this.reportUrl = reportUrl;
	}
	/**
	 * @return the userGroups
	 */
	public List<UserGroupTo> getUserGroups() {
		return userGroups;
	}
	/**
	 * @param userGroups the userGroups to set
	 */
	public void setUserGroups(List<UserGroupTo> userGroups) {
		this.userGroups = userGroups;
	}
	/**
	 * @return the productionGroup
	 */
	public List<UserGroupTo> getProductionGroup() {
		return productionGroup;
	}
	/**
	 * @param productionGroup the productionGroup to set
	 */
	public void setProductionGroup(List<UserGroupTo> productionGroup) {
		this.productionGroup = productionGroup;
	}
	/**
	 * @return the projectUsers
	 */
	public List<String> getProjectUsers() {
		return projectUsers;
	}
	/**
	 * @param projectUsers the projectUsers to set
	 */
	public void setProjectUsers(List<String> projectUsers) {
		this.projectUsers = projectUsers;
	}
	/**
	 * @return the holidays
	 */
	public List<Date> getHolidays() {
		return holidays;
	}
	/**
	 * @param holidays the holidays to set
	 */
	public void setHolidays(List<Date> holidays) {
		this.holidays = holidays;
	}
	/**
	 * @return the taskCategories
	 */
	public List<ListOfValueTo> getTaskCategories() {
		return taskCategories;
	}
	/**
	 * @param taskCategories the taskCategories to set
	 */
	public void setTaskCategories(List<ListOfValueTo> taskCategories) {
		this.taskCategories = taskCategories;
	}
	/**
	 * @return the priorities
	 */
	public List<ListOfValueTo> getPriorities() {
		return priorities;
	}
	/**
	 * @param priorities the priorities to set
	 */
	public void setPriorities(List<ListOfValueTo> priorities) {
		this.priorities = priorities;
	}
	/**
	 * @return the severities
	 */
	public List<ListOfValueTo> getSeverities() {
		return severities;
	}
	/**
	 * @param severities the severities to set
	 */
	public void setSeverities(List<ListOfValueTo> severities) {
		this.severities = severities;
	}
	/**
	 * @return the taskStatuses
	 */
	public List<ListOfValueTo> getTaskStatuses() {
		return taskStatuses;
	}
	/**
	 * @param taskStatuses the taskStatuses to set
	 */
	public void setTaskStatuses(List<ListOfValueTo> taskStatuses) {
		this.taskStatuses = taskStatuses;
	}
	/**
	 * @return the rootCauses
	 */
	public List<ListOfValueTo> getRootCauses() {
		return rootCauses;
	}
	/**
	 * @param rootCauses the rootCauses to set
	 */
	public void setRootCauses(List<ListOfValueTo> rootCauses) {
		this.rootCauses = rootCauses;
	}
	/**
	 * @return the modifyStatues
	 */
	public List<ListOfValueTo> getModifyStatues() {
		return modifyStatues;
	}
	/**
	 * @param modifyStatues the modifyStatues to set
	 */
	public void setModifyStatues(List<ListOfValueTo> modifyStatues) {
		this.modifyStatues = modifyStatues;
	}
	public String getMpxjUrl() {
		return mpxjUrl;
	}
	public void setMpxjUrl(String mpxjUrl) {
		this.mpxjUrl = mpxjUrl;
	}
	public String getThumbnailUrl() {
		return thumbnailUrl;
	}
	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}
}
