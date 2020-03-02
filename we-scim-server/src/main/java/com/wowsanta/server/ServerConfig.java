package com.wowsanta.server;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.wowsanta.scim.LOGGER;
import com.wowsanta.scim.ScimException;

import lombok.Data;

@Data
public class ServerConfig {

	public String implClss;
	public String PORT		  = "PORT";
	public String MAX_THREAD  = "MAX_THREAD";
	public String MIN_THREAD  = "MIN_THREAD";
	public String IDLE_TIMES  = "IDLE_TIMES";
	
	public String KEY_STORE			= "KEY_STORE";
	public String KEY_STORE_PW	 	= "KEY_STORE_PW";
	public String TRUST_STORE 		= "TRUST_STORE";
	public String TRUST_STORE_PW 	= "TRUST_STORE_PW";
	
	
	@SuppressWarnings("unchecked")
	public <T> T build (Class<T> classOf) throws ScimException {
		try {
			Class<?> clazz = Class.forName(this.implClss);
			Gson gson = new GsonBuilder().create();
			return (T) gson.fromJson(gson.toJson(this), clazz);
		} catch (ClassNotFoundException e) {
			throw new ScimException(e);
		}
	}
	
	public void save(String file_name) throws ScimException {
		this.implClss = this.getClass().getName();
		
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.disableHtmlEscaping().setPrettyPrinting().create();
		try (FileWriter writer = new FileWriter(file_name)) {
			LOGGER.system.info("================================== : {}",new Date());
			LOGGER.system.info("save configuration : {}", file_name);
			LOGGER.system.info("{}",this);
			
			writer.write(gson.toJson(this));
		} catch (Exception e) {
			throw new ScimException(e) ;
		}
	}
	public static <T> T load (Class<T> type, String file_name) throws ScimException {
		File file = new File(file_name);
		if(file.exists() && file.isFile()) {
			Gson gson = new GsonBuilder().disableHtmlEscaping().create();
			try (JsonReader reader = new JsonReader(new FileReader(file))) {
				T config = gson.fromJson(reader, type);
				
				LOGGER.system.info("================================== : {}",new Date());
				LOGGER.system.info("- load configuration : {}", file.getName());
				LOGGER.system.info("{}",config);
				
				return config;
			}catch (Exception e) {
				throw new ScimException(e);
			}
		}else {
			throw new ScimException("FILE NOT FOUND : " + file_name);
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
