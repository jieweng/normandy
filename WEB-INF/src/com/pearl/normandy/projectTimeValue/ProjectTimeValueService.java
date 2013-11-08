package com.pearl.normandy.projectTimeValue;

import java.util.List;

import org.apache.log4j.Logger;

import com.pearl.normandy.project.ProjectTo;

public class ProjectTimeValueService {
	
	static Logger log = Logger.getLogger(ProjectTimeValueService.class.getName());
	
	public List<ProjectTimeValue> getProjectTimeValues(ProjectTo project) throws Exception{
		List<ProjectTimeValue> result = projectTimeValueDao.getProjectTimeValues(project);
//		if(result.size() == 0){
//			ProjectTimeValue newProjectTimeValue = new ProjectTimeValue();
//			newProjectTimeValue.setProjectId(project.getId());
//			result.add(createProjectTimeValue(newProjectTimeValue));
//		}
		return result;
	}
	
	public ProjectTimeValue createProjectTimeValue(ProjectTimeValue projectTimeValue) throws Exception{
		int id = projectTimeValueDao.createProjectTimeValue(projectTimeValue);
		projectTimeValue.setId(id);
		return projectTimeValue;
	}
	
	public void updateProjectTimeValue(ProjectTimeValue projectTimeValue) throws Exception{
		projectTimeValueDao.updateProjectTimeValue(projectTimeValue);
	}
	
	public ProjectTimeValue deleteProjectTimeValue(ProjectTimeValue projectTimeValue) throws Exception{
		return projectTimeValueDao.deleteProjectTimeValue(projectTimeValue);
	}
	
	private ProjectTimeValueDao projectTimeValueDao;

	public void setProjectTimeValueDao(ProjectTimeValueDao projectTimeValueDao) {
		this.projectTimeValueDao = projectTimeValueDao;
	}

}
