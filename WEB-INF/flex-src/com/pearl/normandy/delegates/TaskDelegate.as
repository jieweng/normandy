package com.pearl.normandy.delegates
{
	import com.adobe.cairngorm.business.Responder;
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.pearl.normandy.vo.ProjectVO;
	import com.pearl.normandy.vo.TaskVO;
	import com.pearl.normandy.vo.UserVO;
	
	import mx.rpc.AsyncToken;
	
	public class TaskDelegate
	{
		private var responder:Responder;
		private var service:Object;
		
		public function TaskDelegate(responder:Responder){
			this.service = ServiceLocator.getInstance().getService( "taskService" );
			this.responder = responder;
		}
		
        public function getProjectMilestone(project:ProjectVO, getDefault:Boolean): void
        {
            var token : AsyncToken = service.getMilestone(project.id, getDefault);
            token.resultHandler = responder.onResult;
            token.faultHandler = responder.onFault;            
        }   		
        
        public function getProjectTaskCategory(project:ProjectVO, getDefault:Boolean): void
        {
            var token : AsyncToken = service.getTaskCategory(project.id, getDefault);
            token.resultHandler = responder.onResult;
            token.faultHandler = responder.onFault;            
        }  
        
        public function getProjectTaskGroup(project:ProjectVO): void
        {
            var token : AsyncToken = service.getTaskGroupByProjectId(project.id);
            token.resultHandler = responder.onResult;
            token.faultHandler = responder.onFault;            
        }
        
        public function getTaskTaskGroup(project:ProjectVO, task:TaskVO): void{
            var token : AsyncToken = service.getTaskGroupByTaskId(project.id, task.id);
            token.resultHandler = responder.onResult;
            token.faultHandler = responder.onFault;        	
        }                     
		
        public function getUserTask(project:ProjectVO, userId:int): void
        {
            var token : AsyncToken = service.getTaskByProjectUser(project.id, userId);
            token.resultHandler = responder.onResult;
            token.faultHandler = responder.onFault;            
        }   
        
        public function getProjectTask(project:ProjectVO): void
        {
            var token : AsyncToken = service.getTask(project.id, null, null);
            token.resultHandler = responder.onResult;
            token.faultHandler = responder.onFault;            
        }  
        
        public function getLeadTask(project:ProjectVO, userId:int): void
        {
            var token : AsyncToken = service.getTask(project.id, null, null, userId);
            token.resultHandler = responder.onResult;
            token.faultHandler = responder.onFault;            
        }                  
        
        public function createTask(task:TaskVO, user:UserVO): void
        {
            var token : AsyncToken = service.createAndGetTask(task, user);
            token.resultHandler = responder.onResult;
            token.faultHandler = responder.onFault;            
        }  
        
        public function createTaskFromMpp(fileName:String, project:ProjectVO, user:UserVO):void{
        	var token:AsyncToken = service.createTaskFromMpp(fileName, project, user);
        	token.resultHandler =responder.onResult;
        	token.faultHandler = responder.onFault;
        }   
        
        public function addSubtask(task:TaskVO, user:UserVO): void
        {
            var token : AsyncToken = service.addSubtask(task, user);
            token.resultHandler = responder.onResult;
            token.faultHandler = responder.onFault;            
        }                  
        
        public function updateTask(task:TaskVO, field:String, user:UserVO): void
        {
            var token : AsyncToken = service.updateTask(task, field, user);
            token.resultHandler = responder.onResult;
            token.faultHandler = responder.onFault;            
        }     
        
        public function cancelTask(task:TaskVO, user:UserVO): void
        {
            var token : AsyncToken = service.cancelTask(task, user);
            token.resultHandler = responder.onResult;
            token.faultHandler = responder.onFault;            
        } 
        
        public function copyTask(task:TaskVO, num:int, copyDetail:Boolean, user:UserVO): void
        {
            var token : AsyncToken = service.copyTask(task, num, copyDetail, user);
            token.resultHandler = responder.onResult;
            token.faultHandler = responder.onFault;            
        }                             
	}
}