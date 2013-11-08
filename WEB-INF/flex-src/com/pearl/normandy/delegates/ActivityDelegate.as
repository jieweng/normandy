package com.pearl.normandy.delegates
{
	import com.adobe.cairngorm.business.Responder;
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.pearl.normandy.vo.ActivityVO;
	import com.pearl.normandy.vo.TaskVO;
	import com.pearl.normandy.vo.UserVO;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.AsyncToken;
	
	public class ActivityDelegate
	{
		private var responder:Responder;
		private var service:Object;
		
		public function ActivityDelegate(responder:Responder){
			this.service = ServiceLocator.getInstance().getService( "activityService" );
			this.responder = responder;
		}
		
		public function getTaskActivity(task:TaskVO):void{
            var token : AsyncToken = service.getActivitiesByTaskId(task.id);
            token.resultHandler = responder.onResult;
            token.faultHandler = responder.onFault;			
		}
		
		public function getTaskDeletedActivity(task:TaskVO):void{
            var token : AsyncToken = service.getDeletedActivitiesByTaskId(task.id);
            token.resultHandler = responder.onResult;
            token.faultHandler = responder.onFault;			
		}		
		
        public function createActivity(activity:ActivityVO, user:UserVO): void
        {
            var token : AsyncToken = service.createActivity(activity, user);
            token.resultHandler = responder.onResult;
            token.faultHandler = responder.onFault;            
        }   	
        	    
        public function updateActivities(activities:ArrayCollection, user:UserVO): void
        {
            var token : AsyncToken = service.updateActivities(activities, user);
            token.resultHandler = responder.onResult;
            token.faultHandler = responder.onFault;            
        }                           
	}
}