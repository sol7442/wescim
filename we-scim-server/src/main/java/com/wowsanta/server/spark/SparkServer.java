package com.wowsanta.server.spark;

import java.util.HashMap;
import java.util.Map;

import com.wowsanta.scim.LOGGER;
import com.wowsanta.scim.config.Configuration;
import com.wowsanta.scim.config.ScimException;
import com.wowsanta.scim.obj.ScimObject;
import com.wowsanta.server.Server;
import com.wowsanta.server.Service;

import lombok.Builder;
import lombok.Data;
import spark.Spark;

@Data
@Builder
public class SparkServer extends ScimObject implements Server {
		
//	private int port;
//	private int maxThreads;
//	private int minThreads;
//	private int idleTime;
//	private String keystoreFile;
//	private String keystorePassword;
//	private String truststoreFile;
//	private String truststorePassword;
	
	public final static String KEY_STORE		= "KEY_STORE";
	public final static String KEY_STORE_PW	 	= "KEY_STORE_PW";
	public final static String TRUST_STORE 		= "TRUST_STORE";
	public final static String TRUST_STORE_PW 	= "TRUST_STORE_PW";
	
	private Map<String, Service> serviceMap = new HashMap<String, Service>();
	
	
	
	@Override
	public void initialize() {
		LOGGER.system.info(" -- SPARK SERVER : {}", "name");
		LOGGER.system.info("{}",this.toJson(true));
		
		System.out.println(MAX_THREAD);
		System.out.println(MAX_THREAD);
		Spark.port(getInt(KEY_STORE));
		Spark.threadPool(getInt(MAX_THREAD),getInt(MIN_THREAD),getInt(IDLE_TIMES));
		
		//Spark.secure(keystoreFile, keystorePassword, truststoreFile, truststorePassword);
	}

	@Override
	public void start() {
		Spark.awaitInitialization();
	}

	@Override
	public void stop() {
		Spark.awaitStop();
	}

	public SparkServer build() {
		return this;
	}
}
