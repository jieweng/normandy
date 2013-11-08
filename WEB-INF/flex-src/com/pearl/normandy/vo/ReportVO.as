package com.pearl.normandy.vo
{
	import mx.collections.ArrayCollection;
	
	[Managed]
	[RemoteClass(alias="com.pearl.normandy.report.ReportTo")]	
	
	public class ReportVO
	{
		public function ReportVO()
		{
		}
		
		public var employeeNo:String;
		public var taskId:int;
		public var category:String;
		public var fullName:String;
		public var entryDate:Date;
		public var leaveDate:Date;
		public var charts:ArrayCollection;
		
		//Burndown Chart Attributes
		public var effort:Number;
		public var day:String;		
		public var availability:int;
		
		public var name:String;
		public var value:Number;
		public var date:Date;
		public var year:int;
		public var quarter:String;
		public var month:int;		
		
		public var allFeedback:ArrayCollection;
		public var openFeedback:ArrayCollection;
		public var closedFeedback:ArrayCollection;
		
		public var project:String;
		public var projectId:int;
		public var milestone:String;
		
		//Project Resource Cost Attributes
		public var userId:int;
		public var manDays:Number;
		public var salary:Number;
		public var socialBenefit:Number;
		public var insurance:Number;
		
		public var reports:ArrayCollection;
	}
}