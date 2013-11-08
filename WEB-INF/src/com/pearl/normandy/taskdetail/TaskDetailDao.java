package com.pearl.normandy.taskdetail;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class TaskDetailDao extends SqlMapClientDaoSupport {
	static Logger log = Logger.getLogger(TaskDetailDao.class.getName());

	// ==============================
	// Get methods
	// ==============================
	@SuppressWarnings("unchecked")
	public List<TaskDetailTo> getTaskDetailByTaskId(String taskId)
			throws DataAccessException {
		List<TaskDetailTo> list = this.getSqlMapClientTemplate().queryForList("TaskDetail.selectTaskDetailByTaskId", taskId);
		return list;
	}

	// ==============================
	// Create, Update, Delete
	// ==============================
	public void create(HashMap<String, TaskDetailTo> map, Integer taskId,
			String createdBy) throws DataAccessException {
		Set<String> keySet = map.keySet();
		Iterator<String> iterator = keySet.iterator();
		String key;
		TaskDetailTo taskDetail;

		while (iterator.hasNext()) {
			key = (String) iterator.next();
			taskDetail = (TaskDetailTo) map.get(key);

			if (taskDetail.getValue() != null && !"".equals(taskDetail.getValue())) {
				taskDetail.setTaskId(taskId);
				taskDetail.setCreatedDate(new Date());
				taskDetail.setCreatedBy(createdBy);
				this.getSqlMapClientTemplate().insert("TaskDetail.insert",taskDetail);
			}
		}
	}
	
	public void copyTaskDetail(Integer oldTaskId, Integer newTaskId, String createdBy)throws DataAccessException{
		Map param = new HashMap();
		param.put("oldTaskId", oldTaskId);
		param.put("newTaskId", newTaskId);
		param.put("createdBy", createdBy);
		this.getSqlMapClientTemplate().insert("TaskDetail.copyTaskDetail",param);
	}

	public void updateTaskDetail(Map<String, TaskDetailTo> map, Integer taskId, String userName)throws DataAccessException {
		Iterator<String> iterator = map.keySet().iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			TaskDetailTo detail = map.get(key);
			if (detail.getId() == null && detail.getValue() != null) {
				detail.setTaskId(taskId);
				detail.setCreatedDate(new Date());
				detail.setCreatedBy(userName);
				this.getSqlMapClientTemplate().insert("TaskDetail.insert",detail);
			} else {
				if (detail.getValue() != null && !"".equals(detail.getValue())){
					detail.setUpdatedDate(new Date());
					detail.setUpdatedBy(userName);
					this.getSqlMapClientTemplate().update("TaskDetail.update",detail);
				}
				else
					this.getSqlMapClientTemplate().update("TaskDetail.delete",detail.getId());
			}
		}
	}
}
