package com.pearl.normandy.delegates
{
	import com.adobe.cairngorm.business.Responder;
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.pearl.normandy.vo.CommentVO;
	import com.pearl.normandy.vo.TaskVO;
	
	import mx.rpc.AsyncToken;
	
	public class CommentDelegate
	{
		private var responder:Responder;
		private var service:Object;
		
		public function CommentDelegate(responder:Responder){
			this.service = ServiceLocator.getInstance().getService( "commentService" );
			this.responder = responder;
		}
		
        public function getTaskComment(task:TaskVO): void
        {
            var token : AsyncToken = service.getCommentsByTaskId(task.id);
            token.resultHandler = responder.onResult;
            token.faultHandler = responder.onFault;            
        }   	
        
        public function createComment(comment:CommentVO):void{
            var token : AsyncToken = service.createComment(comment);
            token.resultHandler = responder.onResult;
            token.faultHandler = responder.onFault;        	
        }	

	}
}