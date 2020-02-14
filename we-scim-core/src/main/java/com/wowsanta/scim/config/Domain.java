package com.wowsanta.scim.config;

public class Domain {
	public enum MODE{
		SERVICE,DIRECT,EAI
	}
	
	public enum TYPE{
		RESOURCE,PROVIDER,CONSUMER
	}

	public String name;
	public boolean used;
	public MODE mode;
	public TYPE type;
	public String file;
	
}
