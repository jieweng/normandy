package com.pearl.normandy.actionlog;

import java.util.Date;
import java.util.Iterator;

import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

public class ActionLogService {
	static Logger log = Logger.getLogger(ActionLogService.class.getName());

	// ==============================
	// Get methods
	// ==============================
	
	
	// ==============================
	// Create, Update, Delete
	// ==============================
	public void create(Integer projectId, String entity, String entityId, String entityName, String field, 
			String action, String oldValue, String newValue, String actor) throws Exception{
		ActionLogTo actionLog = new ActionLogTo();
		actionLog.setProjectId(projectId);
		actionLog.setEntity(entity);
		actionLog.setEntityId(entityId);
		actionLog.setEntityName(entityName);
		actionLog.setField(field);
		actionLog.setAction(action);
		actionLog.setOldValue(oldValue);
		actionLog.setNewValue(newValue);
		actionLog.setActor(actor);
		actionLog.setCreatedDate(new Date());
		
		actionLogDao.create(actionLog);
	}
	
	@SuppressWarnings("unchecked")
	public void createObject(Integer projectId, String entity, String entityId, String entityName, 
			String action, Object oldValue, Object newValue, String actor) throws Exception{
		BeanMap beanMap = new BeanMap(oldValue);
		Iterator entries = beanMap.keyIterator();
		while(entries.hasNext()){
			String key = (String)entries.next();
			
			if(getFieldLoggable(key)){
				String oldV = BeanUtils.getProperty(oldValue, key);
				String newV = BeanUtils.getProperty(newValue, key);
				if((oldV == null && newV != null) || (oldV != null && newV == null) || (oldV!=null && newV!=null && !oldV.equals(newV))){
					create(projectId, entity, entityId, entityName, key, action, oldV, newV, actor);				
				}
			}
		}		
	}

	// ==============================
	// Private Methods
	// ==============================	
	private boolean getFieldLoggable(String field){
		if(field!=null && !field.equals("updatedDate") && !field.equals("updatedBy")){
			return true;
		}
		else{
			return false;
		}
			
	}
	
	// ==============================
	// Injected Variables
	// ==============================
	private ActionLogDao actionLogDao;

	public void setActionLogDao(ActionLogDao actionLogDao) {
		this.actionLogDao = actionLogDao;
	}
}
