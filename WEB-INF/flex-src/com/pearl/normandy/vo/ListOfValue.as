package com.pearl.normandy.vo
{
	[Managed]
	[RemoteClass(alias="com.pearl.normandy.listofvalue.ListOfValueTo")]	
	public class ListOfValue
	{
		public function ListOfValue()
		{
		}
		public var id:int;
		public var version:int;
		public var listKey:String;
		public var listType:String;
		public var listValue:String;
		public var createdDate:Date;
		public var createdBy:String;
		public var updatedDate:Date;
		public var updatedBy:String;
	}
}