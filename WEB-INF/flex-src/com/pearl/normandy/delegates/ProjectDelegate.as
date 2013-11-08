package com.pearl.normandy.delegates
{
	import com.adobe.cairngorm.business.Responder;
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.pearl.normandy.vo.ProjectVO;
	import com.pearl.normandy.vo.UserVO;
	
	import mx.rpc.AsyncToken;
	
	public class ProjectDelegate
	{
		private var responder:Responder;
		private var service:Object;
		
		public function ProjectDelegate(responder:Responder){
			this.service = ServiceLocator.getInstance().getService( "projectService" );
			this.responder = responder;
		}
		
        public function getAllProject(): void
        {
            var token : AsyncToken = service.getAllProject();
            token.resultHandler = responder.onResult;
            token.faultHandler = responder.onFault;            
        }    		
		
        public function getProjectByUser(user:UserVO): void
        {
            var token : AsyncToken = service.getProjectByUser(user);
            token.resultHandler = responder.onResult;
            token.faultHandler = responder.onFault;            
        }          
        
        
        public function createProject(project:ProjectVO, customer:String, projectRoleId:int, user:UserVO): void
        {
            var token : AsyncToken = service.createProject(project, customer, projectRoleId, user);
            token.resultHandler = responder.onResult;
            token.faultHandler = responder.onFault;      			     
        }    
        
        public function updateProject(project:ProjectVO, customer:String, user:UserVO): void
        {
            var token : AsyncToken = service.updateProject(project, customer, user);
            token.resultHandler = responder.onResult;
            token.faultHandler = responder.onFault;      			     
        }                                
	}
}