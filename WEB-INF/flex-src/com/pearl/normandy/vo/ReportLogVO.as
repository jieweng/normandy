package com.pearl.normandy.vo
{
	import mx.collections.ArrayCollection;
	
	[Managed]
	[RemoteClass(alias="com.pearl.normandy.reportlog.ReportLogTo")]	
	
	public class ReportLogVO
	{
		public function ReportLogVO()
		{
		}
		public var id:int;
		public var reportType:String;
		public var year:int;
		public var month:int;
		public var createdDate:Date;
		public var url:String;
	}
}