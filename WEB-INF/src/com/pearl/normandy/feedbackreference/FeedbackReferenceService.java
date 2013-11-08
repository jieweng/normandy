package com.pearl.normandy.feedbackreference;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.pearl.normandy.feedback.FeedbackTo;
import com.pearl.normandy.utils.NormandyConfiguration;

public class FeedbackReferenceService {
	
	static Logger log = Logger.getLogger(FeedbackReferenceService.class.getName());
	static NormandyConfiguration config = NormandyConfiguration.getInstance();
	
	// ==============================
	// Create,Delete,Update
	// ==============================
	public void saveOrUpdateFeedbackReference(List<FeedbackReferenceTo> list, FeedbackTo feedback)throws Exception{
		int i=0;
		FeedbackReferenceTo feedbackReference = feedbackReferenceDao.getLastFeedbackReferenceByFeedbackId(feedback.getId());
		if(feedbackReference!=null){
			StringBuffer lastImageNum = new StringBuffer(feedbackReference.getUrl());
			i=Integer.parseInt(lastImageNum.substring(lastImageNum.lastIndexOf(".")-3, lastImageNum.lastIndexOf(".")));
		}
		for (FeedbackReferenceTo reference : list) {
			if(reference.getFeedbackId()==0){
				if(reference.getUrl().indexOf("-")==-1){
					i+=1;
					StringBuffer imageNum = new StringBuffer();
					String zero = "000";
					imageNum.append(zero, 0, 3 - String.valueOf(i).length());
					imageNum.append(i);

					String url = this.moveFile(reference.getUrl(), feedback ,imageNum.toString());
					reference.setUrl(url);					
				}else
					reference.setId(0);
				reference.setFeedbackId(feedback.getId());
				feedbackReferenceDao.create(reference);
			}else
				feedbackReferenceDao.update(reference);
		}
	}
	
	public void deleteFeedbackReferenceById(int id)throws Exception{
		feedbackReferenceDao.delete(id);
	}
	
	// ==============================
	// Private methods
	// ==============================
	private String moveFile(String name, FeedbackTo feedback, String imageNum) throws Exception {
		String suffix = name.substring(name.indexOf('.'));
		String projectKey = feedback.getProjectKey(); 
		String url = feedback.getTaskId()+ "-" + feedback.getId() + "-" + imageNum+suffix;
			
		File path 		= new File(config.REFERENCE_FOLDER + projectKey + "/" + "Feedback");
		File srcFile 	= new File(config.REFERENCE_TEMP_FOLDER + name);			
		File destFile 	= new File(path + "/" + url);
		
		if(!path.exists()){
			path.mkdir();
		}
		
		if(destFile.exists() && srcFile.exists()){
			destFile.delete();
		}
		FileUtils.copyFile(srcFile, destFile);

		return projectKey+"/"+"Feedback"+"/"+url;
	}
	
	//==============================
	//Injected Variables
	//==============================
	private FeedbackReferenceDao feedbackReferenceDao;

	public void setFeedbackReferenceDao(FeedbackReferenceDao feedbackReferenceDao) {
		this.feedbackReferenceDao = feedbackReferenceDao;
	}
}
