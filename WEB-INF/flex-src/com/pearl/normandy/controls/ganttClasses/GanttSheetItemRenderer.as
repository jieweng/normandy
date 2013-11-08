package com.pearl.normandy.controls.ganttClasses
{
  import com.pearl.normandy.enum.ActivityEnum;
  import com.pearl.normandy.enum.ColorEnum;
  import com.pearl.normandy.enum.ImageEnum;
  import com.pearl.normandy.utils.Constants;
  import com.pearl.normandy.utils.NormandyModel;
  import com.pearl.normandy.vo.ActivityVO;
  
  import flash.display.GradientType;
  import flash.filters.GlowFilter;
  import flash.geom.Rectangle;
  
  import ilog.gantt.TaskItem;
  import ilog.utils.TimeUnit;
  
  import mx.controls.Image;
  import mx.controls.listClasses.IListItemRenderer;
  import mx.core.IDataRenderer;
  import mx.core.UIComponent;
  import mx.core.UITextField;
  import mx.events.FlexEvent;
  import mx.graphics.GradientEntry;
  import mx.graphics.LinearGradient;
  import mx.utils.ColorUtil;

  [Event(name="dataChange", type="mx.events.FlexEvent")]

  public class GanttSheetItemRenderer extends UIComponent
    implements IDataRenderer, IListItemRenderer
  {
    //--------------------------------------------------------------------------
    //
    //  Class constants
    //
    //--------------------------------------------------------------------------
    

    //--------------------------------------------------------------------------
    //
    //  Variables
    //
    //--------------------------------------------------------------------------
    private var ganttSheet:CustomGanttSheet;
    private var icon:Image;
    protected var iconSize:uint = 64;
    private var label:UITextField;
    private var medal:Image;
    private var medalSize:uint = 16;    
    
    //--------------------------------------------------------------------------
    //
    //  Constructor
    //
    //--------------------------------------------------------------------------
    public function GanttSheetItemRenderer()
    {
      	this.mouseChildren = false;          
    }
    
    //--------------------------------------------------------------------------
    //
    //  Properties
    //
    //--------------------------------------------------------------------------
    [Bindable]
    private var model:NormandyModel = NormandyModel.getInstance();
    
    //---------------------------------
    // data
    //---------------------------------
    [Bindable("dataChange")]
    /**
     * @private 
     * Internal storage for the property value.
     */
    private var _data:Object;
    private var _dataChanged:Boolean;
    
    public function get data():Object
    {
      return _data;
    }
    
    public function set data(value:Object):void
    {
      _dataChanged = true;
      _data = value;
      ganttSheet = taskItem.ganttSheet as CustomGanttSheet;
	  
      invalidateProperties();
      
      dispatchEvent(new FlexEvent(FlexEvent.DATA_CHANGE));
    }

    //---------------------------------
    //  taskItem
    //---------------------------------
    protected function get taskItem():TaskItem
    {
      return _data as TaskItem;  
    }
    
    //--------------------------------------------------------------------------
    //
    //  Overriden methods from UIComponent
    //
    //--------------------------------------------------------------------------
    /**
     * @private
     */
    override protected function createChildren():void
    {
      super.createChildren();
      if (icon == null)
      {
        icon = new Image();
        var ef:GlowFilter = new GlowFilter(0xEEEEEE, 0.5, 2, 2, 5, 5,false,false);
        icon.filters = [ef];
        addChild(icon);
      }
      if (label == null)
      {
        label = new UITextField();
        label.styleName = this;
        label.ignorePadding = false;
        label.includeInLayout = false;
        addChild(label);
      } 
      if (medal == null){
      	medal = new Image();
      	addChild(medal);
      }     
    }

    /**
     * @private
     */
    override protected function commitProperties():void
    {
      super.commitProperties();
      
      if (_dataChanged)
      {
        _dataChanged = false;
        if (taskItem)
        {
//          reason = taskItem.data.reason;
//          label.text = taskItem.data.name;
          if(taskItem.data.activityType == ActivityEnum.PAID_LEAVE){
          	label.text = ActivityEnum.PAID_LEAVE_L + taskItem.data.name;
          }else if(taskItem.data.activityType == ActivityEnum.UNPAID_LEAVE){
          	label.text = ActivityEnum.UNPAID_LEAVE_L + taskItem.data.name;
          }else if(taskItem.data.activityType == ActivityEnum.MANAGEMENT){
          	label.text = ActivityEnum.MANAGEMENT_L + taskItem.data.name;
          }else if(taskItem.data.activityType == ActivityEnum.QA){
          	label.text = ActivityEnum.QA + taskItem.data.name;
          }else if(taskItem.data.activityType == ActivityEnum.COMPENSATION_OFF){
          	label.text = ActivityEnum.COMPENSATION_OFF_L + taskItem.data.name;
          }else if(taskItem.data.activityType == ActivityEnum.DOWN_TIME_TRAINING){
          	label.text = ActivityEnum.DOWN_TIME_TRAINING_L + taskItem.data.name;
          }else if(taskItem.data.activityType == ActivityEnum.PROJECT_TRAINING){
          	label.text = ActivityEnum.PROJECT_TRAINING_L + taskItem.data.name;
          }else if(taskItem.data.activityType == ActivityEnum.WAIT_FEEDBACK){
          	label.text = ActivityEnum.WAIT_FEEDBACK_L + taskItem.data.name;
          }else if(taskItem.data.activityType == ActivityEnum.RECRUITMENT){
          	label.text = ActivityEnum.RECRUITMENT_L + taskItem.data.name;
          }else{
          	label.text = taskItem.data.name;
          }          
        }
        else
          label.text = "";
        invalidateDisplayList();
      }
    }

    /**
     * @private
     */
    override protected function updateDisplayList(unscaledWidth:Number,
                                                  unscaledHeight:Number):void
    {
      super.updateDisplayList(unscaledWidth, unscaledHeight);     
      
      if (!ganttSheet)
      {
        draw(unscaledWidth, unscaledHeight, false, false, false, true);
      }
      else
      {
        var selected:Boolean = ganttSheet.isItemSelected(taskItem.data);
        var highlighted:Boolean = ganttSheet.isItemHighlighted(taskItem.data);
        var groupHighlighted:Boolean = taskItem.data.highlighted; 
        var showIcon:Boolean = TimeUnit.DAY.milliseconds / ganttSheet.zoomFactor > 28;
        draw(unscaledWidth, unscaledHeight, selected, highlighted, groupHighlighted, showIcon);
      }     
    }

    private function draw(unscaledWidth:Number, unscaledHeight:Number, 
                          selected:Boolean, highlighted:Boolean, groupHighlighted:Boolean,
                          showIcon:Boolean):void
    {
      var borderColor:uint;
      var backgroundColor:uint;
      var borderColors:Array;
      var stateColor:uint;
      var medalStartPosition:uint;
      
      graphics.clear();
    
      backgroundColor = getBackgroundColor();
      
      if (isShowIcon(showIcon))
	{
        icon.source = getIcon();
        icon.move(4,4);
        icon.setActualSize(iconSize, iconSize);
        icon.visible = true;
        if (icon.source){
        	medalStartPosition = icon.width + 5;        	
        }else medalStartPosition = 0;
      }
      else
      {
        icon.visible = false;
        medalStartPosition = 0;
      }
      
      var medalNum:uint=taskItem.data.medal;
      
      if (isShowMedal()){
      	if (medalNum==3){
      		medal.source=ImageEnum.STAR;
      	}else if (medalNum==4){
      		medal.source=ImageEnum.LUNA;
      	}else if (medalNum==5){
      		medal.source=ImageEnum.SUN;
      	}else if (medalNum==1){
      		medal.source=ImageEnum.RAIN;
      	}else if (medalNum==2){
      		medal.source=ImageEnum.LEVIN;
      	}
      	medal.move(medalStartPosition,1);
      	medal.setActualSize(medalSize,medalSize);
      	medal.visible=true;
      }else{
      	medal.visible=false;
      }

      //var textStartPosition:Number = (isShowIcon(showIcon) && icon.source) ? (icon.x + icon.width + 1) : 0;
      var textStartPosition:Number;
      
      if (isShowIcon(showIcon) && icon.source && isShowMedal()){
      	textStartPosition = icon.x + icon.width + medal.width + 1;
      }else if ((!isShowIcon(showIcon) && isShowMedal()) || (isShowIcon(showIcon) && !icon.source && isShowMedal())) {
      	textStartPosition = medal.x + medal.width + 1;
      }else if (isShowIcon(showIcon) && icon.source && !isShowMedal()){
      	textStartPosition = icon.x + icon.width + 1;
      }else textStartPosition = 0;

      if (textStartPosition + 10 <= unscaledWidth)
      {
        label.move(textStartPosition, 0);
        label.setActualSize(unscaledWidth - textStartPosition, unscaledHeight);
        label.visible = true;
      }
      else 
      {
        label.visible = false;
      }
                  
      if (groupHighlighted){
        stateColor = 0x8800de;
        filters = [new GlowFilter(stateColor,1,2,2,5,10)];
//        borderColors = [stateColor, ColorUtil.adjustBrightness2(stateColor, -30)];      	
      }  
      if (highlighted)
      {
        stateColor = getStyle("rollOverColor");
        filters = [new GlowFilter(stateColor,1,3,3,5,10)];
        borderColors = [stateColor, ColorUtil.adjustBrightness2(stateColor, -30)];
      }
      if (selected)
      {
        stateColor = getStyle("selectionColor");
        filters = [new GlowFilter(stateColor, 1,2,2,5,10)];
        borderColors = [stateColor, ColorUtil.adjustBrightness2(stateColor, -30)];
      }
      if (!selected && !highlighted && !groupHighlighted)
      {
        stateColor = 0x888888;
        filters = [];
        borderColors = [stateColor, ColorUtil.adjustBrightness2(stateColor, -20)];
      }
      
      //Show border for overlapped task items
      if (ganttSheet.hasOverlappingSiblings(taskItem))
      {
      	stateColor = 0xF00000;
        filters = [new GlowFilter(stateColor,1,4,4,5)];
      	alpha = 0.8;        
      }       
                  
      // border/edge
      drawRoundRect(0, 0, unscaledWidth, unscaledHeight, null,
                    borderColors, 1,
                    verticalGradientMatrix(0, 0, unscaledWidth, unscaledHeight),
                    GradientType.LINEAR, null, null); 

      // linear gradient fills
      var h1:Number = (unscaledHeight-2)/2;
      var h2:Number = unscaledHeight-2 - h1;
      
      var lg:LinearGradient = new LinearGradient();
      lg.angle = 90;
      lg.entries = [
        new GradientEntry(ColorUtil.adjustBrightness2(backgroundColor,50), 0, 1),
        new GradientEntry(backgroundColor)
      ];
      lg.begin(graphics, new Rectangle(0, 0, unscaledWidth, unscaledHeight/1.5-1));
      graphics.drawRect(1, 1, unscaledWidth-2, h1);
      lg.end(graphics);

      var lg2:LinearGradient = new LinearGradient();
      lg2.angle = 90;
      lg2.entries = [ 
        new GradientEntry(ColorUtil.adjustBrightness2(backgroundColor,-20), 0, 1),
        new GradientEntry(ColorUtil.adjustBrightness2(backgroundColor,0), 0.5, 1),        
        new GradientEntry(ColorUtil.adjustBrightness2(backgroundColor,50), 1, 1)
      ];        
      lg2.begin(graphics, new Rectangle(0, 0, unscaledWidth, unscaledHeight-3));
      graphics.drawRect(1, 1+h1, unscaledWidth-2, h2);   
      lg2.end(graphics); 
      
      var lg3:LinearGradient = new LinearGradient();
      lg3.entries = [
      	new GradientEntry(0x7c7cfb)
      ];
      lg3.begin(graphics, new Rectangle(0, 0, unscaledWidth, 3));
      graphics.drawRect(1, unscaledHeight-3, taskItem.data.progress/100*(unscaledWidth-2), 3);
      lg3.end(graphics);
    }
    
	  protected function getBackgroundColor():uint{
	  	
	  	var reason:String = ColorEnum.DEFAULT;
	  	if (taskItem){	  		
	  		var activity:ActivityVO = taskItem.data as ActivityVO;
	  		
	  		if(ActivityVO.isNonProducton(activity)){
	  			if(activity.proved == Constants.BOOLEAN_NO && ActivityVO.isProjectReason(activity)){
	  				reason = ColorEnum.NOT_PROVED
	  			}else{
	  				reason = ColorEnum.NON_PRODUCTION;
	  			}	  			
	  		}
	  		else if(activity.deleted == Constants.BOOLEAN_YES){
	  			reason = ColorEnum.DELETED;
	  		}	  		
	  		else if(activity.trainingFlag == Constants.BOOLEAN_YES){
	  			reason = ColorEnum.TRAINING;
	  		}
  			else if(activity.activityType == ActivityEnum.FEEDBACK){
  				reason = ColorEnum.FEEDBACK;
  			}
	  		else{
	  			return ColorEnum.performanceColor(taskItem.data.assignedEffort, activity.actualStaffDays);	  				  							
		  	}
	  	}
	  	
	  	return ColorEnum.typeToColor[reason];
	  }          
	  
	  protected function getIcon():String{
		if(taskItem){
			if(taskItem.data.taskReferenceUrl == null || taskItem.data.taskReferenceUrl == ""){
				return null;
			}
			else{
				return ActivityVO.getTaskReferenceUrl(taskItem.data as ActivityVO);
			}
		}
		else{
			return null;
		}
	  }
	  
	  protected function isShowIcon(showIcon:Boolean):Boolean{
 		return false;  	
	  }
	  
	  private function isShowMedal():Boolean{
	  	if((TimeUnit.DAY.milliseconds / ganttSheet.zoomFactor < 9)){
	  		return false;
	  	}
	  	else if(taskItem.data.medalEdited == Constants.BOOLEAN_YES && (taskItem.data.activityType == ActivityEnum.PRODUCTION || taskItem.data.activityType == ActivityEnum.FEEDBACK) ){
	  		return true;
	  	}
	  	else{
	  		return false;
	  	} 
	  }
   }
}
