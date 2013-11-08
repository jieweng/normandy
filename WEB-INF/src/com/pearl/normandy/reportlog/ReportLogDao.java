package com.pearl.normandy.reportlog;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class ReportLogDao extends SqlMapClientDaoSupport{

	// ==============================
	// Get methods
	// ==============================
	
	public ReportLogTo getReportLog(Integer year, Integer month)throws DataAccessException{
		Map<String, String> param = new HashMap<String, String>();
		param.put("year", String.valueOf(year));
		param.put("month", String.valueOf(month));
		ReportLogTo reportLog = (ReportLogTo) this.getSqlMapClientTemplate().queryForObject("ReportLog.selectReportLog",param);
		return reportLog;
	}

	public ReportLogTo getReportLog(String reportType,String projectName, String dateStr)throws DataAccessException{
		Map<String, String> param = new HashMap<String, String>();
		String url=projectName+"_"+reportType+"_"+dateStr+".xls";
		param.put("reportType", reportType);
		param.put("url", url);
		ReportLogTo reportLog = (ReportLogTo) this.getSqlMapClientTemplate().queryForObject("ReportLog.selectReportLog",param);
		return reportLog;
	}
	public ReportLogTo getReportLog(String reportType, Integer year, Integer month)throws DataAccessException{
		Map<String, String> param = new HashMap<String, String>();
		param.put("reportType", reportType);
		param.put("year", String.valueOf(year));
		param.put("month", String.valueOf(month));
		ReportLogTo reportLog = (ReportLogTo) this.getSqlMapClientTemplate().queryForObject("ReportLog.selectReportLog",param);
		return reportLog;
	}
	//==============================
	//Create, Update, Delete
	//==============================	
	public Integer create(ReportLogTo reportLog)throws DataAccessException{
		Integer id = (Integer) this.getSqlMapClientTemplate().insert("ReportLog.insert",reportLog);
		return id;
	}
	
	public void update(ReportLogTo reportLog)throws DataAccessException{
		this.getSqlMapClientTemplate().update("ReportLog.update",reportLog);
	}
}
