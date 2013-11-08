package com.pearl.normandy.task;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import com.pearl.normandy.taskdetail.TaskDetailTo;

public class TaskTo extends Task {

	private static final long serialVersionUID = 2906105643863500772L;

	private String projectKey;
	private String projectName;
	private String productionProcess;
	private String priority;
	private String owner;
	private String qaOwner;
	private String resourcesName;
	private Double actualStaffDays;
	private Double staffDaysDifference;
	private Date nextDeliveryDue;
	private String status;
	private String taskProgress; //for display progress with %
	private Double completion;   //for display in task chart
	private Date baselineStart;
	private Date baselineEnd;
	private List<TaskTo> children;
	private HashMap<String, TaskDetailTo> details;
	private int FeedbackNum;
	private Double staffAssigned;
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
	 * @return the productionProcess
	 */
	public String getProductionProcess() {
		return productionProcess;
	}
	/**
	 * @param productionProcess the productionProcess to set
	 */
	public void setProductionProcess(String productionProcess) {
		this.productionProcess = productionProcess;
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
	 * @return the qaOwner
	 */
	public String getQaOwner() {
		return qaOwner;
	}
	/**
	 * @param qaOwner the qaOwner to set
	 */
	public void setQaOwner(String qaOwner) {
		this.qaOwner = qaOwner;
	}
	/**
	 * @return the resourcesName
	 */
	public String getResourcesName() {
		return resourcesName;
	}
	/**
	 * @param resourcesName the resourcesName to set
	 */
	public void setResourcesName(String resourcesName) {
		this.resourcesName = resourcesName;
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
	 * @return the staffDaysDifference
	 */
	public Double getStaffDaysDifference() {
		return staffDaysDifference;
	}
	/**
	 * @param staffDaysDifference the staffDaysDifference to set
	 */
	public void setStaffDaysDifference(Double staffDaysDifference) {
		this.staffDaysDifference = staffDaysDifference;
	}
	/**
	 * @return the nextDeliveryDue
	 */
	public Date getNextDeliveryDue() {
		return nextDeliveryDue;
	}
	/**
	 * @param nextDeliveryDue the nextDeliveryDue to set
	 */
	public void setNextDeliveryDue(Date nextDeliveryDue) {
		this.nextDeliveryDue = nextDeliveryDue;
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
	 * @return the taskProgress
	 */
	public String getTaskProgress() {
		return taskProgress;
	}
	/**
	 * @param taskProgress the taskProgress to set
	 */
	public void setTaskProgress(String taskProgress) {
		this.taskProgress = taskProgress;
	}
	/**
	 * @return the completion
	 */
	public Double getCompletion() {
		return completion;
	}
	/**
	 * @param completion the completion to set
	 */
	public void setCompletion(Double completion) {
		this.completion = completion;
	}
	/**
	 * @return the baselineStart
	 */
	public Date getBaselineStart() {
		return baselineStart;
	}
	/**
	 * @param baselineStart the baselineStart to set
	 */
	public void setBaselineStart(Date baselineStart) {
		this.baselineStart = baselineStart;
	}
	/**
	 * @return the baselineEnd
	 */
	public Date getBaselineEnd() {
		return baselineEnd;
	}
	/**
	 * @param baselineEnd the baselineEnd to set
	 */
	public void setBaselineEnd(Date baselineEnd) {
		this.baselineEnd = baselineEnd;
	}
	/**
	 * @return the children
	 */
	public List<TaskTo> getChildren() {
		return children;
	}
	/**
	 * @param children the children to set
	 */
	public void setChildren(List<TaskTo> children) {
		this.children = children;
	}
	/**
	 * @return the details
	 */
	public HashMap<String, TaskDetailTo> getDetails() {
		return details;
	}
	/**
	 * @param details the details to set
	 */
	public void setDetails(HashMap<String, TaskDetailTo> details) {
		this.details = details;
	}
	public int getFeedbackNum() {
		return FeedbackNum;
	}
	public void setFeedbackNum(int feedbackNum) {
		FeedbackNum = feedbackNum;
	}
	public Double getStaffAssigned() {
		return staffAssigned;
	}
	public void setStaffAssigned(Double staffAssigned) {
		this.staffAssigned = staffAssigned;
	}
}
