package com.pearl.normandy.vo
{
	import mx.collections.ArrayCollection;
	
	[Managed]
	[RemoteClass(alias="com.pearl.normandy.usergroup.UserGroupTo")]	
		
	public class UserGroupVO
	{
		public function UserGroupVO()
		{
		}
		
		public var id:int;
		public var groupName:String;
		public var production:String;
		public var state:String;
		public var description:String;
		public var deleted:String;
		public var version:int;
		public var createdDate:Date;
		public var createdBy:String;
		public var updatedDate:Date;
		public var updatedBy:String;
		public var users:Object;
		
		public var taskCategories:ArrayCollection;
	}
}