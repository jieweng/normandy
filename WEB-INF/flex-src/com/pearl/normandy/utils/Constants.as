package com.pearl.normandy.utils
{
	import flash.net.FileFilter;
	
	import ilog.utils.TimeUnit;
	
	import mx.collections.ArrayCollection;
	
	public final class Constants
	{	
		  //===========================================
		  //  Login screen (Group/State) Mapping
		  //===========================================					
		public static const GROUP_QA:String					= "QA";
//		public static const GROUP_PM:String					= "PM";
		public static const GROUP_QA_NUM:Number				= 2;		
		public static const GROUP_PM_NUM:Number				= 3;
		public static const GROUP_INSPECTOR_NUM:Number		= 5;
		public static const GROUP_FINACIAL_NUM:Number		= 16;		
		
		public static const SELECT_ITEM_ALL:String 			= "--All--";	
		
		public static const LOGIN_PICTURE_ADDRESS:String	= "http://localhost:8080/applications/normandy/temp/loginPicture.jpg";	

		  //===========================================
		  //  Project Status
		  //===========================================
		public static const PROJECT_STATUS_OPEN:String				= "Open";
		public static const PROJECT_STATUS_CLOSE:String				= "Close";	
		
		  //===========================================
		  //  Resource - Project Status
		  //===========================================
		public static const RESOURCE_PROJECT:String					= "PROJECT";
		public static const RESOURCE_PROJECT_STATUS_ASSIGNED:String	= "ASSIGNED";
		public static const RESOURCE_PROJECT_STATUS_BILLABLE:String	= "BILLABLE";
		public static const RESOURCE_PROJECT_STATUS_RELEASED:String	= "RELEASED";
		
		public static const RESOURCE_PROJECT_STATUS_ON:String		= "ON";
		public static const RESOURCE_PROJECT_STATUS_OFF:String		= "OFF";					
		
			
		  //===========================================
		  //  User Type
		  //===========================================		
		public static const USER_TYPE_EMPLOYEE:String		= "Employee";
		public static const USER_TYPE_CUSTOMER:String		= "Customer";	
		public static const USER_TYPE_PERMANENT:String		= "Permanent";
		public static const USER_TYPE_INTERN:String			= "Intern";
		
		  //===========================================
		  //  Constant Variables
		  //===========================================		
		public static const GANTT_DATA_GRID: String 	= "GanttDataGrid";
		public static const GANTT_SHEET: String			= "GanttSheet";
		public static const ZOOM_OUT_RATIO:Number 		= 2;
		public static const ZOOM_IN_RATIO:Number 		= 1 / ZOOM_OUT_RATIO;
			
		public static const MIN_ZOOM_DAY:Number			= TimeUnit.DAY.milliseconds / 40;
		public static const MIN_ZOOM_HOUR:Number		= TimeUnit.HOUR.milliseconds / 60;
		public static const MAX_ZOOM_MONTH:Number		= TimeUnit.MONTH.milliseconds / 90;
		
		public static const SNAPPING_TIME_DAY:Object	= {unit: TimeUnit.DAY, steps: 1};
		public static const SNAPPING_TIME_HOUR:Object	= {unit: TimeUnit.HOUR, steps: 1};
		public static const SNAPPING_TIME_MINUTE:Object	= {unit: TimeUnit.MINUTE, steps: 1};		
		
		public static const SNAPPING_ARRAY:ArrayCollection 	= new ArrayCollection([SNAPPING_TIME_DAY, SNAPPING_TIME_HOUR, SNAPPING_TIME_MINUTE]);
		public static const IMAGE_FILTER: FileFilter		= new FileFilter("Image Files (*.jpg, *.jpeg, *.gif, *.png)", "*.jpg; *.jpeg; *.gif; *.png");
		public static const MPP_FILTER:FileFilter			= new FileFilter("Mpp Files (*.mpp)", "*.mpp");
		
		public static const PRIORITY_HIGH:String			= "High";
		public static const PRIORITY_MEDIUM:String			= "Medium";
		public static const PRIORITY_LOW:String				= "Low"; 		
		
		public static const MILLISECONDS_PER_MINUTE:int 	= 1000 * 60;
		public static const MILLISECONDS_PER_HOUR:int 		= 1000 * 60 * 60;
		public static const MILLISECONDS_PER_DAY:int		= 1000 * 60 * 60 * 24;		
		
		public static const BOOLEAN_YES:String				= "Y";
		public static const BOOLEAN_NO:String				= "N";
		
		public static const FORMAT_ITEMS:String					= "items";
		public static const FORMAT_TREE_ITEMS:String			= "treeItems";
		public static const FORMAT_TREE_DATAGRID_ITEMS:String	= "treeDataGridItems"; 
		
		//==================================================================		
		//		Task DataField
		//===================================================================		
		public static const START_TIME:String				= "startTime";
		public static const END_TIME:String					= "endTime";
		public static const PLANNED_STAFF_DAYS:String		= "plannedStaffDays";
		public static const PLANNED_FEEDBACK_DAYS:String	= "plannedFeedbackDays";
		public static const TYPE:String						= "type";
		public static const PARENT_ID:String				= "parentId";
		public static const TASK_NAME:String				= "name";
		public static const MEDAL:String					= "medal";
		
		//=======================================================================
		//		UserContract Field
		//=======================================================================
		public static const SALARY:String					= "salary";
		public static const SOCIAL_BENEFIT_TYPE_1:String	= "socialBenefitType1";
		public static const SOCIAL_BENEFIT_TYPE_2:String	= "socialBenefitType2";
		public static const HOUSE_FOUND:String				= "houseFound";
		public static const INSURANCE:String				= "insurance";
		public static const LUNCH_BENEFIT:String			= "lunchBenefit";
		public static const DURATION:String					= "duration";
		
		public static const DELETE_YES:String				= "Y";
		public static const DELIVERABLE_YES:String			= 'Y';
		public static const DELIVERABLE_NO:String			= 'N';
		
		public static const TIMEUNIT_MONTH:String			= "month";
		public static const TIMEUNIT_WEEK:String			= "week";
		public static const TIMEUNIT_DAY:String				= "day";
		public static const TIMEUNIT_HOUR:String			= "hour";
		public static const TIMEUNIT_MINUE:String			= "minute";
		
		public static const TASK:String						= "Task";
		
		
		public static const MOUSE_WHEEL_ZOOM_SIZE:int = 15;
		public static const ZOOM_SIZE:int = 30;
		
		  //===========================================
		  //  Action Type of getting project relatives
		  //===========================================			
		public static const ACTION_GET_MILESTONE:String 	= "GetMilestone";
		public static const ACTION_GET_PROCESS:String	 	= "GetProcess";
		public static const ACTION_GET_ACTIVITY:String		= "GetActivity";
		public static const ACTION_GET_LEAD:String			= "GetLead";	
						
		
		  //===========================================
		  //  Project Type
		  //===========================================		
		public static const PROJECT_TYPE_PRODUCTION:String	= "Production";
		public static const PROJECT_TYPE_TRAINING:String	= "Training";
		
		
		  //===========================================
		  //  Default milestone value for returning --All--
		  //===========================================	
		public static const MILESTONE_DEFAULT_TRUE:Boolean	= true;
		public static const MILESTONE_DEFAULT_FALSE:Boolean	= false;
		

		  //===========================================
		  //  Metadata
		  //===========================================		
		public static const PROJECT_ROLE:Object 			= {PM: 1,
															   AD: 2,
															   Lead: 3,
															   Artist: 4,
															   QA: 5
															}
		
		public static const PROJECT_ROLE_APM:String		= "APM";													
		public static const PROJECT_ROLE_PM:String		= "PM";
		public static const PROJECT_ROLE_AD:String		= "AD";
		public static const PROJECT_ROLE_LEAD:String	= "Lead";
		public static const PROJECT_ROLE_ARTIST:String	= "Artist";
		public static const PROJECT_ROLE_QA:String		= "QA";
		
		public static const PROJECT_ROLE_APM_NUM:uint		= 0;													
		public static const PROJECT_ROLE_PM_NUM:uint		= 1;
		public static const PROJECT_ROLE_AD_NUM:uint		= 2;
		public static const PROJECT_ROLE_LEAD_NUM:uint		= 3;
		public static const PROJECT_ROLE_ARTIST_NUM:uint	= 4;
		public static const PROJECT_ROLE_QA_NUM:uint		= 5;		

		public static const VIEW_MODE_ALL:String		= "All";
		public static const VIEW_MODE_PROJECT:String	= "Project";
		public static const VIEW_MODE_ASSIGNED:String	= "Assigned";
		public static const VIEW_MODE_NONE:String		= "None";
	
		  //===========================================
		  //  Value to differentiate current project in NormandyModel
		  //===========================================			
		public static const SCREEN_QA_MANAGEMENT:String 			 = "QAManagement";
		
		
		  //===========================================
		  //  Constant Variables
		  //===========================================				
		public static const TASK_DETAIL_TYPE_LOD:String			= "LOD";
		public static const TASK_DETAIL_TYPE_UV:String			= "UV";
		public static const TASK_DETAIL_TYPE_MATERIAL:String	= "Material";
		public static const TASK_DETAIL_TYPE_DIFFUSE:String		= "Diffuse";
		public static const TASK_DETAIL_TYPE_SPECULAR:String	= "Specular";
		public static const TASK_DETAIL_TYPE_NORMAL:String		= "Normal";
		public static const TASK_DETAIL_TYPE_LIGHTING:String	= "Lighting";
		
		public static const TASK_DETAIL_LOD0:String			= "LOD0";
		public static const TASK_DETAIL_LOD1:String			= "LOD1";
		public static const TASK_DETAIL_LOD2:String			= "LOD2";
		public static const TASK_DETAIL_LOD3:String			= "LOD3";
		public static const TASK_DETAIL_UV1:String			= "UV1";
		public static const TASK_DETAIL_UV2:String			= "UV2";
		public static const TASK_DETAIL_UV3:String			= "UV3";
		public static const TASK_DETAIL_UV4:String			= "UV4";
		public static const TASK_DETAIL_MATERIAL:String		= "materialDescription";
		public static const TASK_DETAIL_DIFFUSE:String		= "diffuseDescription";
		public static const TASK_DETAIL_SPECULAR:String		= "specularDescription";
		public static const TASK_DETAIL_NORMAL:String		= "normalDescription";
		public static const TASK_DETAIL_LIGNTING:String		= "lightingDescription";		
		

		  //===========================================
		  //  Subtask Status Type
		  //===========================================		
		public static const TASK_STATUS_NOTSTARTED:String		= "Not Started";
		public static const TASK_STATUS_WIP:String				= "WIP";
		public static const TASK_STATUS_COMPLETE:String			= "Complete";
		public static const TASK_STATUS_SUBMITTED:String		= "Submitted";
		public static const TASK_STATUS_APPROVED:String			= "Approved";			
		
		public static const TASK_STATUS_NOTSTARTED_NUM:int		= 11;
		public static const TASK_STATUS_WIP_NUM:int				= 12;
		public static const TASK_STATUS_COMPLETE_NUM:int		= 13;
		public static const TASK_STATUS_SUBMITTED_NUM:int		= 14;
		public static const TASK_STATUS_APPROVED_NUM:int		= 15;	
		
		public static const ENTER_KEY_CODE:int					= 13;
		
		public static const QA_ALL_WORK_ITEM:String					= "All QA Work Items";
		public static const QA_MY_WORK_ITEM:String					= "My QA Work Items";
		public static const CUSTOMER_ALL_FEEDBACK_WORK_ITEM:String	= "All Customer Feedback Work Items";
		public static const CUSTOMER_MY_FEEDBACK_WORK_ITEM:String	= "My Customer Feedback Work Items";		

		
		
		public static const FEEDBACK_SELECT:String					= "--请选择--";
		public static const FEEDBACK_TYPE_TECHNIC_ZH:String			= "低级问题";
		public static const FEEDBACK_TYPE_ART_ZH:String				= "常规问题";
		public static const FEEDBACK_TYPE_CUSTOMER_ZH:String		= "客户问题";
		
		public static const FEEDBACK_TYPE_TECHNIC:String			= "TECH_PROBLEM";
		public static const FEEDBACK_TYPE_ART:String				= "VISUAL_PROBLEM";
		public static const FEEDBACK_TYPE_CUSTOMER:String			= "CUSTOMER_PROBLEM";
		
		public static const FEEDBACK_SEVERITY_LOW_ZH:String			= "低";
		public static const FEEDBACK_SEVERITY_MEDIUM_ZH:String		= "中";
		public static const FEEDBACK_SEVERITY_HIGH_ZH:String		= "高";
		
		public static const FEEDBACK_SEVERITY_LOW:String			= "SEVERITY_LOW";
		public static const FEEDBACK_SEVERITY_MEDIUM:String			= "SEVERITY_MEDIUM";
		public static const FEEDBACK_SEVERITY_HIGH:String			= "SEVERITY_HIGH";
		
		public static const FEEDBACK_STATUS_MODIFIED:String			= "MODIFIED";
		public static const FEEDBACK_STATUS_UNMODIFIED:String		= "UNMODIFIED";
		
		public static const FEEDBACK_STATUS_MODIFIED_ZH:String		= "已修改";
		public static const FEEDBACK_STATUS_UNMODIFIED_ZH:String	= "未修改";
		
		public static const FEEDBACK_CATEGORY_INTERNAL:String		= "Internal";
		public static const FEEDBACK_CATEGORY_CUSTOMER:String		= "Customer";
		
		public static const WORK_ITEM_TYPE:String					= "CUSTOMER_FEEDBACK";
		public static const WORK_ITEM_STATUS_OPEN:String			= "OPEN";		
		public static const WORK_ITEM_STATUS_CLOSE:String			= "CLOSE";
		
		public static const WORK_ITEM_CHECK_STATUS:Array 			= ["Pass", "Reject"];
		
		public static const REPORT_UT_CHART:String 					= "UT_CHART";		
		public static const REPORT_DETAIL_CHART:String				= "DETAIL_INFO_CHART";
		public static const REPORT_EFFICIENCY_QUALITY_CHART:String	= "EFFICIENCY_QUALITY_CHART";
		public static const REPORT_PROJECT_MEMBER_ABILITY:String	= "PROJECT_MEMBER_ABILITY";
		public static const REPORT_SAP_UT:String					= "SAP_UT";
		
		public static const MPXJ_TEMPLATE:String					= "template.mpp";
		
		public static const GANTT_VIEW_PERFORMANCE:String			= "Performance";
		public static const GANTT_VIEW_STATUS:String				= "Status";
		
		public static const ORG_CHART_STANDARD:String				= "standard";
		
		public static const USER_GENDER_MALE:String					= "M";
		public static const USER_GENDER_FEMALE:String				= "F";		
		public static const USER_CATEGORY:ArrayCollection			= new ArrayCollection([
			{label:"Permanent"},
			{label:"Intern"}
		]);
		public static const USERGROUP_STATE:ArrayCollection         = new ArrayCollection([
			{label:"Production"},
			{label:"Admin"}
		]);
		public static const USER_PRIVILEGE:ArrayCollection			= new ArrayCollection([
			{label:"None"},
			{label:"R"},
			{label:"R/W"}
		]);
		public static const USER_PRIVILEGE2:ArrayCollection			= new ArrayCollection([
			{label:"Level1"},
			{label:"Level2"},
			{label:"Level3"}
		]);
		public static const USER_PRIVILEGE3:ArrayCollection			= new ArrayCollection([
			{label:"N"},
			{label:"Y"}
		]);
		
		public static const REPORT_LEVEL1:String				= "Level1";
		public static const REPORT_LEVEL2:String				= "Level2";
		public static const REPORT_LEVEL3:String				= "Level3";
		
		public static const PRIV_NONE:String			= "None";
		public static const PRIV_R:String				= "R";
		public static const PRIV_RW:String				= "R/W";
		
		public static const PRIV_ALL:String				= "All";
		public static const PRIV_PRODUCTION:String		= "Production";
		public static const PRIV_NON_PRODUCTION:String	= "Non-Production";
		public static const PRIV_ASSIGNED:String		= "Assigned";
	}
}
