package com.pearl.normandy.feedback;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.pearl.normandy.customer.CustomerService;
import com.pearl.normandy.feedbackreference.FeedbackReferenceDao;
import com.pearl.normandy.feedbackreference.FeedbackReferenceService;

public class FeedbackService {
	static Logger log = Logger.getLogger(CustomerService.class.getName());
	// ==============================
	// Get methods
	// ==============================
	public List<FeedbackTo> getFeedbackByCheckItemId(Integer checkItemId)throws Exception{
		return feedbackDao.getFeedbackByCheckItemId(checkItemId);
	}
	
	public List<FeedbackTo> getFeedbackByTaskId(String taskId, String category)throws Exception{
		return feedbackDao.getFeedbackByTaskId(taskId, category);
	}
	
	
	// ==============================
	// Create,Delete,Update
	// ==============================
	public FeedbackTo create(FeedbackTo feedback)throws Exception{
		feedbackDao.create(feedback);
		return feedback;
	}

	@Transactional
	public void saveFeedback(List<FeedbackTo> list)throws Exception{
		for (FeedbackTo feedback : list) {					
			if(feedback.getId() == 0){
				feedbackDao.create(feedback);
			}else{
				feedbackDao.update(feedback);
			}
			
			feedbackReferenceService.saveOrUpdateFeedbackReference(feedback.getFeedbackReference(), feedback);
		}
	}
	
	@Transactional
	public boolean deleteFeedbackById(int id)throws Exception{
		feedbackDao.delete(id);
		feedbackReferenceDao.deleteFeedbackReferenceByFeedbackId(id);

		return true;
	}
	
	//==============================
	//Injected Variables
	//==============================
	private FeedbackDao feedbackDao;
	private FeedbackReferenceDao feedbackReferenceDao;
	private FeedbackReferenceService feedbackReferenceService;
	
	public void setFeedbackDao(FeedbackDao feedbackDao) {
		this.feedbackDao = feedbackDao;
	}

	public void setFeedbackReferenceDao(FeedbackReferenceDao feedbackReferenceDao) {
		this.feedbackReferenceDao = feedbackReferenceDao;
	}

	public void setFeedbackReferenceService(FeedbackReferenceService feedbackReferenceService) {
		this.feedbackReferenceService = feedbackReferenceService;
	}
}