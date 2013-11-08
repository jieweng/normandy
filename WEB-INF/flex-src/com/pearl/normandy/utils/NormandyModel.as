package com.pearl.normandy.utils
{	
	import com.adobe.cairngorm.model.ModelLocator;
	import com.pearl.normandy.collections.HierarchicalCollectionViewFixed;
	import com.pearl.normandy.event.LoginEvent;
	import com.pearl.normandy.vo.ActivityVO;
	import com.pearl.normandy.vo.CheckItemVO;
	import com.pearl.normandy.vo.MetaDataVO;
	import com.pearl.normandy.vo.ProjectMemberVO;
	import com.pearl.normandy.vo.ProjectUserVO;
	import com.pearl.normandy.vo.ProjectVO;
	import com.pearl.normandy.vo.TaskVO;
	import com.pearl.normandy.vo.UserPrivilegeVO;
	import com.pearl.normandy.vo.UserVO;
	
	import flash.events.EventDispatcher;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.remoting.RemoteObject;
	
	[Event(name="login", type="com.pearl.normandy.event.LoginEvent")]
	
	[Bindable]
	public class NormandyModel extends EventDispatcher implements com.adobe.cairngorm.model.ModelLocator
	{
		private static var normandyModel:NormandyModel;
		
		public static function getInstance():NormandyModel{
		
			if(normandyModel == null){			
				normandyModel = new NormandyModel();
			}
			return normandyModel;
		}
		
		
		public function NormandyModel()
		{
			if(NormandyModel.normandyModel != null){			
				throw new Error( "Only one ModelLocator instance should be instantiated");	
			}
			
			metaDataRo = new RemoteObject;
			metaDataRo.destination = "ro.metadata"
			metaDataRo.addEventListener(ResultEvent.RESULT, metaDataResultHandler);
			userPrivilegeRo = new RemoteObject;
			userPrivilegeRo.destination = "ro.userPrivilege"
			userPrivilegeRo.addEventListener(ResultEvent.RESULT, userPrivilegeResultHandler);						
		}
		
		//===========================
		//Service Objects
		//===========================
		private var metaDataRo:RemoteObject;
		private var projectUserRo:RemoteObject;
		private var userPrivilegeRo:RemoteObject;
		
		//===========================
		//Variables
		//===========================				
		private var _currProjects:Object = new Object();									
		private var _currUser:UserVO;
		
		public var metaData:MetaDataVO	
		private var userPrivilege:UserPrivilegeVO;
		public var errorMessage:Object = new Object();
		
		//Viables to define non-working period
		public var holidayUtil:HolidayUtil;
		public var nonWorkingDays:Array = [0,6];
		public var nonWorkingRanges:Array = [];
		
		public var TYPE:ArrayCollection = new ArrayCollection([{type: Constants.PROJECT_TYPE_PRODUCTION}, {type:Constants.PROJECT_TYPE_TRAINING}]);
		
		//===========================
		//Getter and Setters
		//===========================
 		public function set currUser(value:UserVO):void{
			this._currUser = value;
			metaDataRo.getMetaData(value);
			userPrivilegeRo.getUserPrivilegeByUserId(value.id);
		} 
		
 		public function get currUser():UserVO{
			return this._currUser;
		} 
		
		public function setCurrProject(key:String, value:ProjectVO):void{
			this._currProjects[key] = value;
		}
		
		public function getCurrProject(key:String):ProjectVO{
			if(this._currProjects.hasOwnProperty(key)){
				return this._currProjects[key];
			}
			else{
				return null;
			}
		}		
		
		//===========================
		//Event Handlers
		//===========================
		private function metaDataResultHandler(event:ResultEvent):void{
			metaData = event.result as MetaDataVO;
			
			this.nonWorkingRanges	= metaData.holidays.toArray();			
			holidayUtil = HolidayUtil.getInstance();
			holidayUtil.weekHoliday = nonWorkingDays;		
			holidayUtil.holidays 	= nonWorkingRanges;		
							
			dispatchEvent(new LoginEvent(LoginEvent.LOGIN, currUser));
		}	
		
		private function userPrivilegeResultHandler(event:ResultEvent):void{
			userPrivilege = event.result as UserPrivilegeVO;
			if(userPrivilege == null){
				userPrivilege = new UserPrivilegeVO;
			}
			privilegeProject = userPrivilege.project;
			privilegeResource = userPrivilege.resource;
			privilegeReport = userPrivilege.report;
			privilegeUser = userPrivilege.user;
			privilegeUserGroup = userPrivilege.userGroup;
			privilegeHoliday = userPrivilege.holiday;
			privilegeUserPunctual = userPrivilege.userPunctual;
			privilegeLoginPicture = userPrivilege.loginPicture;
			privilegeLevelShow = userPrivilege.levelShow;
		}
		
		//===========================
		//Shared Values
		//===========================
		public var minVisibleTime:Date = CalendarConfig.getTimeLastMonth();
		public var maxVisibleTime:Date = CalendarConfig.getTimeMaximum();
		public var ctrlKey:Boolean;
		public var shiftKey:Boolean;
		
		public var currProjectPM:Boolean = false;
		public var currProjectAd:Boolean = false;
		public var currProjectLead:Boolean;
		public var projectUserRole:ArrayCollection;					//某人在某个project中的角色列表
		public var reportSelectProject:ProjectVO;					//ReportWindow中被选择的Project
//		public var currView:String;
//		public var currEdit:String;		
		public var taskEditable:Boolean;
		public var taskViewMode:String;
		public var activityEditable:Boolean;
		public var activityViewMode:String;
		
		public var currProjectMember:ProjectMemberVO;
		
		public var releasedProjectMembers:ArrayCollection;
		public var assignedProjectMembers:ArrayCollection;	
		
		public var selectedMilestone:String = Constants.SELECT_ITEM_ALL;
		public var selectedTaskCategory:String = Constants.SELECT_ITEM_ALL;
		public var selectedResource:Object;
		public var selectedCheckItem:CheckItemVO;
		public var selectedUsers:ArrayCollection = new ArrayCollection();
		
		public var isTaskViewLock:Boolean;
		public var isResourceViewLock:Boolean;
		
		public var ganttView:String = Constants.GANTT_VIEW_PERFORMANCE;
		
		//=============================================================================
		//                          Project Management Filter Settings
		//=============================================================================
		public var taskNameFilter:String = "";
		public var taskTypeFilter:String = "";
		public var taskAssignFilter:String = "";
		
		public var isLink:Boolean = false;
		public var isLock:Boolean = false;		
		
		public var limitToProjectResource:Boolean = true;
		public var limitToProjectActivity:Boolean = false;
		public var leadFilter:ProjectUserVO = null;
		public var resourceStatusFilter:String = "Assigned";
		public var employeeNoFilter:String = "";
		public var userNameFilter:String   = "";
		public var userInforFilter:String = "";
		public var activityQualityFilter:int = 0;
		public var isThumb:Boolean = false;
		public var isShowDelMem:Boolean = false;						
		

		//=============================================================================
		//                          Customer
		//=============================================================================	
		public var GLOBAL_ALL_CUSTOMERS:ArrayCollection;

		//=============================================================================
		//                          Project
		//=============================================================================		
		public var GLOBAL_ALL_PROJECTS:ArrayCollection;
		public var GLOBAL_SELECTED_PROJECT:ProjectVO;
		public var GLOBAL_CREATED_PROJECT:ProjectVO;
		
		public var GLOBAL_USER_PROJECTS:ArrayCollection;
				
		public var leads:ArrayCollection;	
		

		//=============================================================================
		//                          Process
		//=============================================================================				
		public var LOCAL_PROJECT_PROCESSES:ArrayCollection;
		
		//=============================================================================
		//                          Task
		//=============================================================================
		public var GLOBAL_PROJECT_MILESTONES:ArrayCollection;
		public var GLOBAL_PROJECT_TASK_CATEGORIES:ArrayCollection;
		public var GLOBAL_PROJECT_TASK_GROUPS:ArrayCollection;
		public var LOCAL_TASK_TASK_GROUPS:ArrayCollection;								
		
		public var GLOBAL_USER_TASKS_HC:HierarchicalCollectionViewFixed;
		public var updateTask:TaskVO;
		public var cancelTask:TaskVO;
		public var draggedTask:TaskVO;
		public var draggedIndex:int;
		
		public var GLOBAL_CREATED_TASKS:ArrayCollection;
		public var GLOBAL_ADD_SUBTASK_WAIT:TaskVO;
		public var GLOBAL_CREATED_SUBTASK:TaskVO;
		public var GLOBAL_SELECTED_TASK:TaskVO;			
		public var GLOBAL_CANCELLED_TASK:TaskVO;
		public var GLOBAL_MOVED_TASK:TaskVO;

		public var LOCAL_TASK_DETAIL:Object;
		public var LOCAL_TASK_FEEDBACKS:ArrayCollection;
		public var LOCAL_TASK_COMMENTS:ArrayCollection;
		
		public var allTaskStaff:Number = 0;
		public var allActivityStaff:Number = 0;
		public var taskStaff:Number = 0;
		public var activityStaff:Number = 0;
		public var taskStaffNotAssigned:Boolean = false;		
		
		//=============================================================================
		//                          Activity
		//=============================================================================
		public var GLOBAL_SELECTED_ACTIVITY:ActivityVO;
		
		public var GLOBAL_CREATED_ACTIVITY:ActivityVO;
		public var GLOBAL_UPDATED_ACTIVITIES:ArrayCollection;	
		
		public var SALARY_MODULU:String;
		public var SALARY_EXPONENT:String;
		
		//=============================================================================
		//                          Privilege
		//=============================================================================
		public var privilegeProject:String;
		public var privilegeResource:String;
		public var privilegeReport:String;
		public var privilegeUser:String;
		public var privilegeUserGroup:String;
		public var privilegeHoliday:String;
		public var privilegeUserPunctual:String;
		public var privilegeLoginPicture:String;
		public var privilegeLevelShow:String;
	}
}