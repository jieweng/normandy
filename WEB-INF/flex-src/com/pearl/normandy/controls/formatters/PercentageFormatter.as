package com.pearl.normandy.controls.formatters
{
	import mx.formatters.Formatter;

	public class PercentageFormatter extends Formatter
	{
		public function PercentageFormatter()
		{
			super();
		}
		
		override public function format(value:Object):String{
			if(isNaN(value as Number)){
				error = "Not a Number";
				return "";
			}
			if(value>100) value = 100;
			return value.toString()+"%";
		}
	}
}