package com.pearl.normandy.component.popTextInput
{
	import flash.events.Event;

	public class SearchEvent extends Event
	{
		public static const	SUBMIT:String	= "onChange";
		public var selectedItem:Object;
		public var selectedIndex:int;
		public function SearchEvent(type:String,obj:Object,index:int)
		{
			super(type);
			this.selectedItem=obj;
			this.selectedIndex=index;
		}
		override public function clone():Event {
            return new SearchEvent(type, selectedItem,selectedIndex);
        }
	}
}