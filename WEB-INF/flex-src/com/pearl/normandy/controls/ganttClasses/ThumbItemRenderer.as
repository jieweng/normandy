package com.pearl.normandy.controls.ganttClasses
{
  import com.pearl.normandy.vo.ActivityVO; 

  public class ThumbItemRenderer extends GanttSheetItemRenderer {

    public function ThumbItemRenderer(){
      	this.mouseChildren = false;
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
