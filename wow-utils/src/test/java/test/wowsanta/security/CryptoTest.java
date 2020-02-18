package test.wowsanta.security;

import org.junit.Test;

import com.wowsanta.util.security.Crypto;
import com.wowsanta.util.security.impl.WowsantaCypto_SEED_CBC;

public class CryptoTest {

	@Test
	public void enc_test() {
		Crypto crypto = new WowsantaCypto_SEED_CBC();
		crypto.addParam(WowsantaCypto_SEED_CBC.KEY,"1234123412341234");
		
		try {
			String pln_text = "test value sss";
			String enc_text = crypto.encrypte(pln_text);
			String dec_text = crypto.decrypt(enc_text);
			
			System.out.println("pln_text : " + pln_text);
			System.out.println("enc_text : " + enc_text);
			System.out.println("dec_text : " + dec_text);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
