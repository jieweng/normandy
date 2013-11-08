package com.pearl.normandy.commands.activity
{
	import com.adobe.cairngorm.business.Responder;
	import com.adobe.cairngorm.commands.Command;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.pearl.normandy.delegates.ActivityDelegate;
	import com.pearl.normandy.event.ActivityOptEvent;
	import com.pearl.normandy.utils.NormandyModel;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.events.ResultEvent;
	
	public class UpdateActivitiesCommand implements Command, Responder
	{
		private var model:NormandyModel = NormandyModel.getInstance();
		
        public function execute( event : CairngormEvent ) : void
        {           
            var delegate : ActivityDelegate = new ActivityDelegate( this );   
            var e : ActivityOptEvent = ActivityOptEvent( event );  
            delegate.updateActivities(e.activities, e.user);          
        }
           
        public function onResult( event : * = null ) : void
        {   
        	var e:ResultEvent = ResultEvent(event);       
        	model.GLOBAL_UPDATED_ACTIVITIES = ArrayCollection(e.result);                 	
        }
                
        public function onFault( event : * = null ) : void
        {
        }

	}
}