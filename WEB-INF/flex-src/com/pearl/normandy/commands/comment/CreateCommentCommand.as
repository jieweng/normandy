package com.pearl.normandy.commands.comment
{
	import com.adobe.cairngorm.business.Responder;
	import com.adobe.cairngorm.commands.Command;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.pearl.normandy.delegates.CommentDelegate;
	import com.pearl.normandy.event.CommentEvent;
	import com.pearl.normandy.utils.NormandyModel;
	import com.pearl.normandy.vo.CommentVO;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.events.ResultEvent;
	
	public class CreateCommentCommand implements Command, Responder
	{
		private var model:NormandyModel = NormandyModel.getInstance();
		
        public function execute( event : CairngormEvent ) : void
        {           
            var delegate : CommentDelegate = new CommentDelegate( this );   
            var e : CommentEvent = CommentEvent( event );  
            delegate.createComment(e.comment);          
        }
           
        public function onResult( event : * = null ) : void
        {                 
        	var e:ResultEvent = ResultEvent(event);       
        	if(model.LOCAL_TASK_COMMENTS == null){            	
        		model.LOCAL_TASK_COMMENTS = new ArrayCollection();
        	}
        	model.LOCAL_TASK_COMMENTS.addItem(e.result as CommentVO);        	
        }
                
        public function onFault( event : * = null ) : void
        {
        }

	}
}