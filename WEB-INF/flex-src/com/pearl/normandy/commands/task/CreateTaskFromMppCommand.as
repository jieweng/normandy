package com.pearl.normandy.commands.task
{
	import com.adobe.cairngorm.business.Responder;
	import com.adobe.cairngorm.commands.Command;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.pearl.normandy.delegates.TaskDelegate;
	import com.pearl.normandy.event.TaskEvent;
	import com.pearl.normandy.utils.NormandyModel;
	
	import mx.rpc.events.ResultEvent;

	public class CreateTaskFromMppCommand implements Command, Responder{
		
		private var model:NormandyModel = NormandyModel.getInstance();

		public function execute(event:CairngormEvent):void{
			var delegate:TaskDelegate = new TaskDelegate(this);
			var e:TaskEvent = TaskEvent(event);
			delegate.createTaskFromMpp(e.mppFileName, e.project, e.user);
		}
		
		public function onResult(event:* =null):void{
			var e:ResultEvent = ResultEvent(event);
			var isSucceeded:Boolean = e.result as Boolean;
			if(isSucceeded){
				CairngormEventDispatcher.getInstance().dispatchEvent(TaskEvent.getProjectTaskEvent(model.GLOBAL_SELECTED_PROJECT));
			}			
		}
		
		public function onFault(event:*=null):void{
			
		}		
	}
}