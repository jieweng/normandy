package com.pearl.normandy.report;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.pearl.normandy.project.ProjectTo;
import com.pearl.normandy.usergroup.UserGroupTo;

public class ReportDao extends SqlMapClientDaoSupport {

	// ==============================
	// Get methods
	// ==============================
	@SuppressWarnings("unchecked")
	public List<ReportTo> getTaskStatus(Integer projectId, String milestone, String taskCategory) throws DataAccessException {
		Map param = new HashMap();
		param.put("projectId", projectId);
		param.put("milestone", milestone);
		param.put("taskCategory", taskCategory);		
		return this.getSqlMapClientTemplate().queryForList("Report.selectTaskStatus", param);
	}

	@SuppressWarnings("unchecked")
	public List<ProjectTo> getProjectProgress() throws DataAccessException {
		return this.getSqlMapClientTemplate().queryForList("Report.selectProjectProgress");
	}
	
	@SuppressWarnings("unchecked")
	public List<ProjectTo> getProjectProgress(List<ProjectTo> projects) throws DataAccessException {
		Map<String,List> param = new HashMap<String, List>();
		param.put("projects", projects);
		return this.getSqlMapClientTemplate().queryForList("Report.selectProjectProgress",param);
	}
	
	@SuppressWarnings("unchecked")
	public List<ProjectTo> getProjectProgress(ProjectTo project, String milestone) throws DataAccessException {
		Map param = new HashMap();
		ArrayList projects = new ArrayList();
		projects.add(project);
		param.put("projects", projects);
		param.put("milestone", milestone);
		return this.getSqlMapClientTemplate().queryForList("Report.selectProjectProgress",param);
	}	
	
	@SuppressWarnings("unchecked")
	public List<ProjectTo> getProjectProgressByTime(ProjectTo project, String milestone, Date start, Date end) throws DataAccessException{
		Map param = new HashMap();
		param.put("projectId", project.getId());
		param.put("milestone", milestone);
		param.put("start", start);
		param.put("end", end);
		return getSqlMapClientTemplate().queryForList("Report.selectProjectProgressByTime", param);
	}
	
	@SuppressWarnings("unchecked")
	public List<ReportTo> getBaselineBurndown(Integer projectId, String milestone, String timeUnit) throws DataAccessException{
		Map param = new HashMap();
		param.put("projectId", projectId);
		param.put("milestone", milestone);
		param.put("timeUnit", timeUnit);
		return this.getSqlMapClientTemplate().queryForList("Report.selectBaselineBurndown", param);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, ReportTo> getCompleteBurndown(Integer projectId, String milestone, String timeUnit) throws DataAccessException{
		Map param = new HashMap();
		param.put("projectId", projectId);
		param.put("milestone", milestone);
		param.put("timeUnit", timeUnit);
		return this.getSqlMapClientTemplate().queryForMap("Report.selectCompleteBurndown", param, "day");
	}
	
	@SuppressWarnings("unchecked")
	public List<ReportTo> getFeedbackSummaryCause(Integer projectId, String milestone)throws DataAccessException{
		Map param = new HashMap();
		param.put("projectId", projectId);
		param.put("milestone", milestone);			
		return this.getSqlMapClientTemplate().queryForList("Report.selectFeedbackSummaryCause", param);
	}
	
	@SuppressWarnings("unchecked")
	public List<ReportTo> getFeedbackSummaryPriority(Integer projectId, String milestone)throws DataAccessException{
		Map param = new HashMap();
		param.put("projectId", projectId);
		param.put("milestone", milestone);			
		return this.getSqlMapClientTemplate().queryForList("Report.selectFeedbackSummaryPriority", param);
	}

	
	@SuppressWarnings("unchecked")
	public List<ReportTo> getTimeSheet(Integer projectId,Integer year,Integer month)throws DataAccessException{
		Map<String,Integer> param = new HashMap<String, Integer>();
		param.put("projectId", projectId);
		param.put("year", year);
		param.put("month", month);
		List<ReportTo> result = this.getSqlMapClientTemplate().queryForList("Report.selectTimeSheet", param);
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<ReportTo> getResourceUtilization(List<ProjectTo> productionProjects,List<ProjectTo> testProjects,Integer year,Integer month)throws DataAccessException{
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("productionProjects", productionProjects);
		param.put("testProjects", testProjects);
		param.put("year", year);
		param.put("month", month);
		List<ReportTo> result = this.getSqlMapClientTemplate().queryForList("Report.selectResourceUtilization",param);
		return result;
	}
	@SuppressWarnings("unchecked")
	public List<ReportDetailInfo> getReportDetail(int projectId)throws DataAccessException{
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("projectId", projectId);
		List<ReportDetailInfo> result = this.getSqlMapClientTemplate().queryForList("Report.selectProjectDetailInfo",param);
		return result;
	}
	@SuppressWarnings("unchecked")
	public List<ProjectTo> getProjectByMonth(Integer year,Integer month)throws DataAccessException{
		Map<String,Integer> param = new HashMap<String, Integer>();
		param.put("year", year);
		param.put("month", month);
		List<ProjectTo> result = this.getSqlMapClientTemplate().queryForList("Report.selectProjectByMonth",param);
		return result;
	}
	
	public Integer getWorkdayByMonth(Integer year,Integer month)throws DataAccessException{
		Map<String,Integer> param = new HashMap<String, Integer>();
		param.put("year", year);
		param.put("month", month);
		Integer workday = (Integer) this.getSqlMapClientTemplate().queryForObject("Report.selectWorkdayByMonth",param);
		return workday;
	}
	
	@SuppressWarnings("unchecked")
	public List<ReportEfficiencyQuality> getReportEfficiencyQuality(Date start, Date end, ProjectTo project, UserGroupTo group) throws DataAccessException{
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("start", start);
		param.put("end", end);
		param.put("project", project);
		param.put("group", group);
		List<ReportEfficiencyQuality> result = this.getSqlMapClientTemplate().queryForList("Report.selectEfficiencyQuality", param);
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<ReportTo> getOtherActivityDaysByTime(ProjectTo project, List<String> typeList, Date start, Date end) throws DataAccessException{
		Map param = new HashMap();
		param.put("projectId", project.getId());
		param.put("typeList", typeList);
		param.put("start", start);
		param.put("end", end);
		return getSqlMapClientTemplate().queryForList("Report.selectOtherActivityDaysByTime", param);
	}
	
	@SuppressWarnings("unchecked")
	public List<ReportTo> getMonthWorkDaysByProjectId(int projectId) throws DataAccessException{
		return this.getSqlMapClientTemplate().queryForList("Report.selectMonthWorkDaysByProjectId", projectId);
	}
	
	@SuppressWarnings("unchecked")
	public List<ReportTo> getResourceCost(List<ProjectTo> projects, int year, int month) throws DataAccessException{
		Map param = new HashMap();
		param.put("projects", projects);
		param.put("year", year);
		param.put("month", month);
		
		return getSqlMapClientTemplate().queryForList("Report.selectResourceCost", param);
	}
}