package com.wowsanta.scim.config;

import java.util.HashMap;
import java.util.Map;


public class ConfigData {
	protected Map<String,Object> settings;
	
	public void add(String key, Object value) {
		if(this.settings == null) {
			this.settings = new HashMap<String,Object>();
		}
		this.settings.put(key,value);
	}
	public Object get(String key) {
		if(this.settings !=null) {
			return this.settings.get(key);
		}
		return null;
	}
	public int getInt(String key) {
		return getInt(key,0);
	}
	public int getInt(String key, int def_value) {
		Object object = get(key);
		if (object instanceof Integer) {
			Integer value = (Integer) object;
			return value.intValue();
		}
		return def_value;
	}
}
