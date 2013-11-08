package com.pearl.normandy.event
{	    
    import flash.events.Event;

    public class ReloadEvent extends Event
    {
    	public static const RELOAD:String	= "reload";
    	
    	public var reloadKind:String;
    			
        public function ReloadEvent(type:String, reloadKind:String) {
                super(type);
    			this.reloadKind = reloadKind;
        }

        override public function clone():Event {
            return new ReloadEvent(type, reloadKind);
        }
    }	
}