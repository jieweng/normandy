package com.pearl.normandy.utils
{
	import mx.utils.ObjectUtil;
	/**
	 * 是一个单例
	 * @author user
	 * 
	 */	
	public class HolidayUtil
	{
		private var timeHelp:TimeHelp = TimeHelp.getInstance();
		private static var holidayUtil:HolidayUtil;
		public static function getInstance():HolidayUtil{
		
			if(holidayUtil == null){
			
				holidayUtil = new HolidayUtil();
			}
			return holidayUtil;
		}
		
		//一周那些天休息,元素为int型
		private var _weekHoliday:Array = new Array();	
		public function set weekHoliday(arr:Array):void{
			
			arr = setHolidayOfWeek(arr);
			if(arr != _weekHoliday){
				_weekHoliday = arr;
			}
		}
		
		public function get weekHoliday():Array{
			
			return _weekHoliday;
		}
		
		//必须工作的天，优先级最高 元素为Data型
		private var _workDays:Array = new Array();
		public function set workDays(val:Array):void{
		
			if(val != _workDays){
				_workDays = val;
			}	
		}
		
		public function get workDays():Array{
			return _workDays;
		}
		
		//对weekHoliday 从小到大排序 
		public function setHolidayOfWeek(holiday:Array):Array{	
			
			return sortHoliday(holiday, 7);
		}
		
		private var _holidays:Array = new Array();//出去weekHoliday 的假期 元素为三维数组
		private var holidayMap:Object = new Object();
		public function set holidays(val:Array):void{
			_holidays = val;
			
			var yyyy:String;
			var mm:String;
			var dd:String;
			
			for each(var d:Date in val){
				yyyy = d.getFullYear().toString();
				mm	 = d.getMonth().toString();
				dd	 = d.getDate().toString();
				
				if(!holidayMap.hasOwnProperty(yyyy)){
					holidayMap[yyyy] = new Object();
				}
				
				if(!holidayMap[yyyy].hasOwnProperty(mm)){
					holidayMap[yyyy][mm] = new Array();
				}
				
				if((holidayMap[yyyy][mm] as Array).indexOf(dd) == -1){
					(holidayMap[yyyy][mm] as Array).push(dd);
				}				
			}
		}
		
		public function get holidays():Array{
			return _holidays;
		}
		
		//对weekHoliday 排序
		private static function sortHoliday(holiday:Array, maxValue:int):Array{
			
			if (holiday == null) return null;
			var ret:Array;			
			var i:int;			
			ret = new Array();
			var len:int = holiday.length;
			for (i = 0; i < len; i++){
				if(!isNaN(holiday[i]) && (int(holiday[i]) > -1)){
					ret.push(holiday[i]);
				}
			}
			ret.sort();
			for (i = 1; i < ret.length; i++){
				if (ret[i] == ret[i - 1] || ret[i] >= maxValue){
					ret.splice(i, 1);
					i--;
				}
			}
			if (ret[0] >= maxValue){
				ret.shift();
			}
			return ret;
		}	 
		
		//核查是否为weekHolidays
		public function isWeekHoliday(date:Date):Boolean{
		
			var dayOfWeek:Number = date.getDay();
			if (weekHoliday != null && weekHoliday.indexOf(dayOfWeek) != -1){
				return true;
			}
			return false;
		}
		
		//核查是否为workDay
		public function isWorkDay(date:Date):Boolean{
		
			var tempDate:Date;
			for(var i:int = 0; i < workDays.length; i++){
			
				tempDate = workDays[i] as Date;
				if(TimeHelp.compareDate(date, tempDate, Constants.TIMEUNIT_DAY) == 0){
		
					return true;
				}
			}
			return false;
		}
				
		//核查是否是一个holiday
		public function isHoliday(date:Date):Boolean{
			var yyyy:String = date.getFullYear().toString();
			var mm:String 	= date.getMonth().toString();
			var dd:String	= date.getDate().toString();
		
			if(holidayMap.hasOwnProperty(yyyy) && holidayMap[yyyy].hasOwnProperty(mm) && (holidayMap[yyyy][mm] as Array).indexOf(dd)!= -1){
				return true;
			}
			else{
				return false;
			}
		}
		
		//获得指定day相邻的下一个weekDay
		private function getNextWeekHoliday(week:int):int{
			
			var varNum:int = weekHoliday[weekHoliday.length - 1] - week;
			if(varNum > 0 && weekHoliday.length == 1){
				return weekHoliday[0];
			}
			if(varNum > 0){
				
				for(var i:int = weekHoliday.length - 2; i > 0; i--){
			
					if((weekHoliday[i] - week) > 0 && (weekHoliday[i] - week) < varNum){
					
						varNum = weekHoliday[i] - week;
					}
				}
				return week + varNum;
			}else{
				return -1
			}
		}
		
		
		//获得工作的天数,精确到分钟
		public function getWorkingDaysByMinutes(s:Date, e:Date):Number{
			//保留3位小数
			return Number(ActivityDateUtil.getInstance().getWorkingTimeInDay(s,e).toFixed(3));
		}
		
		
		//获得工作的天数
		public function getWorkingDays(s:Date, e:Date, timeUnit:String = "hour"):Number{
		
			var duration:Number;
			var workingDayDifference:Number
			var startHours:Number;//开始的小时数
			var endHours:Number;//结束的小时数
			//如果开始时间大于结束时间
			if(TimeHelp.compareDate(s, e, timeUnit) == 1){
				return -getWorkingDays(e, s, timeUnit);
			}
/* 			if(timeUnit == Constants.TIMEUNIT_DAY && TimeHelp.compareDate(s, e, Constants.TIMEUNIT_DAY) == 1){
				return -getWorkingDays(e, s, timeUnit);
			}
			else if(timeUnit == Constants.TIMEUNIT_HOUR && TimeHelp.compareDate(s, e, Constants.TIMEUNIT_HOUR) == 1){
				return -getWorkingDays(e, s, timeUnit);
			}else if(timeUnit == Constants.TIMEUNIT_MINUE && TimeHelp.compareDate(s, e, Constants.TIMEUNIT_MINUE) == 1){
				return -getWorkingDays(e, s, timeUnit);
			} */
			//当timeUnit="Day"时
			else if(timeUnit == Constants.TIMEUNIT_DAY && TimeHelp.compareDate(s, e, Constants.TIMEUNIT_DAY) == 0){
				if((isHoliday(s) || isWeekHoliday(s)) && !isWorkDay(s)){
					return 0;
				}else{
					return 1;
				}
			}
/* 			else if(TimeHelp.compareDate(s, e, Constants.TIMEUNIT_DAY) == 0 && timeUnit == Constants.TIMEUNIT_HOUR){
				if((isHoliday(s) || isWeekHoliday(s)) && !isWorkDay(s)){
					return 0;
				}
				startHours = timeHelp.getWorkHoursByDay(s, CalendarConfig.WORKING_TIMES, true, timeUnit);
				endHours = timeHelp.getWorkHoursByDay(e, CalendarConfig.WORKING_TIMES, false, timeUnit);
				return Number(TimeHelp.formatDecimals(((startHours + endHours - CalendarConfig.WORKING_HOURS) / Number(CalendarConfig.WORKING_HOURS)) ,4));
			}else if(TimeHelp.compareDate(s, e, Constants.TIMEUNIT_DAY) == 0 && timeUnit == Constants.TIMEUNIT_MINUE){
				if((isHoliday(s) || isWeekHoliday(s)) && !isWorkDay(s)){
					return 0;
				}
				startHours = timeHelp.getWorkHoursByDay(s, CalendarConfig.WORKING_TIMES, true, timeUnit);
				endHours = timeHelp.getWorkHoursByDay(e, CalendarConfig.WORKING_TIMES, false, timeUnit);
				return Number(TimeHelp.formatDecimals(((startHours + endHours - CalendarConfig.WORKING_HOURS) / Number(CalendarConfig.WORKING_HOURS)) ,4));
			} */
			//当timeUnit="hour Min"时
			else if(TimeHelp.compareDate(s, e, Constants.TIMEUNIT_DAY) == 0){
				if((isHoliday(s) || isWeekHoliday(s)) && !isWorkDay(s)){
					return 0;
				}
				startHours = timeHelp.getWorkHoursByDay(s, CalendarConfig.WORKING_TIMES, true, timeUnit);
				endHours = timeHelp.getWorkHoursByDay(e, CalendarConfig.WORKING_TIMES, false, timeUnit);
				return Number(TimeHelp.formatDecimals(((startHours + endHours - CalendarConfig.WORKING_HOURS) / Number(CalendarConfig.WORKING_HOURS)) ,4));
			}
			//获得总的天数(包括休息的天数)
			duration = TimeHelp.changeDateToValue(e, Constants.TIMEUNIT_DAY) - TimeHelp.changeDateToValue(s, Constants.TIMEUNIT_DAY) + 1;
			var startWeek:int = s.getDay();
			var endWeek:int = e.getDay();
			//当开始日期和结束日期在一周的情况
			if(duration <= 7 && startWeek < endWeek){
				var len:int = duration;
				var temp:Date = ObjectUtil.copy(s) as Date;
				for(var k:int = 0; k < len; k++){
					
					if((isHoliday(temp) || isWeekHoliday(temp)) && !isWorkDay(temp)){
						duration--;
					}
					temp.date += 1;
				}
				if(timeUnit == Constants.TIMEUNIT_DAY){
					return duration;
				}else{
					
					var startWorkHours:Number;
					var endWorkHours:Number;
					if((isHoliday(s) || isWeekHoliday(s)) && !isWorkDay(s)){
						duration++;
						startWorkHours = 0;
					}else{
						startWorkHours = timeHelp.getWorkHoursByDay(s, CalendarConfig.WORKING_TIMES, true, timeUnit);
					}
					if((isHoliday(e) || isWeekHoliday(e)) && !isWorkDay(e)){
						duration++;
						endWorkHours = 0;
					}else{
						endWorkHours = timeHelp.getWorkHoursByDay(e, CalendarConfig.WORKING_TIMES, false, timeUnit);
					}
					duration = Number(TimeHelp.formatDecimals((((duration - 2) * CalendarConfig.WORKING_HOURS + startWorkHours + endWorkHours) / CalendarConfig.WORKING_HOURS), 4));
					return duration;
				}
			}
			//不在一周的情况
			var wholeWeekWorkingDays:int = duration - (7 - startWeek) - (endWeek + 1);
			var weekHolidayLen:int = weekHoliday.length;
			workingDayDifference = wholeWeekWorkingDays - weekHolidayLen * (wholeWeekWorkingDays / 7);
			var startIndex:int = weekHoliday.indexOf(startWeek);
			var endIndex:int = weekHoliday.indexOf(endWeek);
			var tempDate:Date;
			var nextWeekHolidayIndex:int;
			//开始日期不是weekHoliday
			if(startIndex == -1){
				if(startWeek < weekHoliday[0]){
					workingDayDifference = workingDayDifference + (7 - startWeek) - weekHolidayLen;
				}else if(startWeek > weekHoliday[weekHolidayLen - 1]){
					workingDayDifference = workingDayDifference + (7 - startWeek);
				}else{
					nextWeekHolidayIndex = weekHoliday.indexOf(getNextWeekHoliday(startWeek));
					workingDayDifference = workingDayDifference + (7 - startWeek) - (weekHolidayLen - nextWeekHolidayIndex);
				}
			}else{
				workingDayDifference = workingDayDifference + (7 - startWeek) - (weekHolidayLen - startIndex);
			}
			//结束日期不是weekHoliday
			if(endIndex == -1){
				if(endWeek < weekHoliday[0]){
					
					workingDayDifference = workingDayDifference + endWeek;
				}else if(endWeek > weekHoliday[weekHolidayLen - 1]){
					workingDayDifference = workingDayDifference + endWeek - weekHolidayLen;
				}else{
					
					nextWeekHolidayIndex = weekHoliday.indexOf(getNextWeekHoliday(endWeek - 1));
					workingDayDifference = workingDayDifference + endWeek + 1 - nextWeekHolidayIndex;
				}
			}else{
				
				workingDayDifference = workingDayDifference + endWeek - endIndex;
			}
			var startNum:int;
			var endNum:int;
			
			//减去holiday
			for(var i:int = 0; i < holidays.length; i++){
			
				tempDate = holidays[i];
				if(!isWeekHoliday(tempDate)){
				
					startNum = TimeHelp.compareDate(s, tempDate, Constants.TIMEUNIT_DAY);
					endNum = TimeHelp.compareDate(e, tempDate, Constants.TIMEUNIT_DAY);
					if(startNum == 0 || (startNum == -1 && endNum == 1) || endNum == 0){
					
						workingDayDifference--;
					}
				}
			}
			//添加workDays
			for(var j:int = 0; j < workDays.length; j++){
				
				tempDate = workDays[j];
				startNum = TimeHelp.compareDate(s, tempDate, Constants.TIMEUNIT_DAY);
				endNum = TimeHelp.compareDate(e, tempDate, Constants.TIMEUNIT_DAY);
				if((isHoliday(tempDate) || isWeekHoliday(tempDate)) && (startNum == 0 || (startNum == -1 && endNum == 1) || endNum == 0)){
				
					workingDayDifference++;
				}
			}
			//当timeUnit 是Day
			if(timeUnit == Constants.TIMEUNIT_DAY){
				return workingDayDifference;
			}else{
				
				var sWorkHours:Number;
				var eWorkHours:Number;
				if((isHoliday(s) || isWeekHoliday(s)) && !isWorkDay(s)){
					workingDayDifference++;
					sWorkHours = 0;
				}else{
					sWorkHours = timeHelp.getWorkHoursByDay(s, CalendarConfig.WORKING_TIMES, true, timeUnit);
				}
				if((isHoliday(e) || isWeekHoliday(e)) && !isWorkDay(e)){
					workingDayDifference++;
					eWorkHours = 0;
				}else{
					eWorkHours = timeHelp.getWorkHoursByDay(e, CalendarConfig.WORKING_TIMES, false, timeUnit);
				}
				workingDayDifference = Number(TimeHelp.formatDecimals((((workingDayDifference - 2) * CalendarConfig.WORKING_HOURS + sWorkHours + eWorkHours) / CalendarConfig.WORKING_HOURS), 4))
				return workingDayDifference;
			}
		}
		
		//获得工作时间天数 默认的是小时 
		public function getDifferenceInWorkingDay(s:Date, e:Date):Number{
			
			var duration:Number;
			var workingDayDifference:Number
			//开始时间大于结束时间 返回-1
			if(TimeHelp.compareDate(s, e, Constants.TIMEUNIT_HOUR) == 1){
				return -getDifferenceInWorkingDay(e, s);
			//开始时间和结束时间是同一天 如果是休息日 并不是workDay 或者 时间范围在一天工作时间外 返回0  如果不是则返回
			}else if(TimeHelp.compareDate(s, e, Constants.TIMEUNIT_DAY) == 0){
				if(TimeHelp.compareDate(s, e, Constants.TIMEUNIT_HOUR) == 1 || 
					TimeHelp.compareDate(s, e, Constants.TIMEUNIT_MINUE) == 1){
					return -getDifferenceInWorkingDay(e, s);
				}
				
				if((isHoliday(s) || isWeekHoliday(s)) && !isWorkDay(s)){

					return 0;
				}
				
				return Number(TimeHelp.formatDecimals(((TimeHelp.getWorkHoursOnDay(s, CalendarConfig.WORKING_TIMES, true) + TimeHelp.getWorkHoursOnDay(e, CalendarConfig.WORKING_TIMES, false) - CalendarConfig.WORKING_HOURS) / Number(CalendarConfig.WORKING_HOURS)) ,2));
			}
			//获得总天数
			duration = TimeHelp.changeDateToValue(e, Constants.TIMEUNIT_DAY) - TimeHelp.changeDateToValue(s, Constants.TIMEUNIT_DAY) + 1;
			var startWeek:int = s.getDay();
			var endWeek:int = e.getDay();
			//开始时间和结束时间在同一周的情况
			if(duration <= 7 && startWeek < endWeek){
				var len:int = duration;
				var temp:Date = ObjectUtil.copy(s) as Date;
				for(var k:int = 0; k < len; k++){
					
					if(isHoliday(temp) || isWeekHoliday(temp)){
						duration--;
					}
					if(isWorkDay(temp)){
						duration++;
					}
					temp.date += 1;
				}
				return duration;
			}
			var wholeWeekWorkingDays:int = duration - (7 - startWeek) - (endWeek + 1);
			var weekHolidayLen:int = weekHoliday.length;
			//工作天数
			workingDayDifference = wholeWeekWorkingDays - weekHolidayLen * (wholeWeekWorkingDays / 7);
			var startIndex:int = weekHoliday.indexOf(startWeek);
			var endIndex:int = weekHoliday.indexOf(endWeek);
			var tempDate:Date;
			var nextWeekHolidayIndex:int;
			
			//当开始时间不是一个weekHoliday
			if(startIndex == -1){
				
				if(startWeek < weekHoliday[0]){
				
					workingDayDifference = workingDayDifference + (7 - startWeek) - weekHolidayLen;
				}else if(startWeek > weekHoliday[weekHolidayLen - 1]){
					
					workingDayDifference = workingDayDifference + (7 - startWeek);
				}else{
					
					nextWeekHolidayIndex = weekHoliday.indexOf(getNextWeekHoliday(startWeek));
					workingDayDifference = workingDayDifference + (7 - startWeek) - (weekHolidayLen - nextWeekHolidayIndex);
				}
			}else{
				
				workingDayDifference = workingDayDifference + (7 - startWeek) - (weekHolidayLen - startIndex);
			}
			//结束时间不是一个weekHoliday
			if(endIndex == -1){
				if(endWeek < weekHoliday[0]){
					
					workingDayDifference = workingDayDifference + endWeek;
				}else if(endWeek > weekHoliday[weekHolidayLen - 1]){
					
					workingDayDifference = workingDayDifference + endWeek - weekHolidayLen;
				}else{
					
					nextWeekHolidayIndex = weekHoliday.indexOf(getNextWeekHoliday(endWeek - 1));
					workingDayDifference = workingDayDifference + endWeek + 1 - nextWeekHolidayIndex;
				}
			}else{
				
				workingDayDifference = workingDayDifference + endWeek - endIndex;
			}
			var startNum:int;
			var endNum:int;
			//出去在区间内的holidays
			for(var i:int = 0; i < holidays.length; i++){
			
				tempDate = holidays[i];
				if(!isWeekHoliday(tempDate)){
				
					startNum = TimeHelp.compareDate(s, tempDate, Constants.TIMEUNIT_DAY);
					endNum = TimeHelp.compareDate(e, tempDate, Constants.TIMEUNIT_DAY);
					if(startNum == 0 || (startNum == -1 && endNum == 1) || endNum == 0){
					
						workingDayDifference--;
					}
				}
			}
			//添加在区间内的workDays
			for(var j:int = 0; j < workDays.length; j++){
				
				tempDate = workDays[j];
				startNum = TimeHelp.compareDate(s, tempDate, Constants.TIMEUNIT_DAY);
				endNum = TimeHelp.compareDate(e, tempDate, Constants.TIMEUNIT_DAY);
				if((isHoliday(tempDate) || isWeekHoliday(tempDate)) && (startNum == 0 || (startNum == -1 && endNum == 1) || endNum == 0)){
				
					workingDayDifference++;
				}
			}
			
			return workingDayDifference;
		}
		
		//通过基准时间和时间间隔获得另一个时间
		public function getCalculatedData(startDate:Date = null, interval:Number = 0, endData:Date = null, timeUnit:String = "Day"):Date{
			
			var offDays:int = Math.ceil(interval);//偏移的天数
			var offHours:int = Math.floor((interval - Math.floor(interval)) * CalendarConfig.WORKING_HOURS);
			var offMins:int = Math.floor(((interval - Math.floor(interval)) * CalendarConfig.WORKING_HOURS - offHours) * 60);
			var returnDate:Date;
			var tempDate:Date;
			
			var starteRange:Object = TimeHelp.getDateRangeObject(0);
			var endRange:Object = TimeHelp.getDateRangeObject(CalendarConfig.WORKING_TIMES.length - 1);
			var startTime:Date = starteRange["rangeStartDate"];
			var endTime:Date = endRange["rangeEndDate"];
			var offset:int;
			
			//开始时间为空  
			if(startDate == null){
				if(endData == null){
					return null;
				}else if(interval == 0){
					return endData;
				}
				tempDate = ObjectUtil.copy(endData) as Date;
				//获得归还的开始日期 以天数为准
				returnDate = getCalculatedStartData(Math.ceil(interval), tempDate, Constants.TIMEUNIT_DAY);
				returnDate.hours = endData.hours;
				returnDate.minutes = endData.minutes;
				//获得归还的开始日期  加上时间偏移量
				returnDate = timeHelp.getIntervalDate(returnDate, offHours, offMins, CalendarConfig.WORKING_TIMES, true);
				if(offHours == 0 && offMins ==0){
					returnDate.date -= 1;
				}
				//当获得的开始日期 小时数和分钟数为一天的结束日期小时数和分钟数时 时间往前推
				if(returnDate.hours == endTime.hours && returnDate.minutes == endTime.minutes){
					offset = timeHelp.getWorkDayIndex(returnDate, false);
					returnDate.date += offset;
					returnDate.hours = startTime.hours;
					returnDate.minutes = startTime.minutes;
				}
			//结束时间为空	
			}else if(endData == null){
				if(startDate == null){
					return null;
				}else if(interval == 0){
					return startDate;
				}
				tempDate = ObjectUtil.copy(startDate) as Date;
				returnDate = getCalculatedEndData(Math.ceil(interval), tempDate, Constants.TIMEUNIT_DAY);
				returnDate.hours = startDate.hours;
				returnDate.minutes = startDate.minutes;
				returnDate = timeHelp.getIntervalDate(returnDate, offHours, offMins, CalendarConfig.WORKING_TIMES, false);
				if(offHours == 0 && offMins ==0){
					offset = timeHelp.getWorkDayIndex(returnDate, false);
					returnDate.date += offset;
				}
				//当获得的结束日期小时数和分钟数为一天的开始日期小时数和分钟数时  时间往后推 
				if(returnDate.hours == startTime.hours && returnDate.minutes == startTime.minutes){
					offset = timeHelp.getWorkDayIndex(returnDate, true);
					returnDate.date -= offset;
					returnDate.hours = endTime.hours;
					returnDate.minutes = endTime.minutes;
				}
			}
			return returnDate;
		}
		
		//由结束日期和时间间隔获得开始日期
		private function getCalculatedStartData(interval:Number, endDate:Date, timeUnit:String = "Hour"):Date{
			
			var startDate:Date;
			var workingDayDifference:Number
			startDate = ObjectUtil.copy(endDate) as Date;
			startDate.date -= (interval - 1);
			
 			if(!TimeHelp.compareDate(startDate, endDate, Constants.TIMEUNIT_DAY)){
				startDate.hours = 0;
				endDate.hours = 23;
			}
			//获得时间间隔数
			workingDayDifference = getWorkingDays(startDate, endDate, Constants.TIMEUNIT_DAY);
			interval = interval - workingDayDifference;
			if(interval <= 0){
				return startDate;
			}else{
				startDate.date -= 1;
				endDate = startDate;
				startDate = getCalculatedStartData(interval, startDate);
				return startDate;
			}
		}
		
		//由开始日期和时间间隔获得结束日期
		private function getCalculatedEndData(interval:Number, startDate:Date, timeUnit:String = "Hour"):Date{
		
			var endDate:Date;
			var workingDayDifference:Number
			endDate = ObjectUtil.copy(startDate) as Date;
			endDate.date += (interval - 1);
			if(!TimeHelp.compareDate(startDate, endDate, Constants.TIMEUNIT_DAY)){
				startDate.hours = 0;
				endDate.hours = 23;
			}
			//获得时间间隔数
			workingDayDifference = getWorkingDays(startDate, endDate, Constants.TIMEUNIT_DAY);
			workingDayDifference = Math.ceil(workingDayDifference);
			interval = interval - workingDayDifference;
			if(interval <= 0){
				
				return endDate;
			}else{
				endDate.date += 1;
				startDate = endDate;
				endDate = getCalculatedEndData(interval, startDate);
				return endDate;
			}
		}
	}
}