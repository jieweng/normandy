package com.pearl.normandy.utils;

import java.util.Date;
import java.util.List;

import com.pearl.normandy.report.ReportEfficiencyQuality;

public class ReportUtil {

	private static ReportUtil reportUtil;
	
	private static CalendarUtil calendarUtil = CalendarUtil.getInstance();
	
	public void setHolidays(List<Date> holidays){
		calendarUtil.setHolidays(holidays);
	}
	
	public static ReportUtil getInstance(){
		if(reportUtil == null){
			return new ReportUtil();
		}else{
			return reportUtil;
		}
	}
	
	public ReportEfficiencyQuality createNewREQ(ReportEfficiencyQuality req){
		ReportEfficiencyQuality newReq = new ReportEfficiencyQuality();
		newReq.setUserId(req.getUserId());
		newReq.setEmployeeNo(req.getEmployeeNo());
		newReq.setUserName(req.getUserName());
		newReq.setGroupId(req.getGroupId());
		newReq.setGroupName(req.getGroupName());
		newReq.setActualStaffDays(0.0);
		newReq.setActualStaffDaysF(0.0);
		newReq.setAssignedEffort(0.0);
		newReq.setAssignedEffortF(0.0);
		newReq.setDelay(0);
		newReq.setDelayF(0);
		newReq.setAhead(0);
		newReq.setAheadF(0);
		newReq.setSun(0.0);
		newReq.setSunF(0.0);
		newReq.setLuna(0.0);
		newReq.setLunaF(0.0);
		newReq.setStar(0.0);
		newReq.setStarF(0.0);
		return newReq;
	}
	
}
