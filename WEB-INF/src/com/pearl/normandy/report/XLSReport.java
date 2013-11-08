package com.pearl.normandy.report;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pearl.normandy.project.ProjectTo;
import com.pearl.normandy.utils.Constants;
import com.pearl.normandy.utils.NormandyConfiguration;

import net.sf.jxls.transformer.XLSTransformer;

public class XLSReport {
	static NormandyConfiguration config = NormandyConfiguration.getInstance();	
	
	@SuppressWarnings("unchecked")
	public void genUTChart(List users, Integer year, Integer month, int workingDays) {
		try{						
			Map beans = new HashMap();
			beans.put("users", users);
			beans.put("month", year+"-"+month);
			beans.put("workingDays", workingDays);
			XLSTransformer transformer = new XLSTransformer();
//			transformer.setColumnsToHide(new short[]{(short)3, (short)7, (short)8, (short)9} );
			transformer.setColumnsToHide(new short[]{(short)4, (short)9}); 
			transformer.transformXLS(config.TEMPLATE_JXLS_FOLDER+"UT_CHART.xlsx", beans, config.REPORT_FOLDER+"UT_CHART_"+year+"_"+month+".xlsx");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void genSAPUtChart(List users, Integer year, Integer month, int workingDays){
		try{
			Map beans =  new HashMap();
			beans.put("users", users);
			beans.put("month", year + "-" + month);
			beans.put("workingDays", workingDays);
			XLSTransformer transformer = new XLSTransformer();
			transformer.transformXLS(config.TEMPLATE_JXLS_FOLDER + "SAP_UT.xlsx", beans, config.REPORT_FOLDER + "SAP_UT_" + year + "_" + month + ".xlsx");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void genProjectMemberAbility(List users, Integer year, Integer month) {
		try{						
			Map beans = new HashMap();
			beans.put("users", users);
			XLSTransformer transformer = new XLSTransformer();
			transformer.transformXLS(config.TEMPLATE_JXLS_FOLDER+"PROJECT_MEMBER_ABILITY.xlsx", beans, config.REPORT_FOLDER+"PROJECT_MEMBER_ABILITY_"+year+"_"+month+".xlsx");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void genProjectMemberAbility(List users, Integer year) {
		try{						
			Map beans = new HashMap();
			beans.put("users", users);
			XLSTransformer transformer = new XLSTransformer();
			transformer.transformXLS(config.TEMPLATE_JXLS_FOLDER+"PROJECT_MEMBER_ABILITY.xlsx", beans, config.REPORT_FOLDER+"PROJECT_MEMBER_ABILITY_"+year+".xlsx");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void genReportDetailChart(List projects , String projectName, String dateStr){		
		try{
			Map beans = new HashMap();
			beans.put("projects", projects);
			XLSTransformer transformer = new XLSTransformer();
			transformer.transformXLS(config.TEMPLATE_JXLS_FOLDER+"DETAIL_INFO_CHART.xls", beans, config.REPORT_FOLDER+projectName+"_"+Constants.REPORT_DETAIL_CHART+"_"+dateStr+".xls");
		}
		catch(Exception e){
			e.printStackTrace();
		}		
	}
	
	@SuppressWarnings("unchecked")
	public void genReportEfficiencyQualityChart(List list, Date start, Date end){
		try{
			Map beans = new HashMap();
			beans.put("list", list);
			beans.put("start", start);
			beans.put("end", end);
			String srcFilePath = config.TEMPLATE_JXLS_FOLDER + "EFFICIENCY_QUALITY_CHART.xls";
			String startStr = new SimpleDateFormat("yyyy_MM_dd").format(start);
			String endStr = new SimpleDateFormat("yyyy_MM_dd").format(end);
			String destFilePath = config.REPORT_FOLDER + Constants.REPORT_EFFICIENCY_QUALITY_CHART + "_" + startStr + "-" + endStr + ".xls";
			XLSTransformer transformer =  new XLSTransformer();
			transformer.transformXLS(srcFilePath, beans, destFilePath);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public String genReportProjectResourceCost(List list, int year, int month, int workDays, List<ProjectTo> projects){
		try{			
			String fileName = "";
			String projectName = "";
			for(ProjectTo p : projects){
				projectName += p.getProjectName() + "[" + p.getProjectKey() + "];  ";
			}
			fileName = Constants.REPORT_PROJECT_RESOURCE_COST + "_" + year + "_" + month;
			//fileName = PasswordUtil.encrypt(fileName);
			
			String srcFilePath = config.TEMPLATE_JXLS_FOLDER + Constants.REPORT_PROJECT_RESOURCE_COST + ".xlsx";
			String destFilePath = config.REPORT_FOLDER + fileName + ".xlsx";
			
			Map beans = new HashMap();
			beans.put("list", list);
			beans.put("month", year+"-"+month);
			beans.put("workDays", workDays);
			beans.put("projectName", projectName);
			
			XLSTransformer transformer =  new XLSTransformer();
			transformer.transformXLS(srcFilePath, beans, destFilePath);
			
			return fileName;
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
