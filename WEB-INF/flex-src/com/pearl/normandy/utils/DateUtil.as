package com.pearl.normandy.utils
{
	import mx.formatters.DateFormatter;
	
	public class DateUtil
	{
		private static var dateFormatterSimple:DateFormatter;
		private static var dateFormatterShort:DateFormatter;
		private static var dateFormatterDay:DateFormatter;
		
		public static const SIMPLE:String		= "Simple";
		public static const SHORT:String  		= "Short";
		public static const DAY:String			= "Day";
		public static const YEAR_MONTH:String	= "Year_Month"
	
		public static function getDateFormatter(format:String):DateFormatter{
			switch(format){
				case SIMPLE:
					return getDateFormatterSimple();
					break;
				case SHORT:
					return getDateFormatterShort();
					break;
				case DAY:
					return getDateFormatterDay();
					break;
				case YEAR_MONTH:
					return getDateFormatterYearMonth();					
				default:
					return getDateFormatterSimple();
					break;					
			}
		}	
	
		public static function getDateFormatterSimple():DateFormatter{
			if(dateFormatterSimple == null){
				dateFormatterSimple = new DateFormatter();
				dateFormatterSimple.formatString = "MM/DD/YYYY JJ:NN:SS";
				return dateFormatterSimple;
			}
			else{
				return dateFormatterSimple;
			}
		}
		
		public static function getDateFormatterShort():DateFormatter{
			if(dateFormatterShort == null){
				dateFormatterShort = new DateFormatter();
				dateFormatterShort.formatString = "MM/DD/YYYY";
				return dateFormatterShort;
			}
			else{
				return dateFormatterShort;
			}			
		}
		
		public static function getDateFormatterDay():DateFormatter{
			if(dateFormatterDay == null){
				dateFormatterDay = new DateFormatter();
				dateFormatterDay.formatString = "EEE MM/DD/YY";
				return dateFormatterDay;
			}
			else{
				return dateFormatterDay;
			}			
		}	
		
		public static function getDateFormatterYearMonth():DateFormatter{
			if(dateFormatterDay == null){
				dateFormatterDay = new DateFormatter();
				dateFormatterDay.formatString = "MM/YYYY";
				return dateFormatterDay;
			}
			else{
				return dateFormatterDay;
			}
		}		
		
		public static function format(value:Object, format:String):String{
			return getDateFormatter(format).format(value);
		}
				
		//获得月份的最后一天
		public static function getLastDateOfMonth(d:Date):Date{
			var year:Number = d.getFullYear();
			var month:Number = d.getMonth();
			if(month == 11){
				year++;
				month = 0;
			}else{
				month++;
			}
			var date:Date = new Date(year, month, 1);
			var milliSecondsOfDay:Number = 24 * 60 * 60 * 1000;
			return new Date(date.getTime() - milliSecondsOfDay);
		}
	}
}