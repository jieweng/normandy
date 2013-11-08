package com.pearl.normandy.commands.taskdetail
{
	import com.adobe.cairngorm.business.Responder;
	import com.adobe.cairngorm.commands.Command;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.pearl.normandy.delegates.TaskDetailDelegate;
	import com.pearl.normandy.event.TaskDetailEvent;
	import com.pearl.normandy.utils.NormandyModel;
	
	import mx.rpc.events.ResultEvent;
	
	public class GetTaskDetailCommand implements Command, Responder
	{
		private var model:NormandyModel = NormandyModel.getInstance();
		
        public function execute( event : CairngormEvent ) : void
        {           
            var delegate : TaskDetailDelegate = new TaskDetailDelegate( this );   
            var e : TaskDetailEvent = TaskDetailEvent( event );  
            delegate.getTaskDetail(e.task);          
        }
           
        public function onResult( event : * = null ) : void
        {                 
        	var e:ResultEvent = ResultEvent(event);       
            model.LOCAL_TASK_DETAIL = e.result as Object;
        }
                
        public function onFault( event : * = null ) : void
        {
        }

	}
}