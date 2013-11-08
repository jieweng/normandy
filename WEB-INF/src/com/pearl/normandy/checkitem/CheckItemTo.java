package com.pearl.normandy.checkitem;

import java.util.List;

import com.pearl.normandy.feedback.FeedbackTo;


public class CheckItemTo extends CheckItem {
	private static final long serialVersionUID = 4866209688515696543L;
	
	private Integer projectId;
	private String  projectName;
	private Integer taskId;
	private String  taskName;
	private Integer	parentId;
	private String  parentName;
	private String	taskCategory;
	private String  taskGroup;
	private Integer ownerId;
	private String	owner;
	private String 	priority;
	private String 	activityName;
	private String  resourceName;
	private Integer	userId;
	private List<FeedbackTo> feedbacks;
	
	
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
	 * @return the taskName
	 */
	public String getTaskName() {
		return taskName;
	}
	/**
	 * @param taskName the taskName to set
	 */
	public void setTaskName(String taskName) {
		this.taskName = taskName;
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
	 * @return the taskGroup
	 */
	public String getTaskGroup() {
		return taskGroup;
	}
	/**
	 * @param taskGroup the taskGroup to set
	 */
	public void setTaskGroup(String taskGroup) {
		this.taskGroup = taskGroup;
	}
	/**
	 * @return the ownerId
	 */
	public Integer getOwnerId() {
		return ownerId;
	}
	/**
	 * @param ownerId the ownerId to set
	 */
	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}
	/**
	 * @return the owner
	 */
	public String getOwner() {
		return owner;
	}
	/**
	 * @param owner the owner to set
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}
	/**
	 * @return the priority
	 */
	public String getPriority() {
		return priority;
	}
	/**
	 * @param priority the priority to set
	 */
	public void setPriority(String priority) {
		this.priority = priority;
	}
	/**
	 * @return the activityName
	 */
	public String getActivityName() {
		return activityName;
	}
	/**
	 * @param activityName the activityName to set
	 */
	public void setActivityName(String activityName) {
		this.activityName = activityName;
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
	
	public Integer getUserId(){
		return this.userId;
	}
	
	public void setUserId(Integer userId){
		this.userId = userId;
	}
	
	public List<FeedbackTo> getFeedbacks(){
		return this.feedbacks;
	}
	
	public void setFeedbacks(List<FeedbackTo> feedbacks){
		this.feedbacks = feedbacks;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
}
