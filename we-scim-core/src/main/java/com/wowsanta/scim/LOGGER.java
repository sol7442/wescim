package com.wowsanta.scim;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wowsanta.scim.config.ScimException;

public class LOGGER {
	public static Logger system 	= LoggerFactory.getLogger("system");
	public static Logger process 	= LoggerFactory.getLogger("process");
	public static Logger error 		= LoggerFactory.getLogger("error");
	
	public static ScimException error(Exception e) {
		Logger error = LoggerFactory.getLogger("error");
		error.error("{}",e.getMessage(),e);
		return new ScimException(e);
	}
	
	public static Logger getSystemLogger() {
		return LoggerFactory.getLogger("system");
	}
	public static Logger getProcessLogger() {
		return LoggerFactory.getLogger("process");
	}
	
}
