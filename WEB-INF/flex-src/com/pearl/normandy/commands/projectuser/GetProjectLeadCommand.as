package com.pearl.normandy.commands.projectuser
{
	import com.adobe.cairngorm.business.Responder;
	import com.adobe.cairngorm.commands.Command;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.pearl.normandy.delegates.ProjectUserDelegate;
	import com.pearl.normandy.event.ProjectUserEvent;
	import com.pearl.normandy.utils.NormandyModel;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.events.ResultEvent;
	
	public class GetProjectLeadCommand implements Command, Responder
	{
		private var model:NormandyModel = NormandyModel.getInstance();
		
        public function execute( event : CairngormEvent ) : void
        {           
            var delegate : ProjectUserDelegate = new ProjectUserDelegate( this );   
            var e : ProjectUserEvent = ProjectUserEvent( event );  
            delegate.getProjectLead(e.project, e.getDefault);          
        }
           
        public function onResult( event : * = null ) : void
        {                 
        	var e:ResultEvent = ResultEvent(event);       
            model.leads = e.result as ArrayCollection;
        }
                
        public function onFault( event : * = null ) : void
        {
        }

	}
}