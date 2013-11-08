package com.pearl.normandy.projectuser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.dao.DataAccessException;

import com.pearl.normandy.utils.Constants;


public class ProjectUserDao extends SqlMapClientDaoSupport {
	static Logger log = Logger.getLogger(ProjectUserDao.class.getName());


	//==============================
	//Get methods
	//==============================	
	@SuppressWarnings("unchecked")
	public List<String> getDistinctProjectUserRole(Integer userId) throws DataAccessException {	
		List<String> result = this.getSqlMapClientTemplate().queryForList("ProjectUser.selectDistinctProjectUserRoleByUserId", userId);
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<ProjectUserTo> getProjectUserRole(Integer projectId, Integer userId) throws DataAccessException{
		Map<String, Integer> param = new HashMap<String, Integer>();
		param.put("projectId", projectId);
		param.put("userId", userId);
		List<ProjectUserTo> result = this.getSqlMapClientTemplate().queryForList("ProjectUser.selectProjectUserRole", param);
		return result;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<ProjectUserTo> getProjectUserByUserId(Integer userId, Integer projectId) throws DataAccessException {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("userId", userId);
		map.put("projectId", projectId);
		List result = (List) this.getSqlMapClientTemplate().queryForList("ProjectUser.selectProjectUser", map);
		return result;
	}


	@SuppressWarnings("unchecked")
	public List<ProjectUserTo> getProjectUserByProjectId(Integer projectId) throws DataAccessException{
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("projectId", projectId);
		List<ProjectUserTo> result = this.getSqlMapClientTemplate().queryForList("ProjectUser.selectProjectUser", map);
		return result;
	}

	
/*	@SuppressWarnings("unchecked")
	public List<ProjectUserTo> getAdByProjectId(Integer projectId, Integer roleId, String type) throws DataAccessException{
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("projectRoleId", roleId);
		map.put("projectId", projectId);
		map.put("type", type);
		List<ProjectUserTo> result = this.getSqlMapClientTemplate().queryForList("ProjectUser.selectProjectUser", map);		
		return result;
	}*/
	
/*	@SuppressWarnings("unchecked")
	public List<ProjectUserTo> getProjectUserByRoleIdAndType(Integer projectId, Integer roleId, Integer type) throws DataAccessException{		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("projectId", projectId);
		map.put("projectRoleId", roleId);
		map.put("type", type);
		List<ProjectUserTo> result = this.getSqlMapClientTemplate().queryForList("ProjectUser.selectProjectUser", map);
		return result;
	}	*/

	@SuppressWarnings("unchecked")
	public List<ProjectUserTo> getProjectUserByRoleId(Integer projectId, Integer roleId) throws DataAccessException{
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("projectRoleId", roleId);
		map.put("projectId", projectId);
		List<ProjectUserTo> result = this.getSqlMapClientTemplate().queryForList("ProjectUser.selectProjectUser", map);
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<ProjectUserTo> getDistinctProjectUserByRoleId(Integer projectId, Integer roleId) throws DataAccessException{
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("projectRoleId", roleId);
		map.put("projectId", projectId);
		List<ProjectUserTo> result = this.getSqlMapClientTemplate().queryForList("ProjectUser.selectDistinctProjectUser", map);
		return result;
	}	
	
	@SuppressWarnings("unchecked")
	public List<ProjectUserTo> getProjectUserByRoleName(Integer projectId, String roleName) throws DataAccessException{
		Map<String, String> map = new HashMap<String, String>();
		map.put("projectId", projectId.toString());
		map.put("projectRoleName", roleName);		
		List<ProjectUserTo> result = this.getSqlMapClientTemplate().queryForList("ProjectUser.selectProjectUser", map);
		return result;
	}
	
/*	@SuppressWarnings("unchecked")
	public List<ProjectUserTo> getQaProjectUsers(Integer projectId, Integer type , Integer roleId) throws DataAccessException{		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("projectId", projectId);
		map.put("projectRoleId", roleId);
		map.put("type", type);
		List<ProjectUserTo> result = this.getSqlMapClientTemplate().queryForList("ProjectUser.selectProjectUser", map);
		return result;
	}*/
	
	@SuppressWarnings("unchecked")
	public List<ProjectUserTo> getProjectUsersByGroup(Integer groupId) throws DataAccessException{		
		List<ProjectUserTo> result = this.getSqlMapClientTemplate().queryForList("ProjectUser.selectProjectUserByGroup", groupId);
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<ProjectUserTo> getQaRole(int userId, int projectId) throws DataAccessException{		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("userId", userId);
		map.put("projectRoleId", Constants.PROJECT_ROLE_QA_NUM);
		map.put("projectId", projectId);
		List<ProjectUserTo> result = this.getSqlMapClientTemplate().queryForList("ProjectUser.selectProjectUser", map);
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<ProjectUserTo> getProjectUserBySupervisorId(Integer projectId, Integer supervisorId, Integer projectRoleId) throws DataAccessException{		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("projectId", projectId);
		map.put("supervisorId", supervisorId);
		map.put("projectRoleId", projectRoleId);
		List<ProjectUserTo> result = this.getSqlMapClientTemplate().queryForList("ProjectUser.selectProjectUser", map);
		return result;
	}	
	
	//==============================
	//Create, Update, Delete
	//==============================	
	public ProjectUserTo create(ProjectUserTo projectUserTo) throws DataAccessException {
		projectUserTo.setStatus(Constants.RESOURCE_PROJECT_STATUS_ASSIGNED);
		Integer id = (Integer)this.getSqlMapClientTemplate().insert("ProjectUser.insert", projectUserTo);
		projectUserTo.setId(id);
		return projectUserTo;
	}	

	public void update(ProjectUserTo projectUserTo) throws DataAccessException {
		this.getSqlMapClientTemplate().update("ProjectUser.update", projectUserTo);
	}			
	
	public void updateStatus(ProjectUserTo projectUserTo) throws DataAccessException {
		this.getSqlMapClientTemplate().update("ProjectUser.updateStatus", projectUserTo);
	}		
	
	public void delete(Integer id) throws DataAccessException {
		this.getSqlMapClientTemplate().delete("ProjectUser.delete", id);
	}
	
	public void updateSupervisorId(Integer projectId, Integer projectRoleId, Integer supervisorId, Integer newSupervisorId) throws DataAccessException {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("projectId", projectId);
		map.put("projectRoleId", projectRoleId);
		map.put("supervisorId",  supervisorId);		
		map.put("newSupervisorId", newSupervisorId);
		this.getSqlMapClientTemplate().update("ProjectUser.updateSupervisorId", map);		
	}
}
