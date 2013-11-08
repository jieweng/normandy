package com.pearl.normandy.component.ibizDataGrid
{
	import com.pearl.normandy.utils.Constants;
	import com.pearl.normandy.utils.NormandyModel;
	import com.pearl.normandy.vo.ProjectMemberVO;
	import com.pearl.normandy.vo.ProjectUserVO;
	
	import mx.controls.AdvancedDataGrid;
	import mx.controls.listClasses.ListBaseSelectionData;
	import mx.core.DragSource;
	import mx.events.DragEvent;
	import mx.managers.DragManager;
	
	public class UserDataGrid extends AdvancedDataGrid
	{
		public function UserDataGrid()
		{
			
		}
		
		private var selectDataObj:ProjectMemberVO;
		private var currProjectId:int;
		private var model:NormandyModel = NormandyModel.getInstance();
		
		override protected function dragStartHandler(event:DragEvent):void{
		
			if (event.isDefaultPrevented())
            return;
            var dg:UserDataGrid = event.dragInitiator as UserDataGrid;
            var dragSource:DragSource = new DragSource();
            var arr:Array;
            
            selectDataObj = dg.selectedItem as ProjectMemberVO;
            arr = parseItemToTreeItem(selectDataObj, model.GLOBAL_SELECTED_PROJECT.id);
            currProjectId = model.GLOBAL_SELECTED_PROJECT.id;
	        dragSource.addData(arr, Constants.FORMAT_TREE_ITEMS);   
	        dragSource.addHandler(copySelectedItems2, Constants.FORMAT_ITEMS);
	        DragManager.doDrag(this, dragSource, event, dragImage,
	                           0, 0, 0.5, dragMoveEnabled);
		}
		
		override protected function dragCompleteHandler(event:DragEvent):void{
			
			super.dragCompleteHandler(event);
	        event.preventDefault();
	        event.currentTarget.hideDropFeedback(event);        	
		}
		
		private function parseItemToTreeItem(selectDataObj:ProjectMemberVO, projectId:int):Array{
			
			var projectUserVO:ProjectUserVO = new ProjectUserVO();
			var arr:Array = new Array();
			projectUserVO.name = selectDataObj.userName;
			projectUserVO.projectId = projectId;
			projectUserVO.userId = selectDataObj.userId;
			projectUserVO.projectRoleId = -1;
			//字段没有添加完
			
			arr.push(projectUserVO);
			return arr;
		}
		
		private function copySelectedItems2(boolen:Boolean):Array{
	
			var tmp:Array = [];
			var curSelectionData:ListBaseSelectionData = selectDataObj as ListBaseSelectionData;
		    while (curSelectionData != null)
		    {
		        if (false)
		            tmp.push(curSelectionData.data);
		        else
		            tmp.push(curSelectionData.index);
		          
		    }
		    
		    return tmp;
		}
	}
}