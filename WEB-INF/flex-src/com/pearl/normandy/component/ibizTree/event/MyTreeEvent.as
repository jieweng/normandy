package com.pearl.normandy.component.ibizTree.event
{
	import flash.events.Event;
	
	import mx.controls.listClasses.IListItemRenderer;
	import mx.events.TreeEvent;
	
	public class MyTreeEvent extends TreeEvent
	{
		public static const ADD_ITEM:String = "addItem"
		public static const DELETE_ITEM:String = "deleteItem";
		public var parent:Object;
		public function MyTreeEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false, item:Object=null, itemRenderer:IListItemRenderer=null, triggerEvent:Event=null){
			super(type, bubbles, cancelable, item, itemRenderer, triggerEvent);
		}
	}
}  