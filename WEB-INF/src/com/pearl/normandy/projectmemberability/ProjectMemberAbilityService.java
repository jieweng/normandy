package com.pearl.normandy.projectmemberability;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.pearl.normandy.calendar.CalendarDao;
import com.pearl.normandy.project.ProjectDao;
import com.pearl.normandy.project.ProjectTo;
import com.pearl.normandy.usergroup.UserGroupTo;
import com.pearl.normandy.utils.CalendarUtil;

public class ProjectMemberAbilityService {
	static Logger log = Logger.getLogger(ProjectMemberAbilityService.class.getName());

	public List<ProjectMemberAbilityTo> getProjectMemberAbility(ProjectTo project, int year, int month) throws Exception{
		return projectMemberAbilityDao.selectProjectMemberAbility(project, year, month);
	}
	
	public List<ProjectMemberAbilityTo> getProjectMemberAbilityByAct(int projectId, int year, int month) throws Exception{
		return projectMemberAbilityDao.selectProjectMemberAbilityByAct(projectId, year, month);
	}
	
	public void updateProjectMemberAbility(ProjectMemberAbilityTo pma) throws Exception{
		projectMemberAbilityDao.update(pma);
	}
	
	public int createProjectMemberAbility(ProjectMemberAbilityTo pma) throws Exception{
		return projectMemberAbilityDao.create(pma);
	}
	
	private ProjectMemberAbilityDao projectMemberAbilityDao;

	public void setProjectMemberAbilityDao(ProjectMemberAbilityDao projectMemberAbilityDao) {
		this.projectMemberAbilityDao = projectMemberAbilityDao;
	}
	
	public List<ProjectMemberAbilityTo> getProjectMemberAbilityWithPerformance(ProjectTo project, int year, List months) throws Exception{
		List<ProjectMemberAbilityTo> result = new ArrayList<ProjectMemberAbilityTo>();
		for(int i = 0;i<months.size();i++){
			List<ProjectMemberAbilityTo> monthList = getProjectMemberAbilityWithPerformanceBase(project,year,(Integer)months.get(i));
			result.addAll(monthList);
		}
		Collections.sort(result, new Comparator<ProjectMemberAbilityTo>(){
			@Override
			public int compare(ProjectMemberAbilityTo o1, ProjectMemberAbilityTo o2) {
				if(o1.getUserId()>o2.getUserId()){
					return 1;
				}else{
					return -1;
				}
			}
			
		});
		return result;
	}
	
	public List<ProjectMemberAbilityTo> getProjectMemberAbilityWithPerformanceBase(ProjectTo project, int year, int month) throws Exception{
		List<ProjectMemberAbilityTo> abilityList = getProjectMemberAbility(project, year, month);
		Calendar start = Calendar.getInstance();
		start.set(year, month-1, 1, 0, 0, 0);
		Calendar end = (Calendar)start.clone();
		end.set(Calendar.DAY_OF_MONTH,end.getActualMaximum(Calendar.DAY_OF_MONTH));
		end.set(Calendar.HOUR_OF_DAY, 23);
		end.set(Calendar.MINUTE, 59);
		end.set(Calendar.SECOND, 59);
		List<Performance> performanceList = getProjectMemberPerformance(start.getTime(),end.getTime(),project,null);
		for(ProjectMemberAbilityTo pma:abilityList){
			for(Performance p:performanceList){
				if(p.getUserId() == pma.getUserId() && p.getProjectId() == pma.getProjectId()){
					pma.setAssignedEffort((int)(p.getAssignedEffort()*100)/100.0);
					int pro = (int)(p.getAssignedEffort()/p.getActualStaffDays()*100);
					pma.setProductivity(pro+"%");
					pma.setSun(p.getSun());
					pma.setLuna(p.getLuna());
					pma.setStar(p.getStar());
					pma.setStarlight(p.getStarlight());
					pma.setStardust(p.getStardust());
					
					Double taskQuality = 0.0;
					if((pma.getSun()+pma.getLuna()+pma.getStar()+pma.getStarlight()+pma.getStardust()) != 0.0){
						taskQuality =(pma.getSun()*5+pma.getLuna()*4+pma.getStar()*3+pma.getStarlight()*2+pma.getStardust()*1)
						/(pma.getSun()+pma.getLuna()+pma.getStar()+pma.getStarlight()+pma.getStardust());
					}
					
					pma.setTaskQuality((int)(taskQuality*100)/100.0);
					break;
				}
			}
		}
		return abilityList;
	}
	
	public List<Performance> getProjectMemberPerformance(Date start, Date end, ProjectTo project, UserGroupTo group) throws Exception{		
		List<Performance> performanceList = projectMemberAbilityDao.getPerformance(start, end, project, group);	
		List<Performance> reqList = new ArrayList<Performance>();
		
		if (performanceList.size() > 0){
			
			List<Date> holidays = calendarDao.getHolidays();
			CalendarUtil calendarUtil = CalendarUtil.getInstance();
			calendarUtil.setHolidays(holidays);	
			
			Performance newReq = new Performance();
			double actualStaffDays = 0;
			double assignedEffort  = 0;
			
			for(int i = 0; i < performanceList.size(); i++){
				
				Performance req = performanceList.get(i);				
				
				if((i == 0) || (req.getUserId() != performanceList.get(i-1).getUserId())||(req.getUserId() == performanceList.get(i-1).getUserId())&&req.getProjectId()!= performanceList.get(i-1).getProjectId() ){
					newReq = createNewREQ(req);
					reqList.add(newReq);
				}
				
				actualStaffDays = calendarUtil.getWorkDays(start, end, req.getStartTime(), req.getEndTime());				
				assignedEffort  = req.getAssignedEffort() * actualStaffDays / req.getActualStaffDays();				
				newReq.setActualStaffDays(newReq.getActualStaffDays() + actualStaffDays);
				newReq.setAssignedEffort(newReq.getAssignedEffort() + assignedEffort);
				if(req.getMedal() == 1)	newReq.setStardust(newReq.getStardust() + 1);
				if(req.getMedal() == 2)	newReq.setStarlight(newReq.getStarlight() + 1);
				if(req.getMedal() == 3)	newReq.setStar(newReq.getStar() + 1);
				if(req.getMedal() == 4)	newReq.setLuna(newReq.getLuna() + 1);
				if(req.getMedal() == 5)	newReq.setSun(newReq.getSun() + 1);
			}
		}
		return reqList;
	}
	
	public Performance createNewREQ(Performance req){
		Performance newReq = new Performance();
		newReq.setUserId(req.getUserId());
		newReq.setUserName(req.getUserName());
		newReq.setGroupId(req.getGroupId());
		newReq.setGroupName(req.getGroupName());
		newReq.setProjectId(req.getProjectId());
		newReq.setActualStaffDays(0.0);
		newReq.setAssignedEffort(0.0);
		newReq.setSun(0.0);
		newReq.setLuna(0.0);
		newReq.setStar(0.0);
		newReq.setStarlight(0.0);
		newReq.setStardust(0.0);
		return newReq;
	}
	
	private CalendarDao calendarDao;
	private ProjectDao projectDao;
	public CalendarDao getCalendarDao() {
		return calendarDao;
	}

	public void setCalendarDao(CalendarDao calendarDao) {
		this.calendarDao = calendarDao;
	}

	public ProjectDao getProjectDao() {
		return projectDao;
	}

	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}
}
