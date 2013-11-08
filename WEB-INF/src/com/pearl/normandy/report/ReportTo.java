package com.pearl.normandy.report;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.pearl.normandy.feedback.FeedbackTo;

public class ReportTo implements Serializable {

	private static final long serialVersionUID = 376720819209770268L;
	
	private String employeeNo;
	private Integer taskId;
	private String category;
	private String fullName;
	private Date entryDate;
	private Date leaveDate;
	
	//Burndown Chart Attributes
	private Double  effort;
	private String day;
	private int availability;
	
	private String name;
	private Double value;
	private Date date;
	private int year;
	private String quarter;
	private int month;
	
	private	List<FeedbackTo> allFeedback;
	private	List<FeedbackTo> openFeedback;
	private List<FeedbackTo> closedFeedback;
	
	private String project;
	private Integer projectId;
	private String projectKey;
	private String customerName;
	private String userGroupName;
	private String milestone;
	
	//Project Resource Cost Attributes
	private int userId;
	private Double manDays;
	private Double salary;
	private Double socialBenefit;
	private Double insurance;
	
	@SuppressWarnings("unchecked")
	private List reports;
	

	/**
	 * @return the employeeNo
	 */
	public String getEmployeeNo() {
		return employeeNo;
	}

	/**
	 * @param employeeNo the employeeNo to set
	 */
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
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
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return the entryDate
	 */
	public Date getEntryDate() {
		return entryDate;
	}

	/**
	 * @param entryDate the entryDate to set
	 */
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	/**
	 * @return the leaveDate
	 */
	public Date getLeaveDate() {
		return leaveDate;
	}

	/**
	 * @param leaveDate the leaveDate to set
	 */
	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
	}

	/**
	 * @return the effort
	 */
	public Double getEffort() {
		return effort;
	}

	/**
	 * @param effort the effort to set
	 */
	public void setEffort(Double effort) {
		this.effort = effort;
	}

	/**
	 * @return the day
	 */
	public String getDay() {
		return day;
	}

	/**
	 * @param day the day to set
	 */
	public void setDay(String day) {
		this.day = day;
	}

	/**
	 * @return the availability
	 */
	public int getAvailability() {
		return availability;
	}

	/**
	 * @param availability the availability to set
	 */
	public void setAvailability(int availability) {
		this.availability = availability;
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
	 * @return the value
	 */
	public Double getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(Double value) {
		this.value = value;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}
	
	public String getQuarter() {
		return quarter;
	}
	
	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}

	/**
	 * @return the month
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * @param month the month to set
	 */
	public void setMonth(int month) {
		this.month = month;
	}

	/**
	 * @return the allFeedback
	 */
	public List<FeedbackTo> getAllFeedback() {
		return allFeedback;
	}

	/**
	 * @param allFeedback the allFeedback to set
	 */
	public void setAllFeedback(List<FeedbackTo> allFeedback) {
		this.allFeedback = allFeedback;
	}

	/**
	 * @return the openFeedback
	 */
	public List<FeedbackTo> getOpenFeedback() {
		return openFeedback;
	}

	/**
	 * @param openFeedback the openFeedback to set
	 */
	public void setOpenFeedback(List<FeedbackTo> openFeedback) {
		this.openFeedback = openFeedback;
	}

	/**
	 * @return the closedFeedback
	 */
	public List<FeedbackTo> getClosedFeedback() {
		return closedFeedback;
	}

	/**
	 * @param closedFeedback the closedFeedback to set
	 */
	public void setClosedFeedback(List<FeedbackTo> closedFeedback) {
		this.closedFeedback = closedFeedback;
	}

	/**
	 * @return the project
	 */
	public String getProject() {
		return project;
	}

	/**
	 * @param project the project to set
	 */
	public void setProject(String project) {
		this.project = project;
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
	 * @return the reports
	 */
	@SuppressWarnings("unchecked")
	public List getReports() {
		return reports;
	}

	/**
	 * @param reports the reports to set
	 */
	@SuppressWarnings("unchecked")
	public void setReports(List reports) {
		this.reports = reports;
	}

	public String getProjectKey() {
		return projectKey;
	}

	public void setProjectKey(String projectKey) {
		this.projectKey = projectKey;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getUserGroupName() {
		return userGroupName;
	}

	public void setUserGroupName(String userGroupName) {
		this.userGroupName = userGroupName;
	}

	public Double getManDays() {
		return manDays;
	}

	public void setManDays(Double manDays) {
		this.manDays = manDays;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Double getSocialBenefit() {
		return socialBenefit;
	}

	public void setSocialBenefit(Double socialBenefit) {
		this.socialBenefit = socialBenefit;
	}

	public Double getInsurance() {
		return insurance;
	}

	public void setInsurance(Double insurance) {
		this.insurance = insurance;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
