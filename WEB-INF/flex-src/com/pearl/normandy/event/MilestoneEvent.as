package com.pearl.normandy.event
{	        
    import flash.events.Event;

    public class MilestoneEvent extends Event
    {
    	public static const MILESTONE_CREATED:String	= "milestoneCreated";
    			
    			
        public function MilestoneEvent(type:String) {
                super(type);
        }

        override public function clone():Event {
            return new MilestoneEvent(type);
        }
    }	
}