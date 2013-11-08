package com.pearl.normandy.event
{
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.pearl.normandy.vo.ProjectVO;
	import com.pearl.normandy.vo.UserVO;
	
	public class ProjectEvent extends CairngormEvent
	{
		//=====================================================================================
		//                           Get
		//=====================================================================================		
		public static const EVENT_GET_ALL_PROJECT 	  	: String = "getAllProject";
		public static const EVENT_GET_PROJECT_BY_USER 	: String = "getProjectByUser";
		
		
		
		//=====================================================================================
		//                           Create
		//=====================================================================================
		public static const EVENT_CREATE_PROJECT		:String	= "createProject";
		public static const EVENT_UPDATE_PROJECT		:String = "updateProject";
		
		
						
		
		//Parameter
		public var project:ProjectVO
		public var user:UserVO;		
		public var customer:String;
		public var projectRoleId:int;
		
		
		
		public function ProjectEvent(event:String){
			super(event);
		}


		public static function getAllProjectEvent():ProjectEvent{
			var event:ProjectEvent = new ProjectEvent(EVENT_GET_ALL_PROJECT);
			return event;
		}		
		
		
		public static function getProjectByUserEvent(user:UserVO):ProjectEvent{
			var event:ProjectEvent = new ProjectEvent(EVENT_GET_PROJECT_BY_USER);
			event.user = user;
			return event;
		}


		public static function createProjectEvent(project:ProjectVO, customer:String, projectRoleId:int, user:UserVO):ProjectEvent{
			var event:ProjectEvent = new ProjectEvent(EVENT_CREATE_PROJECT);
			event.project = project;
			event.customer = customer;
			event.projectRoleId = projectRoleId;
			event.user = user;
			return event;
		}	
		
		public static function updateProjectEvent(project:ProjectVO, customer:String, user:UserVO):ProjectEvent{
			var event:ProjectEvent = new ProjectEvent(EVENT_UPDATE_PROJECT);
			event.project = project;
			event.customer = customer;
			event.user = user;
			return event;
		}		
	}
}