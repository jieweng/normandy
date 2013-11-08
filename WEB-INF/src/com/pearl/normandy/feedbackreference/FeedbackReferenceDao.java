package com.pearl.normandy.feedbackreference;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class FeedbackReferenceDao extends SqlMapClientDaoSupport {

	// ==============================
	// Get Methods
	// ==============================
	public FeedbackReferenceTo getLastFeedbackReferenceByFeedbackId(int feedbackId)throws DataAccessException{
		FeedbackReferenceTo result = (FeedbackReferenceTo) this.getSqlMapClientTemplate().queryForObject("FeedbackReference.selectLastFeedbackReferenceByFeedbackId", feedbackId);
		return result;
	}
	
	// ==============================
	// Create,Delete,Update
	// ==============================
	public void create(FeedbackReferenceTo reference)throws DataAccessException {
		this.getSqlMapClientTemplate().insert("FeedbackReference.insert", reference);
	}

	public void update(FeedbackReferenceTo reference)throws DataAccessException{
		this.getSqlMapClientTemplate().update("FeedbackReference.update", reference);
	}
	
	public void delete(int id)throws DataAccessException{
		Map<String,Integer> map = new HashMap<String, Integer>();
		map.put("id", id);
		this.getSqlMapClientTemplate().delete("FeedbackReference.delete",map);
	}
	
	public void deleteFeedbackReferenceByFeedbackId(int feedbackId)throws DataAccessException{
		Map<String,Integer> map = new HashMap<String, Integer>();
		map.put("feedbackId", feedbackId);
		this.getSqlMapClientTemplate().delete("FeedbackReference.delete",map);
	}
}
