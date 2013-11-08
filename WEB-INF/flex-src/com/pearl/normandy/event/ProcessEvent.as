package com.pearl.normandy.event
{	        
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.pearl.normandy.vo.ProductionProcessVO;
	import com.pearl.normandy.vo.ProjectVO;
	
	import mx.collections.ArrayCollection;

    public class ProcessEvent extends CairngormEvent
    {
    	
		//=====================================================================================
		//                           Get
		//=====================================================================================		    	
    	public static const EVENT_GET_PROJECT_PROCESS:String	= "getProjectProcess";
    	
    	
    	
    	
		//=====================================================================================
		//                           Create
		//=====================================================================================    	
    	public static const EVENT_SAVE_PROCESS:String			= "saveProcess";
    	public static const EVENT_DELETE_PROCESS:String			= "deleteProcess";
    			
    			

		//Parameter
		public var processes:ArrayCollection;
		public var process:ProductionProcessVO;
		public var project:ProjectVO    	
		public var getDefault:Boolean				
    			
    			
    			
        public function ProcessEvent(type:String) {
                super(type);
        }

		public static function getProjectProcessEvent(project:ProjectVO, getDefault:Boolean):ProcessEvent{
			var event:ProcessEvent = new ProcessEvent(EVENT_GET_PROJECT_PROCESS);
			event.project = project;
			event.getDefault = getDefault;
			return event;
		}	
		
		public static function saveProcessEvent(processes:ArrayCollection):ProcessEvent{
			var event:ProcessEvent = new ProcessEvent(EVENT_SAVE_PROCESS);
			event.processes = processes;
			return event;
		}	
		
		public static function deleteProcessEvent(process:ProductionProcessVO):ProcessEvent{
			var event:ProcessEvent = new ProcessEvent(EVENT_DELETE_PROCESS);
			event.process = process;
			return event;
		}				
    }	
}