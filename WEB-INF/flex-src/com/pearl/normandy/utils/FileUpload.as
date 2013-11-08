package com.pearl.normandy.utils
{
   	import com.pearl.normandy.component.progressWindow.IProgress;
   	import com.pearl.normandy.event.UploadEvent;
   	import com.pearl.normandy.vo.FeedbackReferenceVO;
   	
   	import flash.events.Event;
   	import flash.events.ProgressEvent;
   	import flash.net.FileReference;
   	import flash.net.FileReferenceList;
   	import flash.net.URLRequest;
   	import flash.net.URLRequestMethod;
   	
   	import mx.collections.ArrayCollection;
   	import mx.containers.TitleWindow;
   	import mx.core.UIComponent;
   	import mx.managers.PopUpManager;
   	
   	[Event(name="complete", type="com.pearl.normandy.event.UploadEvent")]
   	
	public class FileUpload extends UIComponent
	{
        //Define Pop Up Window Class
        private var _popupWindowClass:Class = null;  
        private var _popupWindow:TitleWindow = null;
        private var _popupWindowCenterWindow:UIComponent = null;		
		
		private var fileRefList:FileReferenceList;
		private var feedbackReference:ArrayCollection;
		
		private var _prefix:String = "";
		
		public function set prefix(prefix:String):void{	
			this._prefix = prefix;		
		}
		
		public function get prefix():String{
			return this._prefix;
		}
		
		public var suffix:String = "";
        private var file:FileReference;		
        private var request:URLRequest = new URLRequest();
		
		public function FileUpload()
		{	
		}         
		
		
        public function init(pwc:Class, pwcw:UIComponent=null):void {
			_popupWindowClass = pwc;
			//remove and popups if existed
			if(_popupWindow!=null)
			{
				PopUpManager.removePopUp(_popupWindow);
				_popupWindow = null;
			}        	
			
			_popupWindowCenterWindow = pwcw;
        	
			file = new FileReference();
            file.addEventListener(Event.SELECT, selectHandler);
            file.addEventListener(Event.OPEN, openHandler);
            file.addEventListener(ProgressEvent.PROGRESS, progressHandler);
            file.addEventListener(Event.COMPLETE, completeHandler);
        }		
		
         
         public function browse() : void {
         	prefix = prefix + NormandyModel.getInstance().currUser.id + (new Date().getTime().toString());
            file.browse(new Array(Constants.IMAGE_FILTER));
         }
 
         private function selectHandler(event:Event):void {         	
         	suffix = file.name.substring(file.name.indexOf("."));
            request.url = "referenceupload?filename="+prefix;       
			request.method = URLRequestMethod.POST;                            
            file.upload(request);
         }
         
        private function openHandler(event:Event):void {            
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
				_popupWindow.title = "UPLOADING...";
				PopUpManager.centerPopUp(_popupWindow);
			}	            
        }         
         
        private function progressHandler(event:ProgressEvent):void {
			if ((_popupWindow!=null)&&(_popupWindow is IProgress))
			{
				IProgress(_popupWindow).progress(event.bytesLoaded,event.bytesTotal);
			}
        }         
         
         public function multiBrowse():void{
         	prefix = prefix + NormandyModel.getInstance().currUser.id + (new Date().getTime().toString());
			fileRefList = new FileReferenceList();
			fileRefList.addEventListener(Event.SELECT,multiSelectHandler);
			fileRefList.browse(new Array(Constants.IMAGE_FILTER));
    	}
	   	
	   	private function multiSelectHandler(event:Event):void {
	        var fileReference:FileReference;
	        var fileReferenceList:FileReferenceList = FileReferenceList(event.target);
	        var fileList:Array = fileReferenceList.fileList;
		
		   	feedbackReference = new ArrayCollection();
			for(var i:int=0;i<fileList.length;i++){
				fileReference = FileReference(fileList[i]);
				suffix = fileReference.type;
				request.url = "referenceupload?filename="+prefix + i;
				request.method = URLRequestMethod.POST;
				
				var reference:FeedbackReferenceVO = new FeedbackReferenceVO();
	        	reference.url = prefix + i + suffix;
	        	reference.imageUrl = fileReference.name;
	        	feedbackReference.addItem(reference);
				fileReference.upload(request);
			}
			fileReference.addEventListener(Event.COMPLETE,completeHandler);
	    }
       	
       	private function completeHandler(event:Event):void{
 			if(_popupWindow)
			{
				PopUpManager.removePopUp(_popupWindow);
				_popupWindow = null;
			}           		
       		
        	var url:String = prefix+suffix;
       		dispatchEvent(new UploadEvent(UploadEvent.COMPLETE, url , feedbackReference));
       	}
	}
}