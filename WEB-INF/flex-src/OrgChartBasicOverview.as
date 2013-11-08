package
{
  import flash.display.Graphics;
  import flash.display.Shape;
  import flash.events.Event;
  import flash.events.MouseEvent;
  import flash.geom.Matrix;
  import flash.geom.Point;
  import flash.geom.Rectangle;
  
  import ilog.orgchart.OrgChart;
  import ilog.orgchart.OrgChartEvent;
  
  import mx.collections.IHierarchicalCollectionView;
  import mx.collections.IViewCursor;
  import mx.core.UIComponent;
  import mx.effects.Tween;
  import mx.events.CollectionEvent;
  import mx.events.SandboxMouseEvent;

  /**
   * A sample overview using the API introduced in ILOG Elixir 2.0.
   */  
  public class OrgChartBasicOverview extends UIComponent
  {
    public function OrgChartBasicOverview()
    {
      super();
      
      addEventListener(MouseEvent.MOUSE_DOWN, mouseDownHandler);
      addEventListener(MouseEvent.MOUSE_MOVE, mouseMoveHandler);
      addEventListener(MouseEvent.MOUSE_UP, mouseUpHandler);
      
      addEventListener(MouseEvent.ROLL_OVER, mouseRollOverHandler);
      addEventListener(MouseEvent.ROLL_OUT, mouseRollOutHandler);
      
      addEventListener(MouseEvent.MOUSE_WHEEL, mouseWheelHandler);
                 
    }
    
    private var _chart:OrgChart;
    
     /**
     * @private
     */
    public function set chart(value:OrgChart):void {
      
      if (_chart != null) {
        _chart.removeEventListener(OrgChartEvent.LAYOUT_PERFORMED, layoutPerformed);
        _chart.removeEventListener(OrgChartEvent.VISIBLE_BOUNDS_CHANGE, visibleBoundsChanged);
        _chart.removeEventListener(CollectionEvent.COLLECTION_CHANGE, collectionChanged);      
      }
      
      _chart = value;
      
      _chart.addEventListener(OrgChartEvent.LAYOUT_PERFORMED, layoutPerformed);
      _chart.addEventListener(OrgChartEvent.VISIBLE_BOUNDS_CHANGE, visibleBoundsChanged);
      _chart.addEventListener(CollectionEvent.COLLECTION_CHANGE, collectionChanged);
      
      invalidateProperties();
    }               

    public function get chart():OrgChart{
      return _chart;
    }
    
    private var _pendingModel:Boolean = false;
    private var _pendingModelDrawing:Boolean = false;
    private var _modelBounds:Rectangle;
    private var _visibleBounds:Rectangle;
    
    override protected function commitProperties():void {
      
      if (_pendingModel) {
        
        _modelBounds = _chart.modelBounds;
        
        if (_modelBounds != null) {
          
          _pendingModel = false;
          _pendingModelDrawing = true;
                               
        }       
      }
      
      invalidateDisplayList();
    }
    
    override protected function measure():void {
      measuredHeight = 100;
      measuredWidth = 150;
    }
        
    private var _oldWidth:Number = -1;
    private var _oldHeight:Number = -1;
    
    override protected function updateDisplayList(unscaledWidth:Number, unscaledHeight:Number):void {
      
      if (_oldWidth != unscaledWidth || 
          _oldHeight != unscaledHeight || 
          _pendingModelDrawing) {
            
        _oldWidth = unscaledWidth;  
        _oldHeight = unscaledHeight;
             
        createTransformMatrix(unscaledWidth, unscaledHeight);
      }
      
      if (_matrix == null) {
        return;
      }
      
      drawVisibleBounds(unscaledWidth, unscaledHeight);
      
      var g:Graphics = graphics;
      g.clear();
      
      g.lineStyle(0, 0x555555); // style property
      g.beginFill(0xFEFBBA, _currentOpacity);       
      g.drawRect(0, 0, unscaledWidth, unscaledHeight);
      g.endFill();
      
      g = Shape(mask).graphics;
      g.clear();
      g.beginFill(0xFF0000, 0.5);
      g.drawRect(0, 0, unscaledWidth+1, unscaledHeight+1); 
      g.endFill();
      
            
      if (_pendingModelDrawing) {
        
        _pendingModelDrawing = false;
        
        g = _modelShape.graphics;
        g.clear();
                    
        var col:IHierarchicalCollectionView = IHierarchicalCollectionView(_chart.dataProvider);
        
        var cursor:IViewCursor =  col.createCursor();
        
        g.lineStyle(0, 0x000000);
                                      
        while(!cursor.afterLast) {
          
          var item:Object = cursor.current;
          
          var b:Rectangle = _chart.getItemBounds(item);
          
          if (b != null) {
            b = getTransformedRectangle(b);
            var color:uint = colorFunction == null ? 0xEAF1F6 : colorFunction(item);
            g.beginFill(color, 1);
            g.drawRect(b.x, b.y, b.width, b.height);
            g.endFill();
          }
          
          cursor.moveNext();          
                                     
        }               
      }      
    }
    
    private var _matrix:Matrix;
    
    private function createTransformMatrix(w:Number, h:Number):void {
      
      var rect:Rectangle = _modelBounds = _chart.modelBounds;
      var trect:Rectangle = new Rectangle(0, 0, w, h);
        
      if (_modelBounds != null) {
        
        var m:Matrix = new Matrix();        
        var sx:Number = trect.width/rect.width;
        var sy:Number = trect.height/rect.height;
        m.a = sx;
        m.b = 0;
        m.c = 0;
        m.d = sy;
        m.tx = trect.x - sx * rect.x;
        m.ty = trect.y - sy * rect.y;
        
        _matrix = m;
               
      }          
    }
    
    public var colorFunction:Function;
    
    private function getTransformedRectangle(r:Rectangle):Rectangle {                      
      
      var res:Rectangle = new Rectangle();
      
      var p:Point = _matrix.transformPoint(r.topLeft);
      res.x = p.x;
      res.y = p.y;
      
      var p2:Point = _matrix.transformPoint(r.bottomRight);
      res.width = p2.x - p.x;
      res.height = p2.y -p.y;
      
      return res;                     
    }
    
    override protected function createChildren():void {
      
      if (_modelShape == null) {
        _modelShape = new Shape();
        addChild(_modelShape);
      }
      
      if (_visibleBoundsShape == null) {
        _visibleBoundsShape = new Shape();
        addChild(_visibleBoundsShape);
      }
      
      if (mask == null) {
        mask = new Shape();
        addChild(mask);
      }                 
    }
    
    private var _visibleBoundsShape:Shape;
    private var _modelShape:Shape;
    
    private function drawVisibleBounds(w:Number, h:Number):void {
      
      var g:Graphics = _visibleBoundsShape.graphics;
      g.clear();
      
      if (_modelBounds != null) {
        
        _visibleBounds = _chart.visibleBounds;
        
        if (_visibleBounds != null) {
          
          g.lineStyle(2, 0x5A5AB5, 1);
          
          var r:Rectangle = getTransformedRectangle(_visibleBounds);                   
          
          g.beginFill(0x7DB7FB, 0.3);                           
          g.drawRect(r.x, r.y, r.width, r.height);
          g.endFill();          
          
        }                      
      }
    }
    
    private function layoutPerformed(event:OrgChartEvent):void {
      _pendingModelDrawing = true;
      invalidateProperties();
    }
    
    private function visibleBoundsChanged(event:OrgChartEvent):void {
      invalidateDisplayList();
    }
    
    private function collectionChanged(event:CollectionEvent):void {
      _pendingModel = true;
      invalidateProperties();
    }
        
    private var _dragging:Boolean;
    private var _startPoint:Point;
    private var _startVisibleBounds:Rectangle;
    
    private function mouseDownHandler(event:MouseEvent):void {
      _dragging = true;
      _startPoint = getInvertedPoint(new Point(event.localX, event.localY));
      if (_chart != null) {
        _startVisibleBounds = _chart.visibleBounds;
      }
      
      systemManager.getSandboxRoot().addEventListener(MouseEvent.MOUSE_MOVE, mouseMoveHandler2);
      systemManager.getSandboxRoot().addEventListener(MouseEvent.MOUSE_UP, mouseUpHandler);
      systemManager.getSandboxRoot().addEventListener(SandboxMouseEvent.MOUSE_UP_SOMEWHERE, mouseUpHandler);       
    }
    
    private function mouseMoveHandler2(event:MouseEvent):void {
      if (event.target != this) {
        mouseMoveHandler(event);
      }
    }
    
    private function mouseMoveHandler(event:MouseEvent):void {
      
      if (_dragging) {
        var p:Point = new Point(event.stageX, event.stageY);
        p = globalToLocal(p);
        updateVisibleBounds(p);
      }      
    }
    
    private function mouseUpHandler(event:Event):void {
      
      if (_dragging) {
        
        _dragging = false;
        
        systemManager.getSandboxRoot().removeEventListener(MouseEvent.MOUSE_MOVE, mouseMoveHandler2);
        systemManager.getSandboxRoot().removeEventListener(MouseEvent.MOUSE_UP, mouseUpHandler);
        systemManager.getSandboxRoot().removeEventListener(SandboxMouseEvent.MOUSE_UP_SOMEWHERE, mouseUpHandler);
        
        if (!_isIn) {
          animateAlpha(false);
        }
      }
    }
    
    private function mouseWheelHandler(event:MouseEvent):void {
      event.stopImmediatePropagation();
      event.stopPropagation();
      if (_chart != null) {
        if (event.delta > 0) {
          _chart.zoomBy(1.3, false);
        } else {
          _chart.zoomBy(1/1.3, false);
        }
      }
    }
    
    private var _tween:Tween;
    private var _currentOpacity:Number = 0.3;
    private var _isIn:Boolean;
    
    private function mouseRollOverHandler(event:MouseEvent):void {
      if (!_isIn) {      
        _isIn = true;
        animateAlpha(true);
      }        
    }
    
    private function mouseRollOutHandler(event:MouseEvent):void {
      _isIn = false;
      if (!_dragging) {
        animateAlpha(false);
      }
    }
    
    private function animateAlpha(show:Boolean):void {
      
      if (_tween != null) {
        _tween.stop();
        _tween = null;
      }
                 
      _tween = new Tween(_tweenListener, _currentOpacity, show ? 0.9 : 0.3, 500);                 
    }
    
    
    private var _tweenListener:Object = {
      onTweenUpdate: function(value:Number):void {
        _currentOpacity = value;
        invalidateDisplayList();
      },
      onTweenEnd: function(value:Number):void {
        _currentOpacity = value;
        _tween = null;      
        invalidateDisplayList();
      }
    };
    
    
    private function getInvertedPoint(p:Point):Point {
      
      if (_chart != null) {
        var m2:Matrix = _matrix.clone();
        m2.invert();
        p = p.clone();        
        p = m2.transformPoint(p);
        return p;
      }
      
      return null;
    }
    
    private function updateVisibleBounds(endPoint:Point):void {
      
      if (_chart != null) {
                     
        var r:Rectangle = _chart.visibleBounds;
                
        var p2:Point = getInvertedPoint(endPoint);
                
        r.x = _startVisibleBounds.x + p2.x - _startPoint.x;
        r.y = _startVisibleBounds.y + p2.y - _startPoint.y;
        
        _chart.visibleBounds = r;
      }     
    }        
  }
}