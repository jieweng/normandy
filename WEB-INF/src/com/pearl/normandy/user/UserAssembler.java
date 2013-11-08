package com.pearl.normandy.user;

import com.pearl.normandy.utils.PasswordUtil;
import com.pearl.normandy.core.mail.NormandyNotification;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import flex.data.assemblers.AbstractAssembler;
import flex.messaging.io.ArrayList;

public class UserAssembler extends AbstractAssembler {

	static Logger log = Logger.getLogger(UserAssembler.class.getName());
	private NormandyNotification notification = new NormandyNotification();

	public Collection fill(List fillArgs) {
		List<UserTo> result = null;

		try {
			result = userDao.getAllUsers();
		} catch (Exception e) {
			log.error("Error in fill: ", e);
		}
		return result;
	}

	public Object getItem(Map identity) {
		List<UserTo> result = null;
		UserTo item = null;

		try {
			ArrayList param = new ArrayList();
			param.add((Integer) identity.get("userId"));
			result = userDao.getUserByIds(param);

			if (result.size() > 0) {
				item = (UserTo) result.get(0);
			}
		} catch (Exception e) {
			log.error("Error in getItem: ", e);
		}

		return item;
	}

	public void createItem(Object item) {
		try {
			UserTo newUser = (UserTo) item;
			String password = PasswordUtil.genRandom(6);			
			newUser.setPasswordHash(PasswordUtil.encrypt(password));
			userDao.create(newUser);
			
			notification.createAccount(newUser, password);
		} catch (Exception e) {
			log.error("Error in createItem:", e);
		}
	}

	public void updateItem(Object newVersion, Object prevVersion, List changes) {
		try {
			userDao.update((UserTo) newVersion);
		} catch (Exception e) {
			log.error("Error in updateItem:", e);
		}
	}

	public void deleteItem(Object item) {
	}

	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
}
