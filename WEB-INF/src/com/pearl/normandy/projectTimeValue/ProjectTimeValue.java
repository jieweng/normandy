package com.pearl.normandy.projectTimeValue;

import java.io.Serializable;
import java.util.Date;

public class ProjectTimeValue implements Serializable {

	private static final long serialVersionUID = -2485342184457035228L;
	
	private int id;
	private int projectId;
	private Date time;
	private String day;
	private float value;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}

}
