package com.pearl.normandy.milestone;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class MilestoneDao extends SqlMapClientDaoSupport {

	// ==============================
	// Get methods
	// ==============================
	@SuppressWarnings("unchecked")
	public List<MilestoneTo> getMilestonesByProjectId(Integer projectId) throws DataAccessException {
		List<MilestoneTo> result = getSqlMapClientTemplate().queryForList(
				"Milestone.selectProjectIdMilestones", projectId);
		return result;
	}

	
	//==============================
	//Create, Update, Delete
	//==============================
	public void saveOrUpdateMilestone(List<MilestoneTo> list) throws DataAccessException{
		for (int i = 0; i < list.size(); i++) {
			MilestoneTo milestoneTo = (MilestoneTo) list.get(i);
			if (milestoneTo.getId() == 0 && milestoneTo.getMilestone()!=null) {
				getSqlMapClientTemplate().insert("Milestone.insert", milestoneTo);
			} else {
				getSqlMapClientTemplate().update("Milestone.update", milestoneTo);
			}
		}
	}
}
