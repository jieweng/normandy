package com.pearl.normandy.vo
{
	import com.pearl.normandy.utils.Constants;
	
	import flash.utils.ByteArray;
	
	import mx.collections.ArrayCollection;
	
	[Managed]
	[RemoteClass(alias="com.pearl.normandy.sysConfig.SysConfig")]	
	
	public class SysConfigVO
	{
		public function SysConfigVO()
		{
		}
		public var key:String;
	    public var value:String;


	}
}