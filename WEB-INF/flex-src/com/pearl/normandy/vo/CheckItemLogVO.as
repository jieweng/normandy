package com.pearl.normandy.vo
{
	[Managed]
	[RemoteClass(alias="com.pearl.normandy.checkitemlog.CheckItemLogTo")]	
	public class CheckItemLogVO
	{
		public function CheckItemLogVO()
		{
		}
		
		public var id:int;
		public var checkItemId:int;
		public var status:String;
		public var comment:String;
		public var createdDate:Date;
		public var createdBy:String;
			
		public var projectId:int;
		public var projectName:String;
		public var taskId:String;
		public var taskName:String;
		public var taskCategory:String;
		public var taskGroup:String;
		public var ownerId:int;
		public var owner:String;
		public var priority:String;
		public var resourceName:String;
		public var activityName:String;
	}
}