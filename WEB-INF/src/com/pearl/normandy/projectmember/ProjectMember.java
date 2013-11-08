package com.pearl.normandy.projectmember;

import java.util.Date;

public class ProjectMember implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer userId;
	private Integer projectId;
	private String	status;
	private Integer version;
	private Date 	createdDate;
	private String 	createdBy;
	private Date 	updatedDate;
	private String 	updatedBy;
	private Date	releaseDate;
	
	private String	privTask1;
	private String	privTask2;
	private String	privResource;
	private String	privPrivilege;
	private String	privMail;
	private String	privPerformance;

	public String getPrivMail() {
		return privMail;
	}

	public void setPrivMail(String privMail) {
		this.privMail = privMail;
	}

	public ProjectMember() {
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
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
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

	/**
	 * @return the releaseDate
	 */
	public Date getReleaseDate() {
		return releaseDate;
	}

	/**
	 * @param releaseDate the releaseDate to set
	 */
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getPrivTask1() {
		return privTask1;
	}

	public void setPrivTask1(String privTask1) {
		this.privTask1 = privTask1;
	}

	public String getPrivTask2() {
		return privTask2;
	}

	public void setPrivTask2(String privTask2) {
		this.privTask2 = privTask2;
	}

	public String getPrivResource() {
		return privResource;
	}

	public void setPrivResource(String privResource) {
		this.privResource = privResource;
	}

	public String getPrivPrivilege() {
		return privPrivilege;
	}

	public void setPrivPrivilege(String privPrivilege) {
		this.privPrivilege = privPrivilege;
	}

	public String getPrivPerformance() {
		return privPerformance;
	}

	public void setPrivPerformance(String privPerformance) {
		this.privPerformance = privPerformance;
	}
}