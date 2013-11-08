package com.pearl.normandy.projectTimeValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.pearl.normandy.project.ProjectTo;

public class ProjectTimeValueDao extends SqlMapClientDaoSupport {
	
	@SuppressWarnings("unchecked")
	public List<ProjectTimeValue> getProjectTimeValues(ProjectTo project) throws DataAccessException{
		Map param = new HashMap();
		param.put("projectId", project.getId());
		return getSqlMapClientTemplate().queryForList("ProjectTimeValue.selectByProject", param);
	}
	
	public int createProjectTimeValue(ProjectTimeValue projectTimeValue) throws DataAccessException{
		return (Integer)getSqlMapClientTemplate().insert("ProjectTimeValue.insert", projectTimeValue);
	}
	
	public void updateProjectTimeValue(ProjectTimeValue projectTimeValue) throws DataAccessException{
		getSqlMapClientTemplate().update("ProjectTimeValue.update", projectTimeValue);
	}
	
	public ProjectTimeValue deleteProjectTimeValue(ProjectTimeValue projectTimeValue) throws DataAccessException{
		getSqlMapClientTemplate().update("ProjectTimeValue.delete", projectTimeValue);
		return projectTimeValue;
	}

}
