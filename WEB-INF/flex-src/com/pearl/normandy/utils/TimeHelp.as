package com.pearl.normandy.utils
{
	import mx.utils.ObjectUtil;
		/**
	 * 是一个单例
	 * @author user
	 * 
	 */	
	public class TimeHelp
	{
		
		private static var timeHelp:TimeHelp;
		public static function getInstance():TimeHelp{
		
			if(timeHelp == null){
			
				timeHelp = new TimeHelp();
			}
			return timeHelp;
		}
		
		private static var timeUnitList:Array = ["year", "month", "day", "hour", "minute", "second"];
		
		private var _holiday:HolidayUtil;
		public function set holiday(val:HolidayUtil):void{
			_holiday = val;
		}
		
		public function get holiday():HolidayUtil{
			if(!_holiday){
				_holiday = HolidayUtil.getInstance();
			}
			return _holiday;
		}
		
		//获取两个日期之间某一年的月份数量						by:ZhaoQiaoyang 2010/6/24
		public static function getMonthAccount(start:Date, end:Date, year:Number):int{
			var sYear:Number = start.getFullYear();
			var sMonth:Number = start.getMonth();
			var eYear:Number = end.getFullYear();
			var eMonth:Number = end.getMonth();
			var monthNum:Number = 0;
			
			if (sYear == year && eYear != year)	 	monthNum = 12 - sMonth;
						 
			if (sYear != year && eYear == year) 	monthNum = eMonth + 1; 
			
			if (sYear < year && eYear > year) 		monthNum = 12;
			
			if (sYear == year && eYear == year) 	monthNum = eMonth - sMonth + 1;
			
			return monthNum;
		}
		
		public static function getStartTime(start:Date, end:Date, year:Number):Date {
			var sYear:Number = start.getFullYear();			
			var eYear:Number = end.getFullYear();
//			var sMonth:Number = start.getMonth();
//			var eMonth:Number = end.getMonth();
			var date:Date;
			
			if (sYear < year && eYear == year) {
				date = new Date(year,0,1,9);
			} 
			
			if (sYear == year && eYear == year) {
				date = ObjectUtil.copy(start) as Date;
			}				
			
			if (sYear == year && eYear > year) {
				date = ObjectUtil.copy(start) as Date;
			}
			
			if (sYear < year && eYear > year) {
				date = new Date(year,0,1,9);
			}
			return date;
		}
		
		public static function getNextDate(d:Date):Date{
			var date:Date = ObjectUtil.copy(d) as Date;
			date.setHours(9,0,0,0);
			return new Date(date.time + 24*60*60*1000);
		}
				
		public static function getPreDate(d:Date):Date{
			var date:Date = ObjectUtil.copy(d) as Date;
			date.setHours(18,0,0,0);
			return new Date(date.time - 24*60*60*1000);
		}
		
		public static function changeDateToString(t:Date):String {
			if (t == null) return null;
			
			var Y:String = "";
			var M:String = "";
			var D:String = "";
			var H:String = "";
			var m:String = "";
			var tmp:String = "";
			var rs:String = "";

			Y = String(t.getFullYear());

			tmp = String(t.getMonth()+1);
			M = tmp.length>1 ? tmp: "0"+tmp;

			tmp = String(t.getDate());
			D = tmp.length>1 ? tmp: "0"+tmp;

			tmp = String(t.getHours());
			H = tmp.length>1 ? tmp: "0"+tmp;

			tmp = String(t.getMinutes());
			m = tmp.length>1 ? tmp: "0"+tmp;

			rs = Y + "/" + M + "/" + D + " " + H + ":" +m;
			return rs;
		}
	
	
		public static function changeStringToDate(str:String, 
				timeUnit:String = "minute", 
				isEndDate:Boolean = false,
				needCheck:Boolean = false):Date {
			var timeObj:TimeStringObject = new TimeStringObject(str);
			if (!timeObj.state) return null;
			
			var year:Number;
			var month:Number;
			var day:Number;
			var hour:Number;
			var minute:Number;

			year = timeObj.year;

			if (!isNaN(timeObj.month)) {
				month = timeObj.month - 1;
			} else {
				month = 0;
			}

			if (!isNaN(timeObj.date) && timeUnit != "month") {
				day = timeObj.date;
			} else {
				day = 1;
			}

			if (!isNaN(timeObj.hours) && timeUnit != "month" && timeUnit != "day") {
				hour = timeObj.hours;
			} else {
				hour = 0;
			}

			if (!isNaN(timeObj.min) && timeUnit == "minute") {
				minute = timeObj.min;
			} else {
				minute = 0;
			}
			
			if (needCheck){
				if (!checkDayExist(year, month + 1, day)){
					return null;
				}
			}
			
			// check the endDate
			if (isEndDate){
				switch (timeUnit){
					case "month":
						month ++;
						break;
					case "day":
						day++;
						break;
					case "hour":
						hour++;
						break;
					case "minute":
						minute++;
						break;
				}
			}

			var rtnDate:Date = new Date(year, month, day, hour, minute);
			return rtnDate;
		}	
		
		
		public static function changeValueToString(value:Number, timeUnit:String):String{
			var date:Date = changeValueToDate(value, timeUnit);
			return changeDateToString(date);
		}
		
		public static function changeValueToDate(value:Number, timeUnit:String):Date{
			var index:int = timeUnitList.indexOf(timeUnit);
			if (index == -1) return null;
			var date:Date;
			switch (index){
				case 0:
				date = new Date(1970 + value, 0, 1, 0, 0, 0);
				break;
				case 1:
				date = new Date(1970, value, 1, 0, 0, 0);
				break;
				case 2:
				date = new Date(1970, 0, value + 1, 0, 0, 0);
				break;
				case 3:
				date = new Date(1970, 0, 1, value, 0, 0);
				break;
				case 4:
				date = new Date(1970, 0, 1, 0, value, 0);
				break;
				case 5:
				date = new Date(1970, 0, 1, 0, 0, value);
				break;
			}
			return date;
		}
		
		public static function changeDateToValue(tmpDate:Date, timeUnit:String):Number{
			if(tmpDate == null){
				return NaN;
			}
			var ret:Number;
			var timeOffSet:Number = 1000 * 60 * tmpDate.getTimezoneOffset();
			switch(timeUnit){
				case "minute":
					ret = (tmpDate.getTime() - timeOffSet) / (60 * 1000);
					break;
				case "hour":
					ret = (tmpDate.getTime() - timeOffSet) / (60 * 60 * 1000);
					break;
				case "day":
					ret = (tmpDate.getTime() - timeOffSet) / (24 * 60 * 60 * 1000);
					break;
				case "month":
					ret = (tmpDate.getFullYear() - 1970) * 12 + tmpDate.getMonth();
					break;
				case "year":
					ret = (tmpDate.getFullYear() - 1970);
					break;
				default:
	
			}
			return Math.floor(ret);
		}
		
		public static function checkDayExist(year:Number, month:Number, day:Number):Boolean {
			if (month > 12 || month < 1) return false;
			if (day > 31 || day < 1) return false;
			if (year < 1000) return false;

			if (day <= 28) {
				return true;
			}

			var left:Number = month > 7 ? (month - 1) % 2:month % 2;

			if (month != 2) {
				if (left == 0) {
					if (day > 30) {
						return false;
					}
				} else if (left == 1) {
					if (day > 31) {
						return false;
					}
				} else {
					return false;
				}
			} else {
				if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
					if (day > 29) {
						return false;
					}
				} else {
					if (day > 28) {
						return false;
					}
				}
			}
			return true;
		}
		
		//获得一个月的天数
		public static function getDaysCount(valDate:Date):Number {
		 	var tmpYear:Number = valDate.getFullYear();
			var tmpMonth:Number = valDate.getMonth();
			
			if (tmpMonth == 0 || tmpMonth == 2 || tmpMonth == 4 || tmpMonth == 6 || 
				tmpMonth == 7 || tmpMonth == 9 || tmpMonth == 11) {
				return 31;
			} else if (tmpMonth == 3 || tmpMonth == 5 || tmpMonth == 8 || tmpMonth == 10) {
				return 30;
			} else {
				if (((tmpYear % 4) == 0 && ((tmpYear % 100) != 0)) || ((tmpYear % 400) == 0)) {
					return 29;
				} else {
					return 28;
				}
			}
		}
		
		//复制 日期
		public static function copyDate(date:Date, timeUnit:String):Date{
			if(date == null){
				return null;
			}
			var year:int = date.getFullYear();
			var month:int = 0;
			var day:int = 1;
			var hour:int = 0;
			var minute:int = 0;
			switch(timeUnit){
				case "minute":
					minute = date.getMinutes();
				case "hour":
					hour = date.getHours();
				case "day":
					day = date.getDate();
				case "month":
					month = date.getMonth();
				case "year":	
					year = date.getFullYear();
			}
			var dat:Date = new Date(year, month, day, hour, minute);
			return dat;
		}
		
		//比较日期 
		public static function compareDate(base:Date, refer:Date, timeUnit:String):int{
			
			if(base == null || refer == null || isNaN(base.time) || isNaN(refer.time) || 
			(timeUnit != timeUnitList[2] && timeUnit != timeUnitList[3] &&
				timeUnit != timeUnitList[4] && timeUnit != timeUnitList[5])){
				return -2;
			}
			var baseTime:Number = changeDateToValue(base, timeUnit);
			var referTime:Number = changeDateToValue(refer, timeUnit);
			if(baseTime < referTime){
				return -1;
			}else if(baseTime == referTime){
				return 0;
			}else{
				return 1;
			}
		}
		
		//四舍五入
		public static function formatDecimals(num:Number, digits:int):String{
			
			if (num < 0 ) { 
				num = 0; 
			} 
			  
			if (digits <= 0) { 
				return Math.round(num) + "";
			} 
			  
			var tenToPower:Number = Math.pow(10, digits); 
			var cropped:String = String(Math.round(num * tenToPower) / tenToPower); 
			return cropped;
		} 
		
		//if isStart = true calculate startTime else if isStart = false calculate endTime
		public function getIntervalDate(baseDate:Date, offHours:int, offMins:int, ranges:Array, isStart:Boolean = false):Date{
			//获得索引
			var index:int = getDateRangeIndex(baseDate, isStart);
			var offDay:int;
			var dateRanges:Array = getDateRanges(baseDate);
			var range:Object;
			var startDate:Date;
			var endDate:Date;
			var interval:Number;
			var minNum:Number = offHours * 60 + offMins;
			//是一个开始日期
			if(isStart){
				
				for(var j:Number = index; j >= 0; j--){
					//获得日期在哪一个时间范围
					range = dateRanges[j];
					startDate = range["rangeStartDate"];
					endDate = range["rangeEndDate"];
					//获得索引时间范围的时间数
					interval = changeDateToValue(endDate, Constants.TIMEUNIT_MINUE) - changeDateToValue(startDate, Constants.TIMEUNIT_MINUE);
					offHours = Math.floor(minNum / 60.0);
					offMins = Math.floor(minNum - offHours * 60.0);
					//当基准时间大于结束时间时
					if(compareDate(baseDate, endDate, Constants.TIMEUNIT_MINUE) == 1){
						//如果时间范围间隔数大于了偏移的分钟数
						if(interval >= minNum){
							endDate.hours -= offHours;
							endDate.minutes -= offMins;
							return endDate;
						}
						//剩下的偏移分钟数
						minNum = minNum - interval;
						
						if(j == 0){
							//获得推移后的天数  出去 休息的天数
							offDay = getWorkDayIndex(baseDate, isStart);   
							   
							baseDate.date -= offDay;
							range = dateRanges[dateRanges.length - 1];
							endDate = range["rangeEndDate"];
							baseDate.hours = endDate.hours;
							baseDate.minutes = endDate.minutes;
							//将时间范围也进行推移
							for each(var dateObj:Object in dateRanges){
								startDate = dateObj["rangeStartDate"];
								endDate = dateObj["rangeEndDate"];
								startDate.date -= offDay;
								endDate.date -= offDay;
							}
							index = dateRanges.length - 1;
							j = dateRanges.length;
							continue;
						}else{
							range = getNextDateRange(baseDate, j);
							baseDate =  range["rangeEndDate"];
							continue;
						}
					}else if((compareDate(baseDate, startDate, Constants.TIMEUNIT_MINUE) == 0 || compareDate(baseDate, startDate, Constants.TIMEUNIT_MINUE) == 1) && 
						(compareDate(baseDate, endDate, Constants.TIMEUNIT_MINUE) == 0 || compareDate(baseDate, endDate, Constants.TIMEUNIT_MINUE) == -1)){
							interval = changeDateToValue(baseDate, Constants.TIMEUNIT_MINUE) - changeDateToValue(startDate, Constants.TIMEUNIT_MINUE);
							if(interval >= minNum){
								baseDate.hours -= offHours;
								baseDate.minutes -= offMins;
								return baseDate;
						}
						minNum = minNum - interval;
						if(j == 0){
							offDay = getWorkDayIndex(baseDate, isStart);      
							baseDate.date -= offDay;
							range = dateRanges[dateRanges.length - 1];
							endDate = range["rangeEndDate"];
							baseDate.hours = endDate.hours;
							baseDate.minutes = endDate.minutes;
							for each(var dateObj_1:Object in dateRanges){
								startDate = dateObj_1["rangeStartDate"];
								endDate = dateObj_1["rangeEndDate"];
								startDate.date -= offDay;
								endDate.date -= offDay;
							}
							index = dateRanges.length - 1;
							j = dateRanges.length;
							continue;
						}else{
							range = getForwardDateRange(baseDate, j);
							baseDate =  range["rangeEndDate"];
							continue;
						}
					}
				}
			}else{
				for(var i:Number = index; i < dateRanges.length; i++){
					range = dateRanges[i];
					startDate = range["rangeStartDate"];
					endDate = range["rangeEndDate"];
					interval = changeDateToValue(endDate, Constants.TIMEUNIT_MINUE) - changeDateToValue(startDate, Constants.TIMEUNIT_MINUE);
					offHours = Math.floor(minNum / 60.0);
					offMins = Math.floor(minNum - offHours * 60.0);
					if(compareDate(baseDate, startDate, Constants.TIMEUNIT_MINUE) == -1){
						if(interval >= minNum){
							startDate.hours += offHours;
							startDate.minutes += offMins;
							return startDate;
						}
						minNum = minNum - interval;
						if(i == dateRanges.length - 1){
							offDay = getWorkDayIndex(baseDate, isStart);      
							baseDate.date += offDay;
							range = dateRanges[0];
							startDate = range["rangeStartDate"];
							baseDate.hours = startDate.hours;
							baseDate.minutes = startDate.minutes;
							for each(var dateObj_2:Object in dateRanges){
								startDate = dateObj_2["rangeStartDate"];
								endDate = dateObj_2["rangeEndDate"];
								startDate.date += offDay;
								endDate.date += offDay;
							}
							index = 0;
							i = -1;
							continue;
						}else{
							range = getNextDateRange(baseDate, i);
							baseDate =  range["rangeStartDate"];
							continue;
						}
					}else if((compareDate(baseDate, startDate, Constants.TIMEUNIT_MINUE) == 0 || compareDate(baseDate, startDate, Constants.TIMEUNIT_MINUE) == 1) && 
							(compareDate(baseDate, endDate, Constants.TIMEUNIT_MINUE) == 0 || compareDate(baseDate, endDate, Constants.TIMEUNIT_MINUE) == -1)){
						interval = changeDateToValue(endDate, Constants.TIMEUNIT_MINUE) - changeDateToValue(baseDate, Constants.TIMEUNIT_MINUE);
						if(interval >= minNum){
							baseDate.hours += offHours;
							baseDate.minutes += offMins;
							return baseDate;
						}
						minNum = minNum - interval;
						if(i == dateRanges.length - 1){
							offDay = getWorkDayIndex(baseDate, isStart);      
							baseDate.date += offDay;
							range = dateRanges[0];
							startDate = range["rangeStartDate"];
							baseDate.hours = startDate.hours;
							baseDate.minutes = startDate.minutes;
							for each(var dateObj_3:Object in dateRanges){
								startDate = dateObj_3["rangeStartDate"];
								endDate = dateObj_3["rangeEndDate"];
								startDate.date += offDay;
								endDate.date += offDay;
							}
							index = 0;
							i = -1;
							continue;
						}else{
							range = getNextDateRange(baseDate, i);
							baseDate =  range["rangeStartDate"];
							continue;
						}
					}
				}			
			}

			return baseDate;
		}
		
		//if isStart = true :calculate baseDate front a work day else if isStart = false calculate baseDate next a work day
		public function getWorkDayIndex(baseDate:Date, isStart:Boolean):int{
			
			var d:Date = ObjectUtil.copy(baseDate) as Date;
			var index:int;
			if(isStart){
				d.date -= 1; 
				while(1){
					if((!holiday.isHoliday(d) && !holiday.isWeekHoliday(d)) || holiday.isWorkDay(d)){
						return changeDateToValue(baseDate, Constants.TIMEUNIT_DAY) - changeDateToValue(d, Constants.TIMEUNIT_DAY);
						break; 
					}else{
						d.date -= 1;
					} 
				}
			}else{
				d.date += 1; 
				while(1){
					if((!holiday.isHoliday(d) && !holiday.isWeekHoliday(d)) || holiday.isWorkDay(d)){
						return changeDateToValue(d, Constants.TIMEUNIT_DAY) - changeDateToValue(baseDate, Constants.TIMEUNIT_DAY);
					}else{
						d.date += 1;
					}
				}
			}
			return -1;
		}
		
		//aim the index of data ranges 
		public static function getDateRangeIndex(baseDate:Date, isStart:Boolean):int{
			
			var dateRanges:Array = getDateRanges(baseDate);
			var range:Object;
			var startRange:Date;
			var endRange:Date;
			if(!isStart){
				for(var i:int = 0; i < dateRanges.length; i++){
					range = dateRanges[i];
					startRange = range["rangeStartDate"];
					endRange = range["rangeEndDate"];
					if((compareDate(baseDate, startRange, Constants.TIMEUNIT_MINUE) == -1 || 
						((compareDate(baseDate, startRange, Constants.TIMEUNIT_MINUE) == 0 || compareDate(baseDate, startRange, Constants.TIMEUNIT_MINUE) == 1) && 
							(compareDate(baseDate, endRange, Constants.TIMEUNIT_MINUE) == 0 || compareDate(baseDate, endRange, Constants.TIMEUNIT_MINUE) == -1)))){
						return i;
					}
				}
			}else if(isStart){
				for(var j:int = dateRanges.length - 1; j >= 0; j--){
					range = dateRanges[j];
					startRange = range["rangeStartDate"];
					endRange = range["rangeEndDate"];
					if((compareDate(baseDate, endRange, Constants.TIMEUNIT_MINUE) == 1 || 
						((compareDate(baseDate, startRange, Constants.TIMEUNIT_MINUE) == 0 || compareDate(baseDate, startRange, Constants.TIMEUNIT_MINUE) == 1) && 
							(compareDate(baseDate, endRange, Constants.TIMEUNIT_MINUE) == 0 || compareDate(baseDate, endRange, Constants.TIMEUNIT_MINUE) == -1)))){
						return j;
					}
				}
			}
			return -1;
		}
		
		//Aim date ranges 
		public static function getDateRanges(baseDate:Date):Array{

			var y:int = baseDate.fullYear;
			var m:int = baseDate.getMonth();
			var d:int = baseDate.getDate();
			
			var dateObj:Object;
			var range:Object;
			var rangeStartArr:Array;
			var rangeEndArr:Array;
			var rangeStartHour:int;
			var rangeEndHour:int;
			var rangeStartMin:int;
			var rangeEndMin:int;
			var startDate:Date;
			var endDate:Date;
			
			var dateRanges:Array = new Array();
			
			var ranges:Array = CalendarConfig.WORKING_TIMES;
			for(var i:int = 0; i < ranges.length; i++){
				dateObj = new Object();
				range = ranges[i];
				rangeStartArr = (range["rangeStart"] as String).split(":");
				rangeEndArr = (range["rangeEnd"] as String).split(":");
				rangeStartHour = Number(rangeStartArr[0]);
				rangeStartMin = Number(rangeStartArr[1]);
				rangeEndHour = Number(rangeEndArr[0]);
				rangeEndMin = Number(rangeEndArr[1]);
				startDate = new Date(y, m, d, rangeStartHour, rangeStartMin);
				endDate = new Date(y, m, d, rangeEndHour, rangeEndMin);
				dateObj["rangeStartDate"] = startDate;
				dateObj["rangeEndDate"] = endDate;
				dateRanges.push(dateObj);
			}
			return dateRanges;
		}
		
		//get "index" front date range in date ranges   
		public static function getForwardDateRange(baseDate:Date, index:int):Object{
			
			var dateRangs:Array = getDateRanges(baseDate);
			if(index > 0){
				return dateRangs[index - 1];
			}
			return null;
		}
		
		//get "index" next date range in date ranges
		public static function getNextDateRange(baseDate:Date, index:int):Object{
			
			var dateRangs:Array = getDateRanges(baseDate);
			if(index < dateRangs.length - 1){
				return dateRangs[index + 1];
			}
			return null;
		}
		
		//get the range object by the "CalendarConfig.WORKING_TIMES" and "index"
		public static function getRangeObject(index:int):Object{
			
			if(index >= 0 && index < CalendarConfig.WORKING_TIMES.length){
				return CalendarConfig.WORKING_TIMES[index];
			}
			return null;
		}
		
		//get the date range object by the index"
		public static function getDateRangeObject(index:int):Object {
			
			if(index >= 0 && index < CalendarConfig.WORKING_TIMES.length){
				var arr:Array = getDateRanges(new Date());
				return arr[index];
			}
			return null;
		}
		
		//calculate a day work hours
		public function getWorkHoursByDay(date:Date, ranges:Array, isStart:Boolean, timeUnit:String = "hour"):Number{
		
			var y:int = date.fullYear;
			var m:int = date.getMonth();
			var d:int = date.getDate();
			var range:Object;
			var rangeStartArr:Array;
			var rangeEndArr:Array;
			var rangeStartHour:int;
			var rangeEndHour:int;
			var rangeStartMin:int;
			var rangeEndMin:int;
			
			var dateRanges:Array = new Array();
			var dateRange:Object;
			var dateObj:Object;
			var startDate:Date;
			var endDate:Date;
			
			var startNextDate:Date;
			var endNextDate:Date;
			
			var result:Number = 0;
			
			for(var i:int = 0; i < ranges.length; i++){
				dateObj = new Object();
				range = ranges[i];
				rangeStartArr = (range["rangeStart"] as String).split(":");
				rangeEndArr = (range["rangeEnd"] as String).split(":");
				rangeStartHour = Number(rangeStartArr[0]);
				rangeStartMin = Number(rangeStartArr[1]);
				rangeEndHour = Number(rangeEndArr[0]);
				rangeEndMin = Number(rangeEndArr[1]);
				startDate = new Date(y, m, d, rangeStartHour, rangeStartMin);
				endDate = new Date(y, m, d, rangeEndHour, rangeEndMin);
				dateObj["rangeStartDate"] = startDate;
				dateObj["rangeEndDate"] = endDate;
				dateRanges.push(dateObj);
			}
			
			for(var j:int = 0; j < dateRanges.length; j++){
				
				dateRange = dateRanges[j];
				startDate = dateRange["rangeStartDate"];
				endDate = dateRange["rangeEndDate"];
				if(compareDate(date, startDate, timeUnit) == -1){
					if(isStart){
						for(var k:int = j; k < dateRanges.length; k++){
							dateRange = dateRanges[k];
							startDate = dateRange["rangeStartDate"];
							endDate = dateRange["rangeEndDate"];
							result = result + (changeDateToValue(endDate, timeUnit) - changeDateToValue(startDate, timeUnit));
						}
					}
					break;
				}else if((compareDate(date, startDate, timeUnit) == 0 || compareDate(date, startDate, timeUnit) == 1) && (compareDate(date, endDate,timeUnit) == 0 || compareDate(date, endDate, timeUnit) == -1)){
					if(isStart){
						result = changeDateToValue(endDate, timeUnit) - changeDateToValue(date, timeUnit);
						for(var l:int = j + 1; l < dateRanges.length; l++){
							dateRange = dateRanges[l];
							startDate = dateRange["rangeStartDate"];
							endDate = dateRange["rangeEndDate"];
							result = result + (changeDateToValue(endDate, timeUnit) - changeDateToValue(startDate, timeUnit));
						}
					}else{
						result = changeDateToValue(date, timeUnit) - changeDateToValue(startDate, timeUnit);
						if(j > 0){
							for(var e:int = 0; e < j; e++){
								dateRange = dateRanges[e];
								startDate = dateRange["rangeStartDate"];
								endDate = dateRange["rangeEndDate"];
								result = result + (changeDateToValue(endDate, timeUnit) - changeDateToValue(startDate, timeUnit));
							}
						}
					}
					break;
				}else{
					if(j < dateRanges.length - 1){
						dateRange = dateRanges[j + 1];
						startNextDate = dateRange["rangeStartDate"];
						if(compareDate(date, endDate, timeUnit) == 1 && compareDate(date, startNextDate, timeUnit) == -1){
							if(isStart){
								for(var n:int = j + 1; n < dateRanges.length; n++){
									dateRange = dateRanges[n];
									startDate = dateRange["rangeStartDate"];
									endDate = dateRange["rangeEndDate"];	
									result = result + (changeDateToValue(endDate, timeUnit) - changeDateToValue(startDate, timeUnit));		
								}
							}else{
								for(var f:int = 0; f <= j; f++){
									dateRange = dateRanges[f];
									startDate = dateRange["rangeStartDate"];
									endDate = dateRange["rangeEndDate"];	
									result = result + (changeDateToValue(endDate, timeUnit) - changeDateToValue(startDate, timeUnit));	
								}
							}
							break;
						}
					}else{
						if(isStart){
							result = 0;
						}else{
							result = CalendarConfig.WORKING_HOURS;
							if(timeUnit == Constants.TIMEUNIT_MINUE){
								result = result * 60.0;
							}
						}
						break;
					}
				}
			}
			
			if(timeUnit == Constants.TIMEUNIT_MINUE){
				result = result / 60.0;	
			}
			return result;
		}
		
		public static function getWorkHoursOnDay(date:Date, ranges:Array, isStart:Boolean, timeUnit:String = "hour"):Number{
			
			var workHours:int;
			var hour:int = date.getHours();
			var range:Object;
			var rangeStartArr:Array;
			var rangeEndArr:Array;
			var rangeStart:int;
			var rangeEnd:int;
			var index:int;
			var rangeStartNextArr:Array;
			var rangeStartNext:int;
			//search index
			for(var i:int = 0; i < ranges.length; i++){
				
				range = ranges[i];
				rangeStartArr = (range["rangeStart"] as String).split(":");
				rangeEndArr = (range["rangeEnd"] as String).split(":");
				rangeStart = Number(rangeStartArr[0]);
				rangeEnd = Number(rangeEndArr[0]);
				if((i + 1) < ranges.length){
					rangeStartNextArr = (ranges[i + 1]["rangeStart"] as String).split(":");
					rangeStartNext = Number(rangeStartNextArr[0]);
				}
				index = i;
				if(hour >= rangeStart && hour <= rangeEnd){
					if(isStart){
						workHours = rangeEnd - hour;
						for(var j:int = index + 1; j < ranges.length; j++){
							range = ranges[j];
							rangeStartArr = (range["rangeStart"] as String).split(":");
							rangeEndArr = (range["rangeEnd"] as String).split(":");
							rangeStart = Number(rangeStartArr[0]);
							rangeEnd = Number(rangeEndArr[0]);
							workHours += rangeEnd - rangeStart;					
						}
						break;
					}else{
						workHours = hour - rangeStart;
						for(var k:int = 0; k < index; k++){
							range = ranges[k];
							rangeStartArr = (range["rangeStart"] as String).split(":");
							rangeEndArr = (range["rangeEnd"] as String).split(":");
							rangeStart = Number(rangeStartArr[0]);
							rangeEnd = Number(rangeEndArr[0]);
							workHours += rangeEnd - rangeStart;			
						}
						break;
					}
				}else if(hour < rangeStart){
					if(isStart){
						for(var l:int = index; l < ranges.length; l++){
							range = ranges[l];
							rangeStartArr = (range["rangeStart"] as String).split(":");
							rangeEndArr = (range["rangeEnd"] as String).split(":");
							rangeStart = Number(rangeStartArr[0]);
							rangeEnd = Number(rangeEndArr[0]);
							workHours += rangeEnd - rangeStart;			
						}
						break;
					}else{
						for(var m:int = 0; m < index; m++){
							range = ranges[m];
							rangeStartArr = (range["rangeStart"] as String).split(":");
							rangeEndArr = (range["rangeEnd"] as String).split(":");
							rangeStart = Number(rangeStartArr[0]);
							rangeEnd = Number(rangeEndArr[0]);
							workHours += rangeEnd - rangeStart;		
						}
						break;
					}
				}else if(hour > rangeEnd && ((i == ranges.length - 1) || (hour < rangeStartNext))){
					if(isStart){
						for(var p:int = index + 1; p < ranges.length; p++){
							range = ranges[p];
							rangeStartArr = (range["rangeStart"] as String).split(":");
							rangeEndArr = (range["rangeEnd"] as String).split(":");
							rangeStart = Number(rangeStartArr[0]);
							rangeEnd = Number(rangeEndArr[0]);
							workHours += rangeEnd - rangeStart;			
						}
						break;
					}else{
						for(var n:int = 0; n <= index; n++){
							range = ranges[n];
							rangeStartArr = (range["rangeStart"] as String).split(":");
							rangeEndArr = (range["rangeEnd"] as String).split(":");
							rangeStart = Number(rangeStartArr[0]);
							rangeEnd = Number(rangeEndArr[0]);
							workHours += rangeEnd - rangeStart;		
						}
						break;
					}
				}
			}
			return workHours;
		}
		
		public static function getToday():Date{
			var d:Date = new Date();
			var today:Date = new Date(d.fullYear, d.month, d.date);
			return today;
		}
	}
}	

class TimeStringObject{
	
	private var _state:Boolean = false;
	public function get state():Boolean{
		return _state;
	}
	
	private var _year:Number;
	public function get year():Number{
		return _year;
	}
	
	private var _month:Number;
	public function get month():Number{
		return _month;
	}
	
	private var _date:Number;
	public function get date():Number{
		return _date;
	}
	
	private var _hours:Number;
	public function get hours():Number{
		return _hours;
	}
	
	private var _min:Number;
	public function get min():Number{
		return _min;
	}
	
	public function TimeStringObject(str:String){
		if (str == null) return;
		var arr1:Array = str.split(" ");
		if (arr1.length < 1) return;
		// get date part
		var arr2:Array = (arr1[0] as String).split("/");
		if (arr2.length < 2) return;
		if (arr2[0] != null){
			_year = Number(arr2[0]);
			if (isNaN(_year)) return;
		}
		if (arr2[1] != null){
			_month = Number(arr2[1]);
			if (isNaN(_month)) return;
		}
		if (arr2[2] != null){
			_date = Number(arr2[2]);
			if (isNaN(_date)) return;
		}
		// get time part
		if (arr1[1] == null){
			_state = true;
			return;
		}else if(arr2.length < 3){
			return;
		}
		var arr3:Array = (arr1[1] as String).split(":");
		if (arr3[0] != null){
			_hours = Number(arr3[0]);
			if (isNaN(_hours)) return;
		}
		if (arr3[1] != null){
			_min = Number(arr3[1]);
			if (isNaN(_min)) return;
		}
		// set the flag
		_state = true;
	}
}