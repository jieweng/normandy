package com.pearl.normandy.report;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.pearl.normandy.activity.ActivityDao;
import com.pearl.normandy.activity.ActivityTo;
import com.pearl.normandy.calendar.CalendarDao;
import com.pearl.normandy.feedback.FeedbackDao;
import com.pearl.normandy.feedback.FeedbackTo;
import com.pearl.normandy.project.ProjectService;
import com.pearl.normandy.project.ProjectTo;
import com.pearl.normandy.projectmemberability.ProjectMemberAbilityService;
import com.pearl.normandy.projectmemberability.ProjectMemberAbilityTo;
import com.pearl.normandy.reportlog.ReportLogService;
import com.pearl.normandy.reportlog.ReportLogTo;
import com.pearl.normandy.user.UserTo;
import com.pearl.normandy.userSalary.UserSalary;
import com.pearl.normandy.userSalary.UserSalaryDao;
import com.pearl.normandy.usergroup.UserGroupTo;
import com.pearl.normandy.utils.CalendarConfig;
import com.pearl.normandy.utils.CalendarUtil;
import com.pearl.normandy.utils.Constants;
import com.pearl.normandy.utils.FileUtil;
import com.pearl.normandy.utils.NormandyConfiguration;
import com.pearl.normandy.utils.ReportUtil;
import com.pearl.safe.EncryptUtil;

import flex.messaging.MessageException;

public class ReportService {
	
	static Logger log = Logger.getLogger(ReportService.class.getName());
	
	static ReportUtil reportUtil = ReportUtil.getInstance();
	
	//==============================
	//Get methods
	//==============================	
	public List<ReportTo> getReportsByProjectId(Integer projectId, String milestone, String taskCategory)throws Exception{
		List<ReportTo> result = reportDao.getTaskStatus(projectId, milestone, taskCategory);
		return result;
	}
	
	public List<ProjectTo> getProjectProgress(UserTo userTo)throws Exception{
		List<ProjectTo> projects = projectService.getProjectByUser(userTo);
		return reportDao.getProjectProgress(projects);
	}
	
	public List<ProjectTo> getProjectProgress(ProjectTo project, String milestone)throws Exception{
		return reportDao.getProjectProgress(project, milestone);
	}	
	
	public List<ProjectTo> getProjectProgressByTime(ProjectTo project, String milestone, Date start, Date end) throws Exception{
		return reportDao.getProjectProgressByTime(project, milestone, start, end);
	}
	
	public List<ActivityTo> getRTDActivityByProjectId(Integer projectId)throws Exception{
		return activityDao.getRTDActivityByProjectId(projectId, CalendarConfig.getStartTime(), CalendarConfig.getEndTime());
	}
	
	@SuppressWarnings("unchecked")
	public Map<String,List<ReportTo>> getProjectBurndown(Integer projectId, String milestone, String timeUnit) throws Exception{
		HashMap<String, List<ReportTo>> result = new HashMap<String, List<ReportTo>>();
		ArrayList<ReportTo> complete = new ArrayList<ReportTo>();
		List<ReportTo> rsBaseline = reportDao.getBaselineBurndown(projectId, milestone, timeUnit);
		List<ReportTo> originalBaseline = new ArrayList<ReportTo>();
		for(ReportTo r : rsBaseline){
			ReportTo temp = (ReportTo)BeanUtils.cloneBean(r);
			originalBaseline.add(temp);
		}
		HashMap<String, ReportTo> rsComplete = (HashMap<String, ReportTo>)reportDao.getCompleteBurndown(projectId, milestone, timeUnit);
		List<ReportTo> originalComplete = new ArrayList<ReportTo>();
		for(String key : rsComplete.keySet()){
			ReportTo temp = rsComplete.get(key);
			originalComplete.add(temp);
		}

		if(rsBaseline.size()>0){
			ReportTo last = (ReportTo)BeanUtils.cloneBean((ReportTo)rsBaseline.get(0));			

			String[] day = last.getDay().split("/");
			GregorianCalendar lastDate = new GregorianCalendar(Integer.valueOf(day[0]), Integer.valueOf(day[1])-1, Integer.valueOf(day[2])); 
			
			if(timeUnit.equals(Constants.TIME_UNIT_DAY))	lastDate.add(GregorianCalendar.DATE, 1);
			if(timeUnit.equals(Constants.TIME_UNIT_WEEK))	lastDate.add(GregorianCalendar.DATE, 7);
			if(timeUnit.equals(Constants.TIME_UNIT_MONTH))	lastDate.set(GregorianCalendar.MONTH, lastDate.get(GregorianCalendar.MONTH)+1);

			last.setEffort(new Double(0));
			last.setDay(lastDate.get(GregorianCalendar.YEAR)+"/"+(lastDate.get(GregorianCalendar.MONTH)+1)+"/"+lastDate.get(GregorianCalendar.DATE));
			rsBaseline.add(0, last);
		}
		
		for(int i=1;i<rsBaseline.size();i++){
			ReportTo cur = (ReportTo)rsBaseline.get(i);
			ReportTo pre = (ReportTo)rsBaseline.get(i-1);			
			cur.setEffort(cur.getEffort()+pre.getEffort());
		}
		
		GregorianCalendar now = new GregorianCalendar();
		if(rsBaseline.size()>0){
			Double completeBase = ((ReportTo)rsBaseline.get(rsBaseline.size()-1)).getEffort(); // Get the first point value			
			ReportTo first = (ReportTo)rsBaseline.get(rsBaseline.size()-1);	
			String[] day = first.getDay().split("/");
			GregorianCalendar incrementalDate = new GregorianCalendar(Integer.valueOf(day[0]), Integer.valueOf(day[1])-1, Integer.valueOf(day[2])); 
			
			if(incrementalDate.before(now)){
				while(true){
					ReportTo completeDev = (ReportTo)rsComplete.get(incrementalDate.get(GregorianCalendar.YEAR)+"/"+(incrementalDate.get(GregorianCalendar.MONTH)+1)+"/"+incrementalDate.get(GregorianCalendar.DATE));
									
					if(completeDev != null){
						completeBase = completeBase - completeDev.getEffort();
					}
					
					ReportTo newCompletePoint = new ReportTo();
					
					newCompletePoint.setEffort(completeBase);
					newCompletePoint.setDay(incrementalDate.get(GregorianCalendar.YEAR)+"/"+(incrementalDate.get(GregorianCalendar.MONTH)+1)+"/"+incrementalDate.get(GregorianCalendar.DATE));
					complete.add(newCompletePoint);
					
					//show the current week data
					if(incrementalDate.after(now) || completeBase == 0){break;}	
					
					if(timeUnit.equals(Constants.TIME_UNIT_DAY))	incrementalDate.add(GregorianCalendar.DATE, 1);
					if(timeUnit.equals(Constants.TIME_UNIT_WEEK))	incrementalDate.add(GregorianCalendar.DATE, 7);
					if(timeUnit.equals(Constants.TIME_UNIT_MONTH))	incrementalDate.set(GregorianCalendar.MONTH, incrementalDate.get(GregorianCalendar.MONTH) + 1);					
				}
			}			
		}
		
		result.put("baseline", rsBaseline);
		result.put("complete", complete);
		result.put("originalComplete", originalComplete);
		result.put("originalBaseline", originalBaseline);
		return result;
	}
	
	public List<ReportTo> getFeedbackSummaryCause(Integer projectId, String milestone) throws Exception{
		List<ReportTo> result = null;
		
		result = reportDao.getFeedbackSummaryCause(projectId, milestone);
		
		boolean tech = false,visual = false,customer = false;
		if(result.size()>0){
			for (ReportTo reportTo : result) {
				if(reportTo.getName().equals("TECH_PROBLEM"))
					tech = true;
				else if (reportTo.getName().equals("VISUAL_PROBLEM"))
					visual = true;
				else if (reportTo.getName().equals("CUSTOMER_PROBLEM"))
					customer = true;
			}
			
			if(!tech){
				ReportTo report = new ReportTo();
				report.setName("TECH_PROBLEM");
				report.setValue(0.0);
				result.add(0,report);
			}
			if(!visual){
				ReportTo report = new ReportTo();
				report.setName("VISUAL_PROBLEM");
				report.setValue(0.0);
				result.add(1,report);
			}
			if(!customer){
				ReportTo report = new ReportTo();
				report.setName("CUSTOMER_PROBLEM");
				report.setValue(0.0);
				result.add(2,report);
			}
		}

		return result;
	}

	public List<ReportTo> getFeedbackSummaryPriority(Integer projectId, String milestone) throws Exception{
		List<ReportTo> result = reportDao.getFeedbackSummaryPriority(projectId, milestone);
		boolean high = false,medium = false,low = false;
		if(result.size()>0){
			for (ReportTo reportTo : result) {
				if(reportTo.getName().equals("High"))
					high = true;
				else if (reportTo.getName().equals("Medium"))
					medium = true;
				else if (reportTo.getName().equals("Low"))
					low = true;
			}
			
			if(!high){
				ReportTo report = new ReportTo();
				report.setName("High");
				report.setValue(0.0);
				result.add(0,report);
			}
			if(!medium){
				ReportTo report = new ReportTo();
				report.setName("Medium");
				report.setValue(0.0);
				result.add(1,report);
			}
			if(!low){
				ReportTo report = new ReportTo();
				report.setName("Low");
				report.setValue(0.0);
				result.add(2,report);
			}
		}
		return result;
	}
	
	public ReportTo getFeedbackByProjectId(Integer projectId, String milestone)throws Exception{
		ReportTo report = new ReportTo();
		report.setAllFeedback(feedbackDao.getAllFeedback(projectId, milestone));
		report.setOpenFeedback(feedbackDao.getOpenFeedback(projectId, milestone));
		report.setClosedFeedback(feedbackDao.getClosedFeedback(projectId, milestone));
		return report;
	}
	
	public List<FeedbackTo> getFeedbackByRootCause(Integer projectId, String milestone, String category)throws Exception{
		return feedbackDao.getFeedbackByRootCause(projectId, milestone, category);
	}
	
	public List<FeedbackTo> getFeedbackByPriority(Integer projectId, String milestone, String priority)throws Exception{
		return feedbackDao.getFeedbackByPriority(projectId, milestone, priority);
	}

	public List<ReportTo> getTimeSheet(Integer projectId,Integer year,Integer month)throws Exception{
		return reportDao.getTimeSheet(projectId,year,month);
	}
	
	@Transactional
	public ReportLogTo createResourceUtilizationReport(List<ProjectTo> productionProjects,List<ProjectTo> testProjects,Integer year,Integer month)throws Exception{
		List<ReportTo> users = reportDao.getResourceUtilization(productionProjects, testProjects, year, month);
		int workingDays = reportDao.getWorkdayByMonth(year, month);
		new XLSReport().genUTChart(users, year, month, workingDays);
		ReportLogTo reportLog = reportLogService.createReportLog(Constants.REPORT_UT_CHART, year, month);
		return reportLog;
	}
	
	@Transactional
	public ReportLogTo createSAPUtilizationReport(List<ProjectTo> productionProjects, List<ProjectTo> testProjects, Integer year, Integer month) throws Exception{
		List<ReportTo> users = reportDao.getResourceUtilization(productionProjects, testProjects, year, month);
		int workingDays = reportDao.getWorkdayByMonth(year, month);
		new XLSReport().genSAPUtChart(users, year, month, workingDays);
		ReportLogTo reportLog = reportLogService.createReportLog(Constants.REPORT_SAP_UT, year, month);
		return reportLog;
	}
	
	@Transactional
	public ReportLogTo createReportDetailReport(String projectName,int projectId)throws Exception{
		Date date=new Date();
		String dateStr=new SimpleDateFormat("yyyy_MM").format(date);
		List<ReportDetailInfo> projects = reportDao.getReportDetail(projectId);
		new XLSReport().genReportDetailChart(projects, projectName, dateStr);
		ReportLogTo reportLog = reportLogService.createReportLog(Constants.REPORT_DETAIL_CHART,projectName,dateStr);
		return reportLog;
		
	}
	
	public List<ProjectTo> getProjectByMonth(Integer year,Integer month)throws Exception{
		return reportDao.getProjectByMonth(year, month);
	}
	
	public int createEfficiencyQualityReport(Date start, Date end, ProjectTo project, UserGroupTo group) throws Exception{		
		List<ReportEfficiencyQuality> reportEQ = reportDao.getReportEfficiencyQuality(start, end, project, group);	
		List<ReportEfficiencyQuality> reqList = new ArrayList<ReportEfficiencyQuality>();
		
		if (reportEQ.size() > 0){
			
			List<Date> holidays = calendarDao.getHolidays();
			CalendarUtil calendarUtil = CalendarUtil.getInstance();
			calendarUtil.setHolidays(holidays);	
			
			ReportEfficiencyQuality newReq = new ReportEfficiencyQuality();
			double actualStaffDays = 0;
			double assignedEffort  = 0;
			
			for(int i = 0; i < reportEQ.size(); i++){
				
				ReportEfficiencyQuality req = reportEQ.get(i);				
				
				if((i == 0) || (req.getUserId() != reportEQ.get(i-1).getUserId())){
					newReq = reportUtil.createNewREQ(req);
					reqList.add(newReq);
				}
				
				actualStaffDays = calendarUtil.getWorkDays(start, end, req.getStartTime(), req.getEndTime());				
				assignedEffort  = req.getAssignedEffort() * actualStaffDays / req.getActualStaffDays();				
				newReq.setActualStaffDays(newReq.getActualStaffDays() + actualStaffDays);
				newReq.setAssignedEffort(newReq.getAssignedEffort() + assignedEffort);
				if(req.getMedal() == 3)	newReq.setStar(newReq.getStar() + assignedEffort);
				if(req.getMedal() == 4)	newReq.setLuna(newReq.getLuna() + assignedEffort);
				if(req.getMedal() == 5)	newReq.setSun(newReq.getSun() + assignedEffort);
				if(req.getAssignedEffort() / req.getActualStaffDays() >= 1.2){
					newReq.setAhead(newReq.getAhead() + 1);
				}					
				if(req.getAssignedEffort() / req.getActualStaffDays() <= 0.8){
					newReq.setDelay(newReq.getDelay() + 1);
				}
				
				if(req.getActivityType().equals(Constants.ACTIVITY_TYPE_PRODUCTION)){
					newReq.setActualStaffDaysF(newReq.getActualStaffDaysF() + actualStaffDays);
					newReq.setAssignedEffortF(newReq.getAssignedEffortF() + assignedEffort);
					if(req.getMedal() == 3) newReq.setStarF(newReq.getStarF() + assignedEffort);
					if(req.getMedal() == 4) newReq.setLunaF(newReq.getLunaF() + assignedEffort);
					if(req.getMedal() == 5) newReq.setSunF(newReq.getSunF() + assignedEffort);
					if(req.getAssignedEffort() / req.getActualStaffDays() >= 1.2){
						newReq.setAheadF(newReq.getAheadF() + 1);
					}					
					if(req.getAssignedEffort() / req.getActualStaffDays() <= 0.8){
						newReq.setDelayF(newReq.getDelayF() + 1);
					}
				}
			}
			new XLSReport().genReportEfficiencyQualityChart(reqList, start, end);
		}
		return reqList.size();
	}
	
	public int createProjectMemberAbilityReportBase(ProjectTo project, int year, int month) throws Exception{		
		List<ProjectMemberAbilityTo> reqList = projectMemberAbilityService.getProjectMemberAbilityWithPerformanceBase(project, year, month);	
		if(reqList.size() > 0){
			new XLSReport().genProjectMemberAbility(reqList, year, month);
		}
		return reqList.size();
	}
	
	@SuppressWarnings("unchecked")
	public int createProjectMemberAbilityReport(ProjectTo project, int year, List months) throws Exception{		
		List<ProjectMemberAbilityTo> reqList = projectMemberAbilityService.getProjectMemberAbilityWithPerformance(project, year, months);	
		if(reqList.size() > 0){
			if(months.size() == 12){
				new XLSReport().genProjectMemberAbility(reqList, year);
			}else{
				new XLSReport().genProjectMemberAbility(reqList, year);
			}
			
		}
		return reqList.size();
	}
	
	public List<ReportTo> getOtherActivityDaysByTime(ProjectTo project, List<String> typeList, Date start, Date end) throws Exception{
		return reportDao.getOtherActivityDaysByTime(project, typeList, start, end);
	}
	
	public List<ReportTo> getProjectMonthWorkDays(ProjectTo project) throws Exception{
		int projectId = project.getId();
		List<ReportTo> reportList = reportDao.getMonthWorkDaysByProjectId(projectId);
		List<ActivityTo> activityList = activityDao.getDifferentYearAndMonthActivitys(projectId);
		
		List<Date> holidays = calendarDao.getHolidays();
		CalendarUtil cu = CalendarUtil.getInstance();
		cu.setHolidays(holidays);
		
		for(ReportTo report : reportList){
			String[] day = report.getDay().split("/");
			int year = Integer.valueOf(day[0]);
			int month = Integer.valueOf(day[1]) - 1;
			Calendar monthEarliestTime = new GregorianCalendar(year, month, 1, 0, 0, 0);
			Calendar monthLatestTime = new GregorianCalendar(year, month, 1, 23, 59, 59);
			monthLatestTime.set(Calendar.DAY_OF_MONTH, monthLatestTime.getActualMaximum(Calendar.DAY_OF_MONTH));
			
			for(ActivityTo activity : activityList){
				Calendar start = cu.dateToCalendar(activity.getStartTime());
				Calendar end =cu.dateToCalendar(activity.getEndTime());	
				if(monthEarliestTime.before(end) && monthLatestTime.after(start)){
					Date startTime, endTime;
					if(monthEarliestTime.before(start)){
						startTime = start.getTime();
					}else{
						startTime = monthEarliestTime.getTime();
					}
					if(monthLatestTime.after(end)){
						endTime = end.getTime();
					}else{
						endTime = monthLatestTime.getTime();
					}
					double workdays = cu.getWorkDays(startTime, endTime);
					report.setValue(report.getValue() + workdays);
				}
			}
		}
		return reportList;
	}
	
	//创建“项目人力成本”报表
	public String getResourceCost(List<ProjectTo> projects, int year, int month, String modulus, String exponent, String userName) throws Exception{
		GregorianCalendar c = new GregorianCalendar(year, month - 1, 1);
		Date time = new Date(c.getTimeInMillis());
		int workDays = reportDao.getWorkdayByMonth(year, month);
		List<ReportTo> list = reportDao.getResourceCost(projects, year, month);
		
		for(ReportTo report : list){
			List<UserSalary> salaries = userSalaryDao.getUserSalarysByUserId(report.getUserId());
			for(int i = salaries.size() - 1; i >= 0; i--){
				UserSalary us = salaries.get(i);
				if(us.getStartTime().getTime() <= time.getTime()){
					us = decryptUserSalary(us, modulus, exponent, userName);
					report.setSalary(Double.valueOf(us.getSalary()));
					report.setInsurance(Double.valueOf(us.getInsurance()));
					Double socialBenefit = Double.valueOf(us.getSocialBenefitType1()) + Double.valueOf(us.getSocialBenefitType2()) + Double.valueOf(us.getHouseFound());
					report.setSocialBenefit(socialBenefit);
					break;
				}
			}
		}
		
		return new XLSReport().genReportProjectResourceCost(list, year, month, workDays, projects);
	}
	
	private UserSalary decryptUserSalary(UserSalary us, String modulus, String exponent, String userName){
		try{
			String salary 		= Float.toString(EncryptUtil.decrypt(modulus, exponent, us.getSalary(), userName));
			String insurance 	= Float.toString(EncryptUtil.decrypt(modulus, exponent, us.getInsurance(), userName));
			String socialType1	= Float.toString(EncryptUtil.decrypt(modulus, exponent, us.getSocialBenefitType1(), userName));
			String socialType2	= Float.toString(EncryptUtil.decrypt(modulus, exponent, us.getSocialBenefitType2(), userName));
			String houseFound	= Float.toString(EncryptUtil.decrypt(modulus, exponent, us.getHouseFound(), userName));
			String lunchBenefit	= Float.toString(EncryptUtil.decrypt(modulus, exponent, us.getLunchBenefit(), userName));
			
			us.setSalary(salary);
			us.setInsurance(insurance);
			us.setSocialBenefitType1(socialType1);
			us.setSocialBenefitType2(socialType2);
			us.setHouseFound(houseFound);
			us.setLunchBenefit(lunchBenefit);
			
			return us;
		}catch(Exception e){
			throw new MessageException();
		}
	}
	
	public void deleteProjectResourceReport(String fileName) throws Exception{
		NormandyConfiguration config = NormandyConfiguration.getInstance();
		String destFilePath = config.REPORT_FOLDER + fileName + ".xlsx";
		FileUtil.deleteFile(destFilePath);
	}
	
	public List<ReportTo> getUserEfforts(String year, List<UserTo> users, List<String> types) throws Exception{
		for(String type : types){
			if(type.equals("Production")){
				types.add("Feedback");
				break;
			}
		}
		List<ActivityTo> activities = activityDao.getActivityForEffort(year, users, types);
		List<Date> holidays = calendarDao.getHolidays();
		CalendarUtil cu = CalendarUtil.getInstance();
		cu.setHolidays(holidays);
		
		List<ReportTo> result = new ArrayList<ReportTo>();
		
		for(UserTo user : users){
			for(int month = 1; month < 13; month++){
				ReportTo report = new ReportTo();
				String name = user.getFirstName()+" "+user.getLastName()+"("+user.getEnglishFirstName()+" "+user.getEnglishLastName()+")";
				report.setName(name);
				report.setYear(Integer.parseInt(year));
				String quarter = cu.getQuarter(month);
				report.setQuarter(quarter);
				report.setMonth(month);
				Double effort = getUserMonthEffort(year, month, user.getId(), activities, types, cu);
				report.setEffort(effort);
				result.add(report);
			}
		}
		return result;
	}
	
	private Double getUserMonthEffort(String year, int month, int userId, List<ActivityTo> activities, List<String> types, CalendarUtil cu){
		Date monthFirstDay = cu.getFirstDayOfMonth(Integer.parseInt(year), month);
		Date monthLastDay  = cu.getLastDayOfMonth(Integer.parseInt(year), month);
		
		Double monthEffortSum = 0.0;
		for(int i = 0; i < activities.size(); i++){
			ActivityTo activity = activities.get(i);
			if(activity.getResourceId().equals(userId)){
				if(activity.getStartTime().getTime() < monthLastDay.getTime() && activity.getEndTime().getTime() > monthFirstDay.getTime()){
					boolean typeSuit = false;
					for(String type : types){
						if(activity.getActivityType().equals(type)){
							typeSuit = true;
							break;
						}
					}
					if(typeSuit){
						Date start, end;
						
						if(activity.getStartTime().getTime() < monthFirstDay.getTime()){
							start = monthFirstDay;
						}else{
							start = activity.getStartTime();
						}
						if(activity.getEndTime().getTime() > monthLastDay.getTime()){
							end = monthLastDay;
						}else{
							end = activity.getEndTime();
						}
						
						if(activity.getActivityType().equals("Production")){
							double workDays = cu.getWorkDays(start, end);
							if(workDays < activity.getActualStaffDays()){
								monthEffortSum += workDays / activity.getActualStaffDays() * activity.getAssignedEffort();
							}else{
								monthEffortSum += activity.getAssignedEffort();
							}
						}else{
							monthEffortSum += cu.getWorkDays(start, end);
						}
					}
				}
			}
		}
		return Math.floor(monthEffortSum * 1000 + 0.5) / 1000;
	}
	
	// ==============================
	// Injected Variables
	// ==============================
	private ReportDao reportDao;
	private ProjectService projectService;
	private ReportLogService reportLogService;
	private ActivityDao  activityDao;
	private FeedbackDao	feedbackDao;
	private CalendarDao calendarDao;
	private UserSalaryDao userSalaryDao;
	private ProjectMemberAbilityService projectMemberAbilityService;

	public ProjectMemberAbilityService getProjectMemberAbilityService() {
		return projectMemberAbilityService;
	}

	public void setProjectMemberAbilityService(
			ProjectMemberAbilityService projectMemberAbilityService) {
		this.projectMemberAbilityService = projectMemberAbilityService;
	}

	public void setReportDao(ReportDao reportDao) {
		this.reportDao = reportDao;
	}

	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}

	public void setReportLogService(ReportLogService reportLogService) {
		this.reportLogService = reportLogService;
	}
	
	public void setActivityDao(ActivityDao activityDao) {
		this.activityDao = activityDao;
	}		
	
	public void setFeedbackDao(FeedbackDao feedbackDao) {
		this.feedbackDao = feedbackDao;
	}	
	
	public void setCalendarDao(CalendarDao calendarDao){
		this.calendarDao = calendarDao;
	}

	public void setUserSalaryDao(UserSalaryDao userSalaryDao) {
		this.userSalaryDao = userSalaryDao;
	}
}