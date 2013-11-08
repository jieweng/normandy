package com.pearl.normandy.enum
{
	import com.pearl.normandy.utils.Constants;
	import com.pearl.normandy.vo.ActivityVO;
	
	public final class ColorEnum
	{
		public static const NON_WORKING_COLOR:uint = 0xd6d6d6;
		
		public static const DEFAULT:String			= "Default";
		
		public static const NON_PRODUCTION:String	= "NonProduction";
		public static const FEEDBACK:String			= "Feedback";		
		public static const TRAINING:String			= "Training";
		public static const DELETED:String			= "Deleted";
		public static const NOT_PROVED:String		= "Not_Proved";
		
		public static const NOT_STARTED:String 		= "Not Started";
		public static const WIP:String				= "WIP";
		public static const COMPLETE:String			= "Complete";
		public static const SUBMITTED:String		= "Submitted";
		public static const APPROVED:String			= "Approved";
		
		  //===========================================
		  //  Activity Item Colors
		  //===========================================		
	     public static const typeToColor:Object = {
		    Production: 	0x81ba55,
		    Feedback: 		0xff8200,		    
		    Training:		0xda77ff,	     	
		 	NonProduction: 	0xffe7f6,
			Not_Proved:		0xff0000,	
			NonProduction: 	0x053ed0,
		    Deleted: 		0x999999,
		    Default: 		0x65844d
	    };
	    
 	    public static const statusToColor:Object = {
	    	"Not Started": 		0xc6e8fd,
	    	"WIP":				0xaaea78,
	    	"Complete":			0x007614,
	    	"Submitted":		0x007614,
	    	"Approved":			0xe2e2e2	    	
	    }; 
	    
 	    public static const statusToColorDark:Object = {
	    	"Not Started": 		0x1b95d9,
	    	"WIP":				0xaaea78,
	    	"Complete":			0x007614,
	    	"Submitted":		0x5da6d5,
	    	"Approved":			0x888888
	    	
	    }; 	    
	    
	    public static function performanceColor(assignedEffort:Number, actualStaffDays:Number):uint{
    		var offset:Number = actualStaffDays/assignedEffort;    
    		
    		if(offset <= 0.3){
    			return 0x053ed0;
    		}
    		if(offset <= 0.5){
    			return 0x3b73ff;
    		}
    		else if(offset <= 0.75){
    			return 0x53aeee;
    		}
    		else if(offset <= 0.95){
    			return 0xc6e8fd;
    		}
    		else if(offset <= 1.05){
    			return 0x90e383;
    		}
    		else if(offset <= 1.25){
    			return 0xffedc7;
    		}
    		else if(offset <= 1.5){
    			return 0xffcb91;
    		}
    		else if(offset <= 2){
    			return 0xff817d;
    		}
    		else if(offset <= 3){
    			return 0xf13a36;
    		}
    		else if(offset <= 4){
    			return 0xb30500;
    		}
    		else{
    			return 0x500d0b;
    		}	    	
	    }
	    
	    public static function statusColor(activity:ActivityVO):uint{
	    	var type:String 				= activity.activityType;
	    	var status:String 				= activity.status;
	    	var startTime:Date				= activity.actualStartTime;	    			
	    	var endTime:Date				= activity.actualEndTime;
	    	
	    	if(ActivityVO.isNonProducton(activity)){
	    		return typeToColor[NON_PRODUCTION];
	    	}
	    	else if(activity.deleted == Constants.DELETE_YES){
	    		return typeToColor[DELETED];
	    	}
	    	else{	   
	    		//Production activity colors	    		
	    		if(type == ActivityEnum.PRODUCTION){
	    			if(activity.statusId < Constants.TASK_STATUS_COMPLETE_NUM){
	    				if(endTime){
							return statusToColor[COMPLETE];	    					
	    				}
	    				else if(startTime){
		    				return statusToColor[WIP];	    					
	    				}
	    				else{
		    				return statusToColor[NOT_STARTED];	    					
	    				}    				
	    			}
	    			else{
	    				return statusToColor[status];		
	    			}	    				    		
	    		}	    		 		  		
	    		//Feedback activity colors	    		
	    		else if(type == ActivityEnum.FEEDBACK){
	    			if(activity.statusId < Constants.TASK_STATUS_APPROVED_NUM){
	    				if(endTime){
							return statusToColor[COMPLETE];	    					
	    				}
	    				else if(startTime){
		    				return statusToColor[WIP];	    					
	    				}
	    				else{
		    				return statusToColor[NOT_STARTED];	    					
	    				}		    			
	    			}
	    			else{
	    				return statusToColor[status];		
	    			}    			
	    		}
	    		
	    		return statusToColor[status];
	    	}
		    		
		    return reasonToColor["Dafault"];			    		    			 	    					    		
	    }
	}
}