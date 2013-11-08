package com.pearl.normandy.commands.customer
{
	import com.adobe.cairngorm.business.Responder;
	import com.adobe.cairngorm.commands.Command;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.pearl.normandy.delegates.CustomerDelegate;
	import com.pearl.normandy.event.CustomerEvent;
	import com.pearl.normandy.utils.NormandyModel;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.events.ResultEvent;
	
	public class GetAllCustomerCommand implements Command, Responder
	{
		private var model:NormandyModel = NormandyModel.getInstance();
		
        public function execute( event : CairngormEvent ) : void
        {           
            var delegate : CustomerDelegate = new CustomerDelegate( this );   
            var e : CustomerEvent = CustomerEvent( event );  
            delegate.getAllCustomer();          
        }
           
        public function onResult( event : * = null ) : void
        {                 
        	var e:ResultEvent = ResultEvent(event);       
			model.GLOBAL_ALL_CUSTOMERS = event.result as ArrayCollection;	
        }
                
        public function onFault( event : * = null ) : void
        {
        }

	}
}