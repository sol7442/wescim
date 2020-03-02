package com.wowsanta.server.spark;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.wowsanta.scim.LOGGER;
import com.wowsanta.scim.ScimException;
import com.wowsanta.scim.config.Configuration;
import com.wowsanta.scim.config.ConfigurationBuilder;
import com.wowsanta.server.Server;

import lombok.Builder;
import lombok.Data;
import spark.Spark;

@Data
public class SparkServer implements Server {

	private int port;
	private int maxThread;
	private int minThread;
	private int idleTime;
	
	private String keyStore;
	private String keyStorePw;
	private String trustStore;
	private String trustStorePw;
	
//	public static class SparkServerBuilder extends ConfigurationBuilder{
//		public SparkServer build(String file_name) throws ScimException {
//			return load(SparkServer.class, file_name);
//		}
//	}

	public Configuration save(String file_name) throws ScimException {
		return ConfigurationBuilder.save(this, file_name);
	}
	
	public void registerService(String key, String value) throws ScimException{
		
	}
//	
//	public void build() throws ScimException {
////		Set<Entry<String,String>> service_entris = services.entrySet();
////		for (Entry<String, String> entry : service_entris) {
////			String service_name = entry.getKey();
////			String service_file = entry.getValue();
////			
////			
////			System.out.println("service_name  : " + service_name);
////			System.out.println("service_name  : " + service_file);
////			
////			
////		}
//	}
	
	
	@Override
	public void initialize() throws ScimException{
		LOGGER.system.info(" -- SPARK SERVER INITIALIZE --- : {}", "name");
		LOGGER.system.info("{}",this);
		
		Spark.port(this.port);
		Spark.threadPool(maxThread,minThread,idleTime);
		 
		//Spark.secure(keystoreFile, keystorePassword, truststoreFile, truststorePassword);
	}
	
//	public void addService(String name, String file) {
//		if(this.services == null) {
//			this.services = new HashMap<String, String>();
//		}
//		this.services.put(name,file);
//	}

	@Override
	public void start() {
		Spark.awaitInitialization();
	}

	@Override
	public void stop() {
		Spark.awaitStop();
	}





}
