package com.pearl.normandy.vo
{
	[Managed]
	[RemoteClass(alias="com.pearl.normandy.calendar.CalendarTo")]
	public class CalendarVO
	{
		public function CalendarVO()
		{
		}
		
	public var id:int;
	public var type:String;
	public var date:Date;
	
	}
}