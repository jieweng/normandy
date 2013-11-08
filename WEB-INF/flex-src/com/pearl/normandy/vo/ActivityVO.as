package com.pearl.normandy.vo
{
	import com.pearl.normandy.enum.ActivityEnum;
	import com.pearl.normandy.utils.NormandyModel;
	
	[Managed]
	[RemoteClass(alias="com.pearl.normandy.activity.ActivityTo")]	
	
	public class ActivityVO
	{
		public function ActivityVO()
		{
		}
		
		public var id:int;
		public var resourceId:int;
		public var version:int;
		public var taskId:int;	
		public var projectId:int;			
		public var name:String;		
		public var activityType:String;
		public var startTime:Date;
		public var endTime:Date;
		public var actualStartTime:Date;
		public var actualEndTime:Date;
		public var actualStaffDays:Number;
		public var assignedEffort:Number;
		public var remainingEffort:Number;
		public var progress:int;
		public var trainingFlag:String="N";
		public var deleted:String;
		public var paused:String;
		public var description:String;
		public var createdDate:Date;
		public var createdBy:String;
		public var updatedDate:Date;
		public var updatedBy:String;
		public var medal:int=3;	
		public var proved:String;
		public var provedDate:Date;
		public var provedBy:String;	

		public var projectKey:String;
		public var projectName:String;	
		public var milestone:String;	
		public var taskOwnerId:int
		public var taskOwnerName:String;
		public var taskCategory:String;
		public var taskReferenceUrl:String;
		public var taskDescription:String;
		public var statusId:int;
		public var status:String;
		public var filePath:String;
		public var resourceName:String;		
		public var createdByName:String;
		public var medalLocked:String='N';
		public var medalEdited:String='N';
		public var taskParentReferenceUrl:String;
		
		[Transient]
		public var selected:Boolean = false;
		[Transient]
		public var highlighted:Boolean = false;
		[Transient]	
		public var zOrder:int = 0;	
		
	//--------------------------------------------------------------------------
    //
    //  Private variables
    //
    //--------------------------------------------------------------------------
    	private static var model:NormandyModel = NormandyModel.getInstance();
    	
    	
	//--------------------------------------------------------------------------
    //
    //  Methods
    //
    //--------------------------------------------------------------------------    			
		public static function getTaskReferenceUrl(activity:ActivityVO):String{
			if(activity){
				
				if(activity.taskReferenceUrl!=null && activity.taskReferenceUrl!=""){
					return model.metaData.thumbnailUrl + activity.projectId + "/" + activity.taskReferenceUrl; 
				}
				else if(activity.taskParentReferenceUrl!=null && activity.taskParentReferenceUrl!=""){
					return model.metaData.thumbnailUrl + activity.projectId + "/" + activity.taskParentReferenceUrl; 
				}
				else return null;
			}
			else{
				return null;
			}
		}

		public static function isProducton(activity:ActivityVO):Boolean{
			if(activity.activityType == ActivityEnum.PRODUCTION || activity.activityType == ActivityEnum.FEEDBACK){
	  			return true;
	  		}
	  		else{
	  			return false;
	  		}
		}
		
		public static function isNonProducton(activity:ActivityVO):Boolean{
			if(activity.activityType == ActivityEnum.MANAGEMENT || activity.activityType == ActivityEnum.PROJECT_TRAINING
				|| activity.activityType == ActivityEnum.DOWN_TIME_TRAINING || activity.activityType == ActivityEnum.COMPENSATION_OFF
				|| activity.activityType == ActivityEnum.QA	|| activity.activityType == ActivityEnum.WAIT_FEEDBACK 
				|| activity.activityType == ActivityEnum.RECRUITMENT || activity.activityType == ActivityEnum.PAID_LEAVE	
				|| activity.activityType == ActivityEnum.UNPAID_LEAVE){
	  			return true;
	  		}
	  		else{
	  			return false;
	  		}
		}
		
		public static function isProjectReason(activity:ActivityVO):Boolean{
			if(activity.activityType == ActivityEnum.MANAGEMENT || activity.activityType == ActivityEnum.COMPENSATION_OFF
							|| activity.activityType == ActivityEnum.PROJECT_TRAINING){
				return true;
			}else{
				return false;
			}
		}
		
		public static function isNonProjectReason(activity:ActivityVO):Boolean{
			if(activity.activityType == ActivityEnum.DOWN_TIME_TRAINING || activity.activityType == ActivityEnum.PAID_LEAVE
							|| activity.activityType == ActivityEnum.QA || activity.activityType == ActivityEnum.RECRUITMENT
							|| activity.activityType == ActivityEnum.UNPAID_LEAVE || activity.activityType == ActivityEnum.WAIT_FEEDBACK){
				return true;
			}else{
				return false;
			}
		}
	}
}