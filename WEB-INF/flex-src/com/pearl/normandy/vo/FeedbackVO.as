package com.pearl.normandy.vo
{
	import mx.collections.ArrayCollection;
	
	
	[Managed]
	[RemoteClass(alias="com.pearl.normandy.feedback.FeedbackTo")]	
	
	public class FeedbackVO
	{
		public function FeedbackVO()
		{
		}
		
		public var id:int;
		public var taskId:int;
		public var checkItemId:int;
		public var ownerId:int;
		public var category:String;		
		public var errorText:String;
		public var severity:String;
		public var type:String;		
		public var status:String;
		public var description:String;						
		public var version:int;
		public var createdDate:Date;
		public var createdBy:String;
		public var updatedDate:Date;
		public var updatedBy:String;
		
		public var projectKey:String;  
		public var taskName:String;
		public var ownerName:String;		
		public var severityValue:String;
		public var typeValue:String;
		public var statusValue:String;		
	    
	    public var feedbackReference:ArrayCollection;	    
	}
}