package com.pearl.normandy.vo
{
	import mx.collections.ArrayCollection;
	
	[Managed]
	[RemoteClass(alias="com.pearl.normandy.projectmember.ProjectMemberTo")]	
	public class ProjectMemberVO
	{
		public function ProjectMemberVO()
		{
		}
		
		public var id:int;
		public var userId:int;
		public var resourceId:int;
		public var projectId:int;
		public var name:String;
		public var status:String;
		public var version:int;
		public var createdDate:Date;
		public var createdBy:String;
		public var updatedDate:Date;
		public var updatedBy:String;
		public var releaseDate:Date;
		public var privTask1:String = "None";
		public var privTask2:String = "None";
		public var privResource:String = "None";
		public var privPrivilege:String = "N";
		public var privMail:String = "N";
		public var privPerformance:String = "N";
		
		public var userName:String;
		public var englishName:String;
		public var projectName:String;
		public var createdByName:String;
		
		public var startTime:Date;
		public var endTime:Date;
		public var isQA:int;
		public var isAD:int;
		public var isPM:int;
		
		public var memberType:String;
		public var children:ArrayCollection;
		public var employeeNo:String;
		public var email:String;
		public var isProjectUser:String;
	}
}