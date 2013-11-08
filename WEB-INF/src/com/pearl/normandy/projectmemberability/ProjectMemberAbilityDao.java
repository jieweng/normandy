package com.pearl.normandy.projectmemberability;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.pearl.normandy.project.ProjectTo;
import com.pearl.normandy.report.ReportEfficiencyQuality;
import com.pearl.normandy.usergroup.UserGroupTo;

public class ProjectMemberAbilityDao extends SqlMapClientDaoSupport {
	static Logger log = Logger.getLogger(ProjectMemberAbilityDao.class.getName());
	
	@SuppressWarnings("unchecked")
	public List<ProjectMemberAbilityTo> selectProjectMemberAbility(ProjectTo project, int year, int month) throws DataAccessException{
		Map param = new HashMap();
		param.put("project", project);
		param.put("year", year);
		param.put("month", month);
		
		return this.getSqlMapClientTemplate().queryForList("ProjectMemberAbility.selectProjectMemberAbility", param);
	}
	
	@SuppressWarnings("unchecked")
	public List<ProjectMemberAbilityTo> selectProjectMemberAbilityByAct(int projectId, int year, int month) throws DataAccessException{
		Map param = new HashMap();
		param.put("projectId", projectId);
		param.put("year", year);
		param.put("month", month);
		
		return getSqlMapClientTemplate().queryForList("ProjectMemberAbility.selectProjectMemberActivityByAct", param);
	}
	
	@SuppressWarnings("unchecked")
	public List<Performance> getPerformance(Date start, Date end, ProjectTo project, UserGroupTo group) throws DataAccessException{
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("end", end);
		param.put("project", project);
		param.put("group", group);
		List<Performance> result = this.getSqlMapClientTemplate().queryForList("ProjectMemberAbility.selectPerformance", param);
		return result;
	}
	
	public void update(ProjectMemberAbilityTo pma) throws DataAccessException{
		getSqlMapClientTemplate().update("ProjectMemberAbility.update", pma);
	}
	
	public int create(ProjectMemberAbilityTo pma) throws DataAccessException{
		return (Integer)getSqlMapClientTemplate().insert("ProjectMemberAbility.insert", pma);
	}
}
