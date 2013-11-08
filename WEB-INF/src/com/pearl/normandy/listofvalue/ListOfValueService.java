package com.pearl.normandy.listofvalue;

import java.util.List;

import org.apache.log4j.Logger;

public class ListOfValueService {
	static Logger log = Logger.getLogger(ListOfValueService.class.getName());

	// ==============================
	// Get methods
	// ==============================
	public List<ListOfValueTo> getListOfValueByType(String listType) throws Exception{
		return listOfValueDao.getListOfValueByType(listType);
	}
	
	public List<ListOfValueTo> getTaskCategorieseByProjectId(Integer projectId)throws Exception{
		return listOfValueDao.getTaskCategorieseByProjectId(projectId);
	}

	//==============================
	//Injected Variables
	//==============================
	private ListOfValueDao listOfValueDao;

	public void setListOfValueDao(ListOfValueDao listOfValueDao) {
		this.listOfValueDao = listOfValueDao;
	}
	
	
	//======================= Ready to delete ====================
	public String getExTaskCategoriesByProjectId(Integer projectId)throws Exception{
		return listOfValueDao.getExTaskCategoriesByProjectId(projectId);
	}	
}
