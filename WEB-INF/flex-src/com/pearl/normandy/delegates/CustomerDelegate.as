package com.pearl.normandy.delegates
{
	import com.adobe.cairngorm.business.Responder;
	import com.adobe.cairngorm.business.ServiceLocator;
	
	import mx.rpc.AsyncToken;
	
	public class CustomerDelegate
	{
		private var responder:Responder;
		private var service:Object;
		
		public function CustomerDelegate(responder:Responder){
			this.service = ServiceLocator.getInstance().getService( "customerService" );
			this.responder = responder;
		}
		
        public function getAllCustomer(): void
        {
            var token : AsyncToken = service.getAllCustomer();
            token.resultHandler = responder.onResult;
            token.faultHandler = responder.onFault;            
        }                              
	}
}