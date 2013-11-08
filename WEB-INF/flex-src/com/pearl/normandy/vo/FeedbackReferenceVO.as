package com.pearl.normandy.vo
{
	
	[Managed]
	[RemoteClass(alias="com.pearl.normandy.feedbackreference.FeedbackReferenceTo")]	
	
	public class FeedbackReferenceVO
	{
		public function FeedbackReferenceVO()
		{
		}
		
		public var id:int;
		public var feedbackId:int;
		public var url:String;
		public var version:int;
		public var createdDate:Date;
		public var createdBy:String;
		public var updatedDate:Date;
		public var updatedBy:String;
		
		[Transient]
		public var imageUrl:String;
	}
}