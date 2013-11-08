package com.pearl.normandy.commands.processstep
{
	import com.adobe.cairngorm.business.Responder;
	import com.adobe.cairngorm.commands.Command;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.pearl.normandy.delegates.ProcessStepDelegate;
	import com.pearl.normandy.event.ProcessEvent;
	import com.pearl.normandy.event.ProcessStepEvent;
	import com.pearl.normandy.utils.NormandyModel;
	
	public class DeleteProcessStepCommand implements Command, Responder
	{
		private var model:NormandyModel = NormandyModel.getInstance();
		
        public function execute( event : CairngormEvent ) : void
        {           
            var delegate : ProcessStepDelegate = new ProcessStepDelegate( this );   
            var e : ProcessStepEvent = ProcessStepEvent( event );  
            delegate.deleteProcess(e.processSteps);          
        }
           
        public function onResult( event : * = null ) : void
        {                 
        }
                
        public function onFault( event : * = null ) : void
        {
        }

	}
}