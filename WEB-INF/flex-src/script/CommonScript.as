// ActionScript file	
	import com.pearl.normandy.enum.ActivityEnum;
	import com.pearl.normandy.enum.CheckItemEnum;
	import com.pearl.normandy.enum.ColorEnum;
	import com.pearl.normandy.utils.Constants;
	import com.pearl.normandy.utils.DateUtil;
	import com.pearl.normandy.utils.NormandyModel;
	import com.pearl.normandy.vo.ActivityVO;
	import com.pearl.normandy.vo.CheckItemVO;
	import com.pearl.normandy.vo.FeedbackVO;
	import com.pearl.normandy.vo.ProjectVO;
	import com.pearl.normandy.vo.TaskVO;
	
	import flash.ui.ContextMenu;
	
	import mx.collections.Grouping;
	import mx.collections.GroupingCollection;
	import mx.collections.GroupingField;
	import mx.containers.Panel;
	import mx.controls.Alert;
	import mx.controls.advancedDataGridClasses.AdvancedDataGridColumn;
	import mx.controls.dataGridClasses.DataGridColumn;
	import mx.core.UIComponent;
	import mx.managers.PopUpManager;
	import mx.messaging.messages.ErrorMessage;
	import mx.rpc.events.FaultEvent;
	import mx.utils.ObjectUtil;
	import mx.utils.StringUtil;
	
	import popup.AddFeedbackPopup;
	import popup.FaultHandlerPopup;
	import popup.ViewTaskPopup;	
    
    //==============================
    //Common Varaibles
    //==============================
    [Bindable]
    public var model:NormandyModel = NormandyModel.getInstance();      
    
    //==============================
    //Common Functions
    //==============================   	    
	/**
	* 在考虑计算误差的情况下，判断两个浮点小数是否相等。
	*
	* @param a 一个浮点小数。
	* @param b 另一个浮点小数。
	* @param tol 允许的最大浮点小数的计算误差。
	* @return 如果相等，返回 true；否则返回 false。
	*/		
	public static function eqNumber(a:Number, b:Number,  tol:Number = 1e-12):Boolean {  
		return Math.abs(a - b) < tol;
	}    
	
	/**
	* 在考虑计算误差的情况下，判断两个浮点小数的大小。
	*
	* @param a 一个浮点小数。
	* @param b 另一个浮点小数。
	* @param tol 允许的最大浮点小数的计算误差。
	* @return 如果相等，返回 0；ab，返回 1。
	*/	
	public static function compareNumber(a:Number, b:Number,   tol:Number = 1e-12):int {  
		return eqNumber(a, b, tol)? 0 : a<b?-1:1;
	}	
    
    
    /**
    * Common function to showup a confirmation window
    * */
	public function confirm(text:String,  title:String, confirmHandler:Function):void {
		// instantiate the Alert box
		var a:Alert = Alert.show(text, title, Alert.YES|Alert.NO, 
			UIComponent(this.parentApplication), confirmHandler, null, Alert.NO);
		
		// modify the look of the Alert box
		a.setStyle("backgroundColor", 0xffffff);
		a.setStyle("backgroundAlpha", 0.50);
		a.setStyle("borderColor", 0xffffff);
		a.setStyle("borderAlpha", 0.75);
		a.setStyle("color", 0x000000); // text color
	}    
	
	
	public function createPopUp(objClass:Class):Object{
		return PopUpManager.createPopUp(UIComponent(this.parentApplication), objClass, true);
	}
	
	
	/**
	 * Function to group data by certain Field
	 */
	public function groupData(data:Object, field:String):Object{
        var collection:GroupingCollection=new GroupingCollection();
        collection.source = data;
        var grouping:Grouping = new Grouping();
        grouping.fields = [new GroupingField(field)];
        collection.grouping = grouping;
        collection.refresh();
        return collection;
	}	
	
	
	/**
	 * Function to set start time of gantt item
	 */
	public function getStartTime(date:Date):Date{
  		date.seconds	=	0;
		date.minutes	=	0;
		date.hours		=	0;	
		return date;
	}				   	
	
	
	/**
	 * Function to set end time of gantt item
	 */
	public function getEndTime(date:Date):Date{
		if(date.seconds == 0 && date.minutes == 0 && date.hours == 0){
	  		date.seconds	=	59;
			date.minutes	=	59;
			date.hours		=	23;
		}
		return date;
	}
	
	
	/**
	 * Function to set end time one day before
	 */
	public function getEndTimeBefore(startDate:Date, endDate:Date):Date{
		
		//If end time is mid-night, set one minute before mid-night
		if(endDate.seconds == 0 && endDate.minutes == 0 && endDate.hours == 0){
			if(startDate.getFullYear() == endDate.getFullYear() && startDate.getMonth() == endDate.getMonth() && startDate.getDate() == endDate.getDate()){
				return endDate;
			}
			
  			endDate.seconds		=	59;
			endDate.minutes		=	59;
			endDate.hours		=	23;
			endDate.date 		= 	endDate.date - 1;
		}
		
		return endDate;
	}				
	
	//Date Format: MM/DD/YYYY JJ:NN:SS
	private function formatDateSimple(item:Object, column:AdvancedDataGridColumn):String{
		return DateUtil.format(item[column.dataField], DateUtil.SIMPLE);
	}
	
	//Date Format: MM/DD/YYYY
	private function formatDateShort(item:Object, column:AdvancedDataGridColumn):String{
		return DateUtil.format(item[column.dataField], DateUtil.SHORT);
	}	
	
	//Date Format: EEE MM/DD/YY
	private function formatDateDay(item:Object, column:AdvancedDataGridColumn):String{
		return DateUtil.format(item[column.dataField], DateUtil.DAY);
	}		

	private function formatDate(item:Object, column:DataGridColumn):String{
		return DateUtil.format(item[column.dataField], DateUtil.DAY);
	}		
	
  	public function getTimeString(date:Date):String{
  		if(date.getMinutes()<=9){
  			return date.getHours()+":0"+date.getMinutes();
  		}
  		else{
  			return date.getHours()+":"+date.getMinutes();
  		}
  	}	
	
  	/**
  	 * Function return sliced label
  	 **/
    public function getSliceLabel(item:Object, arg2:String, arg3:Number, arg4:Number):String{
        return item==null?"":item.legend;
    }	
    
    
     public function taskDataTip(item:Object):String{
 		if(item is AdvancedDataGridColumn){
			return "";
		} 
		
		return StringUtil.substitute("{0}\n{1}\n{2}\n{3}\n{4}\n{5}\n{6}",
				resourceManager.getString('Language','label.data.tip.id')+item.id,
				resourceManager.getString('Language','label.data.tip.task')+item.name,
				resourceManager.getString('Language','label.data.tip.priority')+item.priority,
				resourceManager.getString('Language','label.data.tip.milestone')+item.milestone,
				resourceManager.getString('Language','label.data.tip.group')+item.taskGroup,
				resourceManager.getString('Language','label.data.tip.start')+DateUtil.format(item.startTime, DateUtil.SHORT),
				resourceManager.getString('Language','label.data.tip.end')+DateUtil.format(item.endTime, DateUtil.SHORT));	
	} 	
	
	
	private function subtaskDataTip(item:Object):String{
	
 		if(item is AdvancedDataGridColumn){
			return "";
		} 
		return StringUtil.substitute("{0}\n{1}\n{2}\n{3}\n{4}\n{5}\n{6}\n{7}",
				resourceManager.getString('Language','label.data.tip.subtask_id')+item.id,
				resourceManager.getString('Language','label.data.tip.task')+item.taskName,
				resourceManager.getString('Language','label.data.tip.subtask')+item.subtaskName,
				resourceManager.getString('Language','label.data.tip.priority')+item.priority,
				resourceManager.getString('Language','label.data.tip.milestone')+item.milestone,
				resourceManager.getString('Language','label.data.tip.group')+item.taskGroup,
				resourceManager.getString('Language','label.data.tip.start')+DateUtil.format(item.startTime, DateUtil.SIMPLE),
				resourceManager.getString('Language','label.data.tip.end')+DateUtil.format(item.endTime, DateUtil.SIMPLE));				
	}		    	
	
	//Show or Hide lefe side control panel
   	private function showHideControlPanel(controlPanel:Panel):void{
		controlPanel.visible = !controlPanel.visible;
		controlPanel.includeInLayout = !controlPanel.includeInLayout;
	}		
	
	
	public function showViewTaskWindow(item:Object, index:int=0):void{
		var viewTaskPopup:ViewTaskPopup;

		if(item is ActivityVO){
			var activity:ActivityVO = item as ActivityVO;			
			if(activity.activityType == ActivityEnum.PRODUCTION || activity.activityType == ActivityEnum.FEEDBACK){
				viewTaskPopup = ViewTaskPopup(PopUpManager.createPopUp(UIComponent(this.parentApplication), ViewTaskPopup, true));
		   		PopUpManager.centerPopUp(viewTaskPopup);
		   		viewTaskPopup.taskId = activity.taskId;
			}
		}
		else if(item is TaskVO){
			var task:TaskVO = item as TaskVO;
			viewTaskPopup = ViewTaskPopup(PopUpManager.createPopUp(UIComponent(this.parentApplication), ViewTaskPopup, true));
	   		PopUpManager.centerPopUp(viewTaskPopup);
	   		viewTaskPopup.taskId = task.id;
		}
		else if(item as FeedbackVO)
		{
			viewTaskPopup = ViewTaskPopup(PopUpManager.createPopUp(UIComponent(this.parentApplication), ViewTaskPopup, true));
	   		PopUpManager.centerPopUp(viewTaskPopup);
	   		viewTaskPopup.taskId = item.taskId as int;
		}
		
		if(viewTaskPopup){
			viewTaskPopup.viewTaskVS.selectedIndex=index;
		}
	}
	
	//Right click init
	private function removeDefaultItems(myContextMenu:ContextMenu):void {
		myContextMenu.hideBuiltInItems();
		var defaultItems:ContextMenuBuiltInItems = myContextMenu.builtInItems;
		defaultItems.print = true;
	}	
	
	
    //==============================
    //Remote Object Handlers
    //==============================   	
	public function faultHandler(event:FaultEvent):void{
	    Alert.show(""+event);
	}    	
	
	
	public function customFaultHandler(event:FaultEvent):void{
		var errorMsg:ErrorMessage = event.message as ErrorMessage;
		
		if(errorMsg.extendedData){
			var faultHandlerPopup:FaultHandlerPopup = FaultHandlerPopup(PopUpManager.createPopUp(UIComponent(this.parentApplication), FaultHandlerPopup, true));
			PopUpManager.centerPopUp(faultHandlerPopup);
			faultHandlerPopup.message = errorMsg;
		}
		else{
			faultHandler(event);
		}	
	}
	
	public function selectStyleFunction(data:Object, column:AdvancedDataGridColumn):Object{
		switch(data[column.dataField]){
			case Constants.FEEDBACK_SELECT: return {color: 0xc72020, fontWeight:"bold"};
		}
		return null;
	}
	

	private function progressStyleFunction(data:Object, column:AdvancedDataGridColumn):Object{
		if(data != null && data.status != null && column.dataField != null && data.status[column.dataField] != null){
			switch(data.status[column.dataField]){
				case "Not Started": return { color: ColorEnum.statusToColorDark[ColorEnum.NOT_STARTED], fontWeight:"bold", textAlign: "center" };
				case "WIP": return { color: ColorEnum.statusToColorDark[ColorEnum.WIP], fontWeight:"bold", textAlign: "center" };
				case "Fix Required": return { color: ColorEnum.typeToColor[ColorEnum.FEEDBACK], fontWeight:"bold", textAlign: "center" };
				case "Approved": return { color: ColorEnum.statusToColorDark[ColorEnum.APPROVED], fontWeight:"bold", textAlign: "center" };
				default: return { color: ColorEnum.statusToColorDark[ColorEnum.COMPLETE], fontWeight:"bold", textAlign: "center" };
			}
		}
		return null;
	}
	
	private function taskStatusStyleFunction(data:Object, column:AdvancedDataGridColumn):Object{
		if(data != null && data.status != null){
			switch(data.status){
				case "Not Started": return { color: ColorEnum.statusToColorDark[ColorEnum.NOT_STARTED], fontWeight:"bold", textAlign: "center" };
				case "WIP": return { color: ColorEnum.statusToColorDark[ColorEnum.WIP], fontWeight:"bold", textAlign: "center" };
				case "Fix Required": return { color: ColorEnum.typeToColor[ColorEnum.FEEDBACK], fontWeight:"bold", textAlign: "center" };
				case "Approved": return { color: ColorEnum.statusToColorDark[ColorEnum.APPROVED], fontWeight:"bold", textAlign: "center" };
				default: return { color: ColorEnum.statusToColorDark[ColorEnum.COMPLETE], fontWeight:"bold", textAlign: "center" };
			}
		}
		return null;
	}	
	
	private function checkItemADGStyleFunc(data:Object, column:AdvancedDataGridColumn):Object{
		var checkItem:CheckItemVO = data as CheckItemVO;
		if(checkItem){
			switch(checkItem.status){
				case CheckItemEnum.STATUS_PENDING: return { color: 0x007614, fontWeight: "bold"};
				case CheckItemEnum.STATUS_REVIEWED: return { color: 0x000000};
				case CheckItemEnum.STATUS_FIX: return { color: 0xff8200, fontWeight: "bold"};
				case CheckItemEnum.STATUS_COMPLETE: return { color: 0x000000};
				case CheckItemEnum.STATUS_SUBMITTED: return { color: 0x000000};
				default: return { color: 0x007614, fontWeight: "bold"};
			}
		}
		return null;
	}			
		
	//Ready to delete, 2009/12/10, Frank
/* 	public function errorNameFunction(item:Object):String{
		var feedback:FeedbackVO = null;
		if(item is FeedbackVO){
			feedback = item as FeedbackVO;
		}
		if(feedback)
			return StringUtil.substitute("{0}",feedback.errorName);
		else
			return "";	
	}	 */
	
	
	public function fuzzyCompare(fromS:String, toS:String):Boolean{
		if(fromS==null || toS == null || toS == ""){
			return true;
		}else{
			var pattern:RegExp = / /g;
			var fromStr:String = fromS.replace(pattern, "");	
			var toStr:String = toS.replace(pattern, "");
			if(fromStr.toUpperCase().indexOf(toStr.toUpperCase())>=0){
				return true;
			}else{
				return false;
			}			
		}
	}
	
	public function searchCompare(searchArr:Array,searchStr:String):Boolean{
		for each(var str:String in searchArr){
			if(searchStr.toUpperCase().indexOf(str.toUpperCase())>=0){
				return true;
			}
		}
		return false;
	}
	
	public function timeCompare(From:Date, To:Date):Boolean{
		if(From == null || To == null){
			return true;
		}else{
			var fDate:Date = new Date(From.fullYear, From.month, From.date);
			var tDate:Date = new Date(To.fullYear, To.month, To.date);
			var i:int = ObjectUtil.dateCompare(fDate, tDate);
			if(i == 0){
				return true;
			}else{
				return false;
			}
		}
	}
	    //==============================
	    //Label Function
	    //==============================	
/* 	    private function processCBLabelFunc(item:Object):String{
	    	var process:ProductionProcessVO = item as ProductionProcessVO;
	    	
	    	if(process.taskCategoryId != 0){
	    		return process.processName + " (" + process.category + ")";
	    	}
	    	else{
	    		return process.processName;
	    	}
	    } */
	    
	    
	     private function projectLabelFunc(item:Object):String{
	     	var project:ProjectVO = item as ProjectVO;
	     	if(project == null) return "";
	     	if(project.status == Constants.PROJECT_STATUS_CLOSE){
	     		return project.projectName + " [" + project.projectKey + "]" + " - " + project.status;
	     	} 
	     	else{
	     		return project.projectName + " [" + project.projectKey + "]";			
	     	}
	     }	
	     
         private function resourceSliderLabelFunc(value:String):String {
         	switch(value){
         		case "0": return "All";
         		case "1": return "Last 6 Months";
         		case "2": return "Last 3 Months";
         		case "3": return "Last 1 Month";
         		default: return value;
         	}
         }	     
	     
	    //==============================
	    //Parse Function
	    //==============================	     
		private function parseHtml(html:String):String{
			var tempString:String = html;
			var pattern:RegExp = new RegExp("SIZE=\"([0-9]{1,2})\"","g"); 
			
			var result:Object;				
			while(result=pattern.exec(tempString)){
				tempString=tempString.replace(pattern, "STYLE=\"font-size: $1px\"  size=\"$1\"");
			}				
			
			return tempString;
		}	  		   
		
		
	    private function recordFeedback():void{		    	
	    	var addFeedbackPopup:AddFeedbackPopup = AddFeedbackPopup(PopUpManager.createPopUp(UIComponent(this.parentApplication), AddFeedbackPopup, true));
			PopUpManager.centerPopUp(addFeedbackPopup);
			addFeedbackPopup.checkItem = model.selectedCheckItem;
	    }
	    
	    public function userSearch(fromS:String, toS:String):Boolean{
		if(fromS==null || toS == null || toS == ""){
			return true;
		}else{
			var pattern:RegExp = / /g;
			var fromStr:String = fromS.replace(pattern, "");	
			var toStr:String = toS.replace(pattern, "");
			var str:Array=StringUtil.trim(toS).split(" ");
			for(var i:int=0;i<str.length;i++){
				if(fromStr.toUpperCase().indexOf(String(str[i]).toUpperCase())<0){
					return false;
				}
			}
			return true;
		}
	}	
