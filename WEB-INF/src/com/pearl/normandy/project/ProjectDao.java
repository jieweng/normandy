package com.pearl.normandy.project;

import java.util.HashMap;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class ProjectDao extends SqlMapClientDaoSupport {

	
	//==============================
	//Get methods
	//==============================		
	@SuppressWarnings("unchecked")
	public List<ProjectTo> getAllProjects() throws DataAccessException {
		List<ProjectTo> result = getSqlMapClientTemplate().queryForList("Project.selectAllProjects");
		return result;
	}
	
	
	//Ready to delete, 2010/1/4, Frank
/*	@SuppressWarnings("unchecked")
	public List<ProjectTo> getGroupProjectsByUserId(Integer userId) throws DataAccessException {
		List<ProjectTo> result = getSqlMapClientTemplate().queryForList("Project.selectGroupProjectsByUserId", userId);
		return result;
	}*/	

	@SuppressWarnings("unchecked")
	public List<ProjectTo> getProjectsByUserId(int userId) throws DataAccessException {
		List<ProjectTo> result = getSqlMapClientTemplate().queryForList("Project.selectProjectsByUserId", userId);
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<ProjectTo> getProjectsByYear(String year) throws DataAccessException {
		List<ProjectTo> result = getSqlMapClientTemplate().queryForList("Project.selectProjectsByYear", year);
		return result;
	}

	//Ready to delete, 2010/1/4, Frank
/*	@SuppressWarnings("unchecked")
	public List<ProjectTo> getProjectsByRole(Integer userId, String roleName) throws DataAccessException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId.toString());
		map.put("roleName", roleName);		
		List<ProjectTo> result = getSqlMapClientTemplate().queryForList("Project.selectProjectsByRole", map);
		return result;
	}	*/
	
	//Ready to delete, 2010/1/4, Frank
/*	@SuppressWarnings("unchecked")
	public ProjectTo getProjectBySubtaskId(String subtaskId)throws DataAccessException{
		ProjectTo project = (ProjectTo) this.getSqlMapClientTemplate().queryForObject("Project.selectProjectBySubtaskId",subtaskId);
		return project;
	}*/
	
	public ProjectTo getProjectById(Integer projectId)throws DataAccessException{
		ProjectTo project = (ProjectTo) this.getSqlMapClientTemplate().queryForObject("Project.selectProjectById", projectId);
		return project;
	}
	
	//==============================
	//Create, Update, Delete
	//==============================	
	public ProjectTo createProject(ProjectTo projectTo) throws DataAccessException {
		Integer id = (Integer)this.getSqlMapClientTemplate().insert("Project.insert", projectTo);
		projectTo.setId(id);
		return projectTo;
	}

	public void updateProject(ProjectTo projectTo) throws DataAccessException {
		getSqlMapClientTemplate().update("Project.update",projectTo);
	}

	@SuppressWarnings("unchecked")
	public void updateProjectCheckList(Integer projectId, String checkList)throws DataAccessException{
		HashMap param = new HashMap();
		param.put("projectId", projectId);
		param.put("checkList", checkList);
		this.getSqlMapClientTemplate().update("Project.updateProjectCheckList", param);
	}	
	
	public void updateProjectRevision(Integer projectId)throws DataAccessException{
		this.getSqlMapClientTemplate().update("Project.updateProjectRevision", projectId);
	}
	
	public void saveOrUpdateProject(ProjectTo projectTo)throws DataAccessException {
		if(projectTo.getId()==0){
			createProject(projectTo);
		}else{
			updateProject(projectTo);
		}
	}	
}