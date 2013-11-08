package com.pearl.normandy.event
{	    
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.pearl.normandy.vo.TaskVO;
	
	import mx.collections.ArrayCollection;    

    public class FeedbackEvent extends CairngormEvent
    {
		//=====================================================================================
		//                           Get
		//=====================================================================================
		public static const EVENT_GET_TASK_FEEDBACK : String 		= "getTaskFeedback";
				

		
		
		//=====================================================================================
		//                           Create/Update/Delete
		//=====================================================================================		
		public static const EVENT_SAVE_FEEDBACK : String			= "saveFeedback";
		
    	
		//Parameter
		public var task:TaskVO;	  
		public var category:String;		  	
		public var feedbacks:ArrayCollection;    			    			


        public function FeedbackEvent(event:String) {
                super(event);
        }

		public static function getTaskFeedback(task:TaskVO, category:String):FeedbackEvent{
			var event:FeedbackEvent = new FeedbackEvent(EVENT_GET_TASK_FEEDBACK);
			event.task = task;	
			event.category = category;
			return event;	
		}	
		
		public static function saveFeedback(feedbacks:ArrayCollection):FeedbackEvent{
			var event:FeedbackEvent = new FeedbackEvent(EVENT_SAVE_FEEDBACK);
			event.feedbacks = feedbacks;
			return event;	
		}				
    }	
}