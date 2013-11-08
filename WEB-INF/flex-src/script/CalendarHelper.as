// ActionScript file
  import com.pearl.normandy.enum.ActivityEnum;
  import com.pearl.normandy.enum.ColorEnum;
  import com.pearl.normandy.vo.ActivityVO;
  
  import ilog.calendar.Calendar;
  import ilog.calendar.CalendarEvent;
  import ilog.calendar.CalendarItem;
  import ilog.utils.GregorianCalendar;
  import ilog.utils.TimeUnit;
  
  import mx.events.CalendarLayoutChangeEvent;
  import mx.events.DateChooserEvent;
  import mx.formatters.DateFormatter;

  private function changeMode(mode:String):void {
    if (cal.date != null) {
      cal.mode = mode;
    } else {
      cal.date = cal.startDate;
      cal.mode = mode;          
    }               
  }
  
  
  private function changeWWMode():void {
    var date:Date = cal.date == null ? cal.startDate : cal.date;
    if (date == null) {
      date = new Date();
    }
    var calendar:GregorianCalendar = cal.calendar;
    //en_US 1, 5
    var start:Date = calendar.floor(date, TimeUnit.WEEK, 1);
    start = calendar.addUnits(start, TimeUnit.DAY, 1); 
    var end:Date = calendar.addUnits(start, TimeUnit.DAY, 4);
    cal.startDate = start;
    cal.endDate = end;
    cal.date = null;
  }  
  
  
  private function moveToCurrentDay(cal:Calendar):void {
    
    cal.date = new Date();
    cal.mode = Calendar.DAY_MODE;                          
  }  
  
  
  /**
   * Listener of time range selection on a data chooser.
   */         
  private function onDateChange(e:CalendarLayoutChangeEvent):void {
    var dch:DateChooser = e.currentTarget as DateChooser;
    var dc2:DateChooser = dch == dc ? dc2 : dc;
    
    dc2.selectedRanges = [];
    
    var r:Object = dch.selectedRanges[0];
    if (r == null) {
      return;
    }
    
    if (r.rangeStart.time == r.rangeEnd.time) {
      if (e.newDate >= cal.startDisplayedDate && 
          e.newDate <= cal.endDisplayedDate) {     
        cal.date = null;           
        cal.startDate = cal.endDate = e.newDate;    
      } else {
        if (cal.date == null) {
          var d:int = cal.calendar.getDays(cal.startDisplayedDate, cal.endDisplayedDate);
          cal.startDate = e.newDate;
          cal.endDate = cal.calendar.addUnits(e.newDate, TimeUnit.DAY, d);
        } else {
          cal.date = e.newDate;;
        }
      }          
    } else {                       
      cal.date = null;
      cal.startDate = r.rangeStart;
      cal.endDate = r.rangeEnd;
    }                 
  }   
  
  
  /**
   * Zooms in the calendar.
   */  
  private function zoomInCalendar():void {
    if (cal.mode != Calendar.MONTH_MODE) {                   
      cal.zoom(2, true);                    
    }
  }
  
  /**
   * Zooms out the calendar.
   */           
  private function zoomOutCalendar():void {
    if (cal.mode != Calendar.MONTH_MODE) {          
      cal.zoom(0.5, true);          
    }        
  }  
  
  
  //===========================================
  //  Calendar Listner
  //===========================================  
  /**
   * Listener of date range changes to synchronize the date chooser.
   */  
  private function visibleTimeRangeChanged(event:CalendarEvent):void {
	    var s:Date = event.startDate;
	    var e:Date = event.endDate;
	    
	    var ranges:Array = [{ rangeStart: s, rangeEnd: e }]; 
	            
	    dc.selectedRanges = ranges;
	    dc2.selectedRanges = ranges;        
	    
	    //The length of a range cannot exceed 42 days on 2 months
	            
	    if ((dc.displayedMonth == s.month && dc.displayedYear == s.fullYear) || 
	        (dc2.displayedMonth == s.month && dc2.displayedYear == s.fullYear && 
	         dc2.displayedMonth == e.month && dc2.displayedYear == e.fullYear)) {
	      //noop
	    } else {
	      
	      dc.displayedMonth = event.startDate.month;
	      dc.displayedYear =  event.startDate.fullYear;
	      
	      var nextYear:Boolean = event.startDate.month == 11;
	      
	      if (nextYear) {
	        dc2.displayedMonth = 0;        
	        dc2.displayedYear =  event.startDate.fullYear + 1;
	      } else {
	        dc2.displayedMonth = event.startDate.month + 1;        
	        dc2.displayedYear =  event.startDate.fullYear;            
	      }                    
	    }
	                               
	    rangeLabel.text = computeRangeLabel(event.startDate, event.endDate);
	    
	    var buttonEnabled:Boolean = cal.mode != Calendar.MONTH_MODE;
	    zoomInButton.enabled = buttonEnabled;
	    zoomOutButton.enabled = buttonEnabled;  
  }   
  
  
      private var _dateFormatter:DateFormatter = new DateFormatter();
      
      /**
       * Utility to return a string for a time range.
       */   
      private function computeRangeLabel(start:Date, end:Date=null):String {
        if (end == null || start.time == end.time) {
          //same day
          _dateFormatter.formatString = "EEEE D MMMM YYYY";
          return _dateFormatter.format(start);
        } else {
          var format:String = "EEE";
          if (start.month != end.month) {
            format += " MMM";
          }
          format += " D";
          if (start.fullYear != end.fullYear) {
            format += " YYYY";
          }
          _dateFormatter.formatString = format;
          var res:String = _dateFormatter.format(start);
          res += " - ";
          _dateFormatter.formatString = "EEE D MMM YYYY";
          res += _dateFormatter.format(end);                 
          return res;
        }
      }  
      
      
      /**
       * Listener of scrolled data chooser event.
       */
      private function onDateChooserScrolled(event:DateChooserEvent):void {
        if (dc.displayedMonth == 11) {
          dc2.displayedMonth = 0;
          dc2.displayedYear = dc.displayedYear + 1  
        } else {
          dc2.displayedMonth = dc.displayedMonth + 1;
        }        
        dateChooserScrolledImpl();
      }
      
      /**
       * Listener of scrolled data chooser event.
       */   
      private function onDateChooserScrolled2(event:DateChooserEvent):void {
        
        if (dc2.displayedMonth == 0) {
          dc.displayedMonth = 11;
          dc.displayedYear = dc2.displayedYear - 1;
        } else {
          dc.displayedMonth = dc2.displayedMonth - 1;           
        }
        
        dateChooserScrolledImpl();       
      }      
      
      /**
       * Listener of scrolled data chooser event.
       */
      private function dateChooserScrolledImpl():void {
                       
        var calendar:GregorianCalendar = cal.calendar;
        
        //if the calendar is in date mode
        cal.date = null;
        
        switch (cal.mode) {
          case Calendar.DAY_MODE:
            //show the first day if the month
            cal.startDate = new Date(dc.displayedYear, dc.displayedMonth);
            cal.endDate = cal.startDate;           
            break;
          case Calendar.WEEK_MODE:
            //show the a range from the first day of month and that lasts the current duration.
            var d:Number = cal.endDisplayedDate.time - cal.startDisplayedDate.time;
            cal.startDate = new Date(dc.displayedYear, dc.displayedMonth);
            cal.endDate = new Date(cal.startDate.time + d);           
            break;
          case Calendar.MONTH_MODE:
            //show the current month
            cal.startDate = new Date(dc.displayedYear, dc.displayedMonth);
            //compute the last day of the month
            var date:Date = calendar.addUnits(cal.startDate, TimeUnit.MONTH, 1);
            date = calendar.addUnits(date, TimeUnit.DAY, -1, true); 
            cal.endDate = date;            
            break;
        }
      }      


	private function calendarDataTip(item:CalendarItem):String{
		var activity:ActivityVO = item.data as ActivityVO;
		
//		if(ActivityVO.isNonProducton(activity)){
//		    return StringUtil.substitute("{0}\n{1}\n{2}\n{3}",   		    		 
//	                 resourceManager.getString('Language','label.data.tip.name') + item.summary,
//	                 resourceManager.getString('Language','label.data.tip.start') + DateUtil.format(item.startTime, DateUtil.SIMPLE),
//	                 resourceManager.getString('Language','label.data.tip.end') + DateUtil.format(item.endTime, DateUtil.SIMPLE),
//	                 resourceManager.getString('Language','label.data.tip.approved_by') + activity.createdByName);
//		}
		
		if(activity.activityType == ActivityEnum.MANAGEMENT || activity.activityType == ActivityEnum.COMPENSATION_OFF 
					|| activity.activityType == ActivityEnum.PROJECT_TRAINING){
			return StringUtil.substitute("{0}\n{1}\n{2}\n{3}\n{4}\n{5}\n{6}",
					resourceManager.getString('Language', 'label.data.tip.project')+activity.projectName,
					resourceManager.getString('Language', 'label.data.tip.name')+activity.name,
					resourceManager.getString('Language', 'label.data.tip.category')+activity.activityType,
					resourceManager.getString('Language', 'label.data.tip.actual_effort')+activity.actualStaffDays+" Day(s)",
					resourceManager.getString('Language', 'label.data.tip.start')+DateUtil.format(item.startTime, DateUtil.SIMPLE),
					resourceManager.getString('Language', 'label.data.tip.end')+DateUtil.format(item.endTime, DateUtil.SIMPLE),
					resourceManager.getString('Language', 'label.data.tip.approved_by')+activity.createdByName);			
		}else if(activity.activityType == ActivityEnum.DOWN_TIME_TRAINING || activity.activityType == ActivityEnum.PAID_LEAVE 
					|| activity.activityType == ActivityEnum.QA || activity.activityType == ActivityEnum.RECRUITMENT 
					|| activity.activityType == ActivityEnum.UNPAID_LEAVE || activity.activityType == ActivityEnum.WAIT_FEEDBACK){
			return StringUtil.substitute("{0}\n{1}\n{2}\n{3}\n{4}",   		    		 
	                 resourceManager.getString('Language','label.data.tip.name')+item.summary,
	                 resourceManager.getString('Language', 'label.data.tip.category')+activity.activityType,
	                 resourceManager.getString('Language','label.data.tip.start')+DateUtil.format(item.startTime, DateUtil.SIMPLE),
	                 resourceManager.getString('Language','label.data.tip.end')+DateUtil.format(item.endTime, DateUtil.SIMPLE),
	                 resourceManager.getString('Language','label.data.tip.approved_by')+activity.createdByName);
		}else{			
		    return StringUtil.substitute("{0}\t\t{1}\n{2}\n{3}\n{4}\n{5}\n{6}\n{7}\n{8}\n{9}\n{10}",
		    		 resourceManager.getString('Language','label.data.tip.project')+activity.projectName,
		    		 resourceManager.getString('Language','label.data.tip.subtask_id')+activity.taskId,   		    		 
	                 "<b>"+resourceManager.getString('Language','label.data.tip.name')+item.summary+"</b>",
	                 resourceManager.getString('Language','label.data.tip.milestone')+activity.milestone,
	                 resourceManager.getString('Language','label.data.tip.category')+activity.taskCategory,
	                 resourceManager.getString('Language','label.data.tip.assigned_effort')+activity.assignedEffort+" Day(s)",
	                 resourceManager.getString('Language','label.data.tip.actual_effort')+activity.actualStaffDays+" Days(s)",
	                 resourceManager.getString('Language','label.data.tip.start')+DateUtil.format(item.startTime, DateUtil.SIMPLE),
	                 resourceManager.getString('Language','label.data.tip.end')+DateUtil.format(item.endTime, DateUtil.SIMPLE),
	                 resourceManager.getString('Language','label.data.tip.lead')+(activity.taskOwnerName==null?"":activity.taskOwnerName),
	                 resourceManager.getString('Language','label.data.tip.assigned_by')+activity.createdByName,
					 (item.data.taskReferenceUrl!=null&&item.data.taskReferenceUrl!="")?
				 	 	("<img height='120' width='120' src='"+ActivityVO.getTaskReferenceUrl(activity)+"'/>"):"");
	 	}
	}
	
      /**
       * This function is called by the calendar to give a color to the 
       * item renderers.       
       */  
      private function itemColorFunction(calItem:CalendarItem):Object {
      	return ColorEnum.statusColor(calItem.data as ActivityVO);
      }
      
      private function calendarSummaryFunc(item:Object):String{
      	var activity:ActivityVO = item as ActivityVO;
      	var summary:String 		= "";
      	
      	if(ActivityVO.isNonProducton(activity)){
      		if(activity.activityType == ActivityEnum.MANAGEMENT){
      			summary = ActivityEnum.MANAGEMENT_L + activity.name;
      		}else if(activity.activityType == ActivityEnum.COMPENSATION_OFF){
      			summary = ActivityEnum.COMPENSATION_OFF_L + activity.name;
      		}else if(activity.activityType == ActivityEnum.DOWN_TIME_TRAINING){
      			summary = ActivityEnum.DOWN_TIME_TRAINING_L + activity.name;
      		}else if(activity.activityType == ActivityEnum.PAID_LEAVE){
      			summary = ActivityEnum.PAID_LEAVE_L + activity.name;
      		}else if(activity.activityType == ActivityEnum.PROJECT_TRAINING){
      			summary = ActivityEnum.PROJECT_TRAINING_L + activity.name;
      		}else if(activity.activityType == ActivityEnum.QA){
      			summary = ActivityEnum.QA + activity.name;
      		}else if(activity.activityType == ActivityEnum.RECRUITMENT){
      			summary = ActivityEnum.RECRUITMENT_L + activity.name;
      		}else if(activity.activityType == ActivityEnum.WAIT_FEEDBACK){
      			summary = ActivityEnum.WAIT_FEEDBACK_L + activity.name;
      		}else if(activity.activityType == ActivityEnum.UNPAID_LEAVE){
      			summary = ActivityEnum.UNPAID_LEAVE_L + activity.name;
      		}
      	}else{
      		summary = activity.name;
      	}      	
      	return summary;
      }
          	