package com.pearl.normandy.listofvalue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class ListOfValueDao extends SqlMapClientDaoSupport {

	// ==============================
	// Get methods
	// ==============================
	@SuppressWarnings("unchecked")
	public List<ListOfValueTo> getListOfValueByType(String listType) throws DataAccessException {
		Map<String,String> map=new HashMap<String, String>();
		map.put("listType", listType);
		List<ListOfValueTo> result = getSqlMapClientTemplate().queryForList("ListOfValue.selectListOfValue", map);
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<ListOfValueTo> getListOfValueByKey(String listKey) throws DataAccessException {
		Map<String,String> map=new HashMap<String, String>();
		map.put("listKey", listKey);
		List<ListOfValueTo> result = getSqlMapClientTemplate().queryForList("ListOfValue.selectListOfValue", map);
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public ListOfValueTo getListOfValueById(Integer id) throws DataAccessException {
		Map<String,String> map=new HashMap<String, String>();
		map.put("id", id.toString());
		ListOfValueTo result = (ListOfValueTo)getSqlMapClientTemplate().queryForObject("ListOfValue.selectListOfValue", map);
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<ListOfValueTo> getTaskCategorieseByProjectId(Integer projectId)throws DataAccessException{
		List<ListOfValueTo> result = getSqlMapClientTemplate().queryForList("ListOfValue.selectTaskCategoriesByProjectId", projectId);
		return result;
	}
	
	//======================= Ready to delete ====================	
	@SuppressWarnings("unchecked")
	public String getExTaskCategoriesByProjectId(Integer projectId)throws DataAccessException{
		String result = (String) getSqlMapClientTemplate().queryForObject("ListOfValue.selectExTaskCategoriesByProjectId",projectId);
		return result;
	}	
}
