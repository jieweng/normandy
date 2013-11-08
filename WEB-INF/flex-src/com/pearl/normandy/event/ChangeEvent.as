package com.pearl.normandy.event
{	    
    import flash.events.Event;

    public class ChangeEvent extends Event
    {
    	public static const LEAD_CHANGE:String	= "leadChange";
    	public static const LEAD_REPLACE:String = "leadReplace"
    	
    	public var data:Object;
    			
        public function ChangeEvent(type:String, data:Object) {
                super(type);
    			this.data = data;
        }

        override public function clone():Event {
            return new ChangeEvent(type, data);
        }
    }	
}