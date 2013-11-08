package com.pearl.normandy.processstep;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class ProcessStepDao extends SqlMapClientDaoSupport{
	static Logger log = Logger.getLogger(ProcessStepDao.class.getName());

	//==============================
	//Get methods
	//==============================	
	@SuppressWarnings("unchecked")
	public List<ProcessStepTo> getProcessStepByProductionProcessId(int productionProcessId) throws DataAccessException {

		List<ProcessStepTo> result = getSqlMapClientTemplate().queryForList("ProcessStep.selectProcessStepByProductionProcessId", productionProcessId);
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<ProcessStepTo> getProcessStepByProjectId(Integer projectId) throws DataAccessException{

		 List<ProcessStepTo> result = getSqlMapClientTemplate().queryForList("ProcessStep.selectProcessStepByProjectId", projectId);
		return result;
	}
	
	
	//==============================
	//Create, Update, Delete
	//==============================	
	public void saveOrUpdateProcessStep(ProcessStepTo processStepTo) throws DataAccessException{
		if(processStepTo.getId()==0){			
			create(processStepTo);
		}else{			
			update(processStepTo);
		}
	}
	
	public ProcessStepTo create(ProcessStepTo processStepTo) throws DataAccessException {
		Integer id = (Integer)getSqlMapClientTemplate().insert("ProcessStep.insert",processStepTo);
		processStepTo.setId(id);
		return processStepTo;
	}	
	
	public void update(ProcessStepTo processStepTo) throws DataAccessException {
		getSqlMapClientTemplate().update("ProcessStep.update",processStepTo);
	}
	
	public void delete(Integer id) throws DataAccessException {
		getSqlMapClientTemplate().update("ProcessStep.delete", id);
	}
	
	public void deleteByProcessId(Integer id) throws DataAccessException {
		getSqlMapClientTemplate().update("ProcessStep.deleteByProcessId", id);
	}		
}
