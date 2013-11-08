package com.pearl.normandy.commands.project
{
	import com.adobe.cairngorm.business.Responder;
	import com.adobe.cairngorm.commands.Command;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.pearl.normandy.delegates.ProjectDelegate;
	import com.pearl.normandy.event.ProjectEvent;
	import com.pearl.normandy.utils.NormandyModel;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.events.ResultEvent;
	
	public class GetAllProjectCommand implements Command, Responder
	{
		private var model:NormandyModel = NormandyModel.getInstance();
		
        public function execute( event : CairngormEvent ) : void
        {           
            var delegate : ProjectDelegate = new ProjectDelegate( this );   
            var e : ProjectEvent = ProjectEvent( event );  
            delegate.getAllProject();          
        }
           
        public function onResult( event : * = null ) : void
        {                 
        	var e:ResultEvent = ResultEvent(event);       
			model.GLOBAL_ALL_PROJECTS = event.result as ArrayCollection;	
        }
                
        public function onFault( event : * = null ) : void
        {
        }

	}
}