package com.pearl.normandy.enumerator;

public class CheckItemEnum {
	private static final String STATUS_FIX 			= "Fix";	
	private static final String STATUS_PENDING		= "Pending";
	private static final String STATUS_REVIEWED		= "Reviewed";
	private static final String STATUS_COMPLETE		= "Complete";
	private static final String STATUS_SUBMITTED	= "Submitted";

	
	public static final String[] STATUS_ARRAY		= {STATUS_FIX,
													   STATUS_PENDING,  
													   STATUS_REVIEWED, 
													   STATUS_COMPLETE,
													   STATUS_SUBMITTED};
	
	public static final String getInitialStatus(){
		return STATUS_ARRAY[1];
	}
	
	public static final String getFixStatus(){
		return STATUS_ARRAY[0];		                       
	}
	
	public static final String getNextStatus(String currentStatus){
		for(int i=0;i<STATUS_ARRAY.length;i++){
			if(STATUS_ARRAY[i].equals(currentStatus)){
				if(i<STATUS_ARRAY.length){
					return STATUS_ARRAY[i+1];
				}
				else{
					return currentStatus;
				}
			}
		}
		
		return currentStatus;
	}
}
