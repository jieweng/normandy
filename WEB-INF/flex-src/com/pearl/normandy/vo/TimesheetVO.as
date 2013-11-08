package com.pearl.normandy.vo
{
	import mx.collections.ArrayCollection;
	
	[Managed]
	[RemoteClass(alias="com.pearl.normandy.timesheet.TimesheetTo")]
	
	public class TimesheetVO
	{
		public var id:String;
		public var userId:int;
		public var taskId:int;
		public var activityId:int;
		public var type:String;
		public var date:Date;
		public var spentEffort:Number;
		public var version:int;
		public var createdDate:Date;
		public var createdBy:String;
		public var updatedDate:Date;
		public var updatedBy:String;	
	}
}