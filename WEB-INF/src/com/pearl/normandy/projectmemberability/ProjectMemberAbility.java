package com.pearl.normandy.projectmemberability;

import java.io.Serializable;

public class ProjectMemberAbility implements Serializable {

	private static final long serialVersionUID = -2656816537858884935L;

	private int id;
	private int projectId;
	private int userId;
	private int year;
	private int month;
	private String highPoly;		//高模
	private String lowPoly;			//低模
	private String zbrush;			
	private String texture;			//贴图
	private String integration;		//整合
	private String colligation;		//绑定
	private String engine;			//引擎
	private String initiative;		//主动性
	private String communicate;		//合作沟通
	private String artCulture;		//艺术修养
	private String innovation;		//学习创新
	private String punctual;		//出勤
	private String contribute;		//贡献
	private String remark;
	
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
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public String getHighPoly() {
		return highPoly;
	}
	public void setHighPoly(String highPoly) {
		this.highPoly = highPoly;
	}
	public String getLowPoly() {
		return lowPoly;
	}
	public void setLowPoly(String lowPoly) {
		this.lowPoly = lowPoly;
	}
	public String getZbrush() {
		return zbrush;
	}
	public void setZbrush(String zbrush) {
		this.zbrush = zbrush;
	}
	public String getTexture() {
		return texture;
	}
	public void setTexture(String texture) {
		this.texture = texture;
	}
	public String getIntegration() {
		return integration;
	}
	public void setIntegration(String integration) {
		this.integration = integration;
	}
	public String getColligation() {
		return colligation;
	}
	public void setColligation(String colligation) {
		this.colligation = colligation;
	}
	public String getEngine() {
		return engine;
	}
	public void setEngine(String engine) {
		this.engine = engine;
	}
	public String getInitiative() {
		return initiative;
	}
	public void setInitiative(String initiative) {
		this.initiative = initiative;
	}
	public String getCommunicate() {
		return communicate;
	}
	public void setCommunicate(String communicate) {
		this.communicate = communicate;
	}
	public String getArtCulture() {
		return artCulture;
	}
	public void setArtCulture(String artCulture) {
		this.artCulture = artCulture;
	}
	public String getInnovation() {
		return innovation;
	}
	public void setInnovation(String innovation) {
		this.innovation = innovation;
	}
	public String getPunctual() {
		return punctual;
	}
	public void setPunctual(String punctual) {
		this.punctual = punctual;
	}
	public String getContribute() {
		return contribute;
	}
	public void setContribute(String contribute) {
		this.contribute = contribute;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
