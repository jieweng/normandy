package com.pearl.normandy.projectmember;

import java.util.Date;
import java.util.List;

public class ProjectMemberTo extends ProjectMember {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String employeeNo;
	private String email;
	private String name;
	private String userName;
	private String englishName;
	private String memberType;
	private String projectName;
	private String createdByName;
	private String isProjectUser;
	private List<ProjectMemberTo> children;
	
	private int resourceId;
	private Date startTime;
	private Date endTime;
	private int isQA;
	private int isAD;
	private int isPM;
	
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
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
	 * @return the memberType
	 */
	public String getMemberType() {
		return memberType;
	}
	/**
	 * @param memberType the memberType to set
	 */
	public void setMemberType(String memberType) {
		this.memberType = memberType;
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
	 * @return the isProjectUser
	 */
	public String getIsProjectUser() {
		return isProjectUser;
	}
	/**
	 * @param isProjectUser the isProjectUser to set
	 */
	public void setIsProjectUser(String isProjectUser) {
		this.isProjectUser = isProjectUser;
	}
	/**
	 * @return the children
	 */
	public List<ProjectMemberTo> getChildren() {
		return children;
	}
	/**
	 * @param children the children to set
	 */
	public void setChildren(List<ProjectMemberTo> children) {
		this.children = children;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getUserName(){
		return this.userName;
	}
	public void setUserName(String userName){
		this.userName = userName;
	}
	public int getResourceId(){
		return this.resourceId;
	}
	public void setResourceId(int resourceId){
		this.resourceId = resourceId;
	}
	public int getIsQA() {
		return isQA;
	}
	public void setIsQA(int isQA) {
		this.isQA = isQA;
	}
	public int getIsAD() {
		return isAD;
	}
	public void setIsAD(int isAD) {
		this.isAD = isAD;
	}
	public int getIsPM() {
		return isPM;
	}
	public void setIsPM(int isPM) {
		this.isPM = isPM;
	}
	public String getEnglishName() {
		return englishName;
	}
	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}	
}
