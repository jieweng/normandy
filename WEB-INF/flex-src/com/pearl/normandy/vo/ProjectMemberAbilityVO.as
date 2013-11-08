package com.pearl.normandy.vo
{
	[Managed]
	[RemoteClass(alias="com.pearl.normandy.projectmemberability.ProjectMemberAbilityTo")]
	public class ProjectMemberAbilityVO
	{
		public var id:int;
		public var projectId:int;
		public var userId:int;
		public var year:int;
		public var month:int;
		public var difficulty:int;
		public var levelId:int;
		public var highPoly:String;			//高模
		public var lowPoly:String;			//低模
		public var zbrush:String;			
		public var texture:String;			//贴图
		public var integration:String;		//整合
		public var colligation:String;		//绑定
		public var engine:String;			//引擎
		public var initiative:String;		//主动性
		public var communicate:String;		//合作沟通
		public var artCulture:String;		//艺术修养
		public var innovation:String;		//学习创新
		public var punctual:String;			//出勤
		public var contribute:String;		//贡献
		public var remark:String;
		public var assignedEffort:Number;
		public var productivity:String;
		public var taskQuality:Number;
		public var sun:Number;
		public var luna:Number;
		public var star:Number;
		public var starlight:Number;
		public var stardust:Number;
		
		public var projectName:String;
		public var userName:String;
		public var name:String;
		public var userGroupName:String;

	}
}