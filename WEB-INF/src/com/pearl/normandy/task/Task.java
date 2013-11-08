package com.pearl.normandy.task;

import java.util.Date;

public class Task implements java.io.Serializable {

	private static final long serialVersionUID = 879611588251085161L;

	//ID
	private Integer	id;
	private Integer parentId = 0;
	private String  taskId;
	private Integer projectId;

	//Basic Information
	private String 	name;
	private String  parentName;
	private String  type;
	private Integer productionProcessId;	
	private String  milestone;
	private String 	taskGroup;
	private String  taskCategory;	
	private Integer taskPriorityId;	
	private String  isFeedback;	
	private String  referenceUrl;
	private String 	description;

	//Schedule Information
	private Date 	startTime;
	private Date 	endTime;
	private Double 	duration;	
	private Date 	actualStartTime;
	private Date 	actualEndTime;
	private Double 	plannedStaffDays;
	private Double  plannedFeedbackDays;	
	
	//Status Information
	private Integer statusId;
	private Integer progress;	
	private Integer ownerId;
	private Integer qaOwnerId;
	private String 	deleted;
	private String 	paused;	

	//Order Information
	private Integer sequence;
	private String 	predecessor;
	private String 	predecessorTree;
	private Double 	deviation;

	//System Tracking Information
	private Integer revision;
	private Integer version;
	private Date 	createdDate;
	private String 	createdBy;
	private Date 	updatedDate;
	private String 	updatedBy;

	public Task() {
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
	 * @return the parentId
	 */
	public Integer getParentId() {
		return parentId;
	}

	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	/**
	 * @return the taskId
	 */
	public String getTaskId() {
		return taskId;
	}

	/**
	 * @param taskId the taskId to set
	 */
	public void setTaskId(String taskId) {
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
	 * @return the parentName
	 */
	public String getParentName() {
		return parentName;
	}

	/**
	 * @param parentName the parentName to set
	 */
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the productionProcessId
	 */
	public Integer getProductionProcessId() {
		return productionProcessId;
	}

	/**
	 * @param productionProcessId the productionProcessId to set
	 */
	public void setProductionProcessId(Integer productionProcessId) {
		this.productionProcessId = productionProcessId;
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
	 * @return the taskPriorityId
	 */
	public Integer getTaskPriorityId() {
		return taskPriorityId;
	}

	/**
	 * @param taskPriorityId the taskPriorityId to set
	 */
	public void setTaskPriorityId(Integer taskPriorityId) {
		this.taskPriorityId = taskPriorityId;
	}

	/**
	 * @return the isFeedback
	 */
	public String getIsFeedback() {
		return isFeedback;
	}

	/**
	 * @param isFeedback the isFeedback to set
	 */
	public void setIsFeedback(String isFeedback) {
		this.isFeedback = isFeedback;
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
	 * @return the duration
	 */
	public Double getDuration() {
		return duration;
	}

	/**
	 * @param duration the duration to set
	 */
	public void setDuration(Double duration) {
		this.duration = duration;
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
	 * @return the plannedStaffDays
	 */
	public Double getPlannedStaffDays() {
		return plannedStaffDays;
	}

	/**
	 * @param plannedStaffDays the plannedStaffDays to set
	 */
	public void setPlannedStaffDays(Double plannedStaffDays) {
		this.plannedStaffDays = plannedStaffDays;
	}

	/**
	 * @return the plannedFeedbackDays
	 */
	public Double getPlannedFeedbackDays() {
		return plannedFeedbackDays;
	}

	/**
	 * @param plannedFeedbackDays the plannedFeedbackDays to set
	 */
	public void setPlannedFeedbackDays(Double plannedFeedbackDays) {
		this.plannedFeedbackDays = plannedFeedbackDays;
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
	 * @return the qaOwnerId
	 */
	public Integer getQaOwnerId() {
		return qaOwnerId;
	}

	/**
	 * @param qaOwnerId the qaOwnerId to set
	 */
	public void setQaOwnerId(Integer qaOwnerId) {
		this.qaOwnerId = qaOwnerId;
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
	 * @return the sequence
	 */
	public Integer getSequence() {
		return sequence;
	}

	/**
	 * @param sequence the sequence to set
	 */
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	/**
	 * @return the predecessor
	 */
	public String getPredecessor() {
		return predecessor;
	}

	/**
	 * @param predecessor the predecessor to set
	 */
	public void setPredecessor(String predecessor) {
		this.predecessor = predecessor;
	}

	/**
	 * @return the predecessorTree
	 */
	public String getPredecessorTree() {
		return predecessorTree;
	}

	/**
	 * @param predecessorTree the predecessorTree to set
	 */
	public void setPredecessorTree(String predecessorTree) {
		this.predecessorTree = predecessorTree;
	}

	/**
	 * @return the deviation
	 */
	public Double getDeviation() {
		return deviation;
	}

	/**
	 * @param deviation the deviation to set
	 */
	public void setDeviation(Double deviation) {
		this.deviation = deviation;
	}

	/**
	 * @return the revision
	 */
	public Integer getRevision() {
		return revision;
	}

	/**
	 * @param revision the revision to set
	 */
	public void setRevision(Integer revision) {
		this.revision = revision;
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
}