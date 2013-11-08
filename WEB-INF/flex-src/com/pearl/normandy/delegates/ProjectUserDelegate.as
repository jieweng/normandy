package com.pearl.normandy.delegates
{
	import com.adobe.cairngorm.business.Responder;
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.pearl.normandy.vo.ProjectVO;
	
	import mx.rpc.AsyncToken;
	
	public class ProjectUserDelegate
	{
		private var responder:Responder;
		private var service:Object;
		
		public function ProjectUserDelegate(responder:Responder){
			this.service = ServiceLocator.getInstance().getService( "projectUserService" );
			this.responder = responder;
		}
		
        public function getProjectLead(project:ProjectVO, getDefault:Boolean): void
        {
            var token : AsyncToken = service.getLeadsHierarchical(project.id, getDefault);
            token.resultHandler = responder.onResult;
            token.faultHandler = responder.onFault;            
        }   		                   
	}
}