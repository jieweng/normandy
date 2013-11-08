package com.pearl.normandy.controls
{
	import com.pearl.normandy.utils.Constants;
	import com.pearl.normandy.utils.HolidayUtil;
	import com.pearl.normandy.utils.TimeHelp;
	
	import mx.controls.DateField;
	import mx.controls.NumericStepper;
	import mx.controls.TextInput;
	import mx.utils.ObjectUtil;
	
	public class DateCalculate
	{
		private var holidayUtil:HolidayUtil = HolidayUtil.getInstance();
		[Bindable]
		public var selectedStartTime:Date;
		[Bindable]
		public var selectedEndTime:Date;
		
		public function DateCalculate()
		{
		}
		
		private var _startDate:DateField;
		public function set startDate(val:DateField):void{
			_startDate = val;
		}
		
		public function get startDate():DateField{
			return _startDate;
		}
		
		private var _startTimeHoursN:NumericStepper;
		public function set startTimeHoursN(val:NumericStepper):void{
			_startTimeHoursN = val;
		}
		
		public function get startTimeHoursN():NumericStepper{
			return _startTimeHoursN;
		}
		
		private var _startTimeMinN:NumericStepper;
		public function set startTimeMinN(val:NumericStepper):void{
			
			_startTimeMinN = val;
		}
		
		public function get startTimeMinN():NumericStepper{
			return _startTimeMinN;
		}
		
		private var _duration:TextInput;
		public function set duration(val:TextInput):void{
			_duration = val;
		}
		
		public function get duration():TextInput{
			return _duration;
		}
		
		private var _endDate:DateField;
		public function set endDate(val:DateField):void{
			_endDate = val;
		}
		
		public function get endDate():DateField{
			return _endDate;
		}

		private var _endTimeHoursN:NumericStepper;
		public function set endTimeHoursN(val:NumericStepper):void{
			_endTimeHoursN = val;
		}
		
		public function get endTimeHoursN():NumericStepper{
			return _endTimeHoursN;
		}
		
		private var _endTimeMinN:NumericStepper;
		public function set endTimeMinN(val:NumericStepper):void{
			
			_endTimeMinN = val;
		}
		
		public function get endTimeMinN():NumericStepper{
			return _endTimeMinN;
		}
		
		public function onStartDateChange():void{
			
			if(!startDate.selectedDate){
				return;
			}
			selectedStartTime = new Date(startDate.selectedDate.fullYear, startDate.selectedDate.month, startDate.selectedDate.date, 
				startTimeHoursN.value, startTimeMinN.value);
			var plannedDays:Number = Number(duration.text);
			if(!isNaN(plannedDays) && plannedDays > 0 ){
				selectedEndTime = holidayUtil.getCalculatedData(selectedStartTime, plannedDays, null, Constants.TIMEUNIT_MINUE);
				endDate.selectedDate = ObjectUtil.copy(selectedEndTime) as Date;
				endTimeHoursN.value = selectedEndTime.hours;
				endTimeMinN.value = selectedEndTime.minutes;
			}else if((isNaN(plannedDays) || plannedDays == 0) && selectedEndTime && TimeHelp.compareDate(
				selectedStartTime, selectedEndTime, Constants.TIMEUNIT_MINUE) == 1){
				selectedEndTime = null;
				endDate.selectedDate = null;
				endDate.text = "";
				duration.text = "";
				endTimeHoursN.value = 0;  
				endTimeMinN.value = 0;
			}else if((plannedDays == 0 || isNaN(plannedDays)) && selectedEndTime){
				duration.text = holidayUtil.getWorkingDaysByMinutes(selectedStartTime, new Date(selectedEndTime.fullYear, selectedEndTime.month, selectedEndTime.date,
					endTimeHoursN.value, endTimeMinN.value)).toString();
			}
		}
		
		public function onEndDateChange():void{
			
			if(!endDate.selectedDate){
				return;
			}
			selectedEndTime =  new Date(endDate.selectedDate.fullYear, endDate.selectedDate.month, endDate.selectedDate.date, 
				endTimeHoursN.value, endTimeMinN.value);
			var plannedDays:Number = Number(duration.text);
			var plannedDaysNum:Number = Number(duration.text);
			if(TimeHelp.compareDate(selectedStartTime, selectedEndTime, Constants.TIMEUNIT_MINUE) == 0){
				duration.text = 0 + "";
			}
			else if(selectedStartTime && TimeHelp.compareDate(selectedStartTime, selectedEndTime, Constants.TIMEUNIT_MINUE) == -1){
				duration.text = holidayUtil.getWorkingDaysByMinutes(selectedStartTime, selectedEndTime).toString();
			}else if(selectedStartTime && TimeHelp.compareDate(selectedStartTime, selectedEndTime, Constants.TIMEUNIT_MINUE) == 1 && (!isNaN(plannedDays) && plannedDays > 0)){
				selectedStartTime = holidayUtil.getCalculatedData(null, plannedDays, selectedEndTime, Constants.TIMEUNIT_MINUE);
				startDate.selectedDate = ObjectUtil.copy(selectedStartTime) as Date;
				startTimeHoursN.value = selectedStartTime.hours;
				startTimeMinN.value = selectedStartTime.minutes;
			}else if(selectedStartTime && TimeHelp.compareDate(selectedStartTime, selectedEndTime, Constants.TIMEUNIT_MINUE) == 1 && (isNaN(plannedDays) || plannedDays == 0)){
				selectedStartTime = null;
				startDate.selectedDate = null;
				startDate.text = "";
				duration.text = "";
				startTimeHoursN.value = 0;
				startTimeMinN.value = 0;
			}else if(!selectedStartTime && (!isNaN(plannedDaysNum) && plannedDaysNum > 0 )){
				selectedStartTime = holidayUtil.getCalculatedData(null, plannedDays, selectedEndTime, Constants.TIMEUNIT_MINUE);
				startDate.selectedDate = ObjectUtil.copy(selectedStartTime) as Date;
				startTimeHoursN.value = selectedStartTime.hours;
				startTimeMinN.value = selectedEndTime.minutes;
			}
		}			  
		
		public function onDurationCommit():void{  
			
			var plannedDays:Number = Number(duration.text);
			if(!isNaN(plannedDays) && plannedDays > 0){
				if(selectedStartTime){
					selectedEndTime = holidayUtil.getCalculatedData(selectedStartTime,plannedDays, null, Constants.TIMEUNIT_MINUE);
					endDate.selectedDate = ObjectUtil.copy(selectedEndTime) as Date;
					endTimeHoursN.value = selectedEndTime.hours;
					endTimeMinN.value = selectedEndTime.minutes;
				}else if(!startDate.selectedDate && endDate.selectedDate){	
					selectedStartTime = holidayUtil.getCalculatedData(null, plannedDays, selectedEndTime, Constants.TIMEUNIT_MINUE);
					startDate.selectedDate = ObjectUtil.copy(selectedStartTime) as Date;
					startTimeHoursN.value = selectedStartTime.hours;
					startTimeMinN.value = selectedStartTime.minutes;
				}
			}
		}
	}
}