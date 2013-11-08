package com.pearl.normandy.event
{	    
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.pearl.normandy.vo.ProjectVO;
	import com.pearl.normandy.vo.TaskVO;
	import com.pearl.normandy.vo.UserVO;    

    public class TaskEvent extends CairngormEvent
    {
		//=====================================================================================
		//                           Get
		//=====================================================================================
		public static const EVENT_GET_PROJECT_MILESTONE : String 		= "getProjectMilestone";
		public static const EVENT_GET_PROJECT_TASK_CATEGORY : String 	= "getProjectTaskCategory";
		public static const EVENT_GET_PROJECT_TASK_GROUP:String			= "getProjectTaskGroup";	
		public static const EVENT_GET_TASK_TASK_GROUP:String			= "getTaskTaskGroup";										
		public static const EVENT_GET_USER_TASK : String 				= "getUserTask";
		public static const EVENT_GET_PROJECT_TASK : String 			= "getProjectTask";	
		public static const EVENT_GET_LEAD_TASK : String 				= "getLeadTask";						

		
		
		//=====================================================================================
		//                           Create/Update/Delete
		//=====================================================================================		
		public static const EVENT_CREATE_TASK : String 					= "createTask";
		public static const EVENT_ADD_SUBTASK : String 					= "addSubtask";			
		public static const EVENT_UPDATE_TASK : String 					= "updateTask";						    	
		public static const EVENT_CANCEL_TASK : String 					= "cancelTask";	
		public static const EVENT_COPY_TASK : String   					= "copyTask";	
		public static const EVENT_CREATE_TASK_FROM_MPP:String			= "createTaskFromMpp";		    	
    	
		//Parameter
		public var project:ProjectVO;
		public var userId:int;				
		
		public var task:TaskVO;	  
		public var field:String;		  	
		public var getDefault:Boolean;
		public var num:int;    			
		public var copyDetail:Boolean;
		public var user:UserVO;    			
    	public var mppFileName:String; 			    			
    	public var item:Object;
    			
        public function TaskEvent(event:String) {
                super(event);
        }


		public static function getProjectMilestoneEvent(project:ProjectVO, getDefault:Boolean):TaskEvent{
			var event:TaskEvent = new TaskEvent(EVENT_GET_PROJECT_MILESTONE);
			event.project = project;	
			event.getDefault = getDefault;
			return event;	
		}
		
		public static function getProjectTaskCategoryEvent(project:ProjectVO, getDefault:Boolean):TaskEvent{
			var event:TaskEvent = new TaskEvent(EVENT_GET_PROJECT_TASK_CATEGORY);
			event.project = project;	
			event.getDefault = getDefault;
			return event;
		}		
		
		public static function getProjectTaskGroupEvent(project:ProjectVO):TaskEvent{
			var event:TaskEvent = new TaskEvent(EVENT_GET_PROJECT_TASK_GROUP);
			event.project = project;	
			return event;
		}	
		
		public static function getTaskTaskGroupEvent(project:ProjectVO, task:TaskVO):TaskEvent{
			var event:TaskEvent = new TaskEvent(EVENT_GET_TASK_TASK_GROUP);
			event.project = project;	
			event.task = task;
			return event;
		}				

		public static function getUserTaskEvent(project:ProjectVO, userId:int):TaskEvent{
			var event:TaskEvent = new TaskEvent(EVENT_GET_USER_TASK);
			event.project = project;
			event.userId = userId;		
			return event;	
		}
		
		public static function getProjectTaskEvent(project:ProjectVO):TaskEvent{
			var event:TaskEvent = new TaskEvent(EVENT_GET_PROJECT_TASK);
			event.project = project;
			return event;	
		}
		
		public static function getLeadTaskEvent(project:ProjectVO, userId:int):TaskEvent{
			var event:TaskEvent = new TaskEvent(EVENT_GET_LEAD_TASK);
			event.project = project;
			event.userId = userId;		
			return event;	
		}				
		
		public static function createTaskEvent(task:TaskVO):TaskEvent{
			var event:TaskEvent = new TaskEvent(EVENT_CREATE_TASK);
			event.task = task;
			return event;			
		}	
		
		public static function createTaskFromMppEvent(fileName:String, project:ProjectVO, user:UserVO):TaskEvent{
			var event:TaskEvent = new TaskEvent(EVENT_CREATE_TASK_FROM_MPP);
			event.mppFileName = fileName;
			event.project 	  = project;
			event.user 	  	  = user; 
			return event;
		}
		
		public static function addSubtaskEvent(task:TaskVO):TaskEvent{
			var event:TaskEvent = new TaskEvent(EVENT_ADD_SUBTASK);
			event.task = task;
			return event;			
		}							
		
		public static function updateTaskEvent(task:TaskVO, field:String):TaskEvent{
			var event:TaskEvent = new TaskEvent(EVENT_UPDATE_TASK);
			event.task = task;
			event.field = field;
			return event;	
		}	
		
		public static function cancelTaskEvent(task:TaskVO):TaskEvent{
			var event:TaskEvent = new TaskEvent(EVENT_CANCEL_TASK);
			event.task = task;
			return event;			
		}
		
		public static function copyTaskEvent(task:TaskVO, num:int, copyDetail:Boolean, user:UserVO):TaskEvent{
			var event:TaskEvent = new TaskEvent(EVENT_COPY_TASK);
			event.task = task;
			event.num = num;
			event.copyDetail = copyDetail;
			event.user = user;
			return event;			
		}										
    }	
}