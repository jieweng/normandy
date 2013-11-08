package com.pearl.normandy.utils
{
	import flash.events.EventDispatcher;
	
	public class TimeDifference extends EventDispatcher
	{
		
		// start time type:String;
		private var startTimeChanged:Boolean = false;
		private var endTimeChanged:Boolean = false;
		private var _startTime:String;
		public function set startTime(val:String):void{
			if(_startTime != val){
				_startTime = val;
			}
		}
		public function get startTime():String{
			return _startTime;
		}
		
		// end time type:String;
		private var _endTime:String;
		public function set endTime(val:String):void{
			if(_endTime != val){
				_endTime = val;
			}
		}
		public function get endTime():String{
			return _endTime;
		}
		
		
		
		// timeUnit, type:String
		private var _timeUnit:String;
		public function set timeUnit(val:String):void{
			if (val == null) return;
			if(val != "minute" && val != "hour" && val != "day" && val != "month"){
				throw new Error("timeUnit Type Error!");
			}
			if(_timeUnit != val){
				_timeUnit = val;
			}
		}
		
		
		public function get timeUnit():String{
			return _timeUnit;
		}	
		
		
		// startTime:Number;
		private var _startTimeValue:Number;
		internal function get startTimeValue():Number{

			_startTimeValue = TimeHelp.changeDateToValue(startTimeDate, timeUnit);
			return _startTimeValue;
		}
		
		// endTime:Number;
		private var _endTimeValue:Number;
		internal function get endTimeValue():Number{

			_endTimeValue = TimeHelp.changeDateToValue(endTimeDate, timeUnit);
			return _endTimeValue;
		}
		
		private var _startTimeDate:Date;
		public function get startTimeDate():Date{

				_startTimeDate = TimeHelp.changeStringToDate(startTime, _timeUnit, false, true);

			return _startTimeDate;
		}
		
		private var _endTimeDate:Date;
		public function get endTimeDate():Date{

				_endTimeDate = TimeHelp.changeStringToDate(endTime, _timeUnit, true, true);
			return _endTimeDate;
		}
		
		public function TimeDifference(startTime:String = null, endTime:String = null, timeUnit:String = null){
			this.startTime = startTime;
			this.endTime = endTime;
			this.timeUnit = timeUnit;
		}
		
		//获得时间间隔
		public function getTimeInternal():int{
			return endTimeValue - startTimeValue;
		}
		
	}
}