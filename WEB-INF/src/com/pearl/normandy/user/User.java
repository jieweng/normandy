package com.pearl.normandy.user;

import java.util.Date;

public class User implements java.io.Serializable {

	private static final long serialVersionUID = -2279312160507617423L;

	private Integer id;
	private String 	userType;
	private Integer userGroupId;
	private Integer customerId;
	private String 	firstName;
	private String 	lastName;
	private String 	englishFirstName;
	private String 	englishLastName;
	private String 	userName;
	private Integer levelId;
	private String 	passwordHash;
	private String 	employeeNo;
	private String 	email;
	private String 	manager;
	private String 	deleted;
	private Integer version;
	private Date 	createdDate;
	private String 	createdBy;
	private Date 	updatedDate;
	private String 	updatedBy;
	private Date    entryDate;
	private Date    leaveDate;
	
	private Date 	employeeDate;
	private String	department;
	private String	position;
	private Date	birthday;
	private String	gender;
	private String	recommendorName;
	private float	recommendationBonus;
	private float	recommendationBonusPayment;
	private Date	recommendationBonusPaymentTime;
	private String	mobile;
	private String	home;
	private String	personalEmail;
	private String	idNo;
	private String	idAddress;
	private String	hukouAddress;
	private String	shangHaiAddress;
	private float	paidLeave;
	private byte[]	photo;

	public Date getEmployeeDate() {
		return employeeDate;
	}


	public void setEmployeeDate(Date employeeDate) {
		this.employeeDate = employeeDate;
	}


	public String getDepartment() {
		return department;
	}


	public void setDepartment(String department) {
		this.department = department;
	}


	public String getPosition() {
		return position;
	}


	public void setPosition(String position) {
		this.position = position;
	}


	public Date getBirthday() {
		return birthday;
	}


	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getRecommendorName() {
		return recommendorName;
	}


	public void setRecommendorName(String recommendorName) {
		this.recommendorName = recommendorName;
	}


	public float getRecommendationBonus() {
		return recommendationBonus;
	}


	public void setRecommendationBonus(float recommendationBonus) {
		this.recommendationBonus = recommendationBonus;
	}


	public float getRecommendationBonusPayment() {
		return recommendationBonusPayment;
	}


	public void setRecommendationBonusPayment(float recommendationBonusPayment) {
		this.recommendationBonusPayment = recommendationBonusPayment;
	}


	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public String getHome() {
		return home;
	}


	public void setHome(String home) {
		this.home = home;
	}


	public String getPersonalEmail() {
		return personalEmail;
	}


	public void setPersonalEmail(String personalEmail) {
		this.personalEmail = personalEmail;
	}


	public String getIdNo() {
		return idNo;
	}


	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}


	public String getIdAddress() {
		return idAddress;
	}


	public void setIdAddress(String idAddress) {
		this.idAddress = idAddress;
	}


	public String getHukouAddress() {
		return hukouAddress;
	}


	public void setHukouAddress(String hukouAddress) {
		this.hukouAddress = hukouAddress;
	}


	public String getShangHaiAddress() {
		return shangHaiAddress;
	}


	public void setShangHaiAddress(String shangHaiAddress) {
		this.shangHaiAddress = shangHaiAddress;
	}

	public float getPaidLeave() {
		return paidLeave;
	}


	public void setPaidLeave(float paidLeave) {
		this.paidLeave = paidLeave;
	}


	public User(){
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
	 * @return the userType
	 */
	public String getUserType() {
		return userType;
	}


	/**
	 * @param userType the userType to set
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}


	/**
	 * @return the userGroupId
	 */
	public Integer getUserGroupId() {
		return userGroupId;
	}


	/**
	 * @param userGroupId the userGroupId to set
	 */
	public void setUserGroupId(Integer userGroupId) {
		this.userGroupId = userGroupId;
	}


	/**
	 * @return the customerId
	 */
	public Integer getCustomerId() {
		return customerId;
	}


	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
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
	 * @return the englishFirstName
	 */
	public String getEnglishFirstName() {
		return englishFirstName;
	}


	/**
	 * @param englishFirstName the englishFirstName to set
	 */
	public void setEnglishFirstName(String englishFirstName) {
		this.englishFirstName = englishFirstName;
	}


	/**
	 * @return the englishLastName
	 */
	public String getEnglishLastName() {
		return englishLastName;
	}


	/**
	 * @param englishLastName the englishLastName to set
	 */
	public void setEnglishLastName(String englishLastName) {
		this.englishLastName = englishLastName;
	}


	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}


	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}


	/**
	 * @return the levelId
	 */
	public Integer getLevelId() {
		return levelId;
	}


	/**
	 * @param levelId the levelId to set
	 */
	public void setLevelId(Integer levelId) {
		this.levelId = levelId;
	}


	/**
	 * @return the passwordHash
	 */
	public String getPasswordHash() {
		return passwordHash;
	}


	/**
	 * @param passwordHash the passwordHash to set
	 */
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}


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
	 * @return the manager
	 */
	public String getManager() {
		return manager;
	}


	/**
	 * @param manager the manager to set
	 */
	public void setManager(String manager) {
		this.manager = manager;
	}


	/**
	 * @return the deleted
	 */
	public String getDeleted() {
		return deleted;
	}


	/**
	 * @param deleted the deleted to set
	 */
	public void setDeleted(String deleted) {
		this.deleted = deleted;
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
	 * @return the entryDate
	 */
	public Date getEntryDate() {
		return entryDate;
	}


	/**
	 * @param entryDate the entryDate to set
	 */
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}


	/**
	 * @return the leaveDate
	 */
	public Date getLeaveDate() {
		return leaveDate;
	}


	/**
	 * @param leaveDate the leaveDate to set
	 */
	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
	}


	public Date getRecommendationBonusPaymentTime() {
		return recommendationBonusPaymentTime;
	}


	public void setRecommendationBonusPaymentTime(
			Date recommendationBonusPaymentTime) {
		this.recommendationBonusPaymentTime = recommendationBonusPaymentTime;
	}


	public byte[] getPhoto() {
		return photo;
	}


	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
}