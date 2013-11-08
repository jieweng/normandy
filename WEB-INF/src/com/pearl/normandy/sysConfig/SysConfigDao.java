package com.pearl.normandy.sysConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
public class SysConfigDao  extends SqlMapClientDaoSupport{
	
	public String getValue(String key){
		return getSysConfig().get(key);
	}
	
	@SuppressWarnings("unchecked")
	public List<SysConfig> getSysConfigList(){
		return (List<SysConfig>)this.getSqlMapClientTemplate().queryForList("SysConfig.select");
	}

	@SuppressWarnings("unchecked")
	public Map<String, String> getSysConfig(){
		Map<String, String> result = new HashMap<String, String>();
		List<SysConfig> cfgs = this.getSqlMapClientTemplate().queryForList("SysConfig.select");
		for (SysConfig cfg : cfgs ) {
			result.put(cfg.getKey(), cfg.getValue());
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public void setValue(String key, String value){
		Map param = new HashMap();
		param.put("key", key);
		param.put("value", value);
		this.getSqlMapClientTemplate().update("SysConfig.update", param);
	}
	
}
