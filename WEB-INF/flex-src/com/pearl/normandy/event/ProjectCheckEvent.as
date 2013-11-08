package com.pearl.normandy.event
{
	import flash.events.Event;
	
	public class ProjectCheckEvent extends Event
	{
    	public static const DELETE:String	= "delete";
    	public var items:Array;
		public function ProjectCheckEvent(type:String)
		{
			super(type);
		}
	}
}