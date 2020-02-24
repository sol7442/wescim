package com.wowsanta.server;

import com.wowsanta.scim.config.ScimException;

public interface Server {
	
	public void initialize() throws ScimException;
	public void start();
	public void stop();
}
