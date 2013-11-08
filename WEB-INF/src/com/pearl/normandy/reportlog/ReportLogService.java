package com.pearl.normandy.reportlog;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.pearl.normandy.utils.Constants;


public class ReportLogService {

	//==============================
	//Get Method
	//==============================
	public ReportLogTo getReportLog(String type, Integer year,Integer month)throws Exception{
		ReportLogTo reportLog = reportLogDao.getReportLog(type, year, month);
		return reportLog;
	}
	public ReportLogTo getReportLog(String type, String projectName)throws Exception{
		Date date=new Date();
		String dateStr=new SimpleDateFormat("yyyy_MM").format(date);
		
		ReportLogTo reportLog = reportLogDao.getReportLog(type, projectName,dateStr);
		return reportLog;
	}
	
	//==============================
	//Create, Update, Delete
	//==============================
	public ReportLogTo createReportLog(String type, Integer year,Integer month)throws Exception{
		ReportLogTo report = reportLogDao.getReportLog(type, year, month);
		if(report == null){
			report = create(type, year, month);
		}else{
			report.setCreatedDate(new Date());
			reportLogDao.update(report);
		}
		return report;
	}
	public ReportLogTo createReportLog(String type, String projectName,String dataStr)throws Exception{
		ReportLogTo report = reportLogDao.getReportLog(type, projectName ,dataStr);
		if(report == null){
			report = create(type, dataStr,projectName);
		}else{
			report.setCreatedDate(new Date());
			reportLogDao.update(report);
		}
		return report;
	}
	
	private ReportLogTo create(String type, Integer year,Integer month)throws Exception{
		ReportLogTo reportLog = new ReportLogTo();
		reportLog.setReportType(type);
		reportLog.setYear(year);
		reportLog.setMonth(month);
		reportLog.setCreatedDate(new Date());
		if(type == Constants.REPORT_UT_CHART || type == Constants.REPORT_SAP_UT){
			reportLog.setUrl(type+"_"+year+"_"+month+".xlsx");
		}else{
			reportLog.setUrl(type+"_"+year+"_"+month+".xls");
		}		
		Integer id = reportLogDao.create(reportLog);
		reportLog.setId(id);
		
		return reportLog;
	}
	private ReportLogTo create(String type, String dataStr,String projectName)throws Exception{
		ReportLogTo reportLog = new ReportLogTo();
		reportLog.setReportType(type);
		reportLog.setCreatedDate(new Date());
		reportLog.setUrl(projectName+"_"+type+"_"+dataStr+".xls");
		Integer id = reportLogDao.create(reportLog);
		reportLog.setId(id);
		
		return reportLog;
	}
	
	//==============================
	//Injected Variables
	//==============================
	private ReportLogDao reportLogDao;

	/**
	 * @param reportLogDao the reportLogDao to set
	 */
	public void setReportLogDao(ReportLogDao reportLogDao) {
		this.reportLogDao = reportLogDao;
	}
}