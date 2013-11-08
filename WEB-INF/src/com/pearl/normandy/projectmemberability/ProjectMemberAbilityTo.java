package com.pearl.normandy.projectmemberability;

public class ProjectMemberAbilityTo extends ProjectMemberAbility {

	private static final long serialVersionUID = 9110502853739188473L;

	private String projectName;
	private int difficulty;
	private int levelId;
	private String userName;
	private String name;
	private String userGroupName;
	private String  productivity;
	private Double assignedEffort;
	private Double sun;
	private Double luna;
	private Double star;
	private Double starlight;
	private Double stardust;
	private Double taskQuality;
	
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserGroupName() {
		return userGroupName;
	}
	public void setUserGroupName(String userGroupName) {
		this.userGroupName = userGroupName;
	}
	public Double getAssignedEffort() {
		return assignedEffort;
	}
	public void setAssignedEffort(Double assignedEffort) {
		this.assignedEffort = assignedEffort;
	}
	public Double getSun() {
		return sun;
	}
	public void setSun(Double sun) {
		this.sun = sun;
	}
	public Double getLuna() {
		return luna;
	}
	public void setLuna(Double luna) {
		this.luna = luna;
	}
	public Double getStar() {
		return star;
	}
	public void setStar(Double star) {
		this.star = star;
	}
	public Double getStarlight() {
		return starlight;
	}
	public void setStarlight(Double starlight) {
		this.starlight = starlight;
	}
	public Double getStardust() {
		return stardust;
	}
	public void setStardust(Double stardust) {
		this.stardust = stardust;
	}
	public Double getTaskQuality() {
		return taskQuality;
	}
	public void setTaskQuality(Double taskQuality) {
		this.taskQuality = taskQuality;
	}
	public String getProductivity() {
		return productivity;
	}
	public void setProductivity(String productivity) {
		this.productivity = productivity;
	}
	public int getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
	public int getLevelId() {
		return levelId;
	}
	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
