package com.pearl.normandy.feedback;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class FeedbackDao extends SqlMapClientDaoSupport {
	// ==============================
	// Get methods
	// ==============================
	@SuppressWarnings("unchecked")
	public List<FeedbackTo> getFeedbackByCheckItemId(Integer checkItemId)throws DataAccessException{
		List<FeedbackTo> result = this.getSqlMapClientTemplate().queryForList("Feedback.selectFeedbackByCheckItemId",checkItemId);
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<FeedbackTo> getFeedbackByTaskId(String taskId, String category)throws DataAccessException{
		Map<String, String> param = new HashMap<String, String>();
		param.put("taskId", taskId);
		param.put("category", category);
		List<FeedbackTo> result = this.getSqlMapClientTemplate().queryForList("Feedback.selectFeedbackByTaskId",param);
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<FeedbackTo> getAllFeedback(Integer projectId, String milestone)throws DataAccessException{
		Map param = new HashMap();
		param.put("projectId", projectId);
		param.put("milestone", milestone);
		return (List<FeedbackTo>) this.getSqlMapClientTemplate().queryForList("Feedback.selectFeedback",param);
	}
	
	@SuppressWarnings("unchecked")
	public List<FeedbackTo> getOpenFeedback(Integer projectId, String milestone)throws DataAccessException{
		Map param = new HashMap();
		param.put("projectId", projectId);
		param.put("milestone", milestone);		
		param.put("status", "UNMODIFIED");
		return (List<FeedbackTo>) this.getSqlMapClientTemplate().queryForList("Feedback.selectFeedback",param);
	}	

	@SuppressWarnings("unchecked")
	public List<FeedbackTo> getClosedFeedback(Integer projectId, String milestone)throws DataAccessException{
		Map param = new HashMap();
		param.put("projectId", projectId);
		param.put("milestone", milestone);		
		param.put("status", "MODIFIED");
		return (List<FeedbackTo>) this.getSqlMapClientTemplate().queryForList("Feedback.selectFeedback",param);
	}
	
	@SuppressWarnings("unchecked")
	public List<FeedbackTo> getFeedbackByRootCause(Integer projectId, String milestone, String type)throws DataAccessException{
		Map param = new HashMap();
		param.put("projectId", projectId);
		param.put("milestone", milestone);
		param.put("type", type);
		return (List<FeedbackTo>) this.getSqlMapClientTemplate().queryForList("Feedback.selectFeedback",param);
	}
	
	@SuppressWarnings("unchecked")
	public List<FeedbackTo> getFeedbackByPriority(Integer projectId, String milestone, String priority)throws DataAccessException{
		Map param = new HashMap();
		param.put("projectId", projectId);
		param.put("milestone", milestone);
		param.put("priority", priority);
		return (List<FeedbackTo>) this.getSqlMapClientTemplate().queryForList("Feedback.selectFeedback",param);
	}	
	
	
	// ==============================
	// Create,Delete,Update
	// ==============================
	public FeedbackTo create(FeedbackTo feedback)throws DataAccessException{
		Integer id = (Integer) this.getSqlMapClientTemplate().insert("Feedback.insert",feedback);
		feedback.setId(id);
		return feedback;
	}
	
	public void update(FeedbackTo feedback)throws DataAccessException{
		this.getSqlMapClientTemplate().update("Feedback.update",feedback);
	}	
	
	public void delete(int id)throws DataAccessException{
		this.getSqlMapClientTemplate().delete("Feedback.delete",id);
	}
}
