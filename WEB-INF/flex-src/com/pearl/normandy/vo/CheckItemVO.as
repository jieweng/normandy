package com.pearl.normandy.vo
{
	import mx.collections.ArrayCollection;
	
	[Managed]
	[RemoteClass(alias="com.pearl.normandy.checkitem.CheckItemTo")]	
	public class CheckItemVO
	{
		public function CheckItemVO()
		{
		}
		
		public var id:int;
		public var activityId:int;
		public var status:String;
		public var comment:String;
		public var createdDate:Date;
		public var createdBy:String;
		public var updatedDate:Date;
		public var updatedBy:String;
			
		public var projectId:int;
		public var projectName:String;
		public var parentId:int;
		public var parentName:String;
		public var taskId:int;
		public var taskName:String;
		public var taskCategory:String;
		public var taskGroup:String;
		public var ownerId:int;
		public var owner:String;
		public var priority:String;
		public var activityName:String;
		public var userId:int;
		public var resourceName:String;
		public var feedbacks:ArrayCollection;
		public var deleted:String;
		
		[Transient]
		public var isPass:Boolean = false;;
		[Transient]
		public var isFail:Boolean = false;
		[Transient]
		public var isDeleted:Boolean = false;
	}
}