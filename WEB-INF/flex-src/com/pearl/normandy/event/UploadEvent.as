package com.pearl.normandy.event
{	    
    import flash.events.Event;
    
    import mx.collections.ArrayCollection;

    public class UploadEvent extends Event
    {
    	public static const COMPLETE:String	= "complete";
    	
    	public var url:String;
    	public var referenceList:ArrayCollection;
    	
        public function UploadEvent(type:String, url:String=null,referenceList:ArrayCollection=null) {
                super(type);
    			this.url = url;
    			this.referenceList = referenceList;
        }        

        override public function clone():Event {
            return new UploadEvent(type, url , referenceList);
        }
    }	
}