package com.pearl.normandy.event
{
	import mx.events.MenuEvent;
	
	public class RightMenuEvent extends MenuEvent
	{
		public static const RIGHT_MENU_EVENT:String	= "rightMenuEvent";
        public function RightMenuEvent(type:String) {
                super(type);
        }
	}
}