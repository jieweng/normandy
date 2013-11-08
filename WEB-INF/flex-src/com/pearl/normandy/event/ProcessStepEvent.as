package com.pearl.normandy.event
{	        
	import com.adobe.cairngorm.control.CairngormEvent;
	
	import mx.collections.ArrayCollection;

    public class ProcessStepEvent extends CairngormEvent
    {
    	
		//=====================================================================================
		//                           Get
		//=====================================================================================		    	
    	
    	
    	
    	
		//=====================================================================================
		//                           Create
		//=====================================================================================    	
    	public static const EVENT_DELETE_PROCESS_STEP:String			= "deleteProcessStep";
    			
    			

		//Parameter
		public var processSteps:ArrayCollection;
		
    			
    			
    			
        public function ProcessStepEvent(type:String) {
                super(type);
        }

		public static function deleteProcessStepEvent(processSteps:ArrayCollection):ProcessStepEvent{
			var event:ProcessStepEvent = new ProcessStepEvent(EVENT_DELETE_PROCESS_STEP);
			event.processSteps = processSteps
			return event;
		}				
    }	
}