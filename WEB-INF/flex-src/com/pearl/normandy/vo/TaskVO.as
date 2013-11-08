package com.pearl.normandy.vo
{
	import com.adobe.cairngorm.vo.ValueObject;
	import com.pearl.normandy.utils.NormandyModel;
	
	import mx.collections.ArrayCollection;
	
	[Managed]
	[RemoteClass(alias="com.pearl.normandy.task.TaskTo")]
	
	public class TaskVO implements ValueObject
	{
		public function TaskVO()
		{
		}		
		
		//Mapping to Task
		public var id:int;
		public var parentId:int = 0;
		public var taskId:String;
		public var projectId:int;		

		public var name:String;
		public var parentName:String;
		public var type:String;		
		public var productionProcessId:int;
		public var milestone:String;		
		public var taskGroup:String;
		public var taskCategory:String;
		public var taskPriorityId:int;
		public var isFeedback:String;		
		public var referenceUrl:String;			
		public var description:String;

		public var startTime:Date;
		public var endTime:Date;
		public var duration:Number=0;		
		public var actualStartTime:Date;
		public var actualEndTime:Date;
		public var plannedStaffDays:Number=0;
		public var plannedFeedbackDays:Number=0;
		
		public var statusId:int;
		public var progress:int;
		public var ownerId:int;
		public var qaOwnerId:int;
		public var deleted:String;
		public var paused:String;

		public var sequence:int;
		public var predecessor:String;
		public var predecessorTree:String;
		public var deviation:Number=0;		

		public var revision:int;			
		public var version:int;
		public var createdDate:Date;
		public var createdBy:String;
		public var updatedDate:Date;
		public var updatedBy:String;
		public var staffAssigned:Number = 0;




		//Mapping to TaskTo
		public var projectKey:String;
		public var projectName:String;			
		public var productionProcess:String;		
		public var priority:String;
		public var owner:String;
		public var qaOwner:String;
		public var resourcesName:String;			
		public var actualStaffDays:Number;
		public var staffDaysDifference:Number;
		public var nextDeliveryDue:Date;
		public var status:String;		
		public var taskProgress:String;
		public var completion:Number;
		public var baselineStart:Date;
		public var baselineEnd:Date;
		public var details:Object;
		
		public var children:ArrayCollection;	
		public var feedbackNum:int;
		[Transient]
//		public var selected:Boolean;	
		
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
		public static function getReferenceUrl(task:TaskVO):String{
			if(task && task.referenceUrl!=null && task.referenceUrl!=""){
				return model.metaData.thumbnailUrl + task.projectId + "/" + task.referenceUrl; 
			}
			else{
				return null;
			}
		}
	}		
}