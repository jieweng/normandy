package ilog.gantt.examples
{
  import flash.display.Graphics;
  import flash.geom.Point;
  
  import ilog.gantt.GanttSheet;
  import ilog.gantt.TaskItem;
  import ilog.gantt.TaskItemRenderer;
  
  import mx.core.FlexShape;

  /**
   * A custom task item renderer that adds a completion bar and a baseline to
   * the default task item renderer.
   * The completion bar is rendered for data provider items that have a 
   * 'completion' field with the percentage of completion in the range 0 to 1.
   * The baseline is rendered for data provider items that have 'baselineStart'
   * and 'baselineEnd' fields, either with a Date object, or a String 
   * representation of the date.
   */
  public class TaskChartCustomTaskItemRenderer extends TaskItemRenderer
  {
	  //--------------------------------------------------------------------------
    //
    //  Constructor
    //
    //--------------------------------------------------------------------------
    
    public function TaskChartCustomTaskItemRenderer()
    {
    }
    
    //--------------------------------------------------------------------------
    //
    //  Variables
    //
    //--------------------------------------------------------------------------
    
    private var _baseline:FlexShape;
    private var _completion:FlexShape;
    
    //--------------------------------------------------------------------------
    //
    //  Overridden methods: UIComponent
    //
    //--------------------------------------------------------------------------
    
    override protected function createChildren():void
    {
      super.createChildren();
      
      if (!_baseline)
      {
        _baseline = new FlexShape();
        _baseline.name = "baseline";
        addChild(_baseline);
      }
      if (!_completion)
      {
        _completion = new FlexShape();
        _completion.name = "completion";
        addChild(_completion);
      }
    }
    
    override protected function updateDisplayList(unscaledWidth:Number,
                                                  unscaledHeight:Number):void
    {
      // Render the task using the default task item renderer
      super.updateDisplayList(unscaledWidth, unscaledHeight);

      // Render the baseline
      updateBaseline(unscaledWidth, unscaledHeight);
      
      // Render the completion bar
      updateCompletionBar(unscaledWidth, unscaledHeight);
    }
    
    /**
     * Draw the baseline.
     */
    private function updateBaseline(unscaledWidth:Number, unscaledHeight:Number):void
    {
      var g:Graphics = _baseline.graphics;
      g.clear();

      if (TaskItem(data).isMilestone)
        drawMilestoneBaseline(g, unscaledHeight);
      else
        drawTaskBaseline(g, unscaledHeight);

      // Ensure the baseline bar is drawn below the task
      setChildIndex(_baseline, 0);
    }

    /**    
     * Draw the baseline for a milestone.
     * @param g               Graphics to draw into.
     * @param unscaledHeight  The unscaled height of the task item renderer.
     */
    private function drawMilestoneBaseline(g:Graphics, unscaledHeight:Number):void
    {
      var ganttSheet:GanttSheet = TaskItem(data).ganttSheet;

      // Fetch the initial start and end dates
      var baselineStart:Date = getDate("baselineStart");

      if (!baselineStart)
        return;

      // Determine the corresponding coordinates
      var x0:Number = getCoordinate(baselineStart);
      var y0:Number = 0;
      var h:Number = unscaledHeight;

      g.lineStyle(1, 0x889988);
      g.beginFill(0xCCDDCC);
      g.moveTo(x0, y0);
      g.lineTo(x0 + h / 2, y0 + h / 2);
      g.lineTo(x0, y0 + h);
      g.lineTo(x0 - h / 2, y0 + h / 2);
      g.lineTo(x0, y0);
      g.endFill();
    }
    
    /**
     * Draw the baseline for a leaf task or a summary task.
     * @param g               Graphics to draw into.
     * @param unscaledHeight  The unscaled height of the task item renderer.
     */
    private function drawTaskBaseline(g:Graphics, unscaledHeight:Number):void
    {
      // Fetch the initial start and end dates
      var baselineStart:Date = getDate("baselineStart");
      var baselineEnd:Date = getDate("baselineEnd");

      if (!baselineStart || !baselineEnd)
        return;

      // Determine the corresponding coordinates
      var x0:Number = getCoordinate(baselineStart);
      var x1:Number = getCoordinate(baselineEnd);
      var h:Number = unscaledHeight / 2 - 2;
      var y0:Number = unscaledHeight - h;

      // Draw the baseline
      g.lineStyle(1, 0x889988);
      g.beginFill(0xCCDDCC);
      g.drawRect(x0, y0, x1 - x0, h);
      g.endFill();
    }

    /**
     * Draw the completion bar.
     */
    private function updateCompletionBar(unscaledWidth:Number, unscaledHeight:Number):void
    {
      var g:Graphics = _completion.graphics;
      g.clear();

      if (TaskItem(data).isMilestone)
      {
        // No completion bar on milestone
        return;
      }
      else
      {
        drawCompletionBar(g, unscaledWidth, unscaledHeight);
      }
      
      // Ensure the baseline bar is drawn over the task
      setChildIndex(_completion, numChildren - 1);
    }

    /**
     * Draw the completion bar for a task.
     * @param g               Graphics to draw into.
     * @param unscaledWidth   The unscaled width of the task item renderer.
     * @param unscaledHeight  The unscaled height of the task item renderer.
     */
    private function drawCompletionBar(g:Graphics, unscaledWidth:Number, unscaledHeight:Number):void
    {
      // Fetch the percentage of completion
      var completion:Number;
      
      var taskItem:TaskItem = TaskItem(data);
      var item:Object = taskItem.data;
      if (item.hasOwnProperty("completion"))
      {
        completion = item["completion"] as Number;
      }

      if (isNaN(completion) || completion == 0)
        return;

      // Determine the completion date
      var completionDate:Date = new Date(taskItem.startTime.time 
              + (taskItem.endTime.time - taskItem.startTime.time) * completion);

      // Determine the corresponding coordinate
      var barX0:Number = 1;
      var barX1:Number = Math.min(getCoordinate(completionDate), unscaledWidth);
      var barH:Number = unscaledHeight / 2 - 1;
      var barY:Number =  1;

      // Draw the completion bar
      g.beginFill(0x55FF55);
      g.drawRect(barX0, barY, barX1 - barX0, barH);
      g.endFill();
    }

    //--------------------------------------------------------------------------
    //
    //  Utilities
    //    
    //--------------------------------------------------------------------------    

    /**
     * Returns a date from a field value in the data provider item.
     * 
     * @param field  The name of the field from which to obtain the date.
     * @return  A Date object corresponding to the field value. 
     */
    private function getDate(field:String):Date
    {
      var item:Object = TaskItem(data).data;
      
      if (item.hasOwnProperty(field))
      {
        var v:Object = item[field];
        if (v is Date)
          return v as Date;
        else  if (v is String)
          return new Date(v);
        else
          return null;
      }
      return null;
    }
    
    /**
     * Returns the coordinate corresponding to a date, in the renderer's
     * coordinate space. The value is clipped so it does not exceed the Gantt
     * sheet's view by more than 1000 pixels.
     * 
     * @param date  The date for which to retrieve the coordinate.
     * @return  The coordinate corresponding to the date, in the renderer's 
     *          coordinate space.
     */
    private function getCoordinate(date:Date):Number
    {
      var ganttSheet:GanttSheet = TaskItem(data).ganttSheet;
      var x:Number = ganttSheet.getCoordinate(date);

      // Clip the value so we do not draw too much beyond the Gantt sheet's
      // bounds.
      x = Math.min(ganttSheet.width + 1000, Math.max(-1000, x));
      
      // Convert to the renderer's coordinate space
      var p:Point = new Point(x, 0);      
      p = ganttSheet.localToGlobal(p);
      p = globalToLocal(p);
      return p.x;
    }
  }
}
