package com.pearl.normandy.vo
{
	[Managed]
	[RemoteClass(alias="com.pearl.normandy.projectTimeValue.ProjectTimeValue")]
	
	public class ProjectTimeValueVO
	{
		public function ProjectTimeValueVO()
		{
		}
		
		public var id:int;
		public var projectId:int;
		public var time:Date;
		public var day:String;
		public var value:Number;

	}
}