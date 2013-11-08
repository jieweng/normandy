package
{
  import flash.events.Event;

  public class EditingEvent extends Event
  {
    
    public static const MOVE_ITEM:String = "moveItem";
    
    public var item:Object;
    public var parent:Object;
    
    public function EditingEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false, 
                                 item:Object=null, parent:Object=null)
    {
      super(type, bubbles, cancelable);
      this.item = item;
      this.parent = parent;
    }
    
  }
}