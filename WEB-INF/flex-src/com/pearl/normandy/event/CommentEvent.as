package com.pearl.normandy.event
{
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.pearl.normandy.vo.CommentVO;
	import com.pearl.normandy.vo.TaskVO;
	
	public class CommentEvent extends CairngormEvent
	{
		//=====================================================================================
		//                           Get
		//=====================================================================================
		public static const EVENT_GET_TASK_COMMENT : String 		= "getTaskComment";
		
		
		//=====================================================================================
		//                           Create/Update/Delete
		//=====================================================================================				
		public static const	EVENT_CREATE_COMMENT:String				= "createComment";
		
		
		public var task:TaskVO;
		public var comment:CommentVO
		
		
        public function CommentEvent(event:String) {
                super(event);
        }
        
		public static function getTaskCommentEvent(task:TaskVO):CommentEvent{
			var event:CommentEvent = new CommentEvent(EVENT_GET_TASK_COMMENT);
			event.task = task;	
			return event;	
		}
		
		public static function createCommentEvent(comment:CommentVO):CommentEvent{
			var event:CommentEvent = new CommentEvent(EVENT_CREATE_COMMENT);
			event.comment = comment;	
			return event;	
		}   		        
	}
}  