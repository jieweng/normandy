package com.pearl.normandy.user;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.pearl.normandy.project.ProjectDao;
import com.pearl.normandy.projectmember.ProjectMemberDao;
import com.pearl.normandy.projectmember.ProjectMemberTo;
import com.pearl.normandy.projectuser.ProjectUserDao;
import com.pearl.normandy.core.mail.NormandyNotification;
import com.pearl.normandy.usergroup.UserGroupDao;
import com.pearl.normandy.usergroup.UserGroupTo;
import com.pearl.normandy.utils.Constants;
import com.pearl.normandy.utils.PasswordUtil;

import flex.messaging.MessageException;

public class UserService {

	static Logger log = Logger.getLogger(UserService.class.getName());
	
	private NormandyNotification notification = new NormandyNotification();

	//==============================
	//Get methods
	//==============================	
	public List<UserTo> getAllUsers() throws Exception{
		return userDao.getAllUsers();
	}	
	
	public UserTo getLoginUser(String userName, String password) throws Exception{
		UserTo item = null;		
		List<UserTo> result = userDao.getUserByPassword(userName, PasswordUtil.encrypt(password));

		if (result.size() > 0) {
			item = (UserTo) result.get(0);
		}
		return item;
	}	
	
	
	public List<UserTo> getUsersByGroupId(Integer groupId) throws Exception {
		return userDao.getUsersByGroupId(groupId);
	}
	
	
	public List<UserTo> getAllProductionUsers()throws Exception{
		return userDao.getAllProductionGroupUsersWithJoinedProject();
	}	
	
	public List<UserTo> getAllProductionUsersWithDeleted() throws Exception{
		return userDao.getAllProductionUsersWithDeleted();
	}
	
	public List<UserTo> getGroupUsers(Integer groupId) throws Exception{
		List<UserTo> result = null;
		UserGroupTo userGroup = null;

		List<UserGroupTo> list = userGroupDao.getUserGroups(null, groupId);
		if(list.size()>0){
			userGroup = list.get(0);
		}
		
		if(userGroup.getGroupName().equals(Constants.PROJECT_ROLE_QA) && userGroup.getProduction().equals(Constants.BOOLEAN_NO)){
			result = userDao.getQAGroupUsersWithJoinedProject(groupId);
		}
		else{
			result = userDao.getProductionGroupUsersWithJoinedProject(groupId);
		}

		return result;
	}
	

	public List<UserTo> getProjectUsers(Integer projectId, Boolean isShowDeleted)throws Exception{
		return userDao.getProjectUsers(projectId, isShowDeleted);
	}
		

	public List<UserTo> getUsersForLead(Integer projectId, Integer userId) throws Exception{
		return userDao.getUsersForLead(projectId, userId);
	}	
	
	@SuppressWarnings("unchecked")
	public List<UserTo> getUserByIds(ArrayList ids) throws Exception{
		return userDao.getUserByIds(ids);	
	}	
	
	public List<UserTo> getUsersNotAPM(UserTo userTo)throws Exception{
		return userDao.getUserNotAPM(userTo);
	}
	
	public List<UserTo> getNotProjectUser()throws Exception{
		return userDao.getNotProjectUser();
	}
	
	//==============================
	// methods
	//==============================	
	
	@Transactional
	public void modifyPassword(String userName, String oldPass, String newPass) throws Exception{
		MessageException msg = new MessageException();
		Map<String, String> extendedData = new HashMap<String, String>();
		UserTo userTo = getLoginUser(userName, oldPass);
		if(userTo == null){
			extendedData.put("message", "密码错误");
			msg.setExtendedData(extendedData);
			throw msg;
		}
		String hashOldpassword = PasswordUtil.encrypt(oldPass);
		String hashNewpassword = PasswordUtil.encrypt(newPass);
		userDao.modifyPassword(userName, hashOldpassword, hashNewpassword);   
	}
	
	@Transactional
	public UserTo resetPassword(String userName,String email)throws Exception{
		
		UserTo user = userDao.getUserByUserName(userName, email);

		if(user!=null){
			String password = PasswordUtil.genRandom(6);
			user.setPasswordHash(PasswordUtil.encrypt(password));
			userDao.update(user);
			
			NormandyNotification notification = new NormandyNotification();
			notification.resetPassword(user, password);
		}
		return user;
	}
	
	
	
	//==============================
	//Create, Update, Delete
	//==============================	
	
	public UserTo createUser(UserTo user) throws Exception{
		String password = PasswordUtil.genRandom(6);			
		user.setPasswordHash(PasswordUtil.encrypt(password));
		userDao.create(user);
		
		notification.createAccount(user, password);
		return user;
	}
	
	public void updateUser(UserTo user) throws Exception{
		userDao.update(user);
	}
	
	@Transactional
	public boolean deleteUser(UserTo userTo) throws Exception {
		userDao.delete(userTo.getId());
		ProjectMemberTo pmt = new ProjectMemberTo();
		pmt.setUserId(userTo.getId());				
		pmt.setStatus(Constants.RESOURCE_PROJECT_STATUS_RELEASED);
		projectMemberDao.updateStatus(pmt);
		return true;	
	}	
	
	
	//==============================
	//Injected Variables
	//==============================	
	private UserDao userDao;
	private UserGroupDao userGroupDao;
	@SuppressWarnings("unused")
	private ProjectUserDao projectUserDao;
	private ProjectDao projectDao;
	private ProjectMemberDao projectMemberDao;
	
	public ProjectMemberDao getProjectMemberDao() {
		return projectMemberDao;
	}


	public void setProjectMemberDao(ProjectMemberDao projectMemberDao) {
		this.projectMemberDao = projectMemberDao;
	}


	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setUserGroupDao(UserGroupDao userGroupDao) {
		this.userGroupDao = userGroupDao;
	}

	public void setProjectUserDao(ProjectUserDao projectUserDao) {
		this.projectUserDao = projectUserDao;
	}

	public ProjectDao getProjectDao() {
		return projectDao;
	}

	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}
}