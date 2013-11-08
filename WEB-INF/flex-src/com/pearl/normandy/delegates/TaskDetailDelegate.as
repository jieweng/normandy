package com.pearl.normandy.delegates
{
	import com.adobe.cairngorm.business.Responder;
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.pearl.normandy.vo.TaskVO;
	import com.pearl.normandy.vo.UserVO;
	
	import mx.rpc.AsyncToken;
	
	public class TaskDetailDelegate
	{
		private var responder:Responder;
		private var service:Object;
		
		public function TaskDetailDelegate(responder:Responder){
			this.service = ServiceLocator.getInstance().getService( "taskDetailService" );
			this.responder = responder;
		}
		
        public function getTaskDetail(task:TaskVO): void
        {
            var token : AsyncToken = service.getTaskDetailByTaskId(task.id);
            token.resultHandler = responder.onResult;
            token.faultHandler = responder.onFault;            
        }   		
                
        public function updateTaskDetail(task:TaskVO, user:UserVO): void{
            var token : AsyncToken = service.updateTaskDetail(task, user);
            token.resultHandler = responder.onResult;
            token.faultHandler = responder.onFault;        	
        }     
	}
}