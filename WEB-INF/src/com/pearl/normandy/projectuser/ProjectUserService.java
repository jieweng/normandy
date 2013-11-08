package com.pearl.normandy.projectuser;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.pearl.normandy.project.ProjectTo;
import com.pearl.normandy.projectmember.ProjectMemberDao;
import com.pearl.normandy.projectmember.ProjectMemberTo;
import com.pearl.normandy.task.TaskDao;
import com.pearl.normandy.user.UserTo;
import com.pearl.normandy.utils.Constants;

public class ProjectUserService {

	static Logger log = Logger.getLogger(ProjectUserService.class.getName());
	
	//==============================
	//Get methods
	//==============================
	public List<ProjectUserTo> getProjectUserByUserId(Integer userId, Integer projectId)throws Exception{
		List<ProjectUserTo> result = null;
		result = projectUserDao.getProjectUserByUserId(userId, projectId);
		return result;
	}


	public List<String> getDistinctProjectUserRole(Integer userId)throws Exception{
		List<String> result = null;
		result = projectUserDao.getDistinctProjectUserRole(userId);
		return result;
	}
	
	public List<ProjectUserTo> getProjectUserRole(Integer projectId, Integer userId) throws Exception{
		List<ProjectUserTo> result = projectUserDao.getProjectUserRole(projectId, userId);
		return result;
	}

//	public ProjectUserTo getProjectUserHierarchical(Integer projectId, ProjectUserTo ad) throws Exception{
//		List<ProjectUserTo> projectUsers = getProjectUserByProjectId(projectId);
//		ProjectUserTo pu = null;
//		ProjectUserTo adUser = null;
//		ProjectUserTo leadUser = null;
//		List<ProjectUserTo> leadUsers = new ArrayList<ProjectUserTo>();
//
//		adUser = ad;
//		if (adUser == null) {
//			return null;
//		}
//		
//		for (int j = 0; j < projectUsers.size(); j++) {
//			pu = projectUsers.get(j);
//			if (pu.getSupervisorId() != null && pu.getSupervisorId().equals(adUser.getUserId()) && pu.getProjectRoleId().equals(Constants.PROJECT_ROLE_LEAD_NUM)) {
//				if (adUser.getChildren() == null) {
//					adUser.setChildren(new ArrayList<ProjectUserTo>());
//				}
//				leadUsers.add(pu);
//				adUser.getChildren().add(pu);
//				projectUsers.remove(j);
//				j--;
//			}
//		}
//
//		for (int k = 0; k < leadUsers.size(); k++) {
//
//			leadUser = leadUsers.get(k);
//			for (int m = 0; m < projectUsers.size(); m++) {
//
//				pu = projectUsers.get(m);
//				if (pu.getSupervisorId() != null && pu.getSupervisorId().equals(leadUser.getUserId()) && pu.getProjectRoleId().equals(Constants.PROJECT_ROLE_ARTIST_NUM)) {
//
//					if (leadUser.getChildren() == null) {
//						leadUser.setChildren(new ArrayList<ProjectUserTo>());
//					}
//					leadUser.getChildren().add(pu);
//					projectUsers.remove(m);
//					m--;
//				}
//			}
//		}
//
//		return adUser;
//	}
	
	public List<ProjectUserTo> getProjectUserHierarchical(Integer projectId) throws Exception{
		List<ProjectUserTo> projectUsers = getProjectUserByProjectId(projectId);
		ProjectUserTo pu = null;
		ProjectUserTo adUser = null;
		ProjectUserTo leadUser = null;
		List<ProjectUserTo> leadUsers = new ArrayList<ProjectUserTo>();
		List<ProjectUserTo> adUsers = new ArrayList<ProjectUserTo>();
		
		for (int j = 0; j < projectUsers.size(); j++) {
			pu = projectUsers.get(j);
			if (pu.getProjectRoleId().equals(Constants.PROJECT_ROLE_AD_NUM) ||  pu.getProjectRoleId().equals(Constants.PROJECT_ROLE_LEAD_NUM)) {
				pu.setChildren(new ArrayList<ProjectUserTo>());
			}
			if (pu.getProjectRoleId().equals(Constants.PROJECT_ROLE_AD_NUM)) {

				adUsers.add(pu);
				projectUsers.remove(j);
				j--;
			}
		}
		for (int i = 0; i < adUsers.size(); i++) {
			adUser  = adUsers.get(i);
			for (int j = 0; j < projectUsers.size(); j++) {
				pu = projectUsers.get(j);
				if (pu.getSupervisorId() != null && pu.getSupervisorId().equals(adUser.getUserId()) && pu.getProjectRoleId().equals(Constants.PROJECT_ROLE_LEAD_NUM)) {
					if (adUser.getChildren() == null) {
						adUser.setChildren(new ArrayList<ProjectUserTo>());
					}
					leadUsers.add(pu);
					adUser.getChildren().add(pu);
					projectUsers.remove(j);
					j--;
				}
			}

			for (int k = 0; k < leadUsers.size(); k++) {

				leadUser = leadUsers.get(k);
				for (int m = 0; m < projectUsers.size(); m++) {

					pu = projectUsers.get(m);
					if (pu.getSupervisorId() != null && pu.getSupervisorId().equals(leadUser.getUserId()) && pu.getProjectRoleId().equals(Constants.PROJECT_ROLE_ARTIST_NUM)) {

						if (leadUser.getChildren() == null) {
							leadUser.setChildren(new ArrayList<ProjectUserTo>());
						}
						leadUser.getChildren().add(pu);
						projectUsers.remove(m);
						m--;
					}
				}
			}
		}


		return adUsers;
	}
	
	public List<ProjectUserTo> getLeadsHierarchical(Integer projectId, boolean getDefault) throws Exception{
		List<ProjectUserTo> projectUsers = getProjectUserByProjectId(projectId);
		List<ProjectUserTo> leadUsers = projectUserDao.getProjectUserByRoleId(projectId, Constants.PROJECT_ROLE_LEAD_NUM);
		ProjectUserTo pu = null;
		ProjectUserTo leadUser = null;		
		
		for (int i = 0; i < leadUsers.size(); i++) {
			leadUser = leadUsers.get(i);
			for (int j = 0; j < projectUsers.size(); j++) {
				pu = projectUsers.get(j);
				if (pu.getSupervisorId() != null && pu.getSupervisorId().equals(leadUser.getUserId()) && pu.getProjectRoleId().equals(Constants.PROJECT_ROLE_ARTIST_NUM)) {
					if (leadUser.getChildren() == null) {
						leadUser.setChildren(new ArrayList<ProjectUserTo>());
					}
					leadUser.getChildren().add(pu);
				}
			}
		}
		
		if (getDefault){
			leadUsers.add(0, ProjectUserTo.getDefault());
		}		

		return leadUsers;
	}	
	

	public List<ProjectUserTo> getProjectUserByProjectId(Integer projectId) throws Exception{
		List<ProjectUserTo> result = null;
		result = projectUserDao.getProjectUserByProjectId(projectId);
		return result;
	}
	
	public List<ProjectUserTo> getPMByProjectId(Integer projectId) throws Exception{
		return projectUserDao.getProjectUserByRoleId(projectId, Constants.PROJECT_ROLE_PM_NUM);
	}	
	
	public List<ProjectUserTo> getAdByProjectId(Integer projectId)throws Exception{
		return projectUserDao.getProjectUserByRoleId(projectId, Constants.PROJECT_ROLE_AD_NUM);
	}		
	
	public List<ProjectUserTo> getLeadByProjectId(Integer projectId)throws Exception{
		List<ProjectUserTo> result = null;
		result = projectUserDao.getDistinctProjectUserByRoleId(projectId, Constants.PROJECT_ROLE_LEAD_NUM);
		return result;
	}
	
	public List<ProjectUserTo> getArtistByProjectId(Integer projectId)throws Exception{
		List<ProjectUserTo> result = null;
		result = projectUserDao.getProjectUserByRoleId(projectId, Constants.PROJECT_ROLE_ARTIST_NUM);
		return result;
	}
	
	
	public List<ProjectUserTo> getQAUserByProjectId(Integer projectId)throws Exception{
		return projectUserDao.getProjectUserByRoleId(projectId, Constants.PROJECT_ROLE_QA_NUM);
	}
		
	public List<ProjectUserTo> getQaRole(int userId, int projectId) throws Exception{
		return projectUserDao.getQaRole(userId, projectId);
	}
	
	public List<ProjectUserTo> getLeadBySupervisorId(ProjectUserTo projectUser) throws Exception{
		return projectUserDao.getProjectUserBySupervisorId(projectUser.getProjectId(), projectUser.getUserId(), Constants.PROJECT_ROLE_LEAD_NUM);
	}
	
	//==============================
	//Create, Update, Delete
	//==============================	
	@Transactional
	public void createProjectUser(ProjectTo projectTo, UserTo userTo, Integer projectRoleId) throws Exception{
		ProjectUserTo projectUserTo = null;
		
		projectUserTo = new ProjectUserTo();
		projectUserTo.setProjectId(projectTo.getId());
		projectUserTo.setUserId(userTo.getId());
		projectUserTo.setProjectRoleId(projectRoleId);
		projectUserTo.setStatus(Constants.RESOURCE_PROJECT_STATUS_ASSIGNED);
		projectUserTo.setCreatedDate(projectTo.getCreatedDate());
		projectUserTo.setCreatedBy(projectTo.getCreatedBy());
		projectUserTo.setVersion(projectTo.getVersion());
		
		projectUserDao.create(projectUserTo);
	}
	
	//No transaction needed for this method
	public List<ProjectUserTo> createProjectUser(List<ProjectUserTo> projectUserList)throws Exception{
		ProjectUserTo projectUserTo = null;
		
		for(int i = 0; i < projectUserList.size(); i++){
			projectUserTo = projectUserList.get(i);
			projectUserTo = create(projectUserTo);
		}

		return projectUserList;
	}
	
	@Transactional
	public ProjectUserTo create(ProjectUserTo projectUserTo)throws Exception{
		projectUserTo = projectUserDao.create(projectUserTo);
		return projectUserTo;
	}	
	
	
	@Transactional
	public ProjectUserTo createAD(ProjectUserTo projectUserTo)throws Exception{
		//Add AD role in PORJECT_USER table
		projectUserTo = projectUserDao.create(projectUserTo);
		
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("projectId", projectUserTo.getProjectId());
		map.put("userId", projectUserTo.getUserId());
		List<ProjectMemberTo> projectMemberList = projectMemberDao.getProjectMember(map);
				
		if(projectMemberList == null || projectMemberList.size() == 0){			
			//Also add this AD in PROJECT_MEMBER table
			ProjectMemberTo projectMemberTO = new ProjectMemberTo();
			projectMemberTO.setUserId(projectUserTo.getUserId());
			projectMemberTO.setProjectId(projectUserTo.getProjectId());
			projectMemberTO.setStatus(Constants.RESOURCE_PROJECT_STATUS_ASSIGNED);
			projectMemberTO.setCreatedBy(projectUserTo.getCreatedBy());
			projectMemberTO.setCreatedDate(new Date());
			projectMemberTO.setVersion(0);
			projectMemberTO.setStartTime(projectUserTo.getStartDate());
			projectMemberTO.setEndTime(projectUserTo.getEndDate());
			projectMemberTO.setIsAD(projectUserTo.getIsAD());
			projectMemberTO.setIsQA(projectUserTo.getIsQA());
			projectMemberTO.setIsPM(projectUserTo.getIsPM());
			projectMemberDao.create(projectMemberTO);
		}		

		return projectUserTo;
	}	
	
	@Transactional
	public ProjectUserTo createQA(ProjectUserTo projectUserTo)throws Exception{
		//Add QA role in PORJECT_USER table
		projectUserTo = projectUserDao.create(projectUserTo);
		
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("projectId", projectUserTo.getProjectId());
		map.put("userId", projectUserTo.getUserId());
		List<ProjectMemberTo> projectMemberList = projectMemberDao.getProjectMember(map);
				
		if(projectMemberList == null || projectMemberList.size() == 0){			
			//Also add this QA in PROJECT_MEMBER table
			ProjectMemberTo projectMemberTO = new ProjectMemberTo();
			projectMemberTO.setUserId(projectUserTo.getUserId());
			projectMemberTO.setProjectId(projectUserTo.getProjectId());
			projectMemberTO.setStatus(Constants.RESOURCE_PROJECT_STATUS_ASSIGNED);
			projectMemberTO.setCreatedBy(projectUserTo.getCreatedBy());
			projectMemberTO.setCreatedDate(new Date());
			projectMemberTO.setVersion(0);
			projectMemberTO.setStartTime(projectUserTo.getStartDate());
			projectMemberTO.setEndTime(projectUserTo.getEndDate());
			projectMemberTO.setIsAD(projectUserTo.getIsAD());
			projectMemberTO.setIsQA(projectUserTo.getIsQA());
			projectMemberTO.setIsPM(projectUserTo.getIsPM());
			projectMemberDao.create(projectMemberTO);
		}		

		return projectUserTo;
	}		
	
	@Transactional
	public ProjectUserTo updateProjectUser(ProjectUserTo projectUserTo) throws Exception{
		projectUserDao.update(projectUserTo);
		return projectUserTo;
	}
	
	@Transactional
	public ProjectUserTo saveOrUpdate(ProjectUserTo projectUserTo)throws Exception{
		if (projectUserTo.getId() == 0) {
			projectUserTo = projectUserDao.create(projectUserTo);
		} else {			
			projectUserDao.update(projectUserTo);
		}
		return projectUserTo;
	}
	
	@Transactional
	public boolean updateProjectUserStatus(ProjectUserTo projectUserTo) throws Exception{
		projectUserDao.updateStatus(projectUserTo);
		return true;
	}
	
	@Transactional
	public boolean deleteProjectUser(ProjectUserTo projectUserTo)throws Exception{	
		projectUserDao.delete(projectUserTo.getId());			
		return true;
	}	
	
	@Transactional
	public boolean deleteAd(ProjectUserTo projectUserTo)throws Exception{		
		//Delete Project User
		projectUserDao.delete(projectUserTo.getId());			
		return true;
	}		
		
	public boolean deleteQAs(List<ProjectUserTo> list) throws Exception{		
		ProjectUserTo projectUserTo = null;
		
		for(int i = 0; i < list.size(); i++){
			projectUserTo = list.get(i);
			deleteQA(projectUserTo);
		}

		return true;
	}
	
	@Transactional
	public boolean deleteQA(ProjectUserTo projectUserTo) throws Exception{
		projectUserDao.delete(projectUserTo.getId());

		return true;
	}
	
	
	@Transactional
	public Boolean replaceAD(ProjectUserTo projectUserTo, UserTo userTo)throws Exception{
		//Update all leads' supervisor with new AD's id
		projectUserDao.updateSupervisorId(projectUserTo.getProjectId(), Constants.PROJECT_ROLE_LEAD_NUM, projectUserTo.getUserId(), userTo.getId());
		
		//Update current project user with new AD's id
		projectUserTo.setUserId(userTo.getId());
		projectUserDao.update(projectUserTo);
		
		//Check whether project member already exist for this user,
		//if not insert a new one
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("projectId", projectUserTo.getProjectId());
		map.put("userId", userTo.getId());
//		map.put("groupId", userTo.getUserGroupId());
		List<ProjectMemberTo> projectMemberList = projectMemberDao.getProjectMember(map);
				
		if(projectMemberList == null || projectMemberList.size() == 0){			
			ProjectMemberTo projectMemberTo = new ProjectMemberTo();
			projectMemberTo.setProjectId(projectUserTo.getProjectId());
			projectMemberTo.setUserId(userTo.getId());
			projectMemberTo.setStatus(Constants.RESOURCE_PROJECT_STATUS_ASSIGNED);
			projectMemberTo.setCreatedDate(new Date());			
			projectMemberTo.setCreatedBy(projectUserTo.getCreatedBy());
			projectMemberTo.setVersion(0);
			projectMemberDao.create(projectMemberTo);
		}
		
		return true;
	}
	
	@Transactional
	public Boolean replaceAD(ProjectUserTo projectUserTo, ProjectUserTo newProjectUser)throws Exception{
		//Update all leads' supervisor with new AD's id
		projectUserDao.updateSupervisorId(projectUserTo.getProjectId(), Constants.PROJECT_ROLE_LEAD_NUM, projectUserTo.getUserId(), newProjectUser.getUserId());
		
		//Update current project user with new AD's id
		projectUserTo.setUserId(newProjectUser.getUserId());
		projectUserDao.update(projectUserTo);
		
		return true;
	}
	
	@Transactional
	public Boolean replaceLead(ProjectUserTo oldLead, ProjectMemberTo newLead)throws Exception{
		//Update all artists' supervisor with new Lead's id
		projectUserDao.updateSupervisorId(oldLead.getProjectId(), Constants.PROJECT_ROLE_ARTIST_NUM, oldLead.getUserId(), newLead.getUserId());

		//Update oldLead's task with newLead as owner
		taskDao.updateTaskOwnerId(oldLead.getProjectId(), oldLead.getUserId(), newLead.getUserId());		
		
		//Update current project user with new AD's id
		oldLead.setUserId(newLead.getUserId());
		projectUserDao.update(oldLead);		
				
		return true;
	}			
	
	//==============================
	//Injected Variables
	//==============================		
	private ProjectUserDao		projectUserDao;
	private ProjectMemberDao	projectMemberDao;
	private TaskDao				taskDao;
	
	public void setProjectUserDao(ProjectUserDao projectUserDao){
		this.projectUserDao = projectUserDao;
	}

	public void setProjectMemberDao(ProjectMemberDao projectMemberDao){
		this.projectMemberDao = projectMemberDao;
	}
	
	public void setTaskDao (TaskDao taskDao){
		this.taskDao = taskDao;
	}	
}
