package com.pearl.normandy.delegates
{
	import com.adobe.cairngorm.business.Responder;
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.pearl.normandy.vo.ProductionProcessVO;
	import com.pearl.normandy.vo.ProjectVO;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.AsyncToken;
	
	public class ProcessDelegate
	{
		private var responder:Responder;
		private var service:Object;
		
		public function ProcessDelegate(responder:Responder){
			this.service = ServiceLocator.getInstance().getService( "processService" );
			this.responder = responder;
		}
		
        public function getProjectProcess(project:ProjectVO, getDefault:Boolean): void
        {
            var token : AsyncToken = service.getProcessByProjectId(project.id, getDefault);
            token.resultHandler = responder.onResult;
            token.faultHandler = responder.onFault;            
        }    
        
        public function saveProcess(processes:ArrayCollection): void
        {
            var token : AsyncToken = service.saveProcess(processes);
            token.resultHandler = responder.onResult;
            token.faultHandler = responder.onFault;            
        }
        
        public function deleteProcess(process:ProductionProcessVO): void
        {
            var token : AsyncToken = service.deleteProcess(process);
            token.resultHandler = responder.onResult;
            token.faultHandler = responder.onFault;            
        }                        		                            
	}
}