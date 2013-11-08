package com.pearl.normandy.vo
{
	[Managed]
	[RemoteClass(alias="com.pearl.normandy.userPrivilege.UserPrivilege")]
	
	public class UserPrivilegeVO
	{
		public function UserPrivilegeVO()
		{
		}
		
		public var id:int;
		public var userId:int;
		public var project:String = "None";
		public var resource:String = "None";
		public var report:String  = "Level1";
		public var user:String = "N";
		public var userGroup:String = "N";
		public var holiday:String = "N";
		public var userPunctual:String = "N";
		public var loginPicture:String = "N";
		public var levelShow:String = "N";
	}
}