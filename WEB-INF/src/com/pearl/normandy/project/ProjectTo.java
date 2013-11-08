package com.pearl.normandy.project;

public class ProjectTo extends Project {

	private static final long serialVersionUID = 9062598340203927568L;

	private String customerName;
	private int revision;
	private int planned;
	private int approved;
	private double progress;
	private double progressInNum;
	private double plannedStaffDays;
	private double actualStaffDays;
	private double management;
	private double compOff;
	private double projectTraining;
	
	public double getManagement() {
		return management;
	}
	public void setManagement(double management) {
		this.management = management;
	}
	public double getCompOff() {
		return compOff;
	}
	public void setCompOff(double compOff) {
		this.compOff = compOff;
	}
	public double getProjectTraining() {
		return projectTraining;
	}
	public void setProjectTraining(double projectTraining) {
		this.projectTraining = projectTraining;
	}
	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}
	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	/**
	 * @return the revision
	 */
	public int getRevision() {
		return revision;
	}
	/**
	 * @param revision the revision to set
	 */
	public void setRevision(int revision) {
		this.revision = revision;
	}
	/**
	 * @return the planned
	 */
	public int getPlanned() {
		return planned;
	}
	/**
	 * @param planned the planned to set
	 */
	public void setPlanned(int planned) {
		this.planned = planned;
	}
	/**
	 * @return the approved
	 */
	public int getApproved() {
		return approved;
	}
	/**
	 * @param approved the approved to set
	 */
	public void setApproved(int approved) {
		this.approved = approved;
	}
	/**
	 * @return the progress
	 */
	public double getProgress() {
		return progress;
	}
	/**
	 * @param progress the progress to set
	 */
	public void setProgress(double progress) {
		this.progress = progress;
	}
	/**
	 * @return the progressInNum
	 */
	public double getProgressInNum() {
		return progressInNum;
	}
	/**
	 * @param progressInNum the progressInNum to set
	 */
	public void setProgressInNum(double progressInNum) {
		this.progressInNum = progressInNum;
	}
	/**
	 * @return the plannedStaffDays
	 */
	public double getPlannedStaffDays() {
		return plannedStaffDays;
	}
	/**
	 * @param plannedStaffDays the plannedStaffDays to set
	 */
	public void setPlannedStaffDays(double plannedStaffDays) {
		this.plannedStaffDays = plannedStaffDays;
	}
	/**
	 * @return the actualStaffDays
	 */
	public double getActualStaffDays() {
		return actualStaffDays;
	}
	/**
	 * @param actualStaffDays the actualStaffDays to set
	 */
	public void setActualStaffDays(double actualStaffDays) {
		this.actualStaffDays = actualStaffDays;
	}
}