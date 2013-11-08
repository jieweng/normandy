package com.pearl.normandy.userSalary;

import java.io.Serializable;
import java.util.Date;

public class UserSalary implements Serializable{

	private static final long serialVersionUID = 334002359107512157L;

	private int id;
	private int userId;
	private String salary;
	private String insurance;
	private String lunchBenefit;
	private String socialBenefitType1;
	private String socialBenefitType2;
	private String houseFound;
	private Date startTime;
	private int creatorId;
	private Date createTime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public String getInsurance() {
		return insurance;
	}
	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}
	public String getLunchBenefit() {
		return lunchBenefit;
	}
	public void setLunchBenefit(String lunchBenefit) {
		this.lunchBenefit = lunchBenefit;
	}
	public String getSocialBenefitType1() {
		return socialBenefitType1;
	}
	public void setSocialBenefitType1(String socialBenefitType1) {
		this.socialBenefitType1 = socialBenefitType1;
	}
	public String getSocialBenefitType2() {
		return socialBenefitType2;
	}
	public void setSocialBenefitType2(String socialBenefitType2) {
		this.socialBenefitType2 = socialBenefitType2;
	}
	public String getHouseFound() {
		return houseFound;
	}
	public void setHouseFound(String houseFound) {
		this.houseFound = houseFound;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public int getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(int creatorId) {
		this.creatorId = creatorId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
