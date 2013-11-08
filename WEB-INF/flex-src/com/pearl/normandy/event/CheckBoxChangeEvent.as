package com.pearl.normandy.event
{
	import flash.events.Event;

	public class CheckBoxChangeEvent extends Event
	{
		public static const CHANGE:String = "checkBoxChange";
		public static const RESULT:String = "result";
		
		public var object:Object;
		
		public function CheckBoxChangeEvent(type:String, object:Object, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
			this.object = object;
		}
		
	}
}