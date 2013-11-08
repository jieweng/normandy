package com.pearl.normandy.commands.feedback
{
	import com.adobe.cairngorm.business.Responder;
	import com.adobe.cairngorm.commands.Command;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.pearl.normandy.delegates.FeedbackDelegate;
	import com.pearl.normandy.event.FeedbackEvent;
	import com.pearl.normandy.utils.NormandyModel;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.events.ResultEvent;
	
	public class GetTaskFeedbackCommand implements Command, Responder
	{
		private var model:NormandyModel = NormandyModel.getInstance();
		
        public function execute( event : CairngormEvent ) : void
        {           
            var delegate : FeedbackDelegate = new FeedbackDelegate( this );   
            var e : FeedbackEvent = FeedbackEvent( event );  
            delegate.getTaskFeedback(e.task, e.category);          
        }
           
        public function onResult( event : * = null ) : void
        {                 
        	var e:ResultEvent = ResultEvent(event);       
            model.LOCAL_TASK_FEEDBACKS = e.result as ArrayCollection;
        }
                
        public function onFault( event : * = null ) : void
        {
        }

	}
}