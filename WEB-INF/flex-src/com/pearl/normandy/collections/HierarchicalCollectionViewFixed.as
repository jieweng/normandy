package com.pearl.normandy.collections
{
  import mx.core.mx_internal;
  import mx.utils.UIDUtil;  
  import mx.collections.HierarchicalCollectionView;
  import mx.collections.ICollectionView;
  import mx.collections.IViewCursor;
  import mx.collections.IHierarchicalData;
  import mx.collections.SortField;
  import mx.collections.errors.ItemPendingError;
  import mx.events.CollectionEvent;
  import mx.events.CollectionEventKind;
  import mx.events.PropertyChangeEvent;
  
  use namespace mx_internal;
  
  public class HierarchicalCollectionViewFixed extends HierarchicalCollectionView
  {
    public function HierarchicalCollectionViewFixed(hierarchicalData:IHierarchicalData=null, argOpenNodes:Object=null)
    {
      super(hierarchicalData, argOpenNodes);
    }
    
    override public function itemUpdated(item:Object, property:Object=null, oldValue:Object=null, newValue:Object=null):void {
      var event:CollectionEvent =
            new CollectionEvent(CollectionEvent.COLLECTION_CHANGE);
        event.kind = CollectionEventKind.UPDATE;

        var objEvent:PropertyChangeEvent =
            new PropertyChangeEvent(PropertyChangeEvent.PROPERTY_CHANGE);
        objEvent.source = item;
        objEvent.property = property;
        objEvent.oldValue = oldValue;
        objEvent.newValue = newValue;
        event.items.push(objEvent);

        dispatchEvent(event);
    }
    
    
		/**
	     * Following code is temporary solution for fixing expandAll performance issue 
	     * Code copied from HierarchicalCollectionView::openNode()
	     * http://bugs.adobe.com/jira/browse/FLEXDMV-1964
	     */
		public function fastOpenNode(node:Object):void
	    {
	        // add the node to the openNodes object
	        openNodes[UIDUtil.getUID(node)] = node;
	        
	        // apply the sort/filter to the child collection of the opened node.
	        var childrenCollection:ICollectionView = getChildren(node);
	        
	        // return if there are no children
	        if (!childrenCollection)
	            return;
	        
	        if (sortCanBeApplied(childrenCollection) && !(childrenCollection.sort == null && sort == null))
	        {
	            childrenCollection.sort = this.sort;
	        }
	        if (!(childrenCollection.filterFunction == null && filterFunction == null))
	        {
	            childrenCollection.filterFunction = this.filterFunction;
	        }
	        childrenCollection.refresh();
	
	
			var cursor:IViewCursor = childrenCollection.createCursor();
			while (!cursor.afterLast)
			{
				var uid:String = UIDUtil.getUID(cursor.current);
				parentMap[uid] = node;
	
				try
				{
					cursor.moveNext();
				}
				catch (e:ItemPendingError)
				{
					break;
				}
			}
	
	        // don't update the length
	        //updateLength();
	    }
		
		/**
	     *  Code copied from HierarchicalCollectionView::closeNode()
	     */
		public function fastCloseNode(node:Object):void
	    {
	        var childrenCollection:ICollectionView = getChildren(node);
	
	        // removes the node from the openNodes object
	        delete openNodes[UIDUtil.getUID(node)];
	
			if (childrenCollection)
			{
				var cursor:IViewCursor = childrenCollection.createCursor();
				while (!cursor.afterLast)
				{
					var uid:String = UIDUtil.getUID(cursor.current);
					delete parentMap[uid];
	
					try
					{
						cursor.moveNext();
					}
					catch (e:ItemPendingError)
					{
						break;
					}
	        
				}
			}
			
			// don't update the length
			//updateLength();
	    }


		/**
	     * @private
	     * Check if a collection has the properties on which the sort is applied.
	     * 
	     * Copied from HierarchicalCollectionView, as it's private   
	     */	
	    private function sortCanBeApplied(coll:ICollectionView):Boolean
	    {
	    	if (sort == null)
	    		return true;
	    	
	        // get the current item
	        var obj:Object = coll.createCursor().current;
	        
	        if (!obj || !sort.fields)
	            return false;
	        
	        // check for the properties (sort fields) in the current object
	        for (var i:int = 0; i < sort.fields.length; i++)
	        {
	            var sf:SortField = sort.fields[i];
	            if (!obj.hasOwnProperty(sf.name))
	                return false;
	        }
	        return true;
	    }
	}    
}