package com.pearl.normandy.task;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.pearl.normandy.utils.Constants;

public class TaskDao extends SqlMapClientDaoSupport{
	static Logger log = Logger.getLogger(TaskDao.class.getName());


	//==============================
	//Get methods
	//==============================	
	@SuppressWarnings("unchecked")
	public List<String> getMilestone(Integer projectId) throws DataAccessException {
		List<String> result = getSqlMapClientTemplate().queryForList("Task.selectMilestone", projectId);
		return result;
	}	
	
	@SuppressWarnings("unchecked")
	public List<String> getTaskCategory(Integer projectId) throws DataAccessException {
		List<String> result = getSqlMapClientTemplate().queryForList("Task.selectTaskCategory", projectId);
		return result;
	}	
	
	@SuppressWarnings("unchecked")
	public List<TaskTo> getTask(Map map) throws DataAccessException {
		List<TaskTo> result = this.getSqlMapClientTemplate().queryForList("Task.selectTask", map);
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public TaskTo getTaskById(Integer id) throws DataAccessException {
		HashMap param= new HashMap();
		param.put("id", id);
		List<TaskTo> result = getTask(param);
		
		if(result.size() > 0){
			return (TaskTo)result.get(0);
		}
		
		return null;
	}	
	
	@SuppressWarnings("unchecked")
	public List<TaskTo> getTaskByIds(List ids) throws DataAccessException {
		HashMap param= new HashMap();
		param.put("ids", ids);
		List<TaskTo> result = this.getSqlMapClientTemplate().queryForList("Task.selectTask", param);
		return result;
	}		
	
	@SuppressWarnings("unchecked")
	public List<TaskTo> getTask(Integer projectId, String milestone, String taskCategory) throws DataAccessException {
		Map param = new HashMap();
		param.put("projectId", projectId);
		param.put("milestone", milestone);
		param.put("taskCategory", taskCategory);				
		List<TaskTo> result = this.getSqlMapClientTemplate().queryForList("Task.selectTask", param);
		return result;
	}	
	
	@SuppressWarnings("unchecked")
	public List<TaskTo> getTaskByStatus(Integer projectId, String milestone, String taskCategory, String status) throws DataAccessException {
		Map param = new HashMap();
		param.put("projectId", projectId);
		param.put("milestone", milestone);
		param.put("taskCategory", taskCategory);
		param.put("status", status);
		param.put("root", Constants.BOOLEAN_YES);
		List<TaskTo> result = this.getSqlMapClientTemplate().queryForList("Task.selectTask", param);
		return result;
	}		
	
	@SuppressWarnings("unchecked")
	public Map getTaskRootMap(Integer projectId, String milestone, String taskCategory, Integer ownerId) throws DataAccessException {
		Map map = new HashMap();
		map.put("projectId", projectId);
		map.put("milestone", milestone);
		map.put("taskCategory", taskCategory);
		map.put("ownerId", ownerId);
		map.put("root", Constants.BOOLEAN_YES);
		Map result = this.getSqlMapClientTemplate().queryForMap("Task.selectTask", map, "id");
		return result;
	}	
	
	@SuppressWarnings("unchecked")
	public Map getTaskRootMapByIds(List ids) throws DataAccessException {
		Map map = new HashMap();
		map.put("ids", ids);
		Map result = this.getSqlMapClientTemplate().queryForMap("Task.selectTask", map, "id");
		return result;
	}		
	
	@SuppressWarnings("unchecked")
	public List<TaskTo> getTaskLeaf(Integer projectId, String milestone, String taskCategory) throws DataAccessException {
		Map map = new HashMap();
		map.put("projectId", projectId);
		map.put("milestone", milestone);
		map.put("taskCategory", taskCategory);
		map.put("root", Constants.BOOLEAN_NO);
		List<TaskTo> result = this.getSqlMapClientTemplate().queryForList("Task.selectTask", map);
		return result;
	}	
	
	
	@SuppressWarnings("unchecked")
	public List<TaskTo> getTaskLeafByIds(List parentIds) throws DataAccessException {
		Map map = new HashMap();
		map.put("parentIds", parentIds);
		List<TaskTo> result = this.getSqlMapClientTemplate().queryForList("Task.selectTask", map);
		return result;
	}		

	//Ready to delete, 2009/11/20, Frank
/*	public TaskTo getLastTaskByProjectId(Integer projectId) throws DataAccessException {
		TaskTo taskTo = (TaskTo) this.getSqlMapClientTemplate().queryForObject("Task.selectLastTaskByProjectId", projectId);
		return taskTo;
	}*/

	@SuppressWarnings("unchecked")
	public List<TaskTo> getTaskByProductionProcessId(Integer productionProcessId) throws DataAccessException {
		List<TaskTo> result = this.getSqlMapClientTemplate().queryForList("Task.selectTaskByProductionProcessId", productionProcessId);
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<TaskTo> getTaskStatusDetail(Map map)throws DataAccessException{
		List<TaskTo> result = this.getSqlMapClientTemplate().queryForList("Task.selectTaskStatusDetail", map);
		return result;
	}	
	
	@SuppressWarnings("unchecked")
	public List<String> getTaskGroupByProjectId(Integer projectId)throws DataAccessException{
		List<String> result = this.getSqlMapClientTemplate().queryForList("Task.selectTaskGroupByProjectId", projectId);
		return result;
	}	
	
	@SuppressWarnings("unchecked")
	public List<String> getTaskGroupByTaskId(Integer projectId, Integer taskId)throws DataAccessException{
		Map param = new HashMap();
		param.put("projectId", projectId);
		param.put("taskId", taskId);
		List<String> result = this.getSqlMapClientTemplate().queryForList("Task.selectTaskGroupByTaskId", param);
		return result;
	}		
	
	@SuppressWarnings("unchecked")
	public Double getTaskStatusAvg(Integer taskId)throws DataAccessException{
		Double result = (Double)this.getSqlMapClientTemplate().queryForObject("Task.selectTaskStatusAvg", taskId);
		return result;
	}		
	
	
	//==============================
	//Create, Update, Delete
	//==============================	
	public TaskTo create(TaskTo taskTo) throws DataAccessException {
		Integer id = (Integer)this.getSqlMapClientTemplate().insert("Task.insert", taskTo);
		taskTo.setId(id);
		return taskTo;
	}
	
	public void createSubtask(TaskTo taskTo) throws DataAccessException {
		this.getSqlMapClientTemplate().insert("Task.insertSubtask", taskTo);
	}
	
	@SuppressWarnings("unchecked")
	public void copySubtask(Integer oldId, Integer newId, String parentName, Integer statusId, Integer progress) throws DataAccessException {
		HashMap param = new HashMap();
		param.put("oldId", oldId);
		param.put("newId", newId);
		param.put("parentName", parentName);
		param.put("statusId", statusId);
		param.put("progress", progress);
		this.getSqlMapClientTemplate().insert("Task.copySubtask", param);
	}	

	public void update(TaskTo taskTo) throws DataAccessException {
		this.getSqlMapClientTemplate().update("Task.update", taskTo);
	}
	
	public void updateParentName(Integer taskId) throws DataAccessException {
		this.getSqlMapClientTemplate().update("Task.updateParentName", taskId);
	}		
	
	@SuppressWarnings("unchecked")
	public void updateTaskStatus(Integer taskId,Integer statusId)throws DataAccessException{
		Map map = new HashMap();
		map.put("taskId",  taskId);
		map.put("statusId", statusId);
		this.getSqlMapClientTemplate().update("Task.updateTaskStatus",map);
	}	
	
	@SuppressWarnings("unchecked")
	public void updateTaskByParentId(Integer parentId, String type)throws DataAccessException{
		Map map = new HashMap();
		map.put("parentId",  parentId);
		map.put("type", type);
		this.getSqlMapClientTemplate().update("Task.updateTypeByParentId",map);
	}		
	
	public void updateTaskProgress(Integer taskId) throws DataAccessException {
		this.getSqlMapClientTemplate().update("Task.updateTaskProgress", taskId);
	}		
	
	public void updateSummaryTaskProgress(Integer taskId) throws DataAccessException {
		this.getSqlMapClientTemplate().update("Task.updateSummaryTaskProgress", taskId);
	}	
	
	public void updateTaskOwnerId(Integer projectId, Integer ownerId, Integer newOwnerId) throws DataAccessException {
		HashMap<String,Integer> map = new HashMap<String, Integer>();
		map.put("projectId", projectId);			
		map.put("ownerId", ownerId);
		map.put("newOwnerId", newOwnerId);
		this.getSqlMapClientTemplate().update("Task.updateTaskOwnerId", map);
	}
	
	public void cancel(Integer taskId) throws DataAccessException {
		this.getSqlMapClientTemplate().update("Task.cancel", taskId);
	}	
	
	
	//=========================== Ready to delete ==============================
/*	@SuppressWarnings("unchecked")	
	public List<TaskTo> getSubtaskAsTask(Map map) throws DataAccessException {
		List<TaskTo> result = this.getSqlMapClientTemplate().queryForList("Task.selectSubtaskAsTask", map);
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<TaskTo> getTaskRightJoinUser(Map map) throws DataAccessException {
		List<TaskTo> result = this.getSqlMapClientTemplate().queryForList("Task.selectTaskRightJoinUser", map);
		return result;
	}	
	
	
	public TaskTo getTaskBySubtaskId(String subtaskId)throws DataAccessException{
		TaskTo taskTo = (TaskTo) this.getSqlMapClientTemplate().queryForObject("Task.selectTaskBySubtaskId",subtaskId);
		return taskTo;
	}	*/
	
/*	public Integer getTaskCount(Integer projectId, Integer groupId)throws DataAccessException{
		Integer result = 0;
		Map<String,Integer> param = new HashMap<String, Integer>();
		param.put("projectId", projectId);
		param.put("groupId", groupId);
		result = (Integer) this.getSqlMapClientTemplate().queryForObject("Task.selectTaskCount", param);
		return result;
	}	*/
}
