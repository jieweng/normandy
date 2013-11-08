package com.pearl.normandy.controls
{
    import mx.core.UITextField;
    import mx.skins.halo.ToolTipBorder;
    import mx.controls.ToolTip;

    public class NToolTip extends ToolTipBorder {
        
        override protected function updateDisplayList(unscaledWidth:Number, unscaledHeight:Number):void{
                    
            var toolTip:ToolTip = (this.parent as ToolTip);
            var textField:UITextField = toolTip.getChildAt(1) as UITextField;
            textField.htmlText = textField.text;
            
            var calHeight:Number = textField.height;
            calHeight += textField.y*2;
            calHeight += textField.getStyle("paddingTop");
            calHeight += textField.getStyle("paddingBottom");
            
            var calWidth:Number = textField.textWidth;
            calWidth += textField.x*2;
            calWidth += textField.getStyle("paddingLeft");
            calWidth += textField.getStyle("paddingRight");
            
            super.updateDisplayList(calWidth, calHeight);
            
        }        
    }
}