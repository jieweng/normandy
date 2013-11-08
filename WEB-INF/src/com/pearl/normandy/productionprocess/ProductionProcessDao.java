package com.pearl.normandy.productionprocess;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class ProductionProcessDao extends SqlMapClientDaoSupport{
	
	static Logger log = Logger.getLogger(ProductionProcessDao.class.getName());
	
	//==============================
	//Get methods
	//==============================	
	@SuppressWarnings("unchecked")
	public List<ProductionProcessTo> getProcessByProjectId(Integer projectId) throws DataAccessException{
		
		List<ProductionProcessTo> result = null;
		result = getSqlMapClientTemplate().queryForList(
			"ProductionProcess.selectProcessByProjectId", projectId);
		return result;
	}			

	
	//==============================
	//Create, Update, Delete
	//==============================	
	public ProductionProcessTo create(ProductionProcessTo processTo) throws DataAccessException{		
		Integer id = (Integer)getSqlMapClientTemplate().insert("ProductionProcess.insert", processTo);
		processTo.setId(id);
		return processTo;
	}
	
	public void update(ProductionProcessTo processTo) throws DataAccessException{
		
		getSqlMapClientTemplate().update("ProductionProcess.update",processTo);
	}
	
	public ProductionProcess save(ProductionProcessTo processTo) throws DataAccessException{		
		ProductionProcess result = null;
		if(processTo.getId() == 0){			
			result = create(processTo);
		}else{			
			update(processTo);
		}
		
		return result;
	}
	
	public void delete(Integer processId) throws DataAccessException{		
		this.getSqlMapClientTemplate().delete("ProductionProcess.delete", processId);	
	}
	
	
	
	
	
	
	
	
	//==========================Ready to delete ======================
	@SuppressWarnings("unchecked")
	public List<ProductionProcessTo> getProcessByProjectAndUserId(Integer projectId, Integer userId, Integer projectRoleId) throws DataAccessException{		
		List<ProductionProcessTo> result = null;
		HashMap<String, Integer> param = new HashMap<String, Integer>();
		param.put("projectId", projectId);
		param.put("userId", userId);
		param.put("projectRoleId", projectRoleId);
		
		result = getSqlMapClientTemplate().queryForList(
			"ProductionProcess.selectProcessByProjectAndUserId", param);
		return result;
	}		
	
	
	@SuppressWarnings("unchecked")
	public List<ProductionProcessTo> getProductionProcessByProjectIdAndTaskCategoryId(Map<String, Integer> map) throws DataAccessException {
		List<ProductionProcessTo> result = getSqlMapClientTemplate().queryForList(
				"ProductionProcess.selectProductionProcessByProjectIdAndTaskCategoryId", map);
		return result;
	}	
}
