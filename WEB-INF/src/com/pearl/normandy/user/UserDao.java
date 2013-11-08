package com.pearl.normandy.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.pearl.normandy.utils.Constants;


public class UserDao extends SqlMapClientDaoSupport {
	static Logger log = Logger.getLogger(UserDao.class.getName());

	//==============================
	//Get methods
	//==============================		
	@SuppressWarnings("unchecked")
	public List<UserTo> getAllUsers() throws DataAccessException {
		List<UserTo> result = null;
		result = this.getSqlMapClientTemplate().queryForList("User.selectUser", null);
		return result;
	}	
	
	@SuppressWarnings("unchecked")
	public List<UserTo> getUserByPassword(String userName, String password) throws DataAccessException {
		HashMap param = new HashMap();
		param.put("userName", userName);
		param.put("passwordHash", password);
		param.put("deleted", Constants.BOOLEAN_NO);
		List<UserTo> result = null;
		result = this.getSqlMapClientTemplate().queryForList("User.selectUser",param);
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<UserTo> getUsersByGroupId(Integer userGroupId) throws DataAccessException {
		HashMap param = new HashMap();
		param.put("userGroupId", userGroupId);
		List<UserTo> result = null;
		result = this.getSqlMapClientTemplate().queryForList("User.selectUser",param);
		return result;
	}	
	
	@SuppressWarnings("unchecked")
	public List<UserTo> getManagerByGroupId(Integer userGroupId) throws DataAccessException {
		HashMap param = new HashMap();
		param.put("userGroupId", userGroupId);
		param.put("manager", Constants.BOOLEAN_YES);
		List<UserTo> result = null;
		result = this.getSqlMapClientTemplate().queryForList("User.selectUser",param);
		return result;
	}		
	
	
	@SuppressWarnings("unchecked")
	public List<UserTo> getUserByIds(List ids) throws DataAccessException {
		HashMap param = new HashMap();
		param.put("ids", ids);
		List<UserTo> result = null;
		result = this.getSqlMapClientTemplate().queryForList("User.selectUser",param);
		return result;
	}	
	
	@SuppressWarnings("unchecked")
	public List<UserTo> getProductionGroupUsersWithJoinedProject(Integer groupId) throws DataAccessException{
		List<UserTo> result = null;
		result = this.getSqlMapClientTemplate().queryForList("User.selectProductionGroupUsers",groupId);
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<UserTo> getAllProductionGroupUsersWithJoinedProject() throws DataAccessException{
		List<UserTo> result = null;
		result = this.getSqlMapClientTemplate().queryForList("User.selectAllProductionGroupUsers");
		return result;
	}	
	
	@SuppressWarnings("unchecked")
	public List<UserTo> getAllProductionUsersWithDeleted() throws DataAccessException{
		List<UserTo> result = null;
		result = this.getSqlMapClientTemplate().queryForList("User.selectAllProductionUsersWithDeleted");
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<UserTo> getQAGroupUsersWithJoinedProject(Integer groupId) throws DataAccessException{
		List<UserTo> result = null;
		result = this.getSqlMapClientTemplate().queryForList("User.selectQAGroupUsers",groupId);
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<UserTo> getProjectUsers(Integer projectId, Boolean isShowDeleted) throws DataAccessException {
//		Map<String, Integer> map = new HashMap<String, Integer>();
		Map map = new HashMap();
		map.put("projectId", projectId);
		map.put("isShowDeleted", isShowDeleted);
		return this.getSqlMapClientTemplate().queryForList("User.selectProjectUsers", map);
	}

	@SuppressWarnings("unchecked")
	public List<UserTo> getUsersForLead(Integer projectId, Integer userId) throws DataAccessException {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("projectId", projectId);
		map.put("supervisorId", userId);

		return this.getSqlMapClientTemplate().queryForList("User.selectUsersForLead", map);
	}

	@SuppressWarnings("unchecked")
	public List<UserTo> getUsersByProjectSupervisorId(Integer projectId, Integer supervisorId, String role) throws DataAccessException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("projectId", projectId.toString());
		map.put("supervisorId", supervisorId.toString());
		map.put("role", role);

		return this.getSqlMapClientTemplate().queryForList(
				"User.selectUsersByProjectSupervisorId", map);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<UserTo> getUserNotAPM(UserTo userTo){
		return this.getSqlMapClientTemplate().queryForList("User.selectUserNotAPM",userTo.getId());
	}
	
	@SuppressWarnings("unchecked")
	public List<UserTo> getResourceByTaskId(Integer taskId)throws DataAccessException{
		return this.getSqlMapClientTemplate().queryForList("User.selectResourceByTaskId",taskId);
	}
	
	@SuppressWarnings("unchecked")
	public List<UserTo> getAPMByTaskId(Integer taskId)throws DataAccessException{
		return this.getSqlMapClientTemplate().queryForList("User.selectAPMByTaskId", taskId);
	}
	
	@SuppressWarnings("unchecked")
	public List<UserTo> getQaUsersByTaskId(Integer taskId)throws DataAccessException{
		return this.getSqlMapClientTemplate().queryForList("User.selectQaUsersByTaskId",taskId);
	}
	
	@SuppressWarnings("unchecked")
	public List<UserTo> getNotProjectUser()throws DataAccessException{
		return this.getSqlMapClientTemplate().queryForList("User.selectNotProjectUser");
	}
	
	@SuppressWarnings("unchecked")
	public List<UserTo> getRecipients() throws DataAccessException{
		return getSqlMapClientTemplate().queryForList("User.selectRecipients");
	}

	//==============================
	// methods
	//==============================
	public void modifyPassword(String userName, String hashOldpassword, String hashNewpasswor) throws DataAccessException{
		Map<String, String> map = new HashMap<String, String>();
		map.put("userName", userName);
		map.put("hashOldpassword", hashOldpassword);
		map.put("hashNewpasswor", hashNewpasswor);
		this.getSqlMapClientTemplate().update("User.updatePassword", map);
	}
	
	@SuppressWarnings("unchecked")
	public UserTo getUserByUserName(String userName, String email)throws Exception{
		HashMap param = new HashMap();
		param.put("userName", userName);
		param.put("email", email);
		param.put("deleted", Constants.BOOLEAN_NO);
		return (UserTo) this.getSqlMapClientTemplate().queryForObject("User.selectUser",param);
	}
	
	//==============================
	//Create, Update, Delete
	//==============================	
	public UserTo create(UserTo userTo) throws DataAccessException {
		userTo.setId((Integer) this.getSqlMapClientTemplate().insert("User.insert",userTo));
		return userTo;
	}	
	
	public void update(UserTo userTo) throws DataAccessException {
		this.getSqlMapClientTemplate().update("User.update",userTo);
	}

	
	public void delete(Integer userId) throws DataAccessException {
		this.getSqlMapClientTemplate().update("User.deleteUser", userId);
	}	
}
