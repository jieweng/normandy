package com.pearl.normandy.commands.project
{
	import com.adobe.cairngorm.business.Responder;
	import com.adobe.cairngorm.commands.Command;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.pearl.normandy.delegates.ProjectDelegate;
	import com.pearl.normandy.event.ProjectEvent;
	import com.pearl.normandy.utils.NormandyModel;
	import com.pearl.normandy.vo.ProjectVO;
	
	import mx.rpc.events.ResultEvent;
	
	public class UpdateProjectCommand implements Command, Responder
	{
		private var model:NormandyModel = NormandyModel.getInstance();
		
        public function execute( event : CairngormEvent ) : void
        {           
            var delegate : ProjectDelegate = new ProjectDelegate( this );   
            var e : ProjectEvent = ProjectEvent( event );  
            delegate.updateProject(e.project, e.customer, e.user);          
        }
           
        public function onResult( event : * = null ) : void
        {                 
	
        }
                
        public function onFault( event : * = null ) : void
        {
        }

	}
}