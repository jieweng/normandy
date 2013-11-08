package com.pearl.normandy.vo
{
	import com.pearl.normandy.utils.Constants;
	
	import flash.utils.ByteArray;
	
	import mx.collections.ArrayCollection;
	
	[Managed]
	[RemoteClass(alias="com.pearl.normandy.userpunctual.UserPunctual")]	
	
	public class UserPunctualVO
	{
		public function UserPunctualVO()
		{
		}
		public var id:int;
		public var userId:int;
		public var employeeNo:String;
		public var fullName:String;	    
	    public var userGroupName:String;    
	    public var searchStr:String;
	
		public var year:int;
		public var month:int;	
		
		public var number:Number = 0;
	}
}