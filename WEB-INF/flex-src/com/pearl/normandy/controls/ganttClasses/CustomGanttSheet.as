package com.pearl.normandy.controls.ganttClasses
{
  import ilog.gantt.GanttSheet;
  import ilog.gantt.GanttSheetEvent;
  import ilog.gantt.TaskItem;
  
  import mx.controls.listClasses.IListItemRenderer;
  import mx.core.EventPriority;
  import mx.core.IInvalidating;

  public class CustomGanttSheet extends GanttSheet
  {
    /**
     * The TaskItem being edited. The value is kept up to date by the
     * itemEditBegin/itemEditEnd handlers.
     */
    protected var editedTaskItem:TaskItem;
    
    /**
     * The original resource associated to the TaskItem being edited. The 
     * value is kept up to date by the handlers for the 
     * itemEditBegin/itemEditEnd events.
     */
    protected var editedTaskItemOriginalResource:Object; 

    /**
     * Constructor.
     */
    public function CustomGanttSheet()
    {
      super();
      addEventListener(GanttSheetEvent.ITEM_EDIT_BEGIN, handleItemEditBegin);
      addEventListener(GanttSheetEvent.ITEM_EDIT_END, handleItemEditEnd);
      
      addEventListener(GanttSheetEvent.ITEM_EDIT_MOVE, handleItemEditMove, 
                       false, EventPriority.DEFAULT_HANDLER - 1);
      addEventListener(GanttSheetEvent.ITEM_EDIT_RESIZE, handleItemEditResize, 
                       false, EventPriority.DEFAULT_HANDLER - 1);
      addEventListener(GanttSheetEvent.ITEM_EDIT_REASSIGN, handleItemEditReassign, 
                       false, EventPriority.DEFAULT_HANDLER - 1);
    }

    /**
     * Handle itemEditBegin events. This method updates the informations on
     * the task item being edited.
     */
    private function handleItemEditBegin(event:GanttSheetEvent):void {
      // Take note of which item is being edited and its original resource
      editedTaskItem = TaskItem(event.itemRenderer.data);
      editedTaskItemOriginalResource = editedTaskItem.resource;
    }

    /**    
     * Handle itemEditEnd events. This method updates the informations on
     * the task item being edited.
     */
    private function handleItemEditEnd(event:GanttSheetEvent):void {
      editedTaskItem = null;
      editedTaskItemOriginalResource = null;
    }

    /**    
     * Handle itemEditMove events. This method invalidates the siblings of the
     * task being edited.
     */
    private function handleItemEditMove(event:GanttSheetEvent):void {
      invalidateTasksOfResource(TaskItem(event.itemRenderer.data).resource);
    }
    
    /**    
     * Handle itemEditResize events. This method invalidates the siblings of the
     * task being edited.
     */
    private function handleItemEditResize(event:GanttSheetEvent):void {
      invalidateTasksOfResource(TaskItem(event.itemRenderer.data).resource);
    }
    
    /**    
     * Handle itemEditReassign events. This method invalidates the siblings of 
     * the task being edited.
     */
    private function handleItemEditReassign(event:GanttSheetEvent):void {
      var taskItem:TaskItem = TaskItem(event.itemRenderer.data);
      invalidateTasksOfResource(taskItem.resource);
      
      if (taskItem.resource != editedTaskItemOriginalResource)
        // Also refresh the tasks of the original resource
        invalidateTasksOfResource(editedTaskItemOriginalResource);
    }

    /**
     * Invalidate the item renderers of the tasks associated with a resource.
     * 
     * @param resource  The resource date provider item for which to invalidate 
     *                  the tasks. 
     */ 
    public function invalidateTasksOfResource(resource:Object):void {
      var siblings:Array = getTasks(resource);
      if (!siblings)
        return;
  
      // Invalidate the item renderers of the siblings tasks
      for each (var task:Object in siblings) {
        var r:IListItemRenderer = itemToItemRenderer(task) as IListItemRenderer;
        if (r is IInvalidating)
          IInvalidating(r).invalidateDisplayList();
      }
    }

    /**
     * Returns the tasks associated with a resource, considering the current
     * editing state of the relationship.
     * 
     * @param resource  The resource for which to look for tasks. 
     *
     * @return  An <code>Array</code> of the tasks associated with the 
     *          resource. 
     */ 
    public function getTasks(resource:Object):Array {
  
      var tasks:Array = resourceChart.getTasks(resource);

      // Do not modify the internal returned array.
      tasks = tasks ? tasks.concat() : [];
  
      // The return value of ResourceChart.getTasks() is based on the state
      // of the data items in the data providers. During editing of tasks the 
      // state of the relationship between resources and tasks may be 
      // different from the state in the data providers, specifically when the 
      // task is reassigned to a different resource.
      // The proper rendering of the overlapping condition requires that we
      // consider the current editing state of the task items.

      if (editedTaskItem == null) {
        // Not editing tasks.
        return tasks;
      }
  
      if (editedTaskItem.resource == editedTaskItemOriginalResource) {
        // The task being edited is associated to the same resource as in the 
        // data provider: the editing state of the relationship is the same as
        // the data provider state.
        return tasks;
      }
  
      if (resource == editedTaskItem.resource) {
        // We are looking for the tasks associated with the resource that is
        // now associated to the task being edited. 
        tasks.push(editedTaskItem.data);
        return tasks;
      }
      
      if (resource == editedTaskItemOriginalResource) {
        // We are looking for the tasks associated with the resource that
        // was originally associated to the task being edited: the edited task
        // is no longer associated to this resource.
        var index:int = tasks.indexOf(editedTaskItem.data);
        if (index != -1)
          tasks.splice(index, 1);
        return tasks;
      }

      return tasks;
    }
  
    /**
     * Returns the sibling tasks of a task.
     * 
     * @param taskItem  The <code>TaskItem</code> for which to look for 
     *                  siblings.
     * @return  An <code>Array</code> of the siblings task data provider items 
     *          of the task. It includes the task itself.
     */ 
    public function getSiblingTasks(taskItem:TaskItem):Array {
      if (!taskItem)
        return null;
  
      return getTasks(taskItem.resource);
    }
    
    /**
     * Returns <code>true</code> when a task has overlapping sibling tasks.
     * 
     * @param taskItem  The task item for which to look for overlapping 
     *                  siblings.
     * @return <code>true</code> when the task has overlapping siblings.
     */ 
    public function hasOverlappingSiblings(taskItem:TaskItem):Boolean {
      var siblings:Array = getSiblingTasks(taskItem);
      if (!siblings)
        return false;
  
      // Look for other tasks that overlap taskItem
      for each (var task:Object in siblings)
      {
        var other:TaskItem = itemToTaskItem(task);
        if (other && (other.data.id != taskItem.data.id)
            && (other.startTime.time < taskItem.endTime.time)
            && (other.endTime.time > taskItem.startTime.time))
          return true;
      }
      return false;
    }
  }
}