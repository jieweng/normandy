package  com.pearl.normandy.component.ibizList
{
	import mx.controls.List;
	import mx.controls.listClasses.ListBaseSelectionData;
	import mx.core.DragSource;
	import mx.events.DragEvent;
	import mx.managers.DragManager;
	import mx.utils.UIDUtil;
	
	public class MyList extends List
	{
		public function MyList()
		{
			
		}
		
		private var selectData:Object;
		override protected function dragStartHandler(event:DragEvent):void{
		
			 if (event.isDefaultPrevented())
            return;
            selectData = (event.currentTarget as List).selectedItem;
	        var dragSource:DragSource = new DragSource();
	        var arr:Array = parseItemToTreeItem(selectData);
	        dragSource.addData(arr, "treeItems");         
	        dragSource.addHandler(copySelectedItems2, "treeItems");
	        DragManager.doDrag(this, dragSource, event, dragImage,
	                           0, 0, 0.5, dragMoveEnabled);
		}
		
		private function parseItemToTreeItem(selectData:Object):Array{
			 var obj:Object = {name: "DICE", label: "DICE"}			

			  var arr:Array = new Array();
			  arr.push(obj);
			  obj.mx_internal_uid = UIDUtil.createUID();
			  return arr;
		}
		
		private function copySelectedItems2(boolen:Boolean):Array{
	
			var tmp:Array = [];
			var curSelectionData:ListBaseSelectionData = selectData as ListBaseSelectionData;
		    while (curSelectionData != null)
		    {
		        if (false)
		            tmp.push(curSelectionData.data);
		        else
		            tmp.push(curSelectionData.index);
		          
		//        curSelectionData = curSelectionData.nextSelectionData;
		    }
		    
		    return tmp;
		}
	}
}