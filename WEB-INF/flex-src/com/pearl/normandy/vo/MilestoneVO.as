package com.pearl.normandy.vo
{
	[Managed]
	[RemoteClass(alias="com.pearl.normandy.milestone.MilestoneTo")]	
		
	public class MilestoneVO
	{
		public function MilestoneVO()
		{
		}

		public var id:int;
		public var projectId:int;
		public var milestone:String;
		public var description:String;
		public var completionDate:Date;
		public var objective:String;
		public var assumption:String;
		public var constraints:String;
		public var deleted:String;
		public var version:int;
		public var createdDate:Date;
		public var createdBy:String;
		public var updatedDate:Date;
		public var updatedBy:String;
	}
}