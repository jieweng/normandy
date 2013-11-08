package com.pearl.normandy.checkitem;

import java.util.HashMap;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class CheckItemDao extends SqlMapClientDaoSupport {
	// ==============================
	// Get methods
	// ==============================
	@SuppressWarnings("unchecked")
	public List<CheckItemTo> getCheckItemsPending(Integer userId)throws DataAccessException{
		List<CheckItemTo> result = this.getSqlMapClientTemplate().queryForList("CheckItem.selectCheckItemsPending",userId);
		return result;
	}	
	
	@SuppressWarnings("unchecked")
	public List<CheckItemTo> getCheckItemsReviewed(Integer userId)throws DataAccessException{
		List<CheckItemTo> result = this.getSqlMapClientTemplate().queryForList("CheckItem.selectCheckItemsReviewed",userId);
		return result;
	}	
	
	@SuppressWarnings("unchecked")
	public List<CheckItemTo> getCheckItemsComplete(Integer userId)throws DataAccessException{
		List<CheckItemTo> result = this.getSqlMapClientTemplate().queryForList("CheckItem.selectCheckItemsComplete",userId);
		return result;
	}		
	
	@SuppressWarnings("unchecked")
	public List<CheckItemTo> getCheckItemsByActivityId(Integer activityId)throws DataAccessException{
		return getCheckItemsByActivityId(activityId, null);
	}	
	
	@SuppressWarnings("unchecked")
	public List<CheckItemTo> getCheckItemsByActivityId(Integer activityId, String status)throws DataAccessException{
		HashMap param = new HashMap();
		param.put("activityId", activityId);
		param.put("status", status);
		List<CheckItemTo> result = this.getSqlMapClientTemplate().queryForList("CheckItem.selectCheckItemsByActivityId", param);
		return result;
	}		
	
	// ==============================
	// Create, Update, Delete
	// ==============================
	public CheckItemTo create(CheckItemTo checkItem) throws DataAccessException{
		Integer id = (Integer) this.getSqlMapClientTemplate().insert("CheckItem.insert",checkItem);
		checkItem.setId(id);
		return checkItem;
	}
	
	public void update(CheckItemTo checkItem) throws DataAccessException{
		this.getSqlMapClientTemplate().update("CheckItem.update",checkItem);
	}
	
	public void updateComment(CheckItemTo checkItem) throws DataAccessException{
		this.getSqlMapClientTemplate().update("CheckItem.updateCommentById",checkItem);
	}	
}