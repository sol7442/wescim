package com.wowsanta.server.spark;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.wowsanta.scim.LOGGER;
import com.wowsanta.scim.config.ScimException;
import com.wowsanta.server.Server;

import lombok.Builder;
import lombok.Data;
import spark.Spark;

@Data
@Builder
public class SparkServer implements Server {

	private int port;
	private int maxThread;
	private int minThread;
	private int idleTime;
	
	private String keyStore;
	private String keyStorePw;
	private String trustStore;
	private String trustStorePw;
	
	private transient Map<String, List<SparkService>> services;
	
	public void registerService(String key, String value) throws ScimException{
		
		List<SparkService> service_list = null;
		if(services == null) {
			services  = new HashMap<String, List<SparkService>>();
			service_list = services.get(key);
			if(service_list == null) {
				service_list = new ArrayList<SparkService>();
				services.put(key, service_list);
			}
		}
		
		
		
		
		
		
		
		
	}
	
	public void build() throws ScimException {
//		Set<Entry<String,String>> service_entris = services.entrySet();
//		for (Entry<String, String> entry : service_entris) {
//			String service_name = entry.getKey();
//			String service_file = entry.getValue();
//			
//			
//			System.out.println("service_name  : " + service_name);
//			System.out.println("service_name  : " + service_file);
//			
//			
//		}
	}
	
	
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
