package com.pearl.normandy.component.ibizTree.event
{
	import mx.controls.listClasses.ListBase;
	import mx.controls.treeClasses.TreeListData;
	
	public class MyTreeListData extends TreeListData
	{
		public function MyTreeListData(text:String, uid:String,
								 owner:ListBase, rowIndex:int = 0,
								 columnIndex:int = 0)
		{
			super(text, uid, owner, rowIndex, columnIndex);
		}
		
		public var state:String = "";
	}
}