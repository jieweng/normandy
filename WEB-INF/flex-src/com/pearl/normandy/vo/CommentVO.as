package com.pearl.normandy.vo
{
	[Managed]
	[RemoteClass(alias="com.pearl.normandy.comment.CommentTo")]	
	public class CommentVO
	{
		public function CommentVO()
		{
		}
		public var id:int;
		public var version:int;
		public var taskId:int;		
		public var comment:String;
		public var createdDate:Date;
		public var createdBy:String;
		public var updatedDate:Date;
		public var updatedBy:String;
	}
}