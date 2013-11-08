package com.pearl.normandy.component.ibizDataGrid.event
{
	import com.pearl.normandy.vo.ProjectUserVO;
	
	import flash.events.Event;
	
	public class UserDataGridEvent extends Event
	{
		public static const ADD_ITEM:String = "addItem"
		public var item:ProjectUserVO;
		public var parent:Object;
		public function UserDataGridEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
		}

	}
}