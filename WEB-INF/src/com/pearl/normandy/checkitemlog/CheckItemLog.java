package com.pearl.normandy.checkitemlog;

import java.io.Serializable;
import java.util.Date;

public class CheckItemLog implements Serializable {
	private static final long serialVersionUID = -2941756800615977324L;
	
	private Integer id;
	private Integer checkItemId;
	private String status;
	private String comment;
	private Date createdDate;
	private String createdBy;
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
	 * @return the checkItemId
	 */
	public Integer getCheckItemId() {
		return checkItemId;
	}
	/**
	 * @param checkItemId the checkItemId to set
	 */
	public void setCheckItemId(Integer checkItemId) {
		this.checkItemId = checkItemId;
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
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}
	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
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
}
