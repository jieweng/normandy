package com.pearl.normandy.sysConfig;

import java.util.List;
import java.util.Map;

import com.pearl.normandy.utils.NormandyConfiguration;

public class SysConfigService {
	@SuppressWarnings("unchecked")
	public Map<String,String> getSysConfig(){
		return	(Map<String, String>)sysConfigDao.getSysConfig();
	}
	
	@SuppressWarnings("unchecked")
	public List<SysConfig> getSysConfigList() throws Exception{
		List<SysConfig> list=(List<SysConfig>)sysConfigDao.getSysConfigList();
		return list;
	}
	
	public void updateSysValue(String key, String value) throws Exception{
		sysConfigDao.setValue(key, value);
	}
	
	public void updateSysConfig(List<SysConfig> configs) throws Exception{
		for(SysConfig config : configs){
			updateSysValue(config.getKey(), config.getValue());
		}
		NormandyConfiguration config = NormandyConfiguration.getInstance();
		Map<String,String> sysConfig = sysConfigDao.getSysConfig();
		config.MAIL_SMTP 			= sysConfig.get("mail.smtp");
		config.MAIL_PORT 			= Integer.valueOf(sysConfig.get("mail.port"));
		config.MAIL_USERNAME  		= sysConfig.get("mail.username");
		config.MAIL_PASSWORD  		= sysConfig.get("mail.password");
		config.MAIL_FROM_ADDR 		= sysConfig.get("mail.from.addr");
		config.MAIL_FROM_NAME 		= sysConfig.get("mail.from.name");	
		config.ERROR_MAIL_TO_ADDR	= sysConfig.get("error.mail.to.addr");
		config.ERROR_MAIL_TO_NAME	= sysConfig.get("error.mail.to.name");
		config.ACTIVITY_LOCKED_TIME    = Integer.valueOf(sysConfig.get("activity.locked.time"));
	}
	
	private SysConfigDao sysConfigDao;

	public SysConfigDao getSysConfigDao() {
		return sysConfigDao;
	}

	public void setSysConfigDao(SysConfigDao sysConfigDao) {
		this.sysConfigDao = sysConfigDao;
	}
}
