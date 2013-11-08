package com.pearl.normandy.feedback;

import java.util.List;

import com.pearl.normandy.feedbackreference.FeedbackReferenceTo;

public class FeedbackTo extends Feedback {

	private static final long serialVersionUID = -2536050800614411705L;

	private String projectKey;
	private String taskName;
	private String ownerName;
	private String typeValue;
	private String statusValue;
	private String severityValue;
	
	private List<FeedbackReferenceTo> feedbackReference;

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
	 * @return the ownerName
	 */
	public String getOwnerName() {
		return ownerName;
	}

	/**
	 * @param ownerName the ownerName to set
	 */
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	/**
	 * @return the typeValue
	 */
	public String getTypeValue() {
		return typeValue;
	}

	/**
	 * @param typeValue the typeValue to set
	 */
	public void setTypeValue(String typeValue) {
		this.typeValue = typeValue;
	}

	/**
	 * @return the statusValue
	 */
	public String getStatusValue() {
		return statusValue;
	}

	/**
	 * @param statusValue the statusValue to set
	 */
	public void setStatusValue(String statusValue) {
		this.statusValue = statusValue;
	}

	/**
	 * @return the severityValue
	 */
	public String getSeverityValue() {
		return severityValue;
	}

	/**
	 * @param severityValue the severityValue to set
	 */
	public void setSeverityValue(String severityValue) {
		this.severityValue = severityValue;
	}

	/**
	 * @return the feedbackReference
	 */
	public List<FeedbackReferenceTo> getFeedbackReference() {
		return feedbackReference;
	}

	/**
	 * @param feedbackReference the feedbackReference to set
	 */
	public void setFeedbackReference(List<FeedbackReferenceTo> feedbackReference) {
		this.feedbackReference = feedbackReference;
	}
}
