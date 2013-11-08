package com.pearl.normandy.delegates
{
	import com.adobe.cairngorm.business.Responder;
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.pearl.normandy.vo.TaskVO;
	import com.pearl.normandy.vo.UserVO;
	
	import mx.rpc.AsyncToken;
	
	public class TaskCategoryDelegate
	{
		private var responder:Responder;
		private var service:Object;
		
		public function TaskCategoryDelegate(responder:Responder){
			this.service = ServiceLocator.getInstance().getService( "taskCategoryService" );
			this.responder = responder;
		}
		
        public function getTaskCategory(projectId:int, getDefault:Boolean): void
        {
            var token : AsyncToken = service.getTaskCategory(projectId, getDefault);
            token.resultHandler = responder.onResult;
            token.faultHandler = responder.onFault;            
        }   		                    
	}
}