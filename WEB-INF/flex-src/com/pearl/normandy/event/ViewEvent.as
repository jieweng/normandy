package com.pearl.normandy.event
{	    
    import flash.events.Event;    
    import mx.core.Container;

    public class ViewEvent extends Event
    {    	    	    	
		public static const CHANGE:String 		= "change";    	
    	
    	public var view:Container
    			
        public function ViewEvent(type:String, view:Container) {
                super(type);
    			this.view 	= view;
        }

        override public function clone():Event {
            return new ViewEvent(type, view);
        }
    }	
}