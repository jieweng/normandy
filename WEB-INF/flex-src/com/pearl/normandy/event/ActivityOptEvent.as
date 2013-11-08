package com.pearl.normandy.event
{	
    import com.adobe.cairngorm.control.CairngormEvent;
    import com.pearl.normandy.vo.ActivityVO;
    import com.pearl.normandy.vo.TaskVO;
    import com.pearl.normandy.vo.UserVO;
    
    import mx.collections.ArrayCollection;

    public class ActivityOptEvent extends CairngormEvent
    {    	
		public static const CANCEL:String 		= "cancelActivity";
		public static const DELETE:String 		= "deleteActivity";    	
		//=====================================================================================
		//                           		Get
		//=====================================================================================		
		public static const EVENT_GET_TASK_ACTIVITY:String		   = "getTaskActivity";
		public static const EVENT_GET_TASK_DELETED_ACTIVITY:String = "getTaskDeletedActivity";			
    	
    	
		//=====================================================================================
		//                           Create/Update/Delete
		//=====================================================================================		
		public static const EVENT_CREATE_ACTIVITY : String = "createActivity";
		public static const EVENT_UPDATE_ACTIVITIES:String = "updateActivities";
			    	
    	 		
    	public var task:TaskVO
    	public var activity:ActivityVO;
    	public var activities:ArrayCollection;
    	public var user:UserVO
    			
        public function ActivityOptEvent(type:String) {
                super(type);
        }

		public static function getTaskActivityEvent(task:TaskVO):ActivityOptEvent{
			var event:ActivityOptEvent = new ActivityOptEvent(EVENT_GET_TASK_ACTIVITY);
			event.task = task;
			return event;
		}
		
		public static function getTaskDeletedActivityEvent(task:TaskVO):ActivityOptEvent{
			var event:ActivityOptEvent = new ActivityOptEvent(EVENT_GET_TASK_DELETED_ACTIVITY);
			event.task = task;
			return event;
		}		

		public static function createActivityEvent(activity:ActivityVO, user:UserVO):ActivityOptEvent{
			var event:ActivityOptEvent = new ActivityOptEvent(EVENT_CREATE_ACTIVITY);
			event.activity = activity;
			event.user = user;
			return event;			
		}	
		
		public static function updateActivitiesEvent(activities:ArrayCollection, user:UserVO):ActivityOptEvent{
			var event:ActivityOptEvent = new ActivityOptEvent(EVENT_UPDATE_ACTIVITIES);
			event.activities = activities;
			event.user = user;
			return event;			
		}		        
    }	
}