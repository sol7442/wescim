package com.wowsanta.scim.config;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.wowsanta.scim.LOGGER;

public class ConfigurationFactory {
	
	public static <T> T load (Class<T> type, String file_name) throws ScimException {
		T obj;
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		try (JsonReader reader = new JsonReader(new FileReader(file_name))) {
			obj = gson.fromJson(reader, type);
			
			LOGGER.system.info("================================== : {}",new Date());
			LOGGER.system.info("- load configuration : {}", file_name);
			LOGGER.system.info("{}",obj);
			
		} catch (Exception e) {
			throw LOGGER.error(e) ;
		}
		
		return obj;
	}
	
	public static void save(Object obj, String file_name) throws ScimException{
		Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
		try (FileWriter writer = new FileWriter(file_name)) {
			LOGGER.system.info("================================== : {}",new Date());
			LOGGER.system.info("save configuration : {}", file_name);
			LOGGER.system.info("{}",obj);
			
			writer.write(gson.toJson(obj));
		} catch (Exception e) {
			throw LOGGER.error(e) ;
		}
	}
	  
	public String toJson(boolean pretty) {
		GsonBuilder builder = new GsonBuilder().disableHtmlEscaping();//
		if(pretty) {
			builder.setPrettyPrinting();
		}
		return builder.create().toJson(this);
	}
}
