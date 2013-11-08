package com.pearl.normandy.activity;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.pearl.normandy.core.mail.IdleEmployee;
import com.pearl.normandy.task.TaskTo;
import com.pearl.normandy.utils.Constants;
import com.pearl.normandy.user.UserTo;

public class ActivityDao extends SqlMapClientDaoSupport{
	
	static Logger log = Logger.getLogger(ActivityDao.class.getName());

	
	//==============================
	//Get methods
	//==============================	
	@SuppressWarnings("unchecked")
	public List<ActivityTo> getActivitiesByUsers(List users, Date startDate, Date endDate, String deleted) throws DataAccessException {
		HashMap map = new HashMap();		
		map.put("ids", users);
		map.put("startDate", startDate);			
		map.put("endDate", endDate);
		map.put("deleted", deleted);
		List<ActivityTo> result = this.getSqlMapClientTemplate().queryForList("Activity.selectActivitiesByUsers", map);
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<ActivityTo> getActivitiesByUsers(List users) throws DataAccessException {
		return getActivitiesByUsers(users, null, null, null);
	}		
	
	@SuppressWarnings("unchecked")
	public List<ActivityTo> getActivitiesByUsers(List users, String deleted) throws DataAccessException {
		return getActivitiesByUsers(users, null, null, deleted);
	}			

	@SuppressWarnings("unchecked")
	public ActivityTo getActivityById(Integer id) throws DataAccessException {
		ActivityTo result = (ActivityTo)this.getSqlMapClientTemplate().queryForObject("Activity.selectActivityById", id);
		return result;
	}	
	
	@SuppressWarnings("unchecked")
	public List<ActivityTo> getActivitiesByTaskId(Integer taskId) throws DataAccessException {
		HashMap param = new HashMap();
		param.put("taskId", taskId);
		param.put("deleted", Constants.BOOLEAN_NO);

		List result = this.getSqlMapClientTemplate().queryForList("Activity.selectActivitiesByTaskId", param);
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public Double getAssignedEffortByProjectId(Integer projectId) throws DataAccessException {

		Double result = (Double)this.getSqlMapClientTemplate().queryForObject("Activity.selectAssignedEffortByProjectId", projectId);
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<ActivityTo> getDeletedActivitiesByTaskId(Integer taskId) throws DataAccessException {
		HashMap param = new HashMap();
		param.put("taskId", taskId);
		param.put("deleted", Constants.BOOLEAN_YES);

		List result = this.getSqlMapClientTemplate().queryForList("Activity.selectActivitiesByTaskId", param);
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public Double getAssignedEffortByTaskId(Integer taskId) throws DataAccessException {
		Double result = (Double) this.getSqlMapClientTemplate().queryForObject("Activity.selectAssignedEffortByTaskId", taskId);
		return result;
	}		

	@SuppressWarnings("unchecked")
	public List<ActivityTo> getActivityByTaskId(Integer taskId) throws DataAccessException {
		List<ActivityTo> result = this.getSqlMapClientTemplate().queryForList("Activity.selectActivitiesBySummaryTaskId", taskId);
		return result;
	}

	public int getCountActivityForWIP(Integer taskId)throws DataAccessException{
		return (Integer) this.getSqlMapClientTemplate().queryForObject("Activity.selectCountActivityForWIP",taskId);
	}
	
	@SuppressWarnings("unchecked")
	public List<ActivityTo> getNotCompleteActivityByTaskId(Integer taskId, Integer userId, Date endTime)throws DataAccessException{
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("taskId", taskId);
		param.put("userId", userId);
		param.put("endTime", endTime);
		return (List<ActivityTo>) this.getSqlMapClientTemplate().queryForList("Activity.selectNotCompleteActivityByTaskId",param);
	}
	
	@SuppressWarnings("unchecked")
	public List<ActivityTo> getRTDActivityByProjectId(Integer projectId, String startTime,String endTime) throws DataAccessException {
		Map param = new HashMap();
		param.put("projectId", projectId);
		param.put("startTime", startTime);
		param.put("endTime", endTime);		
		List result = this.getSqlMapClientTemplate().queryForList("Activity.selectRTDActivity", param);
		return result;
	}	
	
	@SuppressWarnings("unchecked")
	public List<ActivityTo> getActivityForEffort(String year, List<UserTo> users, List<String> types) throws DataAccessException {
		Map param = new HashMap();
		param.put("year", year);
		param.put("users", users);
		param.put("types", types);
		List<ActivityTo> result = this.getSqlMapClientTemplate().queryForList("Activity.selectActivityForEffort", param);
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<ActivityTo> getActivityByTime(Date date) throws DataAccessException {
		List<ActivityTo> result = this.getSqlMapClientTemplate().queryForList("Activity.selectActivityByTime", date);
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<ActivityTo> getNonProductionActivity(Integer projectId, List<String> typeList) throws DataAccessException{
		Map param = new HashMap();
		param.put("projectId", projectId);
		param.put("typeList", typeList);
		List<ActivityTo> result = this.getSqlMapClientTemplate().queryForList("Activity.selectNonProductionActivity", param);
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<ActivityTo> getOtherActivityForProjectProgress(Integer projectId, String milestone) throws DataAccessException{
		Map param = new HashMap();
		param.put("projectId", projectId);
		param.put("milestone", milestone);
		List<ActivityTo> result = this.getSqlMapClientTemplate().queryForList("Activity.selectOtherActivityForProjectProgress", param);
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<ActivityTo> getDifferentYearAndMonthActivitys(int projectId) throws DataAccessException{
		return this.getSqlMapClientTemplate().queryForList("Activity.selectDifferentYearAndMonthActivitys", projectId);
	}
	
	@SuppressWarnings("unchecked")
	public List<IdleEmployee> getIdleEmployee(Date start, Date end, double workdays) throws DataAccessException{
		Map param = new HashMap();
		param.put("start", start);
		param.put("end", end);
		param.put("workdays", workdays);
		return this.getSqlMapClientTemplate().queryForList("Activity.selectIdleEmployee", param);
	}
	
	//==============================
	//Create, Update, Delete
	//==============================
	public ActivityTo create(ActivityTo activityTo) throws DataAccessException {
		Integer id = (Integer) this.getSqlMapClientTemplate().insert("Activity.insert", activityTo);
		activityTo.setId(id);
		return activityTo;
	}
	
	public void update(ActivityTo activityTo) throws DataAccessException {
		this.getSqlMapClientTemplate().update("Activity.update", activityTo);
	}
	
	@SuppressWarnings("unchecked")
	public void completeActivityByTaskId(Integer taskId) throws DataAccessException {
		HashMap param = new HashMap();
		param.put("taskId", taskId);
		this.getSqlMapClientTemplate().update("Activity.completeActivityByTaskId", param);
	}	
	
	@SuppressWarnings("unchecked")
	public void completeProductionActivityByTaskId(Integer taskId) throws DataAccessException {
		HashMap param = new HashMap();
		param.put("taskId", taskId);
		param.put("activityType", Constants.ACTIVITY_TYPE_PRODUCTION);
		this.getSqlMapClientTemplate().update("Activity.completeActivityByTaskId", param);
	}		
	
	public void resetActivityByTaskId(Integer taskId) throws DataAccessException{
		this.getSqlMapClientTemplate().update("Activity.resetActivityByTaskId",taskId);
	}	
	
	public void resetActualEndTime(Integer taskId) throws DataAccessException{
		this.getSqlMapClientTemplate().update("Activity.resetActualEndTime", taskId);
	}
	public void resetActualEndTimeByIds(List<Integer> ids) throws DataAccessException{
		this.getSqlMapClientTemplate().update("Activity.resetActualEndTimeByIds", ids);
	}	
	
	@SuppressWarnings("unchecked")
	public void updateTrainingFlagByTaskId(boolean flag, Integer taskId) throws DataAccessException{
		HashMap param = new HashMap();
		param.put("taskId", taskId);
		
		if(flag){
			param.put("flag", Constants.BOOLEAN_YES);
		}
		else{
			param.put("flag", Constants.BOOLEAN_NO);
		}
		
		this.getSqlMapClientTemplate().update("Activity.updateTrainingFlagByTaskId",param);
	}	
	
	public void updateActivityName(Integer taskId)throws DataAccessException{
		this.getSqlMapClientTemplate().update("Activity.updateActivityName",taskId);
	}

	public void cancel(Integer id) throws DataAccessException {
		this.getSqlMapClientTemplate().update("Activity.cancel", id);
	}

	public void delete(Integer id) throws DataAccessException {
		this.getSqlMapClientTemplate().delete("Activity.delete", id);
	}
	
	@SuppressWarnings("unchecked")
	public void cancelActivityByTaskIds(List<TaskTo> ids) throws DataAccessException {
		HashMap param = new HashMap();
		param.put("ids", ids);
		this.getSqlMapClientTemplate().delete("Activity.cancelActivityByTaskIds", param);
	}
	
	@SuppressWarnings("unchecked")
	public void modifyActualStaffDays(int id, double days) throws DataAccessException{
		HashMap param = new HashMap();
		param.put("id", id);
		param.put("days", days);
		this.getSqlMapClientTemplate().update("Activity.modifyActulStaffDays", param);
	}
}
