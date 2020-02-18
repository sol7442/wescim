package com.wowsanta.server.spark;

import java.util.ArrayList;
import java.util.List;

import com.wowsanta.server.Server;
import com.wowsanta.server.Service;

import lombok.Data;
import spark.Spark;

@Data
public class SparkServer implements Server {
		
	private int port;
	private int maxThreads;
	private int minThreads;
	private int idleTime;
	private String keystoreFile;
	private String keystorePassword;
	private String truststoreFile;
	private String truststorePassword;

	private List<Service> services = new ArrayList<Service>();
	
	@Override
	public void initialize() {
		Spark.port(port);
		Spark.threadPool(maxThreads,minThreads,idleTime);
		Spark.secure(keystoreFile, keystorePassword, truststoreFile, truststorePassword);
	}

	@Override
	public void start() {
		Spark.awaitInitialization();
	}

	@Override
	public void stop() {
		Spark.awaitStop();
	}
}
