package com.pearl.normandy.core.mail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import com.pearl.normandy.activity.ActivityDao;
import com.pearl.normandy.activity.ActivityTo;
import com.pearl.normandy.checkitem.CheckItemTo;
import com.pearl.normandy.core.velocity.DefaultVelocityManager;
import com.pearl.normandy.enumerator.CheckItemEnum;
import com.pearl.normandy.feedback.FeedbackDao;
import com.pearl.normandy.feedback.FeedbackTo;
import com.pearl.normandy.projectmember.ProjectMemberService;
import com.pearl.normandy.projectmember.ProjectMemberTo;
import com.pearl.normandy.projectuser.ProjectUserDao;
import com.pearl.normandy.projectuser.ProjectUserTo;
import com.pearl.normandy.user.UserDao;
import com.pearl.normandy.user.UserTo;
import com.pearl.normandy.utils.Constants;
import com.pearl.normandy.utils.NormandyConfiguration;

public class NormandyNotification {	
	
	@SuppressWarnings("unchecked")
	public void sendNotification(CheckItemTo checkItem, UserTo currUser) throws Exception{
		String type = checkItem.getStatus();
		List<String> roleList = NormandyConfiguration.getNotificationList(type);
		HashSet<Integer> ids = new HashSet<Integer>();
		ActivityTo activity = activityDao.getActivityById(checkItem.getActivityId());
		
		if(roleList!=null && roleList.size()>0){						
			for(int i=0; i<roleList.size(); i++){
				String role = (String)roleList.get(i);			
				
				if(role.equals(Constants.PROJECT_ROLE_ARTIST)){
					List<UserTo> artistUserList = userDao.getResourceByTaskId(activity.getTaskId());
					setUserId(artistUserList, ids);
				}
				else if(role.equals(Constants.PROJECT_ROLE_LEAD)){
					ids.add(activity.getTaskOwnerId());
				}
				else if(role.equals(Constants.PROJECT_ROLE_QA)){
					List<UserTo> qaUserList = userDao.getQaUsersByTaskId(activity.getTaskId());					
					setUserId(qaUserList, ids);
				}
				else{
					List<ProjectUserTo> projectUserList = projectUserDao.getProjectUserByRoleName(activity.getProjectId(), role);
					setUserId(projectUserList, ids);				
				}
			}			
			
			//Send notification to managers
			String managerFlag = NormandyConfiguration.getNotificationManagerFlag(type);
			if(checkManager(managerFlag, Constants.PROJECT_ROLE_APM)){
				List<UserTo> apmList = userDao.getAPMByTaskId(activity.getTaskId());
				setUserId(apmList, ids);				
			}
			
			if(checkManager(managerFlag, Constants.PROJECT_ROLE_QA)){
				List<UserTo> qaManagerList = userDao.getManagerByGroupId(Constants.USERGROUP_QA_NUM);
				setUserId(qaManagerList, ids);				
			}
		}
		
		
		if(ids.size()>0){
			List<UserTo> users = userDao.getUserByIds(new ArrayList(ids));
			mail.sendMail(users, getNotificationSubject(type, activity), getNotificationMsg(checkItem, activity ,currUser));
		}
	}
	
	@SuppressWarnings("unchecked")
	public void sendNotification(List<CheckItemTo> list, UserTo currUser) throws Exception{
		HashSet<Integer> artistIds 		= new HashSet<Integer>();
		HashSet<Integer> leadIds   		= new HashSet<Integer>();
		HashSet<Integer> qaIds	   		= new HashSet<Integer>();
		HashSet<Integer> otherIds  		= new HashSet<Integer>();
		HashSet<Integer> qaManagerIds	= new HashSet<Integer>();
		String type = list.get(0).getStatus();
		List<String> roleList = NormandyConfiguration.getNotificationList(type);
		String managerFlag	  = NormandyConfiguration.getNotificationManagerFlag(type);
		
		if(roleList != null && roleList.size() > 0){
			for(int m = 0; m < roleList.size(); m++){
				String role = roleList.get(m);
				if(role.equals(Constants.PROJECT_ROLE_ARTIST)){
					for(int i = 0; i < list.size(); i++){
						CheckItemTo checkItem = list.get(i);
						List<UserTo> artists = userDao.getResourceByTaskId(checkItem.getTaskId());
						setUserId(artists, artistIds);
					}					
					getUserAllCheckItemsAndSendMail(artistIds, Constants.PROJECT_ROLE_ARTIST, list, type, currUser);					
				}else if(role.equals(Constants.PROJECT_ROLE_LEAD)){
					for(int i = 0; i < list.size(); i++){
						CheckItemTo checkItem = list.get(i);
						leadIds.add(checkItem.getOwnerId());
					}					
					getUserAllCheckItemsAndSendMail(leadIds, Constants.PROJECT_ROLE_LEAD, list, type, currUser);					
				}else if(role.equals(Constants.PROJECT_ROLE_QA)){
					for(int i = 0; i < list.size(); i++){
						CheckItemTo checkItem = list.get(i);
						List<UserTo> qas = userDao.getQaUsersByTaskId(checkItem.getTaskId());
						setUserId(qas, qaIds);
					}					
					getUserAllCheckItemsAndSendMail(qaIds, Constants.PROJECT_ROLE_QA, list, type, currUser);					
				}else{
					for(int i = 0; i < list.size(); i++){
						CheckItemTo checkItem = list.get(i);
						List<ProjectUserTo> others = projectUserDao.getProjectUserByRoleName(checkItem.getProjectId(), role);
						setUserId(others, otherIds);
						List<ProjectMemberTo> mailList = projectMemberService.getMailProjectMembersByProjectId(checkItem.getProjectId());
						setUserId(mailList, otherIds);
					}
					
					getUserAllCheckItemsAndSendMail(otherIds, role, list, type, currUser);
				}				
			}
		}
		
		if(managerFlag != null){
			if(checkManager(managerFlag, Constants.PROJECT_ROLE_QA)){
				List<UserTo> qaManagers = userDao.getManagerByGroupId(Constants.USERGROUP_QA_NUM);
				setUserId(qaManagers, qaManagerIds);
				if(qaManagerIds.size() > 0){
					List<UserTo> users = userDao.getUserByIds(new ArrayList<Integer>(qaManagerIds));
					if(users != null && users.size() > 0){
						for(int i = 0; i < users.size(); i++){
							UserTo user = users.get(i);
							List<UserTo> uList = new ArrayList<UserTo>();
							uList.add(user);
							mail.sendMail(users, getNotificationSubject(type), getNotificationMsg(list, currUser));
						}
					}								
				}
			}
		}
	}	
	
	@SuppressWarnings("unchecked")
	private void getUserAllCheckItemsAndSendMail(HashSet<Integer> hashset, String role, List<CheckItemTo> checkItemList, String type, UserTo currUser){
		if(hashset.size() > 0){
			Iterator<Integer> iterator = hashset.iterator();
			while(iterator.hasNext()){
				List<CheckItemTo> checkItems = new ArrayList<CheckItemTo>();
				List<Integer> ids = new ArrayList<Integer>();
				ids.add(iterator.next());
				List<UserTo> users = userDao.getUserByIds(ids);
				
				if(users != null && users.size() > 0){
					UserTo user = users.get(0);
					
					for(int i = 0; i < checkItemList.size(); i++){
						CheckItemTo ci = checkItemList.get(i);
						List userList = new ArrayList();
						if(role.equals(Constants.PROJECT_ROLE_ARTIST)){
							userList = userDao.getResourceByTaskId(ci.getTaskId());
						}else if(role.equals(Constants.PROJECT_ROLE_LEAD)){
							if(user.getId() == ci.getOwnerId()){
								checkItems.add(ci);
							}
						}else if(role.equals(Constants.PROJECT_ROLE_QA)){
							userList = userDao.getQaUsersByTaskId(ci.getTaskId());							
						}else{
							userList = projectUserDao.getProjectUserByRoleName(ci.getProjectId(), role);
						}
						
						if(userList.size() > 0){
							if(isInList(user, userList)){
								checkItems.add(ci);
							}
						}											
					}
					
					if(users != null && users.size() > 0 && checkItems.size() > 0){
						mail.sendMail(users, getNotificationSubject(type), getNotificationMsg(checkItems, currUser));
					}					
				}
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private boolean isInList(UserTo user, List list){
		boolean result =false;
		int userId = user.getId();
		for(int i = 0; i < list.size(); i++){
			Object item = list.get(i);
			if(item instanceof UserTo){
				int uid = ((UserTo)item).getId();
				if(uid == userId){
					result =true;
					break;
				}
			}
			if(item instanceof ProjectUserTo){
				int uid = ((ProjectUserTo)item).getUserId();
				if(uid == userId){
					result = true;
					break;
				}
			}
		}
		return result;
	}
	
	private String getNotificationSubject(String type){
		return "<"+NormandyConfiguration.getNotificationTitle(type)+">";
	}
	
	@SuppressWarnings("unchecked")
	private String getNotificationMsg(List<CheckItemTo> checkItems, UserTo currUser){
		DefaultVelocityManager vm = new DefaultVelocityManager();
		HashMap ctxParam = new HashMap();		
		String msg = "";
		String type = checkItems.get(0).getStatus();
		
		ctxParam.put("checkItems", checkItems);
		ctxParam.put("currUser", currUser);
		
		if(type.equals(CheckItemEnum.getFixStatus())){
			for(int i = 0; i < checkItems.size(); i++){
				CheckItemTo checkItem = checkItems.get(i);
				List<FeedbackTo> feedbackList = feedbackDao.getFeedbackByCheckItemId(checkItem.getId());
				checkItem.setFeedbacks(feedbackList);
			}
		}
		
		msg = vm.getEncodedBody(NormandyConfiguration.getNotificationTemplate(type),
				NormandyConfiguration.getInstance().REFERENCE_URL,
				NormandyConfiguration.getInstance().ENCODING, ctxParam);
		
		return msg.toString();
	}
	
	@SuppressWarnings("unchecked")
	private void setUserId (List list, HashSet<Integer> ids){
		for(int i=0; i<list.size(); i++){
			Object item = list.get(i);
			
			if(item instanceof ProjectUserTo){
				ids.add(((ProjectUserTo)item).getUserId());				
			}
			else if(item instanceof UserTo){
				ids.add(((UserTo)item).getId());
			}else if(item instanceof ProjectMemberTo){
				ids.add(((ProjectMemberTo)item).getUserId());
			}
		}		
	}
	
	private String getNotificationSubject(String type, ActivityTo activity){
		return "<"+NormandyConfiguration.getNotificationTitle(type)+"> - ["+activity.getProjectName()+"] "+"["+activity.getTaskCategory()+"] "+activity.getName();
	}
	
	@SuppressWarnings("unchecked")
	private String getNotificationMsg(CheckItemTo checkItem, ActivityTo activity, UserTo currUser){
		DefaultVelocityManager vm = new DefaultVelocityManager();
		HashMap ctxParam = new HashMap();		
		String msg = "";
			
		ctxParam.put("activity", activity);		
		ctxParam.put("checkItem", checkItem);
		ctxParam.put("currUser", currUser);		
		
		if(checkItem.getStatus().equals(CheckItemEnum.getFixStatus())){
			List feedbackList = feedbackDao.getFeedbackByCheckItemId(checkItem.getId());
			ctxParam.put("feedbacks", feedbackList);
		}
		
		msg = vm.getEncodedBody(NormandyConfiguration.getNotificationTemplate(checkItem.getStatus()), 
				NormandyConfiguration.getInstance().REFERENCE_URL, NormandyConfiguration.getInstance().ENCODING, ctxParam);		

		return msg.toString();
	}
	
	private boolean checkManager(String flag, String manager){
		String[] arr = flag.split(";");
		
		for(int i=0; i<arr.length; i++){
			if(arr[i].toUpperCase().equals(manager.toUpperCase())){
				return true;
			}
		}
		
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public void createAccount(UserTo user, String password){
		List<UserTo> users = new ArrayList<UserTo>();
		users.add(user);
		
		DefaultVelocityManager vm = new DefaultVelocityManager();
		HashMap ctxParam = new HashMap();		
		
		ctxParam.put("user", user);		
		ctxParam.put("password", password);
		
		NormandyMail mail = new NormandyMail(); //The invoker userAssembler is not injected, so has to new a NormandyMail
		mail.sendMail(users, NormandyConfiguration.getNotificationTitle("ACCOUNT_CREATED"), 
				vm.getEncodedBody(NormandyConfiguration.getNotificationTemplate("ACCOUNT_CREATED"), 
				NormandyConfiguration.getInstance().ENCODING, ctxParam));		
	}
	
	@SuppressWarnings("unchecked")
	public void resetPassword(UserTo user, String password){
		List<UserTo> users = new ArrayList<UserTo>();
		users.add(user);
		
		DefaultVelocityManager vm = new DefaultVelocityManager();
		HashMap ctxParam = new HashMap();		
		
		ctxParam.put("user", user);		
		ctxParam.put("password", password);
		
		NormandyMail mail = new NormandyMail(); 
		mail.sendMail(users, NormandyConfiguration.getNotificationTitle("PASSWORD_RESET"), 
				vm.getEncodedBody(NormandyConfiguration.getNotificationTemplate("PASSWORD_RESET"), 
				NormandyConfiguration.getInstance().ENCODING, ctxParam));		
	}	
	
	// ==============================
	// Injected Variables
	// ==============================	
	private ProjectUserDao projectUserDao;
	private FeedbackDao	feedbackDao;
	private UserDao	userDao;
	private ActivityDao activityDao;
	
	private NormandyMail mail;
	
	private ProjectMemberService projectMemberService;



	public ProjectMemberService getProjectMemberService() {
		return projectMemberService;
	}

	public void setProjectMemberService(ProjectMemberService projectMemberService) {
		this.projectMemberService = projectMemberService;
	}

	/**
	 * @param projectUserDao the projectUserDao to set
	 */
	public void setProjectUserDao(ProjectUserDao projectUserDao) {
		this.projectUserDao = projectUserDao;
	}

	/**
	 * @param userDao the userDao to set
	 */
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	/**
	 * @param feedbackDao the feedbackDao to set
	 */
	public void setFeedbackDao(FeedbackDao feedbackDao) {
		this.feedbackDao = feedbackDao;
	}
	
	/**
	 * @param activityDao the activityDao to set
	 */
	public void setActivityDao(ActivityDao activityDao) {
		this.activityDao = activityDao;
	}		
	
	/**
	 * @param mail the mail to set
	 */
	public void setMail(NormandyMail mail) {
		this.mail = mail;
	}
}
