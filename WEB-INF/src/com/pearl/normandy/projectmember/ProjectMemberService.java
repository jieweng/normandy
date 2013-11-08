package com.pearl.normandy.projectmember;

import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.pearl.normandy.project.ProjectTo;
import com.pearl.normandy.projectuser.ProjectUserDao;
import com.pearl.normandy.projectuser.ProjectUserTo;
import com.pearl.normandy.user.UserTo;
import com.pearl.normandy.utils.Constants;

import flex.messaging.MessageException;

public class ProjectMemberService {
	static Logger log = Logger.getLogger(ProjectMemberService.class.getName());
	
	//==============================
	//Get methods
	//==============================
	public List<ProjectMemberTo> getProjectMembersByProjectId(Integer projectId)throws Exception{
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("projectId", projectId);
//		map.put("groupId", groupId);			
		return projectMemberDao.getProjectMember(map);
	}
	
	public ProjectMemberTo getProjectMemberByUserId(Integer userId, Integer projectId)throws Exception{
		ProjectMemberTo result = null;
		for(ProjectMemberTo pm:getProjectMembersByProjectId(projectId)){
			if(pm.getUserId().equals(userId)){
				result = pm;
				break;
			}
		}
		return result;
	}
	
	public List<ProjectMemberTo> getProjectMembersByUserId(Integer userId)throws Exception{
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("userId", userId);
		return projectMemberDao.getProjectMember(map);
	}
	
	public List<ProjectMemberTo> getMailProjectMembersByProjectId(Integer projectId)throws Exception{
		List<ProjectMemberTo> result = new ArrayList<ProjectMemberTo>();
		for(ProjectMemberTo pm:getProjectMembersByProjectId(projectId)){
			if(pm.getPrivMail() != null && pm.getPrivMail().equals("Y")){
				result.add(pm);
			}
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<ProjectMemberTo> getReleasedProjectMembersByProjectId(Integer projectId)throws Exception{
		Map map = new HashMap();
		map.put("projectId", projectId);
		map.put("status", Constants.RESOURCE_PROJECT_STATUS_RELEASED);			
		return projectMemberDao.getProjectMember(map);
	}	
	
	@SuppressWarnings("unchecked")
	public List<ProjectMemberTo> getAssignedProjectMembersByProjectId(Integer projectId)throws Exception{
		Map map = new HashMap();
		map.put("projectId", projectId);
		map.put("status", Constants.RESOURCE_PROJECT_STATUS_ASSIGNED);			
		return projectMemberDao.getProjectMember(map);
	}		
	
	@SuppressWarnings("unchecked")
	public List<ProjectMemberTo> getProjectMemberByProjectIdAndUserId(int projectId, int userId) throws Exception{
		Map map = new HashMap();
		map.put("projectId", projectId);
		map.put("userId", userId);
		map.put("status", Constants.RESOURCE_PROJECT_STATUS_ASSIGNED);
		return projectMemberDao.getProjectMember(map);
	}
	
	//==============================
	//Create, Update, Delete
	//==============================
	@Transactional
	public ProjectMemberTo createProjectMember(ProjectTo project, UserTo user)throws Exception{
		ProjectMemberTo projectMemberTo =  new ProjectMemberTo();
		
		projectMemberTo.setProjectId(project.getId());
		projectMemberTo.setUserId(user.getId());
		projectMemberTo.setStatus(Constants.RESOURCE_PROJECT_STATUS_ASSIGNED);
		projectMemberTo.setCreatedDate(new Date());
		projectMemberTo.setCreatedBy(user.getUserName());
		projectMemberTo.setVersion(0);
		projectMemberTo.setPrivTask1("All");
		projectMemberTo.setPrivTask2("All");
		projectMemberTo.setPrivResource("All");
		projectMemberTo.setPrivPrivilege("Y");
		projectMemberTo.setPrivMail("Y");
		
		return projectMemberDao.create(projectMemberTo);
	}	
	
	@Transactional
	public List<ProjectMemberTo> saveProjectMembers(List<ProjectMemberTo> projectMembers)throws Exception{
		ProjectMemberTo projectMemberTo = null;
		ProjectMemberTo returnMember = null;

		for(int i = 0; i < projectMembers.size(); i++){
			projectMemberTo = (ProjectMemberTo) projectMembers.get(i);
			returnMember = projectMemberDao.create(projectMemberTo);
			projectMemberTo.setId(returnMember.getId());
		}

		return projectMembers;
	}
	
	public void updateProjectMember(ProjectMemberTo projectMember) throws Exception{
		projectMemberDao.update(projectMember);
	}
	
	@Transactional
	public void updateProjectMemberStatus(ProjectMemberTo projectMember)throws Exception{
		projectMemberDao.updateStatus(projectMember);
	}
	
	public void deleteProjectMembers(List<ProjectMemberTo> projectMembers, Integer projectId) throws Exception{
		MessageException msg = new MessageException();
		Map<String, List<ProjectUserTo>> extendedData = new HashMap<String, List<ProjectUserTo>>();
		List<ProjectUserTo> projectUsers = new ArrayList<ProjectUserTo>();
		ProjectMemberTo projectMemberTo = null;

		for(int i = 0; i < projectMembers.size(); i++){
			projectMemberTo = projectMembers.get(i);
			deleteProjectMember(projectMemberTo.getUserId(), projectId, projectUsers);
		}
		extendedData.put("ProjectUser", projectUsers);
		msg.setExtendedData(extendedData);
		throw msg;
	}
	
	@Transactional
	public boolean deleteProjectMember(Integer userId, Integer projectId, List<ProjectUserTo> projectUsers)throws Exception{
		boolean result = false;
		
		List<ProjectUserTo> projectUserList = projectUserDao.getProjectUserByUserId(userId, projectId);
		if (projectUserList.size() > 0) {
			projectUsers.addAll(projectUserList);
			return false;
		}
		result = projectMemberDao.deleteProjectMember(userId, projectId);

		return result;
	}

	//==============================
	//Injected Variables
	//==============================	
	
	private ProjectMemberDao projectMemberDao;
	private ProjectUserDao projectUserDao;
	
	public void setProjectMemberDao(ProjectMemberDao projectMemberDao) {
		this.projectMemberDao = projectMemberDao;
	}

	public void setProjectUserDao(ProjectUserDao projectUserDao) {
		this.projectUserDao = projectUserDao;
	}
}
