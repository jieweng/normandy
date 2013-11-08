package com.pearl.normandy.commands.task
{
	import com.adobe.cairngorm.business.Responder;
	import com.adobe.cairngorm.commands.Command;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.pearl.normandy.delegates.TaskDelegate;
	import com.pearl.normandy.event.TaskEvent;
	import com.pearl.normandy.utils.NormandyModel;
	import com.pearl.normandy.vo.TaskVO;
	
	public class AddSubtaskCommand implements Command, Responder
	{
		private var model:NormandyModel = NormandyModel.getInstance();
		
        public function execute( event : CairngormEvent ) : void
        {           
            var delegate : TaskDelegate = new TaskDelegate( this );   
            var e : TaskEvent = TaskEvent( event );  
            delegate.addSubtask(e.task, model.currUser);          
        }
           
        public function onResult( event : * = null ) : void
        {   
       		var task:TaskVO = event.result as TaskVO;
       		task.children = null;
       		model.GLOBAL_CREATED_SUBTASK = task;
        }
                
        public function onFault( event : * = null ) : void
        {
        }

	}
}