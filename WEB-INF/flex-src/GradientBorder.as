package
{
  import flash.display.GradientType;
  import flash.filters.GlowFilter;
  import flash.geom.Matrix;
  
  import ilog.orgchart.OrgChartItemRendererBorder;
  
  import mx.core.EdgeMetrics;
  import mx.utils.ColorUtil;

  public class GradientBorder extends OrgChartItemRendererBorder
  {
    public function GradientBorder()
    {
      super();
    }
    
    override protected function drawBorder(color:uint, radius:Number, hole:Object, thickness:Number, width:Number, height:Number):void {
      
      if (name == "selectedOverSkin" || name == "selectedSkin") {
      
        color = 0xB2E1FF;
        
        if (filters.length == 0) {
          filters = [new GlowFilter(0x60BEFF, 1, 10, 10)];
        }  
      } else {
        filters = null;
      }
      
      super.drawBorder(color, radius, hole, thickness, width, height);
           
    }
    
    override protected function drawBackground(color:uint, radius:Number, width:Number, height:Number):void {
                
      var bm:EdgeMetrics = borderMetrics;
      
      var colors:Array = [ColorUtil.adjustBrightness(color, 100), color];
      
      var m:Matrix = new Matrix();
      m.createGradientBox(width, height, Math.PI/2);
     
      if (radius != 0)  {                      
        drawRoundRect(
            bm.left, bm.top,
            width - (bm.left + bm.right),
            height - (bm.top + bm.bottom), 
            radius,
            colors, [1, 1], m,
            GradientType.LINEAR, [0, 255],
            null);
      } else {          
        graphics.beginGradientFill(GradientType.LINEAR, colors, [1.0, 1.0], [0, 255], m);
        graphics.drawRect(bm.left, bm.top,
                          width - bm.right - bm.left, 
                          height - bm.bottom - bm.top);
        graphics.endFill();
      }
                
    }
    
  }
}