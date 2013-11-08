package com.pearl.normandy.usergroup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.pearl.normandy.utils.Constants;

public class UserGroupDao extends SqlMapClientDaoSupport{
	
	
	//==============================
	//Get methods
	//==============================	
	@SuppressWarnings("unchecked")
	public List<UserGroupTo> getUserGroups() throws DataAccessException{
		Map<String, String> map = new HashMap<String, String>();
		return  this.getSqlMapClientTemplate().queryForList("UserGroup.selectUserGroups",map);
	}
	
	@SuppressWarnings("unchecked")
	public List<UserGroupTo> getProductionGroup() throws DataAccessException{
		Map<String, String> map = new HashMap<String, String>();
		map.put("production", Constants.BOOLEAN_YES);
		return this.getSqlMapClientTemplate().queryForList("UserGroup.selectUserGroups",map);
	}
	
	@SuppressWarnings("unchecked")
	public List<UserGroupTo> getUserGroups(Integer projectId,Integer groupId)throws DataAccessException{
		Map<String,Integer> param = new HashMap<String, Integer>();
		param.put("projectId", projectId);
		param.put("groupId", groupId);
		return this.getSqlMapClientTemplate().queryForList("UserGroup.selectUserGroupsById",param);
	}
	
	@SuppressWarnings("unchecked")
	public List<UserGroupTo> getUserGroupAs()throws DataAccessException{
		return this.getSqlMapClientTemplate().queryForList("UserGroup.selectUserGroupAs");
	}
	
	@SuppressWarnings("unchecked")
	public UserGroupTo createUserGroup(UserGroupTo group)throws DataAccessException{
		List<UserGroupTo> listGroup=getUserGroups();
		group.setId(listGroup.size());
		this.getSqlMapClientTemplate().insert("UserGroup.createUserGroup",group);
		return group;
	}
	
	@SuppressWarnings("unchecked")
	public void updateUserGroup(UserGroupTo group)throws DataAccessException{
		this.getSqlMapClientTemplate().update("UserGroup.updateUserGroup", group);
	}
}
