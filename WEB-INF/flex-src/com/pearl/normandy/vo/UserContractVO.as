package com.pearl.normandy.vo
{
	[Managed]
	[RemoteClass(alias="com.pearl.normandy.userContract.UserContract")]
	public class UserContractVO
	{
		public function UserContractVO()
		{
		}
		
		public var id:int;
		public var userId:int;
		public var startTime:Date;
		public var duration:Number = 0;
		public var creatorId:int;
		public var createTime:Date;		

	}
}