package com.pearl.normandy.vo
{
	import mx.collections.ArrayCollection;
	
	[Managed]
	[RemoteClass(alias="com.pearl.normandy.taskdetail.TaskDetailTo")]
	
	public class TaskDetailVO
	{
		public var id:String;
		public var taskId:int;
		public var type:String;
		public var level:int;
		public var value:String;
		public var version:int;
		public var createdDate:Date;
		public var createdBy:String;
		public var updatedDate:Date;
		public var updatedBy:String;
	}
}