package com.pearl.normandy.event
{	    
    import flash.events.Event;

    public class PopupEvent extends Event
    {
    	public static const POP_UP:String	= "popup";
    	
    			
        public function PopupEvent(type:String) {
                super(type);
        }

        override public function clone():Event {
            return new PopupEvent(type);
        }
    }	
}