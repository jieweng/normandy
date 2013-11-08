package
{
    /*LinkButton 仿IE超链接
      by  Kelvin
      MSN:xiaobolove@hotmail.com
      Email:xiaobolove334421@163.com

      样式：
      color:字体颜色
      textDecoration:下线划（无：normal,有:underline）
      textRollOverColor:鼠标滑过时字的颜色
      textSelectedColor:鼠标按下时字的颜色，即被选中时的颜色
    */

    import flash.events.MouseEvent;
    import mx.controls.LinkButton;

    public class Kelvin_LinkButton extends LinkButton
    {
       public function Kelvin_LinkButton(){
           super();
           super.alpha=1.0;
           super.setStyle("color","#00ff00");//设置初始化字体颜色
       }

       //鼠标悬浮在字上
       override protected function rollOverHandler(event:MouseEvent):void
       {
           super.rollOverHandler(event);
           super.setStyle("textDecoration","underline");//显示下划线
           super.setStyle('textRollOverColor','#FF0000');//设置鼠标滑过时的字体颜色
       }

       //鼠标按下
       override protected function mouseDownHandler(event:MouseEvent):void
       {
           super.mouseDownHandler(event);
           super.setStyle('textDecoration','underline');
           super.setStyle('textRollOverColor','#0000FF');
           super.setStyle('textSelectedColor','#00000FF');//设置被选中时的字体颜色
       }

       //鼠标抬起

       override protected function mouseUpHandler(event:MouseEvent):void
       {
           super.mouseUpHandler(event);
           super.setStyle("color","#bc11c2");//鼠标抬起后更改字体颜色，表示已经点击过
       }

       //鼠标移出
       override protected function rollOutHandler(event:MouseEvent):void
       {

           super.rollOutHandler(event);

           super.setStyle('textDecoration','normal');

           super.setStyle('textRollOverColor','#0000FF');

       }
    }
}