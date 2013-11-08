package com.pearl.normandy.taskdetail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.pearl.normandy.task.TaskTo;
import com.pearl.normandy.user.UserTo;
import com.pearl.normandy.utils.Constants;

public class TaskDetailService {
	static Logger log = Logger.getLogger(TaskDetailService.class.getName());

	// ==============================
	// Get methods
	// ==============================
	public Map<String, TaskDetailTo> getTaskDetailByTaskId(String taskId)throws Exception{
		List<TaskDetailTo> result = null;
		Map<String, TaskDetailTo> map = new HashMap<String, TaskDetailTo>();

		result = taskDetailDao.getTaskDetailByTaskId(taskId);
		for (int i = 0; i < result.size(); i++) {
			TaskDetailTo detail = result.get(i);
			if(detail.getType().equals(Constants.TASK_DETAIL_LOD) || detail.getType().equals(Constants.TASK_DETAIL_UV))
				map.put(detail.getType() + detail.getLevel(), detail);
			else
				map.put(detail.getType(), detail);
		}
		
		return map;
	}

	// ==============================
	// Create, Update, Delete
	// ==============================
	@Transactional
	public void updateTaskDetail(TaskTo task, UserTo user)throws Exception{
		taskDetailDao.updateTaskDetail(task.getDetails(), task.getId(), user.getUserName());
	}

	// ==============================
	// Injected Variables
	// ==============================
	private TaskDetailDao taskDetailDao;

	public void setTaskDetailDao(TaskDetailDao taskDetailDao) {
		this.taskDetailDao = taskDetailDao;
	}
}
