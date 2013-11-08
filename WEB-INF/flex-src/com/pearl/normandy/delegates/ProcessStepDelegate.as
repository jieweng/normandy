package com.pearl.normandy.delegates
{
	import com.adobe.cairngorm.business.Responder;
	import com.adobe.cairngorm.business.ServiceLocator;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.AsyncToken;
	
	public class ProcessStepDelegate
	{
		private var responder:Responder;
		private var service:Object;
		
		public function ProcessStepDelegate(responder:Responder){
			this.service = ServiceLocator.getInstance().getService( "processStepService" );
			this.responder = responder;
		}
        
        public function deleteProcess(processSteps:ArrayCollection): void
        {
            var token : AsyncToken = service.deleteProcessSteps(processSteps);
            token.resultHandler = responder.onResult;
            token.faultHandler = responder.onFault;            
        }                        		                            
	}
}