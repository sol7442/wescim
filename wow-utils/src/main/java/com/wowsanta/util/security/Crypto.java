package com.wowsanta.util.security;

public interface Crypto {
	public String encrypte(String pTxt)  throws Exception;
	public String decrypt(String eTxt) throws Exception;
	public void addParam(String key, String value);
}
