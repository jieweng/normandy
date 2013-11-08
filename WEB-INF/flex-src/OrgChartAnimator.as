package
{
  import flash.geom.Rectangle;
  
  import ilog.orgchart.OrgChart;
  
  import mx.effects.Tween;
  import mx.effects.easing.Exponential;
  
  public class OrgChartAnimator
  {
    public function OrgChartAnimator()
    {
    }
    
    /**
     * Singleton
     */  
    public static const instance:OrgChartAnimator = new OrgChartAnimator();
    
    /**
     * Duration in milliseconds of the animation.
     */  
    public var duration:Number = 300;
    
    /**
     * Easing function of the tween.
     */  
    public var easingFunction:Function = Exponential.easeOut;
    
    private var _boundsFrom:Rectangle;
    private var _boundsTo:Rectangle;
    private var _tween:Tween;    
    private var _chart:OrgChart;
    
    /**
     * Animates the transition from an area to another area of the OrgChart.
     */  
    public function animateViewTo(chart:OrgChart, destination:Rectangle):void {
      if (_tween != null) {
        _tween.stop();
        _tween = null;
      }
      
      _chart = chart;
      
      _boundsFrom = chart.visibleBounds;
      _boundsTo = destination;
      
      _tween = new Tween(_tweenListener, 0, 1, duration);
      if (easingFunction != null) {
        _tween.easingFunction = easingFunction;
      }  
    }         
    
    private var _tweenListener:Object = {
      
      onTweenUpdate: function (value:Number):void {
        var r:Rectangle = _boundsFrom.clone();
        r.x += value * (_boundsTo.x - _boundsFrom.x);
        r.y += value * (_boundsTo.y - _boundsFrom.y);
        r.width += value * (_boundsTo.width - _boundsFrom.width);
        r.height += value * (_boundsTo.height - _boundsFrom.height);
        _chart.visibleBounds = r;
      },
      
      onTweenEnd: function (value:Number):void {
        _chart.visibleBounds = _boundsTo;
        _tween = null;
      }
    }; 

  }
}