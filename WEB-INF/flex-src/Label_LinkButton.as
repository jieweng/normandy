package
{
	import flash.events.MouseEvent;
	
	import mx.controls.Label;

	public class Label_LinkButton extends Label
	{
		public function Label_LinkButton()
		{
			super();
			super.setStyle("color","#00ff00");
			super.setStyle("fontWeight","bold");
			this.addEventListener(MouseEvent.ROLL_OVER,overHandler);
			this.addEventListener(MouseEvent.MOUSE_DOWN,downHandler);
			this.addEventListener(MouseEvent.MOUSE_UP,upHandler);
			this.addEventListener(MouseEvent.ROLL_OUT,init);
			
		}
		private function init(e:MouseEvent):void{
			super.setStyle("color","#00ff00");
			super.setStyle('textDecoration','normal');
		}
		private function overHandler(e:MouseEvent):void{
			super.setStyle("textDecoration","underline");//显示下划线
           	super.setStyle("color",'#FF0000');
		}
		private function downHandler(e:MouseEvent):void{
			super.setStyle('textDecoration','underline');
           	super.setStyle('color','#0000FF');
           	this.addEventListener(MouseEvent.ROLL_OUT,outHandler);
		}
		private function upHandler(e:MouseEvent):void{
            super.setStyle("color","#bc11c2");//鼠标抬起后更改字体颜色，表示已经点击过
		}
		private function outHandler(e:MouseEvent):void{
				super.setStyle('textDecoration','normal');
            	super.setStyle('color','#bc11c2');
		}
		
	}
}