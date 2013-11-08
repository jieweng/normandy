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
	
	public class UpdateTaskCommand implements Command, Responder
	{
		private var model:NormandyModel = NormandyModel.getInstance();
		
        public function execute( event : CairngormEvent ) : void
        {           
            var delegate : TaskDelegate = new TaskDelegate( this );   
            var e : TaskEvent = TaskEvent( event );  
            delegate.updateTask(e.task, e.field, model.currUser);          
        }
           
        public function onResult( event : * = null ) : void
        {   
        	var e:ResultEvent = ResultEvent(event);                     
			if(!e.result || model.updateTask==null || model.updateTask.referenceUrl==null || model.updateTask.referenceUrl == ""){
				return;
			}
			
			var url:String = model.updateTask.referenceUrl;
			model.updateTask.referenceUrl = model.updateTask.id + url.substring(url.indexOf("."),url.length); 
			
			model.updateTask = null;			        	
        }
                
        public function onFault( event : * = null ) : void
        {
        }

	}
}