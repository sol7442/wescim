package com.wowsanta.util.security.impl;

import java.util.HashMap;
import java.util.Map;

import com.wowsanta.util.security.Crypto;

import lombok.Data;

@Data
public class WowsantaCypto_SEED_CBC extends encryptSEED implements Crypto {
	public static final String IV 	= "IV";
	public static final String KEY 	= "KEY";
	
	private Map<String,String> params = new HashMap<String, String>();

	@Override
	public void addParam(String key, String value) {
		this.params.put(key,value);
	}

	@Override
	public String encrypte(String pTxt) throws Exception {
		return encrypt(pTxt, params.get(KEY));
	}
	

	@Override
	public String decrypt(String eTxt) throws Exception {
		return decrypt(eTxt, params.get(KEY));
	}

}
