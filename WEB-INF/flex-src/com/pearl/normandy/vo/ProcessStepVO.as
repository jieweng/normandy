package com.pearl.normandy.vo
{
	import mx.collections.ArrayCollection;
	
	
	[Managed]
	[RemoteClass(alias="com.pearl.normandy.processstep.ProcessStepTo")]	
	public class ProcessStepVO
	{
		public function ProcessStepVO()
		{
		}
		
		public var id:int;
		public var productionProcessId:int;
		public var step:String;
		public var sequence:int;
		public var deliverable:String;
		public var description:String;
		public var deleted:String;
		public var version:int;
		public var createdDate:Date;
		public var createdBy:String;
		public var updatedDate:Date;
		public var updatedBy:String;
	}
}