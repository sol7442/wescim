package com.wowsanta.server;

public interface Server {
	public final static String PORT		  = "PORT";
	public final static String MAX_THREAD = "MAX_THREAD";
	public final static String MIN_THREAD = "MIN_THREAD";
	public final static String IDLE_TIMES = "IDLE_TIMES";
	
	public void initialize();
	public void start();
	public void stop();
}
