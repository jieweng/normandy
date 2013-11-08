package com.pearl.normandy.project;

import java.io.File;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.pearl.normandy.actionlog.ActionLogService;
import com.pearl.normandy.customer.CustomerDao;
import com.pearl.normandy.customer.CustomerTo;
import com.pearl.normandy.projectmember.ProjectMemberService;
import com.pearl.normandy.projectuser.ProjectUserService;
import com.pearl.normandy.user.UserDao;
import com.pearl.normandy.user.UserTo;
import com.pearl.normandy.userPrivilege.UserPrivilege;
import com.pearl.normandy.userPrivilege.UserPrivilegeDao;
import com.pearl.normandy.utils.Constants;
import com.pearl.normandy.utils.NormandyConfiguration;

public class ProjectService {

	static Logger log = Logger.getLogger(UserDao.class.getName());
	static NormandyConfiguration config = NormandyConfiguration.getInstance();
	static String ENTITY = Constants.ENTITY_PROJECT;
	
	
	//==============================
	//Get methods
	//==============================	
	public List<ProjectTo> getAllProject() throws Exception {
		return projectDao.getAllProjects();
	}
	
	public List<ProjectTo> getProjectsByYear(String year) throws Exception {
		return projectDao.getProjectsByYear(year);
	}


	public List<ProjectTo> getProjectByUser(UserTo userTo) throws Exception {
		List<ProjectTo> result = null;

		//APM and Inspector role will see all the projects
//		if ((userTo.getManager() != null && userTo.getManager().equals(Constants.BOOLEAN_YES)) 
//				|| userTo.getUserGroupId() == Constants.USERGROUP_INSPECTOR_NUM) {
		UserPrivilege up = userPrivilegeDao.getUserPrivilegesByUserId(userTo.getId());
		if (up == null || up.getProject().equals("None")) {
			result = projectDao.getProjectsByUserId(userTo.getId());
		} else {
			result = projectDao.getAllProjects();
		}
		
		return result;
	}
	
	
	public ProjectTo getProjectById(Integer projectId) throws Exception {
		return projectDao.getProjectById(projectId);
	}


	
	//==============================
	//Create, Update, Delete
	//==============================
	@SuppressWarnings("static-access")
	@Transactional
	public ProjectTo createProject(ProjectTo projectTo, String customer, Integer projectRoleId, UserTo creator) throws Exception {
		if(customer != null){
			CustomerTo customerTo = customerDao.getCustomerByName(customer);
			
			if(customerTo == null){
				customerTo = new CustomerTo();
				customerTo.setCustomerName(customer);
				customerTo.setVersion(0);
				customerTo.setCreatedBy(creator.getUserName());
				projectTo.setCustomerId(customerDao.create(customerTo).getId());				
			}
			else{
				projectTo.setCustomerId(customerTo.getId());
			}			
		}
		
		//Create Project
		ProjectTo newProject = projectDao.createProject(projectTo);
		
		//Create project member entry
		projectMemberService.createProjectMember(projectTo, creator);
		
		//Assign PM role to creator
		projectUserService.createProjectUser(projectTo, creator, projectRoleId);
		
		//Create Project Folder in file system		
		createProjectFolder(projectTo.getId());
		
		//Log Action
		actionLogService.create(newProject.getId(), this.ENTITY, newProject.getId().toString(), newProject.getProjectName(), null,
				Constants.ACTION_CREATE, null, null, creator.getName());
		return newProject;
	}	
	
	@SuppressWarnings("static-access")
	@Transactional
	public void updateProject(ProjectTo projectTo, String customer, UserTo updator)throws Exception{
		if(customer != null){
			CustomerTo customerTo = customerDao.getCustomerByName(customer);
			
			if(customerTo == null){
				customerTo = new CustomerTo();
				customerTo.setCustomerName(customer);
				customerTo.setVersion(0);
				customerTo.setCreatedBy(updator.getUserName());
				projectTo.setCustomerId(customerDao.create(customerTo).getId());				
			}
			else{
				projectTo.setCustomerId(customerTo.getId());
			}		
		}
		
		//Get old value
		ProjectTo oldProjectTo = projectDao.getProjectById(projectTo.getId());		
		Project oldValue = new Project();
		Project newValue = new Project();
		BeanUtils.copyProperties(oldValue, oldProjectTo);
		BeanUtils.copyProperties(newValue, projectTo);
		
		//Update project
		projectDao.updateProject(projectTo);
		
		//Log Action
		actionLogService.createObject(projectTo.getId(), this.ENTITY, projectTo.getId().toString(), projectTo.getProjectName(),
				Constants.ACTION_UPDATE, oldValue, newValue, updator.getName());							
	}
	
	@Transactional
	public void updateProjectCheckList(Integer projectId, String checkList)throws Exception{		
		//Update project
		projectDao.updateProjectCheckList(projectId, checkList);							
	}	
	

	//==============================
	//Private Methods
	//==============================		
	private void createProjectFolder(Integer projectId){
		File file = new File(config.REFERENCE_FOLDER+projectId);
		if(file.exists()){
			return;
		}else{
			file.mkdir();
		}
	}
	
	//==============================
	//Injected Variables
	//==============================	
	private ProjectDao projectDao;
	private CustomerDao customerDao;
	private ProjectMemberService projectMemberService;
	private ProjectUserService projectUserService;
	private ActionLogService actionLogService;
	private UserPrivilegeDao userPrivilegeDao;

	public UserPrivilegeDao getUserPrivilegeDao() {
		return userPrivilegeDao;
	}

	public void setUserPrivilegeDao(UserPrivilegeDao userPrivilegeDao) {
		this.userPrivilegeDao = userPrivilegeDao;
	}

	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}
	
	public void setCustomerDao(CustomerDao customerDao){
		this.customerDao = customerDao;
	}
	
	public void setProjectMemberService(ProjectMemberService projectMemberService) {
		this.projectMemberService = projectMemberService;
	}
	

	public void setProjectUserService(ProjectUserService projectUserService) {
		this.projectUserService = projectUserService;
	}

	public void setActionLogService(ActionLogService actionLogService){
		this.actionLogService = actionLogService;
	}
	
	
	
	
	
	
	
	
	//=============================Ready to delete ========================
/*	public void saveOrUpdateProjectWithListProcess(ProjectTo projectTo)throws Exception {
		ProductionProcessTo processTo = null;

		List<ProductionProcessTo> listProcess = projectTo.getProcesss();
		for (int i = 0; i < listProcess.size(); i++) {
			processTo = listProcess.get(i);
			productionProcessService.saveOrUpdateProcessWithProcessSteps(processTo);
		}
	}*/		
	
	
	
/*	public ProjectTo getProject(UserTo user, Integer projectId, String role) throws Exception{
		return getProject(user, projectId,true, true, true, true, role);
	}

	
	public ProjectTo getProject(UserTo user, Integer projectId, String action, String role) throws Exception {
		ProjectTo project = null;

		if (action != null) {
			if (action.equals(Constants.ACTION_GET_MILESTONE)) {
				project = getProject(user, projectId, true, false, false, false, role);
			}
			
			if (action.equals(Constants.ACTION_GET_PROCESS)){
				project = getProject(user, projectId, false, true, false, false, role);				
			}

			if (action.equals(Constants.ACTION_GET_ACTIVITY)) {
				project = getProject(user, projectId, false, false, true, false, role);
			}
			
			if (action.equals(Constants.ACTION_GET_LEAD)) {
				project = getProject(user, projectId, false, false, false, true, role);
			}
		}
		return project;
	}

	
	public ProjectTo getProject(UserTo user, Integer projectId, boolean getMilestone, 
			boolean getProcess, boolean getActivity, boolean getLead, String role) throws Exception {
		ProjectTo project = new ProjectTo();
		
		if (getMilestone) {
			List<String> milestones = taskService.getMilestone(projectId, true);
			project.setMilestones(milestones);
		}
		
		if(getProcess) {
			List<ProductionProcessTo> processes = productionProcessService.getProcessByProjectId(projectId, true);
			project.setProcesss(processes);
		}

		if (getActivity) {
			List<UserTo> users = userService
					.getUsersByRole(user, projectId, role);
			project.setUsers(users);

			if (users.size() > 0) {
				List<ActivityTo> activities = activityDao.getActivitiesByUsers(users);
				project.setActivities(activities);
			}
		}
		
		if (getLead) {
			List<ProjectUserTo> leads = projectUserService.getLeadsHierarchical(projectId, true);
			project.setLeads(leads);
		}
		return project;
	}*/	
	
	
/*	public Project getAllUsersAndActivity(UserTo userTo)throws Exception{
		ProjectTo project = new ProjectTo();

		List<UserTo> users = userService.getUsersNotAPM(userTo);
		project.setUsers(users);
		if (users.size() > 0) {
			List<ActivityTo> activities = activityDao.getActivitiesByUsers(users);
			project.setActivities(activities);
		}
		
		return project;
	}	*/
	
	
	
	
/*	public List<ProjectTo> getProjectsForApm(Integer userId) throws Exception {
		return projectDao.getGroupProjectsByUserId(userId);
	}

	public List<ProjectTo> getProjectsForAD(Integer userId) throws Exception {
		return projectDao.getProjectsByRole(userId, Constants.PROJECT_ROLE_AD);
	}

	public List<ProjectTo> getProjectsForLead(Integer userId) throws Exception {
		return projectDao.getProjectsByRole(userId, Constants.PROJECT_ROLE_LEAD);
	}
	
	public List<ProjectTo> getProjectsForQA(Integer userId) throws Exception {
		return projectDao.getProjectsByRole(userId, Constants.PROJECT_ROLE_QA);
	}
	
	public List<ProjectTo> getProjectsForProductionUser(String roleName, Integer userId) throws Exception {
		List<ProjectTo> result = null;
		
		if (roleName.equals(Constants.PROJECT_ROLE_APM)) {
			result = this.getProjectsForApm(userId);
		} else if (roleName.equals(Constants.PROJECT_ROLE_AD)) {
			result = this.getProjectsForAD(userId);
		} else if (roleName.equals(Constants.PROJECT_ROLE_LEAD)) {
			result = this.getProjectsForLead(userId);
		} else if(roleName.equals(Constants.PROJECT_ROLE_QA)){
			result = this.getProjectsForQA(userId);
		}
			
		return result;
	}	*/	
}