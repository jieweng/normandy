package com.pearl.normandy.projectmember;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class ProjectMemberDao extends SqlMapClientDaoSupport{
	static Logger log = Logger.getLogger(ProjectMemberDao.class.getName());
	
	//==============================
	//Get methods
	//==============================
	@SuppressWarnings("unchecked")
	public List<ProjectMemberTo> getAllProject()throws DataAccessException{
		return getSqlMapClientTemplate().queryForList("ProjectMember.selectAllProject");
	}
	
	@SuppressWarnings("unchecked")
	public List<ProjectMemberTo> getProjectMember(Map map) throws DataAccessException {
		List<ProjectMemberTo> result = this.getSqlMapClientTemplate().queryForList("ProjectMember.selectProjectMember", map);
		return result;
	}
	
	//==============================
	//Create, Update, Delete
	//==============================	
	public ProjectMemberTo create(ProjectMemberTo projectMemberTo) throws DataAccessException {
		Integer id = (Integer) this.getSqlMapClientTemplate().insert("ProjectMember.insert",projectMemberTo);
		projectMemberTo.setId(id);
		return projectMemberTo;
	}	
	
	public void update(ProjectMemberTo projectMemberTo) throws DataAccessException {
		this.getSqlMapClientTemplate().update("ProjectMember.update",projectMemberTo);
	}
	
	public void updateStatus(ProjectMemberTo projectMemberTo) throws DataAccessException {
		this.getSqlMapClientTemplate().update("ProjectMember.updateStatus",projectMemberTo);
	}
	
	public void delete(int id) throws DataAccessException{
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("id", id);
		this.getSqlMapClientTemplate().delete("ProjectMember.delete", map);
	}
	
	public boolean deleteProjectMember(int userId, int projectId) throws DataAccessException{
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("userId", userId);
		map.put("projectId", projectId);
		this.getSqlMapClientTemplate().delete("ProjectMember.delete", map);
		return true;
	}
}
