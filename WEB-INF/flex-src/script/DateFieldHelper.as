// ActionScript file
	import mx.controls.DateField;
	import com.pearl.normandy.utils.CalendarConfig;
	
    public function parseStartTime(valueString:String, inputFormat:String):Date
    {		
        return CalendarConfig.parseStartTime(DateField.stringToDate(valueString, inputFormat));
    }
    
    public function parseEndTime(valueString:String, inputFormat:String):Date
    {		
        return CalendarConfig.parseEndTime(DateField.stringToDate(valueString, inputFormat));
    }        	
    
    
    