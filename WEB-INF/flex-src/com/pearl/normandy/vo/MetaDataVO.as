package com.pearl.normandy.vo
{
	import mx.collections.ArrayCollection;
	
	[Managed]
	[RemoteClass(alias="com.pearl.normandy.metadata.MetaData")]		
	public class MetaDataVO
	{
		public function MetaDataVO()
		{
		}
		
		public var referenceUrl:String;
		public var referenceTempUrl:String;
		public var reportUrl:String;
		public var mpxjUrl:String;
		public var thumbnailUrl:String;
		
		public var activityLockedTime:Date;
		
		public var userGroups:ArrayCollection;
		public var productionGroup:ArrayCollection;
		public var projectUsers:ArrayCollection;
		public var holidays:ArrayCollection;
		public var taskCategories:ArrayCollection;
		public var priorities:ArrayCollection;
		public var severities:ArrayCollection;
		public var taskStatuses:ArrayCollection;
		public var rootCauses:ArrayCollection;
		public var modifyStatues:ArrayCollection;

	}
}