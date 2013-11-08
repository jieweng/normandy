package com.pearl.normandy.event
{	    
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.pearl.normandy.vo.TaskVO;
	import com.pearl.normandy.vo.UserVO;    

    public class TaskDetailEvent extends CairngormEvent
    {
		//=====================================================================================
		//                           Get
		//=====================================================================================
		public static const EVENT_GET_TASK_DETAIL : String 		= "getTaskDetail";
				

		
		
		//=====================================================================================
		//                           Create/Update/Delete
		//=====================================================================================		
		public static const EVENT_UPDATE_TASK_DETAIL : String	= "updateTaskDetail";
    	
		//Parameter
		public var task:TaskVO;
		public var user:UserVO;	      			    			

        public function TaskDetailEvent(event:String) {
                super(event);
        }


		public static function getTaskDetailEvent(task:TaskVO):TaskDetailEvent{
			var event:TaskDetailEvent = new TaskDetailEvent(EVENT_GET_TASK_DETAIL);
			event.task = task;	
			return event;	
		}			
		
		public static function updateTaskDetailEvent(task:TaskVO, user:UserVO):TaskDetailEvent{
			var event:TaskDetailEvent = new TaskDetailEvent(EVENT_UPDATE_TASK_DETAIL);
			event.task = task;	
			event.user = user;
			return event;			
		}				
    }	
}