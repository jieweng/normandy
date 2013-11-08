package com.pearl.normandy.report;

import java.util.Date;


public class ReportDetailInfo implements java.io.Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 5571115499366412107L;
	private int id;
	private String milestone;
	private String task_category;
	private String Priority;
	private String Name;
	private Date Plan_Start;
	private Date Plan_End;
	private Double Plan_Duration;
	private Date Actual_Start;
	private Date Actual_End;
	private Double Acutal_Duration;
	private Double Total_Iteration;
	private Double Fix_Time;
	private String Progress;
	private String Status;

	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getMilestone() {
		return milestone;
	}


	public void setMilestone(String milestone) {
		this.milestone = milestone;
	}


	public String getTask_category() {
		return task_category;
	}


	public void setTask_category(String task_category) {
		this.task_category = task_category;
	}


	public String getPriority() {
		return Priority;
	}


	public void setPriority(String priority) {
		Priority = priority;
	}


	public String getName() {
		return Name;
	}


	public void setName(String name) {
		Name = name;
	}


	public Date getPlan_Start() {
		return Plan_Start;
	}


	public void setPlan_Start(Date plan_Start) {
		Plan_Start = plan_Start;
	}


	public Date getPlan_End() {
		return Plan_End;
	}


	public void setPlan_End(Date plan_End) {
		Plan_End = plan_End;
	}


	public Double getPlan_Duration() {
		return Plan_Duration;
	}


	public void setPlan_Duration(Double plan_Duration) {
		Plan_Duration = plan_Duration;
	}


	public Date getActual_Start() {
		return Actual_Start;
	}


	public void setActual_Start(Date actual_Start) {
		Actual_Start = actual_Start;
	}


	public Date getActual_End() {
		return Actual_End;
	}


	public void setActual_End(Date actual_End) {
		Actual_End = actual_End;
	}


	public Double getAcutal_Duration() {
		return Acutal_Duration;
	}


	public void setAcutal_Duration(Double acutal_Duration) {
		Acutal_Duration = acutal_Duration;
	}


	public Double getTotal_Iteration() {
		return Total_Iteration;
	}


	public void setTotal_Iteration(Double total_Iteration) {
		Total_Iteration = total_Iteration;
	}


	public Double getFix_Time() {
		return Fix_Time;
	}


	public void setFix_Time(Double fix_Time) {
		Fix_Time = fix_Time;
	}


	public String getProgress() {
		return Progress;
	}


	public void setProgress(String progress) {
		Progress = progress;
	}


	public String getStatus() {
		return Status;
	}


	public void setStatus(String status) {
		Status = status;
	}


	public ReportDetailInfo() {
	}

}
