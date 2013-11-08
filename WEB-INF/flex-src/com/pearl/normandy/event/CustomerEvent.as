package com.pearl.normandy.event
{
	import com.adobe.cairngorm.control.CairngormEvent;
	
	public class CustomerEvent extends CairngormEvent
	{
		public static const EVENT_GET_ALL_CUSTOMER : String = "getAllCustomer";		
		
		//Parameter		
		
        public function CustomerEvent(event:String) {
                super(event);
        }
        
        public static function getAllCustomerEvent():CustomerEvent{
        	var event:CustomerEvent = new CustomerEvent(EVENT_GET_ALL_CUSTOMER);
        	return event;
        }
	}
}