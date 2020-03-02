package com.wowsanta.repository;

import lombok.*;

import com.wowsanta.util.security.Crypto;
import com.wowsanta.util.security.impl.WowsantaCypto_SEED_CBC;

@Data
@ToString(exclude="cyptoKey")
public class DataSource  {
	private transient final String cyptoKey = "1234wow!@wow1234"; 
	
	private String driver;
	private String url;
	private String user;

	@Setter(AccessLevel.NONE)
	@Getter(AccessLevel.NONE)
	private String password;
	private String encPw;

	public void setPassword(String pw) {
		Crypto cypto = new WowsantaCypto_SEED_CBC();
		cypto.addParam(WowsantaCypto_SEED_CBC.KEY, cyptoKey);
		try {
			this.encPw = cypto.encrypte(pw);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String getPassword() {
		if(encPw != null) {
			Crypto cypto = new WowsantaCypto_SEED_CBC();
			cypto.addParam(WowsantaCypto_SEED_CBC.KEY, cyptoKey);
			try {
				return cypto.decrypt(encPw);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			Crypto cypto = new WowsantaCypto_SEED_CBC();
			cypto.addParam(WowsantaCypto_SEED_CBC.KEY, cyptoKey);
			try {
				this.encPw = cypto.encrypte(password);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return password;
	}
	
}
