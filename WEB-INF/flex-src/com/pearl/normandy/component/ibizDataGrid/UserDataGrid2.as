package com.pearl.normandy.component.ibizDataGrid
{
	import mx.controls.DataGrid;
	import mx.events.DragEvent;
	
	public class UserDataGrid2 extends DataGrid
	{
		public function UserDataGrid2()
		{
		}
		
		override protected function dragDropHandler(event:DragEvent):void{
		
			if(event.dragInitiator == this){
			
				return;
			}
			
			super.dragDropHandler(event);
		} 
	}
}