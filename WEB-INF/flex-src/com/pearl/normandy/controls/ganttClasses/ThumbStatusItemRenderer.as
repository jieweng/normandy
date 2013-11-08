package com.pearl.normandy.controls.ganttClasses
{
  import com.pearl.normandy.enum.ColorEnum;
  import com.pearl.normandy.vo.ActivityVO;	
  import com.pearl.normandy.utils.Constants; 

  public class ThumbStatusItemRenderer extends GanttSheetItemRenderer {

    public function ThumbStatusItemRenderer(){
      	this.mouseChildren = false;
    }  
    
	  override protected function getBackgroundColor():uint{
	  	
	  	var reason:String = ColorEnum.DEFAULT;
	  	if (taskItem){	  		
	  		var activity:ActivityVO = taskItem.data as ActivityVO;
	  		return ColorEnum.statusColor(activity);
	  	}
	  	
	  	return ColorEnum.typeToColor[reason];
	  }        
	  
	override protected function isShowIcon(showIcon:Boolean):Boolean{
 		if(showIcon && ActivityVO.isProducton(taskItem.data as ActivityVO)){
			return true;
		}
		else{
			return false;
		} 	  	
	}
  }
}
