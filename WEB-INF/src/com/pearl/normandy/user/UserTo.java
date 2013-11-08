package com.pearl.normandy.user;

import java.util.List;

import com.pearl.normandy.project.ProjectTo;

public class UserTo extends User {

	private static final long serialVersionUID = 5744574628801885891L;
	private String name;
	private String fullName;
	private String userGroupName;
	private String state; //USER_GROUP.state
	private String productionGroup; //USER_GROUP.production
	private String customerName;
	private String member;
	private String projectsId 	= "";
	private String projectsName = "";
	private String searchStr;
	
	private int year;
	private int month;
	private List<ProjectTo> projects ;
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
	 * @return the userGroupName
	 */
	public String getUserGroupName() {
		return userGroupName;
	}
	/**
	 * @param userGroupName the userGroupName to set
	 */
	public void setUserGroupName(String userGroupName) {
		this.userGroupName = userGroupName;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return the productionGroup
	 */
	public String getProductionGroup() {
		return productionGroup;
	}
	/**
	 * @param productionGroup the productionGroup to set
	 */
	public void setProductionGroup(String productionGroup) {
		this.productionGroup = productionGroup;
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
	 * @return the member
	 */
	public String getMember() {
		return member;
	}
	/**
	 * @param member the member to set
	 */
	public void setMember(String member) {
		this.member = member;
	}
	/**
	 * @return the projectsId
	 */
	public String getProjectsId() {
		return projectsId;
	}
	/**
	 * @param projectsId the projectsId to set
	 */
	public void setProjectsId(String projectsId) {
		this.projectsId = projectsId;
	}
	/**
	 * @return the projectsName
	 */
	public String getProjectsName() {
		return projectsName;
	}
	/**
	 * @param projectsName the projectsName to set
	 */
	public void setProjectsName(String projectsName) {
		this.projectsName = projectsName;
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
	 * @return the projects
	 */
	public List<ProjectTo> getProjects() {
		return projects;
	}
	/**
	 * @param projects the projects to set
	 */
	public void setProjects(List<ProjectTo> projects) {
		this.projects = projects;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getSearchStr() {
		return searchStr;
	}
	public void setSearchStr(String searchStr) {
		this.searchStr = searchStr;
	}
}
