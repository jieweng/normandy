package com.pearl.normandy.comment;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.dao.DataAccessException;


public class CommentDao extends SqlMapClientDaoSupport{

	static Logger log = Logger.getLogger(CommentDao.class.getName());
	
	@SuppressWarnings("unchecked")
	public List<CommentTo> getCommentsByTaskId(Integer taskId) throws DataAccessException{		
		List<CommentTo> result = this.getSqlMapClientTemplate().queryForList("Comment.selectCommentsByTaskId", taskId);
		return result;
	}
	
	//==============================
	//Create, Update, Delete
	//==============================
	
	public CommentTo create(CommentTo commentTo) throws DataAccessException{
		
		Integer id = (Integer) this.getSqlMapClientTemplate().insert("Comment.insert", commentTo);
		commentTo.setId(id);
		return commentTo;
	}
}
