package com.pearl.normandy.enum
{
	import mx.collections.ArrayCollection;
	
	
	public final class ActivityEnum
	{
		public static const PRODUCTION:String			= "Production";
		public static const FEEDBACK:String				= "Feedback";
		public static const MANAGEMENT:String			= "Management";
		public static const PROJECT_TRAINING:String		= "Project Training";
		public static const DOWN_TIME_TRAINING:String 	= "Down Time Training";
		public static const RECRUITMENT:String			= "Recruitment";		
		public static const PAID_LEAVE:String			= "Paid Leave";
		public static const UNPAID_LEAVE:String			= "Unpaid Leave";
		public static const QA:String					= "QA";
		public static const COMPENSATION_OFF:String		= "Comp Off";
		public static const WAIT_FEEDBACK:String		= "Wait Feedback";
		
		public static const MANAGEMENT_L:String				= "项目管理(Management)";
		public static const PROJECT_TRAINING_L:String		= "项目内培训(Project Training)";
		public static const DOWN_TIME_TRAINING_L:String		= "项目外培训(Down Time Training)";
		public static const RECRUITMENT_L:String			= "招聘(Recruitment)";
		public static const PAID_LEAVE_L:String				= "带薪假(Company Paid Leave)";
		public static const	UNPAID_LEAVE_L:String			= "不带薪假(Unpaid Leave)";
		public static const WAIT_FEEDBACK_L:String			= "等反馈(Wait Feedback)";
		public static const COMPENSATION_OFF_L:String		= "调休(Comp Off)";
		
		public static const NON_PRODUCTION_TYPES:ArrayCollection = new ArrayCollection([			
			{value:ActivityEnum.MANAGEMENT, 			label:ActivityEnum.MANAGEMENT_L},
			{value:ActivityEnum.PAID_LEAVE, 			label:ActivityEnum.PAID_LEAVE_L}, 
			{value:ActivityEnum.UNPAID_LEAVE, 			label:ActivityEnum.UNPAID_LEAVE_L},
			{value:ActivityEnum.COMPENSATION_OFF, 		label:ActivityEnum.COMPENSATION_OFF_L},												    
			{value:ActivityEnum.QA, 					label:ActivityEnum.QA},
			{value:ActivityEnum.PROJECT_TRAINING, 		label:ActivityEnum.PROJECT_TRAINING_L},
			{value:ActivityEnum.DOWN_TIME_TRAINING, 	label:ActivityEnum.DOWN_TIME_TRAINING_L}, 												   											   
			{value:ActivityEnum.WAIT_FEEDBACK, 			label:ActivityEnum.WAIT_FEEDBACK_L},
			{value:ActivityEnum.RECRUITMENT, 			label:ActivityEnum.RECRUITMENT_L}	
		]);
		
		public static const PROJECT_REASON:ArrayCollection = new ArrayCollection([
			{value:ActivityEnum.MANAGEMENT, 			label:ActivityEnum.MANAGEMENT_L},
			{value:ActivityEnum.PROJECT_TRAINING, 		label:ActivityEnum.PROJECT_TRAINING_L},
			{value:ActivityEnum.COMPENSATION_OFF, 		label:ActivityEnum.COMPENSATION_OFF_L}
		]);
		
		public static const OTHER_REASON:ArrayCollection  = new ArrayCollection([
			{value:ActivityEnum.PAID_LEAVE, 			label:ActivityEnum.PAID_LEAVE_L}, 
			{value:ActivityEnum.UNPAID_LEAVE, 			label:ActivityEnum.UNPAID_LEAVE_L},
			//{value:ActivityEnum.QA, 					label:ActivityEnum.QA},
			//{value:ActivityEnum.DOWN_TIME_TRAINING, 	label:ActivityEnum.DOWN_TIME_TRAINING_L}, 												   											   
			{value:ActivityEnum.WAIT_FEEDBACK, 			label:ActivityEnum.WAIT_FEEDBACK_L},
			{value:ActivityEnum.RECRUITMENT, 			label:ActivityEnum.RECRUITMENT_L}	
		]);
	}	
}