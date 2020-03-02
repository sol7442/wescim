package com.wowsanta.scim.config;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.wowsanta.scim.ScimException;
import com.wowsanta.util.log.LOGGER;

import lombok.Data;

@Data
public class ConfigurationBuilder {
	
	public static Object load(Configuration config)throws ScimException {
		try {
			Class<?> type = Class.forName(config.getImplClss());
			String file_name = config.getFileName();
			
			return load(type,file_name);
		} catch (ClassNotFoundException e) {
			throw new ScimException(e);
		}
		
	}
	
	public static <T> T load (Class<T> type, String file_name) throws ScimException {
		T config;
		File file = new File(file_name);
		if(file.exists() && file.isFile()) {
			Gson gson = new GsonBuilder().disableHtmlEscaping().create();
			try (JsonReader reader = new JsonReader(new FileReader(file))) {
				config = gson.fromJson(reader, type);
				
				LOGGER.system.info("================================== : {}",new Date());
				LOGGER.system.info("load configuration : {}", file.getName());
				LOGGER.system.info("{}",toJson(config));
				
				return config;
			} catch (Exception e) {
				throw new ScimException(e);
			}
		}else {
			throw new ScimException("FILE NOT FOUND : " + file_name);
		}
	}
	
	public static Configuration save(Object config, String file_name) throws ScimException {
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.disableHtmlEscaping().setPrettyPrinting().create();
		gson = builder.create();
		try (FileWriter writer = new FileWriter(file_name)) {
			LOGGER.system.info("================================== : {}",new Date());
			LOGGER.system.info("save configuration : {}", file_name);
			LOGGER.system.info("{}",config);
			
			writer.write(gson.toJson(config));
		} catch (Exception e) {
			throw new ScimException(e) ;
		}
		
		return new Configuration(file_name, config.getClass());
	}
	
	public static String toJson(Object config) throws ScimException {
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.disableHtmlEscaping().setPrettyPrinting().create();
		try {
			return gson.toJson(config);
		} catch (Exception e) {
			throw new ScimException(e) ;
		}
	}
}
