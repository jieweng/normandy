package com.pearl.normandy.vo
{
	import mx.collections.ArrayCollection;
	
	[Managed]
	[RemoteClass(alias="com.pearl.normandy.projectuser.ProjectUserTo")]	
	
	public class ProjectUserVO
	{
		public function ProjectUserVO()
		{
		}
		
		public var id:int;
		public var userId:int;
		public var projectId:int;
		public var projectRoleId:int;		
		public var supervisorId:int;
		public var status:String;
		public var version:int;
		public var createdDate:Date;
		public var createdBy:String;
		public var updatedDate:Date;
		public var updatedBy:String;
		public var releaseDate:Date

		
		public var projectRoleName:String;
		public var projectName:String;		
		public var supervisor:String;
		public var name:String;
		public var firstName:String;
		public var lastName:String;
		public var children:ArrayCollection;
		
		public var startDate:Date;
		public var endDate:Date;
		public var isQA:int;
		public var isAD:int;
		public var isPM:int;
	}
}