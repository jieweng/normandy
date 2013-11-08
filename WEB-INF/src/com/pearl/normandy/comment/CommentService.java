package com.pearl.normandy.comment;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;


public class CommentService {

	static Logger log = Logger.getLogger(CommentService.class.getName());
	
	public List<CommentTo> getCommentsByTaskId(Integer taskId)throws Exception{
		return commentDao.getCommentsByTaskId(taskId);
	}
	
	// ==============================
	// Create, Update, Delete
	// ==============================
	@Transactional
	public CommentTo createComment(CommentTo commentTo)throws Exception{
		return commentDao.create(commentTo);
	}
	
	// ==============================
	// Injected Variables
	// ==============================
	private CommentDao commentDao;

	public void setCommentDao(CommentDao commentDao) {
		this.commentDao = commentDao;
	}
}
