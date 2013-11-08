package com.pearl.normandy.commands.task
{
	import com.adobe.cairngorm.business.Responder;
	import com.adobe.cairngorm.commands.Command;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.pearl.normandy.delegates.TaskDelegate;
	import com.pearl.normandy.event.TaskEvent;
	import com.pearl.normandy.utils.NormandyModel;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.events.ResultEvent;
	
	public class GetProjectMilestoneCommand implements Command, Responder
	{
		private var model:NormandyModel = NormandyModel.getInstance();
		
        public function execute( event : CairngormEvent ) : void
        {           
            var delegate : TaskDelegate = new TaskDelegate( this );   
            var e : TaskEvent = TaskEvent( event );  
            delegate.getProjectMilestone(e.project, e.getDefault);          
        }
           
        public function onResult( event : * = null ) : void
        {                 
        	var e:ResultEvent = ResultEvent(event);       
            model.GLOBAL_PROJECT_MILESTONES = e.result as ArrayCollection;
        }
                
        public function onFault( event : * = null ) : void
        {
        }

	}
}