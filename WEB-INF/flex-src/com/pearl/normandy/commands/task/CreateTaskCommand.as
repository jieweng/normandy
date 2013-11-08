package com.pearl.normandy.commands.task
{
	import com.adobe.cairngorm.business.Responder;
	import com.adobe.cairngorm.commands.Command;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.pearl.normandy.delegates.TaskDelegate;
	import com.pearl.normandy.event.TaskEvent;
	import com.pearl.normandy.utils.NormandyModel;
	import com.pearl.normandy.vo.TaskVO;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.events.ResultEvent;
	
	public class CreateTaskCommand implements Command, Responder
	{
		private var model:NormandyModel = NormandyModel.getInstance();
		
        public function execute( event : CairngormEvent ) : void
        {           
            var delegate : TaskDelegate = new TaskDelegate( this );   
            var e : TaskEvent = TaskEvent( event );  
            delegate.createTask(e.task, model.currUser);          
        }
           
        public function onResult( event : * = null ) : void
        {   
        	var e:ResultEvent = ResultEvent(event);     
        	var task:TaskVO = e.result as TaskVO;
        	
        	if(task.children != null && task.children.length == 0){
        		task.children = null;
        	}
        	
        	var tasks:ArrayCollection = new ArrayCollection();
        	tasks.addItem(task);
        	model.GLOBAL_CREATED_TASKS = tasks;            		        	
        }
                
        public function onFault( event : * = null ) : void
        {
        }

	}
}