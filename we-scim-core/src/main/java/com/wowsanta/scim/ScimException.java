package com.wowsanta.scim;

public class ScimException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = -279719751199128888L;

	public ScimException(Throwable e) {
		super(e.getMessage(),e);
	}

	public ScimException(String msg) {
		super(msg);
	}
}
