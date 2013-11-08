package com.pearl.normandy.utils;

public class Constants {	
	public static final String BOOLEAN_YES 	= "Y";
	public static final String BOOLEAN_NO 	= "N";
	public static final String DELETE_YES	= "Y";
	public static final String DELETE_NO	= "N";

	public static final String PROJECT_ROLE_APM			= "APM";													
	public static final String PROJECT_ROLE_PM			= "PM";
	public static final String PROJECT_ROLE_AD			= "AD";
	public static final String PROJECT_ROLE_LEAD		= "Lead";
	public static final String PROJECT_ROLE_ARTIST		= "Artist";
	public static final String PROJECT_ROLE_QA			= "QA";
	
	public static final int PROJECT_ROLE_APM_NUM			= 0;													
	public static final int PROJECT_ROLE_PM_NUM				= 1;
	public static final int PROJECT_ROLE_AD_NUM				= 2;
	public static final int PROJECT_ROLE_LEAD_NUM			= 3;
	public static final int PROJECT_ROLE_ARTIST_NUM			= 4;
	public static final int PROJECT_ROLE_QA_NUM				= 5;
	
	public static final String SELECT_ITEM_ALL = "--All--";
	
	public static final String ACTION_GET_MILESTONE = "GetMilestone";
	public static final String ACTION_GET_PROCESS 	= "GetProcess";
	public static final String ACTION_GET_ACTIVITY	= "GetActivity";
	public static final String ACTION_GET_LEAD		= "GetLead";

	
	  //===========================================
	  //  Project Status
	  //===========================================
	public static final String PROJECT_STATUS_OPEN				= "Open";
	public static final String PROJECT_STATUS_CLOSE				= "Close";		
	
	  //===========================================
	  //  Resource - Project Status
	  //===========================================
	public static final String RESOURCE_PROJECT_STATUS_ASSIGNED	= "ASSIGNED";
	public static final String RESOURCE_PROJECT_STATUS_BILLABLE	= "BILLABLE";
	public static final String RESOURCE_PROJECT_STATUS_RELEASED	= "RELEASED";		
	
	  //===========================================
	  //  Task Category Type
	  //  These constants value are mapped with 
	  //  LIST_OF_VALUE table in database
	  //===========================================		
	public static final String LOV_TYPE_TASK_CATEGORY	= "TASK CATEGORY";
	public static final String LOV_TYPE_PRIORITY		= "PRIORITY";
	public static final String LOV_TYPE_SEVERITY		= "SEVERITY";
	public static final String LOV_TYPE_SUBTASK_STATUS	= "SUBTASK STATUS";
	public static final String LOV_TYPE_ROOT_CAUSE		= "ROOT CAUSE";
	public static final String LOV_TYPE_MODIFY_STATUS	= "MODIFY STATUS";	
	
	  //===========================================
	  //  Activity Type
	  //===========================================	
	public static final String ACTIVITY_TYPE_PRODUCTION		= "Production";
	public static final String ACTIVITY_TYPE_FEEDBACK		= "Feedback";
	
	  //===========================================
	  //  Project Type
	  //===========================================		
	public static final String PROJECT_TYPE_PRODUCTION	= "Production";
	public static final String PROJECT_TYPE_TRAINING	= "Training";	
	
	
	  //===========================================
	  //  Task Detail Type
	  //===========================================		
	public static final String TASK_DETAIL_LOD				= "LOD";	
	public static final String TASK_DETAIL_UV				= "UV";	
	
	
	  //===========================================
	  //  Subtask Status Type
	  //===========================================
	public static final String SUBTASK_STATUS_NOTSTARTED		= "Not Started";
	public static final String SUBTASK_STATUS_WIP				= "WIP";
	public static final String SUBTASK_STATUS_COMPLETE			= "Complete";
	public static final String SUBTASK_STATUS_SUBMITTED			= "Submitted";
	public static final String SUBTASK_STATUS_APPROVED			= "Approved";	
	
	public static final int SUBTASK_STATUS_NOTSTARTED_NUM		= 11;
	public static final int SUBTASK_STATUS_WIP_NUM				= 12;
	public static final int SUBTASK_STATUS_COMPLETE_NUM			= 13;
	public static final int SUBTASK_STATUS_SUBMITTED_NUM		= 14;
	public static final int SUBTASK_STATUS_APPROVED_NUM			= 15;
	
	 //===========================================
	 //  Group Type
	 //===========================================	
	public static final int USERGROUP_QA_NUM			= 2;
//	public static final int USERGROUP_PM_NUM			= 3;
//	public static final int USERGROUP_ADMIN_NUM			= 4;
	public static final int USERGROUP_INSPECTOR_NUM		= 5;
	
	 //===========================================
	 //  Action Log Entities
	 //===========================================	
	public static final String ENTITY_PROJECT	= "Project";
	public static final String ENTITY_TASK		= "Task";
	public static final String ENTITY_SUBTASK	= "Subtask";
	public static final String ENTITY_ACTIVITY	= "Activity";	
	
	 //===========================================
	 //  Action Log Actions
	 //===========================================		
	public static final String ACTION_CREATE	= "Create";
	public static final String ACTION_UPDATE	= "Update";
	public static final String ACTION_CANCEL	= "Cancel";
	public static final String ACTION_DELETE	= "Delete";	
	public static final String ACTION_REASSIGNED= "Reassign";	
	
	public static final String QA_FEEDBACK_CATEGORY_INTERNAL	= "Internal";
	public static final String QA_FEEDBACK_CATEGORY_CUSTOMER	= "Customer";
	
	public static final String CALENDAR_TYPE_HOLIDAY			= "HOLIDAY";
	public static final String CALENDAR_TYPE_WORKINGDAY			= "WORKING_DAY";
	
	public static final String REPORT_UT_CHART 					= "UT_CHART";
	public static final String REPORT_DETAIL_CHART 				= "DETAIL_INFO_CHART";
	public static final String REPORT_EFFICIENCY_QUALITY_CHART	= "EFFICIENCY_QUALITY_CHART";
	public static final String REPORT_PROJECT_RESOURCE_COST		= "PROJECT_RESOURCE_COST";
	public static final String REPORT_SAP_UT					= "SAP_UT";
	
	public static final String QA_WORK_ITEM_TYPE				= "CUSTOMER_FEEDBACK";
	public static final String QA_WORK_ITEM_STATUS_OPEN			= "OPEN";		
	public static final String QA_WORK_ITEM_STATUS_CLOSE		= "CLOSE";	
	
//	=========================================================
//		Time Units
//	=========================================================
	public static final String TIME_UNIT_DAY		= "day";
	public static final String TIME_UNIT_WEEK		= "week";
	public static final String TIME_UNIT_MONTH		= "month";
	
	//========================================================
	//	Work Time Per Day
	//========================================================
	public static final int WORK_HOURS_PER_DAY		= 8;
	
	public static final String PRIVILEGE_RW		= "R/W";
	public static final String PRIVILEGE_R		= "R";
	public static final String PRIVILEGE_NONE		= "None";
}