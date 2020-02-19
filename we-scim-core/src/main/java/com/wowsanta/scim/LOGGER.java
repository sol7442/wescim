package com.wowsanta.scim;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LOGGER {
	public static Logger system 	= LoggerFactory.getLogger("system");
	public static Logger process 	= LoggerFactory.getLogger("process");
	public static Logger error 		= LoggerFactory.getLogger("error");
	
	public static void error(String msg, Object...objects ) {
		error.error(msg,objects);
	}
	
}
