package com.pearl.normandy.commands.process
{
	import com.adobe.cairngorm.business.Responder;
	import com.adobe.cairngorm.commands.Command;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.pearl.normandy.delegates.ProcessDelegate;
	import com.pearl.normandy.event.ProcessEvent;
	import com.pearl.normandy.utils.NormandyModel;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.events.ResultEvent;
	
	public class GetProjectProcessCommand implements Command, Responder
	{
		private var model:NormandyModel = NormandyModel.getInstance();
		
        public function execute( event : CairngormEvent ) : void
        {           
            var delegate : ProcessDelegate = new ProcessDelegate( this );   
            var e : ProcessEvent = ProcessEvent( event );  
            delegate.getProjectProcess(e.project, e.getDefault);          
        }
           
        public function onResult( event : * = null ) : void
        {                 
        	var e:ResultEvent = ResultEvent(event);       
			model.LOCAL_PROJECT_PROCESSES = event.result as ArrayCollection;	
        }
                
        public function onFault( event : * = null ) : void
        {
        }

	}
}