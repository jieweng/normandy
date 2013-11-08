package com.pearl.normandy.commands.comment
{
	import com.adobe.cairngorm.business.Responder;
	import com.adobe.cairngorm.commands.Command;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.pearl.normandy.delegates.CommentDelegate;
	import com.pearl.normandy.event.CommentEvent;
	import com.pearl.normandy.utils.NormandyModel;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.events.ResultEvent;
	
	public class GetTaskCommentCommand implements Command, Responder
	{
		private var model:NormandyModel = NormandyModel.getInstance();
		
        public function execute( event : CairngormEvent ) : void
        {           
            var delegate : CommentDelegate = new CommentDelegate( this );   
            var e : CommentEvent = CommentEvent( event );  
            delegate.getTaskComment(e.task);          
        }
           
        public function onResult( event : * = null ) : void
        {                 
        	var e:ResultEvent = ResultEvent(event);       
            model.LOCAL_TASK_COMMENTS = e.result as ArrayCollection;
        }
                
        public function onFault( event : * = null ) : void
        {
        }

	}
}