package com.pearl.normandy.activity;

public class ActivityTo extends Activity {

	private static final long serialVersionUID = 1489758739942159312L;

	private String  projectKey;
	private String  projectName;
	private String  milestone;
	private Integer	taskOwnerId;
	private String	taskOwnerName;
	private String  taskCategory;
	private String  taskReferenceUrl;
	private String	taskDescription;
	private Integer statusId;
	private String	status;
	private String 	resourceName;
	private String  createdByName;
	private String  taskParentReferenceUrl;

	/**
	 * @return the projectKey
	 */
	public String getProjectKey() {
		return projectKey;
	}
	/**
	 * @param projectKey the projectKey to set
	 */
	public void setProjectKey(String projectKey) {
		this.projectKey = projectKey;
	}
	/**
	 * @return the projectName
	 */
	public String getProjectName() {
		return projectName;
	}
	/**
	 * @param projectName the projectName to set
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	/**
	 * @return the milestone
	 */
	public String getMilestone() {
		return milestone;
	}
	/**
	 * @param milestone the milestone to set
	 */
	public void setMilestone(String milestone) {
		this.milestone = milestone;
	}
	/**
	 * @return the taskOwnerId
	 */
	public Integer getTaskOwnerId() {
		return taskOwnerId;
	}
	/**
	 * @param taskOwnerId the taskOwnerId to set
	 */
	public void setTaskOwnerId(Integer taskOwnerId) {
		this.taskOwnerId = taskOwnerId;
	}
	/**
	 * @return the taskOwnerName
	 */
	public String getTaskOwnerName() {
		return taskOwnerName;
	}
	/**
	 * @param taskOwnerName the taskOwnerName to set
	 */
	public void setTaskOwnerName(String taskOwnerName) {
		this.taskOwnerName = taskOwnerName;
	}
	/**
	 * @return the taskCategory
	 */
	public String getTaskCategory() {
		return taskCategory;
	}
	/**
	 * @param taskCategory the taskCategory to set
	 */
	public void setTaskCategory(String taskCategory) {
		this.taskCategory = taskCategory;
	}
	/**
	 * @return the taskReferenceUrl
	 */
	public String getTaskReferenceUrl() {
		return taskReferenceUrl;
	}
	/**
	 * @param taskReferenceUrl the taskReferenceUrl to set
	 */
	public void setTaskReferenceUrl(String taskReferenceUrl) {
		this.taskReferenceUrl = taskReferenceUrl;
	}
	/**
	 * @return the taskDescription
	 */
	public String getTaskDescription() {
		return taskDescription;
	}
	/**
	 * @param taskDescription the taskDescription to set
	 */
	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}
	/**
	 * @return the statusId
	 */
	public Integer getStatusId() {
		return statusId;
	}
	/**
	 * @param statusId the statusId to set
	 */
	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the resourceName
	 */
	public String getResourceName() {
		return resourceName;
	}
	/**
	 * @param resourceName the resourceName to set
	 */
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	/**
	 * @return the createdByName
	 */
	public String getCreatedByName() {
		return createdByName;
	}
	/**
	 * @param createdByName the createdByName to set
	 */
	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}
	/**
	 * @return the taskParentReferenceUrl
	 */
	public String getTaskParentReferenceUrl(){
		return taskParentReferenceUrl;
	}
	/**
	 * @param taskParentReferenceUrl the taskParentReferenceUrl to set
	 */
	public void setTaskParentReferenceUrl(String taskParentReferenceUrl){
		this.taskParentReferenceUrl=taskParentReferenceUrl;
	}
}