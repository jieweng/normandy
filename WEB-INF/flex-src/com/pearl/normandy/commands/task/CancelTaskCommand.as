package com.pearl.normandy.commands.task
{
	import com.adobe.cairngorm.business.Responder;
	import com.adobe.cairngorm.commands.Command;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.pearl.normandy.delegates.TaskDelegate;
	import com.pearl.normandy.event.TaskEvent;
	import com.pearl.normandy.utils.NormandyModel;
	import com.pearl.normandy.vo.TaskVO;
	
	import mx.rpc.events.ResultEvent;
	
	public class CancelTaskCommand implements Command, Responder
	{
		private var model:NormandyModel = NormandyModel.getInstance();
		
        public function execute( event : CairngormEvent ) : void
        {           
            var delegate : TaskDelegate = new TaskDelegate( this );   
            var e : TaskEvent = TaskEvent( event );  
            delegate.cancelTask(e.task, model.currUser);          
        }
           
        public function onResult( event : * = null ) : void
        {   
        	var e:ResultEvent = ResultEvent(event);                     
			var result:Boolean = e.result as Boolean;
			if(result){
				model.GLOBAL_CANCELLED_TASK = model.cancelTask;
			}			        	
        }
                
        public function onFault( event : * = null ) : void
        {
        }

	}
}