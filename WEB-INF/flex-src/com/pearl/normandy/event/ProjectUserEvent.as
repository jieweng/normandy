package com.pearl.normandy.event
{
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.pearl.normandy.vo.ProjectVO;	
	
	public class ProjectUserEvent extends CairngormEvent
	{
		public static const EVENT_GET_PROJECT_LEAD : String = "getProjectLead";		
		
		//Parameter
		public var project:ProjectVO;	
		public var getDefault:Boolean
		
		public function ProjectUserEvent(event:String):void{
			super(event);
		}
		
		public static function getProjectLeadEvent(project:ProjectVO, getDefault:Boolean):ProjectUserEvent{
			var event:ProjectUserEvent = new ProjectUserEvent(EVENT_GET_PROJECT_LEAD);
			event.project = project;	
			event.getDefault = getDefault;
			return event;
		}
	}
}