package com.pearl.normandy.utils
{
	import mx.utils.ObjectUtil;
	
	public class CalendarConfig
	{
		  //===========================================
		  //  Custom defined working variables
		  //===========================================			
		public static const WORKING_TIME_START_HOUR:uint		= 9;
		public static const WORKING_TIME_START_MINUTE:uint		= 0;
		public static const WORKING_TIME_START_SECOND:uint		= 0;
		
		public static const WORKING_TIME_END_HOUR:uint			= 18;
		public static const WORKING_TIME_END_MINUTE:uint		= 0;
		public static const WORKING_TIME_END_SECOND:uint		= 0;		
		
		public static const WORKING_HOURS:uint					= 8;
		
		public static const START_TIME_STRING:String			= "9:00";
		public static const END_TIME_STRING:String				= "18:00";
		public static const WORKING_TIMES:Array				= [{rangeStart: "9:00", rangeEnd: "12:00"}, {rangeStart: "13:00", rangeEnd: "18:00"}];		
		
		  //===========================================
		  //  Attributes and functions
		  //===========================================					 					
		public function CalendarConfig()
		{
		}
		
/* 		public static const START:String						= "Start";
		public static const END:String							= "End";		 		
		
 		public static function getWorkingTimeString(type:String):String{
			switch(type){
				case START: return WORKING_TIME_START_HOUR+":"+WORKING_TIME_START_MINUTE; break;
				case END: return WORKING_TIME_END_HOUR+":"+WORKING_TIME_END_MINUTE;break;
				default: return ""; break;
			}
		} */
		
 		public static function parseStartTime(date:Date):Date{
 			var tmp:Date = ObjectUtil.copy(date) as Date;
 			if(tmp){
				tmp.hours		= 	WORKING_TIME_START_HOUR;
  				tmp.minutes		=	WORKING_TIME_START_MINUTE;
  				tmp.seconds		=   WORKING_TIME_START_SECOND;
  			}
  			
			return tmp;
		}	
		
 		public static function parseEndTime(date:Date):Date{
 			var tmp:Date = ObjectUtil.copy(date) as Date;
 			if(tmp){
				tmp.hours		= 	WORKING_TIME_END_HOUR;
  				tmp.minutes		=	WORKING_TIME_END_MINUTE;
  				tmp.seconds		= 	WORKING_TIME_END_SECOND;
  			}
  		
			return tmp;
		}	
		
		
		//Modify original value
 		public static function modifyStartTime(date:Date):Date{
 			if(date){
				date.hours		= 	WORKING_TIME_START_HOUR;
  				date.minutes	=	WORKING_TIME_START_MINUTE;
  			}
  			
			return date;
		}	
		
 		public static function modifyEndTime(date:Date):Date{
 			if(date){
				date.hours		= 	WORKING_TIME_END_HOUR;
  				date.minutes	=	WORKING_TIME_END_MINUTE;
  			}
  		
			return date;
		}			
		
		
		public static function getTimeLastMonth():Date{
			var d:Date = new Date();
			d.month = d.month - 1;
			d.date = 1;
			return d;
		}	
		
		public static function getTimeLastThreeMonths():Date{
			var d:Date = new Date();
			d.month = d.month - 3;
			d.date = 1;
			return d;
		}
		
		public static function getTimeLastSixMonths():Date{
			var d:Date = new Date();
			d.month = d.month - 6;
			d.date = 1;
			return d;
		}	
		
		public static function getTimeMinimum():Date{
			var d:Date = new Date(1900, 1, 1);
			return d;
		}			
		
		public static function getTimeMaximum():Date{
			var d:Date = new Date();
			d.month = d.month + 12;
			return d;			
		}												 		
	}
}