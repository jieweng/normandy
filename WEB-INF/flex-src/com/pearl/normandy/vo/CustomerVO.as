package com.pearl.normandy.vo
{
	[Managed]
	[RemoteClass(alias="com.pearl.normandy.customer.CustomerTo")]	
	public class CustomerVO
	{
		public function CustomerVO()
		{
		}
		
		public var id:int;
		public var customerName:String;
		public var description:String;
		public var deleted:String;
		public var version:int;
		public var createdDate:Date;    
		public var createdBy:String;
		public var updatedDate:Date;
		public var updatedBy:String;

	}
}