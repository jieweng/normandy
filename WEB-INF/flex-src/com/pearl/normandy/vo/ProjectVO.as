package com.pearl.normandy.vo
{
	import mx.collections.ArrayCollection;
	
	[Managed]
	[RemoteClass(alias="com.pearl.normandy.project.ProjectTo")]	
	public class ProjectVO
	{
		public function ProjectVO()
		{
		}
	   	public var id:int;
	   	public var customerId:int;
     	public var version:int;
     	public var projectName:String;
     	public var type:String;
     	public var status:String;
     	public var url:String;
     	public var description:String;
     	public var deleted:String;
     	public var createdDate:Date;
     	public var createdBy:String;
     	public var updatedDate:Date;
     	public var updatedBy:String;
     	public var projectKey:String;
     	public var needQa:String;
     	public var difficulty:int;
     	public var checkList:String;
     	public var baselineRevision:int;
     	
     	public var customerName:String;
     	public var revision:int;     	
     	public var planned:int;
		public var approved:int;
		public var progress:Number;
		public var progressInNum:Number;
		public var plannedStaffDays:Number;
		public var actualStaffDays:Number;
		public var management:Number;
		public var compOff:Number;
		public var projectTraining:Number;	
	}
}