package com.pearl.normandy.projectuser;

import java.util.Date;
import java.util.List;

import com.pearl.normandy.utils.Constants;

public class ProjectUserTo extends ProjectUser {

	private static final long serialVersionUID = 5996409968093995673L;

	private String projectRoleName;
	private String projectName;
	private String name;
	private String supervisor;
	private String firstName;
	private String lastName;	
	private List<ProjectUserTo> children;
	private static ProjectUserTo defaultProjectUser;
	
	private Date startDate;
	private Date endDate;
	private int isQA = 0;
	private int isAD = 0;
	private int isPM = 0;

	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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


	public static ProjectUserTo getDefault() {
		if (defaultProjectUser == null) {
			defaultProjectUser = new ProjectUserTo();
			defaultProjectUser.setUserId(0);
			defaultProjectUser.setName(Constants.SELECT_ITEM_ALL);
		}

		return defaultProjectUser;
	}	
	
	
	/**
	 * @return the projectRoleName
	 */
	public String getProjectRoleName() {
		return projectRoleName;
	}
	/**
	 * @param projectRoleName the projectRoleName to set
	 */
	public void setProjectRoleName(String projectRoleName) {
		this.projectRoleName = projectRoleName;
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
	 * @return the supervisor
	 */
	public String getSupervisor() {
		return supervisor;
	}
	/**
	 * @param supervisor the supervisor to set
	 */
	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the children
	 */
	public List<ProjectUserTo> getChildren() {
		return children;
	}
	/**
	 * @param children the children to set
	 */
	public void setChildren(List<ProjectUserTo> children) {
		this.children = children;
	}
}
