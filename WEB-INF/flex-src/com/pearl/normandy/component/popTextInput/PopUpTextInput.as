package com.pearl.normandy.component.popTextInput
{
	import flash.display.DisplayObject;
	import flash.events.Event;
	import flash.events.FocusEvent;
	import flash.events.KeyboardEvent;
	import flash.events.MouseEvent;
	import flash.geom.Point;
	import flash.ui.Keyboard;
	
	import mx.collections.ArrayCollection;
	import mx.collections.ICollectionView;
	import mx.collections.IList;
	import mx.collections.IViewCursor;
	import mx.collections.ListCollectionView;
	import mx.collections.XMLListCollection;
	import mx.controls.List;
	import mx.controls.TextInput;
	import mx.core.Application;
	import mx.events.CollectionEvent;
	import mx.events.FlexEvent;
	import mx.events.ListEvent;
	import mx.managers.PopUpManager;
	import mx.utils.StringUtil;
	
    [Event(name="onChange", type="com.pearl.normandy.component.popTextInput.SearchEvent")]
	public class PopUpTextInput extends TextInput
	{
		private var _popUp:List=null;
		private var _enable:Boolean;
		private var listCount:int=10;
		private var _collection:ArrayCollection;
		private var prefix:String="";
		
		public function PopUpTextInput()
		{
			super();
			addEventListener(Event.CHANGE,textChangeHandler);
			addEventListener(MouseEvent.CLICK,focInHandler);
			addEventListener(FlexEvent.CREATION_COMPLETE,createHandler);
			addEventListener(KeyboardEvent.KEY_DOWN,selectItemHandler);
			addEventListener(FocusEvent.FOCUS_OUT,focOutHandler);
		}
		
		private function focInHandler(e:Event):void{
			if(_keepSelection || (null!=_item && text==_item.label)){
				this.setSelection(0,text.length);
				prefix="";
			}
			displayPop();
			callLater(function():void{
				if(_keepSelection || (null!=_item && text==_item.label)){
					if(_item){
					_popUp.selectedIndex=_item.index;
					}
				}else{
					_popUp.selectedIndex=0;
				}
				keepSelectionVisible();
			}
			)
		}
		
		private function focOutHandler(e:Event):void{
			if(open){
				_popUp.visible=false;
				open=false;
			}
			if(_keepSelection){
				text=_item.label;
			}
			else if(null!=_item){
				if(text==_item.label)return;
				_item=null;
				_index=-1;
				dispatchEvent(new SearchEvent(SearchEvent.SUBMIT,null,-1));
			}
		}
		
		private function selectItemHandler(e:KeyboardEvent):void{
			if(!open || _collection.length==0)return ;
			if(e.keyCode==Keyboard.UP && _popUp.selectedIndex>0){
				_popUp.selectedIndex=_popUp.selectedIndex-1;
				keepSelectionVisible();
			}
			if(e.keyCode==Keyboard.DOWN && _popUp.selectedIndex<_collection.length-1){ //修改显示条数
				_popUp.selectedIndex=_popUp.selectedIndex+1;
				keepSelectionVisible();
			}
			if(e.keyCode == Keyboard.ENTER && _popUp.selectedIndex>=0){
				popUpItemClickHandler(e);
			}
			if(e.keyCode==Keyboard.ESCAPE){
				focOutHandler(e);
			}
		}
		
		private function keepSelectionVisible() : void
        {
            if (_popUp.selectedIndex >= 0)
            {
                var first : int = _popUp.verticalScrollPosition;
                var index : int = _popUp.selectedIndex;
                if (index < first || index >= first + listCount){
                    _popUp.verticalScrollPosition = Math.min(_popUp.selectedIndex, _popUp.maxVerticalScrollPosition);
                }
            }
        }
		
		private function createHandler(e:Event):void{
			_popUp = PopUpManager.createPopUp(DisplayObject(Application.application), List, false) as List;
			_popUp.dataProvider=_collection;
			_popUp.labelField="label";
			_popUp.owner=this;
			_popUp.width=this.width;
			_popUp.visible=false;
			if(_collection && _collection.length>0 && _keepSelection){
				_item=_collection.getItemAt(0) as PopTextObject;
				_index=_item.index;
				text=_item.label;
				dispatchEvent(new SearchEvent(SearchEvent.SUBMIT,selectedItem,selectedIndex));
			}
			_popUp.addEventListener(ListEvent.ITEM_CLICK,popUpItemClickHandler);
		}
		
		private function getLabel(obj:Object):String{
			if(_labelFunc!=null)return _labelFunc(obj);
			if(_label!=""){
				if (typeof(obj) == "object" || typeof(obj) == "xml"){
					return obj[_label]
				}
			}
	        if (typeof(obj) == "string"){
	            return String(obj);
			}
			return obj.toString();
		}
		
		private function textChangeHandler(e:Event):void{
			prefix=text;
			displayPop();
			_popUp.selectedIndex = 0;
            keepSelectionVisible();
		}
		
		private function displayPop():void{
			if(!_enable)return ;
			if(_collection==null)return;
			if(_collection.filterFunction==null){
			_collection.filterFunction=filterGroup;
			}
			_collection.refresh();
			if(_collection.length>0){
				_popUp.dataProvider=_collection;
				var point:Point = new Point(0, measuredHeight);
				point = localToGlobal(point);
				_popUp.x=point.x;
				_popUp.y=point.y;
				_popUp.rowCount=Math.min(_collection.length,listCount);
				_popUp.visible=true;
				open=true;
			}else{
				_popUp.visible=false;
				open=false;
			}
		}
		
		private function popUpItemClickHandler(e:Event):void{
			if(_popUp.selectedIndex<0)return;
			if(_index!=_popUp.selectedItem.index){
				_item=_popUp.selectedItem as PopTextObject;
				_index=_item.index;
				dispatchEvent(new SearchEvent(SearchEvent.SUBMIT,selectedItem,selectedIndex));
			}
			text=_item.label;
			this.setSelection(text.length,text.length);
			_popUp.visible=false;
			open=false;
		}
		
		
		private function filterGroup(item:Object):Boolean{
			if(StringUtil.trim(prefix)=="")return true;
			var toStr:String = StringUtil.trim(prefix);
			if(item.label.toUpperCase().indexOf(toStr.toUpperCase())>=0){
				return true;
			}else{
				return false;
			}	
		}
		
		private var _group:ICollectionView;
		public function  set dataProvider(value:Object):void{
			if (value is Array)
            {
                _group = new ArrayCollection(value as Array);
            }
            else if (value is ICollectionView)
            {
                _group = ICollectionView(value);
            }
            else if (value is IList)
            {
                _group = new ListCollectionView(IList(value));
            }
            else if (value is XMLList)
            {
                _group = new XMLListCollection(value as XMLList);
            }
            else
            {
                // convert it to an array containing this one item
                _group = new ArrayCollection([value]);
            }
            _group.addEventListener(CollectionEvent.COLLECTION_CHANGE,collChangeHandler);
			handleGroup(_group);
			if(null!=_popUp){
				_popUp.dataProvider=_collection;
				commitItem();
			}
		}
		
		private function collChangeHandler(e:Event):void{
			handleGroup(_group);
			if(null!=_popUp){
				_popUp.dataProvider=_collection;
				commitItem();
			}
		}
		
		private function commitItem():void{
			if(null!=_collection){
					if(_keepSelection){
						_item=_collection.getItemAt(0) as PopTextObject;
						_index=0;
						text=_item.label;
					}
			}else{
					_item=null;
					_index=-1;
					text="";
			}
		}
		
		private function handleGroup(group:ICollectionView):void{
			if(null==group || group.length==0)return;
			_collection=new ArrayCollection();
			var iterator : IViewCursor = group.createCursor();
            var i : int = 0;
            var obj:PopTextObject;
            if (iterator.current)
            {
                do
                {
                    obj=new PopTextObject;
                    obj.index=i;
                    obj.item=iterator.current;
                    obj.label=getLabel(iterator.current);
                    _collection.addItem(obj);
                    i++;
                } while (iterator.moveNext());
            }
    		if(_collection.length==0){
    			_collection=null;
    		}
		}
		
		override public function set enabled(value:Boolean):void
   		{
   			super.enabled=value;
   			_enable=value;
   		}
		
		
		private var _label:String="";
		public function set labelField(value:String):void{
			_label=value;
			handleGroup(_group  as ArrayCollection);
		}
		
		private var _labelFunc:Function;
		public function set labelFunction(value:Function):void{
			_labelFunc=value;
			handleGroup(_group  as ArrayCollection);
		}
		
		private var _keepSelection:Boolean=false;
		public function set keepSelection(value:Boolean):void{
			_keepSelection=value;
		}
		
		private var _item:PopTextObject;
		public function get selectedItem():Object{
			if(null==_item)return null;
			return _item.item;
		}
		public function set selectedItem(value:Object):void{
			if(null==_group || _group.length==0)return;
			if(null!=_collection){
				_collection.filterFunction=null;
				_collection.refresh();
				for(var i:int=0;i<_collection.length;i++){
					var obj:PopTextObject=_collection.getItemAt(i) as PopTextObject;
					if(obj.item==value){
						_item=obj;
						_index=obj.index;
						text=obj.label;
					}
				}
			}
		}
		
		private var _index:int=-1;
		public function get selectedIndex():int{
			return _index;
		}
		public function set selectedIndex(value:int):void{
			if(value<0 || null==_group || _group.length==0 )return ;
			value=Math.min(value,_group.length);
			if(null!=_collection){
				_collection.filterFunction=null;
				_collection.refresh();
				var obj:PopTextObject=_collection.getItemAt(value) as PopTextObject;
				if(null!=obj){
					_item=obj;
					_index=obj.index;
					text=obj.label;
				}
			}
		}
		
		private var open:Boolean=false;
		public function isOpen():Boolean{
			return open;
		}
		
		public function get selectedLabel():String{
			if(null==_item)return "";
			else return _item.label;
		}
	}
}