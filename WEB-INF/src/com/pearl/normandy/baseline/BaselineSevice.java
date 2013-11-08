package com.pearl.normandy.baseline;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.pearl.normandy.project.ProjectDao;
import com.pearl.normandy.project.ProjectTo;

public class BaselineSevice {

	static Logger log = Logger.getLogger(BaselineSevice.class.getName());
	
	// ==============================
	// Create, Update, Delete
	// ==============================
	@Transactional
	public boolean createBaseline(int projectId) throws Exception {
		boolean result = false;
		try {
			projectDao.updateProjectRevision(projectId);
			ProjectTo project = projectDao.getProjectById(projectId);

			baselineDao.deleteBaseline(projectId);
			baselineDao.createBaseline(projectId,project.getBaselineRevision());
			
			result = true;
		} catch (Exception e) {
			log.error("Error in createBaseLine", e);
			throw e;
		}
		return result;
	}

	// ==============================
	// Injected Variables
	// ==============================
	private ProjectDao projectDao;
	private BaselineDao baselineDao;

	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}

	public void setBaselineDao(BaselineDao baselineDao) {
		this.baselineDao = baselineDao;
	}

}