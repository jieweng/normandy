package com.pearl.normandy.utils {
    import com.pearl.normandy.component.progressWindow.IProgress;
    
    import flash.events.*;
    import flash.net.FileReference;
    import flash.net.URLRequest;
    
    import mx.containers.TitleWindow;
    import mx.controls.Alert;
    import mx.controls.Button;
    import mx.core.UIComponent;
    import mx.managers.PopUpManager;

    public class FileDownload extends UIComponent {       
        public var DOWNLOAD_URL:String = "";
        
        //Define Pop Up Window Class
        private var _popupWindowClass:Class = null;  
        private var _popupWindow:TitleWindow = null;
        private var _popupWindowCenterWindow:UIComponent = null;
              
        public var fr:FileReference;
        // Define reference to the download ProgressBar component.
        private var btn:Button;

        public function FileDownload() {
        }

        /**
         * Set references to the components, and add listeners for the OPEN, 
         * PROGRESS, and COMPLETE events.
         */
        public function init( pwc:Class,btn:Button=null, pwcw:UIComponent=null):void {
			_popupWindowClass = pwc;
			//remove and popups if existed
			if(_popupWindow!=null)
			{
				PopUpManager.removePopUp(_popupWindow);
				_popupWindow = null;
			}        	
			
			_popupWindowCenterWindow = pwcw;
        	if(btn){
            this.btn = btn;}
            fr = new FileReference();
            fr.addEventListener(IOErrorEvent.IO_ERROR,ioErroHandler);
            fr.addEventListener(Event.OPEN, openHandler);
            fr.addEventListener(ProgressEvent.PROGRESS, progressHandler);
            fr.addEventListener(Event.COMPLETE, completeHandler);
        }
        public function ioErroHandler(event:IOErrorEvent):void{
        	Alert.show(resourceManager.getString('Language','alert.erroMessage'));
        	if(_popupWindow)
			{
				PopUpManager.removePopUp(_popupWindow);
				_popupWindow = null;
			}
        }
        /**
         * Immediately cancel the download in progress and disable the cancel button.
         */
        public function cancelDownload():void {
            fr.cancel();
            btn.enabled = false;
        }

        /**
         * Begin downloading the file specified in the DOWNLOAD_URL constant.
         */
        public function startDownload():void {
            var request:URLRequest = new URLRequest();
            request.url = DOWNLOAD_URL;
            fr.download(request);
        }

        /**
         * When the OPEN event has dispatched, change the progress bar's label 
         * and enable the "Cancel" button, which allows the user to abort the 
         * download operation.
         */
        private function openHandler(event:Event):void {
        	if(btn){
            btn.enabled = true;}
            
			if(_popupWindowClass)
			{
				//remove previous if exists
				PopUpManager.removePopUp(_popupWindow);
				_popupWindow = null;		
						
				if(_popupWindowCenterWindow==null)
					_popupWindow = TitleWindow(PopUpManager.createPopUp(this, _popupWindowClass));
				else
				{
					_popupWindow = TitleWindow(PopUpManager.createPopUp(_popupWindowCenterWindow, _popupWindowClass));
				}
				_popupWindow.title = "DOWNLOADING...";
				PopUpManager.centerPopUp(_popupWindow);
			}	            
        }
        
        /**
         * While the file is downloading, update the progress bar's status and label.
         */
        private function progressHandler(event:ProgressEvent):void {
			if ((_popupWindow!=null)&&(_popupWindow is IProgress))
			{
				IProgress(_popupWindow).progress(event.bytesLoaded,event.bytesTotal);
			}
        }

        /**
         * Once the download has completed, change the progress bar's label and 
         * disable the "Cancel" button since the download is already completed.
         */
        private function completeHandler(event:Event):void {
 			if(_popupWindow)
			{
				PopUpManager.removePopUp(_popupWindow);
				_popupWindow = null;
			} 
			if(btn){    
            btn.enabled = false;
   			}
        }
    }
}