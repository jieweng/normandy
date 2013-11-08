package com.pearl.normandy.delegates
{
	import com.adobe.cairngorm.business.Responder;
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.pearl.normandy.vo.TaskVO;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.AsyncToken;
	
	public class FeedbackDelegate
	{
		private var responder:Responder;
		private var service:Object;
		
		public function FeedbackDelegate(responder:Responder){
			this.service = ServiceLocator.getInstance().getService( "feedbackService" );
			this.responder = responder;
		}
		
        public function getTaskFeedback(task:TaskVO, category:String): void
        {
            var token : AsyncToken = service.getFeedbackByTaskId(task.id, category);
            token.resultHandler = responder.onResult;
            token.faultHandler = responder.onFault;            
        }   
        
        		
        public function saveFeedback(feedbacks:ArrayCollection): void
        {
            var token : AsyncToken = service.saveFeedback(feedbacks);
            token.resultHandler = responder.onResult;
            token.faultHandler = responder.onFault;            
        }             
	}
}