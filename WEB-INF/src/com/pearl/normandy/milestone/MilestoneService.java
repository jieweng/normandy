package com.pearl.normandy.milestone;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

public class MilestoneService {

	static Logger log = Logger.getLogger(MilestoneService.class.getName());

	// ==============================
	// Get methods
	// ==============================
	public List<MilestoneTo> getMilestonesByProjectId(Integer projectId,
		boolean getDefault) throws Exception{
		List<MilestoneTo> result = null;
		
		result = milestoneDao.getMilestonesByProjectId(projectId);
		if (getDefault){
			result.add(0, MilestoneTo.getDefault());
		}

		return result;
	}

	// ==============================
	// Create, Update, Delete
	// ==============================
	@Transactional
	public void saveOrUpdateMilestone(List<MilestoneTo> list) throws Exception{
		milestoneDao.saveOrUpdateMilestone(list);
	}

	//==============================
	//Injected Variables
	//==============================
	private MilestoneDao milestoneDao;

	public void setMilestoneDao(MilestoneDao milestoneDao) {
		this.milestoneDao = milestoneDao;
	}
}