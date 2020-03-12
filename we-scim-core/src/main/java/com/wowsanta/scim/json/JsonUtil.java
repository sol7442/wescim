package com.wowsanta.scim.json;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wowsanta.scim.ScimException;

public class JsonUtil {
	public static GsonBuilder builder = new GsonBuilder();
	
	static public void init() {
		
	}
	static public <T> T load (Class<T> type, String file_name) throws ScimException{
		return null;
	}
	
	static public <T> T parse (Class<T> type, String json) throws ScimException{
		return null;
	}
	
	static public String toJson (Object obj) throws ScimException{
		Gson gson = builder.disableHtmlEscaping().setPrettyPrinting().create();
		try {
			return gson.toJson(obj);
		} catch (Exception e) {
			throw new ScimException(e) ;
		}
	}
	
	static public void save (Object obj, String file_name) throws ScimException{
		
	}
}
