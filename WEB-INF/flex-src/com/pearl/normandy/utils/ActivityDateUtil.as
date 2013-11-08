package com.pearl.normandy.utils
{
	import mx.utils.ObjectUtil;
	
	/**
	 * DateUtils for calculate the working days, accurate to a minute
	 * @author Timon Zhang
	 * */
	public class ActivityDateUtil
	{	
		private static var holidayUtil:HolidayUtil =  HolidayUtil.getInstance();
		
		private static var FORENOON_START_H:int = 9 ;
		private static var FORENOON_START_M:int = 0 ;
		
		private static var FORENOON_END_H:int = 12 ;
		private static var FORENOON_END_M:int = 0 ;
		
		private static var AFTERNOON_START_H:int = 13 ;
		private static var AFTERNOON_START_M:int = 0 ;
		
		private static var AFTERNOON_END_H:int = 18 ;
		private static var AFTERNOON_END_M:int = 0 ;
		
		
		private static var FORENOON_START_DT:DayTime ;
		private static var FORENOON_END_DT:DayTime ;
		private static var AFTERNOON_START_DT:DayTime ;
		private static var AFTERNOON_END_DT:DayTime ;
		
		private static var instance:ActivityDateUtil ;
		
		public static function getInstance():ActivityDateUtil{
			if (instance == null) {
				instance = new ActivityDateUtil();
			}
			return instance;
		}
		
		public function ActivityDateUtil()
		{
				FORENOON_START_DT = new DayTime();
				FORENOON_START_DT.setHour(FORENOON_START_H);
				FORENOON_START_DT.setMinutes(FORENOON_START_M);
				
				FORENOON_END_DT = new DayTime();
				FORENOON_END_DT.setHour(FORENOON_END_H);
				FORENOON_END_DT.setMinutes(FORENOON_END_M);
				
				AFTERNOON_START_DT  = new DayTime();
				AFTERNOON_START_DT.setHour(AFTERNOON_START_H);
				AFTERNOON_START_DT.setMinutes(AFTERNOON_START_M);
				
				AFTERNOON_END_DT  = new DayTime();
				AFTERNOON_END_DT.setHour(AFTERNOON_END_H);
				AFTERNOON_END_DT.setMinutes(AFTERNOON_END_M);
		}
		
		// get total days between two date, not include end day
		private function getDays(start:Date,end:Date):int{
			//ignore the field about hour and minutes and seconds
			var s:Date = new Date(start.getFullYear(),start.getMonth(),start.getDate());
			var e:Date = new Date(end.getFullYear(),end.getMonth(),end.getDate());
			
			var milliseconds_diff:Number = Math.abs(s.time - e.time);
			var days:Number = milliseconds_diff/ (24 * 3600 * 1000);
			return ObjectUtil.dateCompare(start,end)<=0?days:-days;
		}
		
		//get working days between two date, not include end day
		private function getWorkingDays(startDate:Date,endDate:Date):int{
			//ignore the field about hour and minutes and seconds
			var start:Date = new Date(startDate.getFullYear(),startDate.getMonth(),startDate.getDate());
			var end:Date = new Date(endDate.getFullYear(),endDate.getMonth(),endDate.getDate());
			
			var noneWeekendHolidays:int = 0;
			for (var i:int = 0; i<  holidayUtil.holidays.length;i++) {
				var holiday:Date = 	holidayUtil.holidays[i] as Date;
				if (ObjectUtil.dateCompare(holiday,start) >= 0 && ObjectUtil.dateCompare(holiday,end) <0 && !holidayUtil.isWeekHoliday(holiday)) {
					noneWeekendHolidays++;
				}
			}
			
			var totalDays:int = getDays(start,end);
			var weekendDays:int = 0;
			
			//get the offset to Saturday, Sunday is 0 and Saturday is 6
			var toNearestStaurday:int =  6 - start.getDay() ;
			
			//move 'start' to next Saturday as a week begin, exception :if currentDay is Sunday, latter will add it 
			var saturdayStart:Date = new Date(start.getFullYear(),start.getMonth(),start.getDate());
			saturdayStart.setTime(saturdayStart.getTime() + toNearestStaurday * 24 * 3600 * 1000);
			
			//if it's sunday 				
			if (start.getDay() == 0 && totalDays >0) {
				weekendDays ++;
			}
						
			//define weeks as a week start at Saturday
			var weeks:int = 0;
			var remainTotalDays:int = totalDays - toNearestStaurday;
			
			if (remainTotalDays > 0){
				weeks = remainTotalDays/7;
				//move to last saturday
				saturdayStart.setTime(saturdayStart.getTime() + weeks * 7 * 24 * 3600 * 1000);
				//the remain day must start at Saturday, also calculate it 
				var remainDays:int = getDays(saturdayStart,end);
				weekendDays += (weeks *2) + (remainDays >2 ? 2 : remainDays );
			}
			return totalDays - weekendDays - noneWeekendHolidays;
		}
		
		public function getWorkingTimeInHour(start:Date, end:Date):Number{
			return getWorkingTimeInMinutes(start,end)/60;
		}
		
		public function getWorkingTimeInDay(start:Date, end:Date):Number{
			return getWorkingTimeInHour(start,end)/8;
		}
		
		public function getWorkingTimeInMinutes(start:Date, end:Date):Number{
			//make sure start <= end
			if (ObjectUtil.dateCompare(start,end) >0) {
				return 0;
			}
			var firstDayWorkingMinutes:int = 0;
			var lastDayWorkingMinutes:int = 0;
			var totalWorkingMinutes:Number = 0;
			
			var startDT:DayTime = new DayTime();
			startDT.setHour(start.getHours());
			startDT.setMinutes(start.getMinutes());
			
			var endDT:DayTime = new DayTime();
			endDT.setHour(end.getHours());
			endDT.setMinutes(end.getMinutes());
			
			//end date and start date are at same day 
			if (getDays(start,end) == 0) {
				if (isWorkingDay(start)){
					totalWorkingMinutes =  workingMinutesOfOneDay(startDT,endDT);
				}else {
					totalWorkingMinutes = 0;
				}
			}else{// start date and end date are not same day
				  //calculate start date and end date individually 
				  // then
				  //calculate working days between start and end(don't include start and end)
				var workingDay:int = getWorkingDays(start,end); 
				
				if (isWorkingDay(start)) {
					firstDayWorkingMinutes = workingMinutesOfOneDay(startDT,AFTERNOON_END_DT);
					workingDay --;// minus 1 : exclusive start day
				}
				if (isWorkingDay(end)) {
					lastDayWorkingMinutes = workingMinutesOfOneDay(FORENOON_START_DT,endDT);
				}
				//workingday -1 : exclusive start day
				totalWorkingMinutes  = firstDayWorkingMinutes + workingDay  * 8 * 60 + lastDayWorkingMinutes;
			}
		
			return totalWorkingMinutes;
		}
		
		private function  workingMinutesOfOneDay(start:DayTime,end:DayTime):int{
			return getOverlapMinutes(FORENOON_START_DT,FORENOON_END_DT,start,end) + getOverlapMinutes(AFTERNOON_START_DT,AFTERNOON_END_DT,start,end);
		}
		
		//get the minutes of of intersection two time period 
		private  function getOverlapMinutes(start1:DayTime, end1:DayTime, start2:DayTime, end2:DayTime):int{
			var biggerStart:DayTime = start1.compareTo(start2) >= 0 ? start1:start2;
			var smallerEnd:DayTime = end1.compareTo(end2) <= 0 ? end1:end2;
			return smallerEnd.compareTo(biggerStart) >0? smallerEnd.minutesBetween(biggerStart) : 0;
		}
		
		private function isWorkingDay(day:Date):Boolean{
			return !holidayUtil.isHoliday(day) &&  !holidayUtil.isWeekHoliday(day) || holidayUtil.isWorkDay(day);
		}
	}
}

 class DayTime{
		private var hour:int;
		private var minutes:int;
		
		public function  getHour():int{ 
			return hour;
		}
		
		public function  getMinutes():int{ 
			return minutes;
		}
		
		public function setHour(hour:int):void {
			this.hour = hour;
		}
		
		public function setMinutes(minutes:int):void{
			this.minutes = minutes;
		}
		
		public function compareTo(o2:DayTime):int{
			if (this.getHour()<o2.getHour() || (this.getHour() == o2.getHour() && this.getMinutes() < o2.getMinutes()) ) {
				return -1;
			}else if (this.getHour() > o2.getHour() || (this.getHour() == o2.getHour() && this.getMinutes() > o2.getMinutes()) ) {
				return 1;
			}else {
				return 0;
			}
		}
		
		public function minutesBetween(o2:DayTime):int{
			var smallDayTime:DayTime = this.compareTo(o2) < 0?this:o2;
			var biggerDayTime:DayTime = smallDayTime == this?o2:this;
			
			var absMinutes:int = (biggerDayTime.getHour() - smallDayTime.getHour()) * 60 + biggerDayTime.getMinutes() - smallDayTime.getMinutes();
			return this == smallDayTime? -absMinutes : absMinutes;
		}		
	
	}