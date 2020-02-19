package com.wowsanta.scim.config;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.wowsanta.scim.LOGGER;

import lombok.Data;

@Data
public class ConfigurationFactory {
	private String name;
	private String implClass;
	private String fileName;
	
	public ConfigurationFactory load () throws ScimException {
		File file = new File(fileName);
		if(file.exists() && file.isFile()) {
			try {
				Class<?> type = Class.forName(implClass);
				return (ConfigurationFactory) load(type, file);
				
			} catch (ClassNotFoundException e) {
				throw new ScimException(e);
			}
		}else {
			throw new ScimException("FILE NOT FOUND : " + fileName);
		}
	}
	
	public static <T> T load (Class<T> type, String file_name) throws ScimException {
		File file = new File(file_name);
		if(file.exists() && file.isFile()) {
			return load(type, file);
		}else {
			throw new ScimException("FILE NOT FOUND : " + file_name);
		}
	}
	
	public static <T>T load (Class<T> type, File file) throws ScimException {
		T config;
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		try (JsonReader reader = new JsonReader(new FileReader(file))) {
			config = gson.fromJson(reader, type);
			
			LOGGER.system.info("================================== : {}",new Date());
			LOGGER.system.info("- load configuration : {}", file.getName());
			LOGGER.system.info("{}",config);
			
			return config;
		} catch (Exception e) {
			throw new ScimException(e);
		}
	}

	public void save(String file_name) throws ScimException {
		this.fileName 	= file_name;
		this.implClass 	= this.getClass().getName();
		
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.disableHtmlEscaping().setPrettyPrinting().create();
		gson = builder.create();
		try (FileWriter writer = new FileWriter(file_name)) {
			LOGGER.system.info("================================== : {}",new Date());
			LOGGER.system.info("save configuration : {}", file_name);
			LOGGER.system.info("{}",this);
			
			writer.write(gson.toJson(this));
		} catch (Exception e) {
			throw new ScimException(e) ;
		}
	}
//	
//	public void save(String file_name, Class<?> class1, Class<?> class2) throws ScimException {
//		GsonBuilder builder = new GsonBuilder();
//		Gson gson = builder.disableHtmlEscaping().setPrettyPrinting().create();
//		builder.registerTypeHierarchyAdapter(class1, gson.getAdapter(class2));
//		gson = builder.create();
//		try (FileWriter writer = new FileWriter(file_name)) {
//			LOGGER.system.info("================================== : {}",new Date());
//			LOGGER.system.info("save configuration : {}", file_name);
//			LOGGER.system.info("{}",this);
//			
//			writer.write(gson.toJson(this));
//		} catch (Exception e) {
//			throw new ScimException(e) ;
//		}
//		
//	}
//	public void save(String file_name, Class<?> type) throws ScimException{
//		Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
//		try (FileWriter writer = new FileWriter(file_name)) {
//			LOGGER.system.info("================================== : {}",new Date());
//			LOGGER.system.info("save configuration : {}", file_name);
//			LOGGER.system.info("{}",this);
//			
//			writer.write(gson.toJson(this,type));
//		} catch (Exception e) {
//			throw new ScimException(e) ;
//		}
//	}
	public String toJson(boolean pretty) {
		GsonBuilder builder = new GsonBuilder().disableHtmlEscaping();//
		if(pretty) {
			builder.setPrettyPrinting();
		}
		return builder.create().toJson(this);
	}
}
