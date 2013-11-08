package com.pearl.normandy.vo
{
	import mx.collections.ArrayCollection;
	
	[Managed]
	[RemoteClass(alias="com.pearl.normandy.productionprocess.ProductionProcessTo")]	
	public class ProductionProcessVO
	{
		public function ProductionProcessVO()
		{
		}
		
		public var id:int;
		public var version:int;
		public var projectId:int;
		public var processName:String;
		public var description:String;
		public var deleted:String;
		public var createdDate:Date;
		public var createdBy:String;
		public var updatedDate:Date;
		public var updatedBy:String;
		
		public var processSteps:ArrayCollection = new ArrayCollection();
	}
}