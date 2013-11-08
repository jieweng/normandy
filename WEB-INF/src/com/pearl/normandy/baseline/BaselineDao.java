package com.pearl.normandy.baseline;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.pearl.normandy.project.ProjectTo;

public class BaselineDao extends SqlMapClientDaoSupport {
	
	//==============================
	//Get methods
	//==============================		
	private ProjectTo getBaselineProjectById(int projectId)throws DataAccessException{
		ProjectTo project = (ProjectTo) this.getSqlMapClientTemplate().queryForObject("BaselineProject.selectBaselineProjectById",projectId);
		return project;
	}

	// ==============================
	// Create, Update, Delete
	// ==============================
	public void createBaseline(int projectId,int revision) throws DataAccessException {
		Map<String, Integer> map = new HashMap<String, Integer>();
		this.getSqlMapClientTemplate().insert("BaselineProject.insertBaselineProject",projectId);
		map.put("id", projectId);
		map.put("revision", revision);
		this.getSqlMapClientTemplate().insert("BaselineMilestone.insertBaselineMilestone",map);
		this.getSqlMapClientTemplate().insert("BaselineProductionProcess.insertBaselineProductionProcess",map);
		this.getSqlMapClientTemplate().insert("BaselineProcessStep.insertBaselineProcessStep",map);
		this.getSqlMapClientTemplate().insert("BaselineTask.insertBaselineTask",map);
		this.getSqlMapClientTemplate().insert("BaselineSubtask.insertBaselineSubtask",map);
	}
	
	public void deleteBaseline(int projectId)throws DataAccessException{
		ProjectTo project = this.getBaselineProjectById(projectId);
		if(project!=null){
			this.getSqlMapClientTemplate().delete("BaselineProject.deleteBaselineProject",projectId);
			this.getSqlMapClientTemplate().delete("BaselineMilestone.deleteBaselineMilestone",project.getId());
			this.getSqlMapClientTemplate().delete("BaselineProcessStep.deleteBaselineProcessStep",project.getId());
			this.getSqlMapClientTemplate().delete("BaselineProductionProcess.deleteBaselineProductionProcess",project.getId());
			this.getSqlMapClientTemplate().delete("BaselineSubtask.deleteBaselineSubtask",project.getId());
			this.getSqlMapClientTemplate().delete("BaselineTask.deleteBaselineTask",project.getId());
		}
	}
}