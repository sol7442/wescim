package test.wowsanta.server;

import org.junit.Test;

import com.wowsanta.ProviderServer;
import com.wowsanta.scim.config.ScimException;

public class ServerConfigTest {

	
	@Test
	public void create_test() {
		ProviderServer server = null;
		try {
			server = new ProviderServer();
			server.save("../config/dev.provider.json");
		} catch (ScimException e) {
			e.printStackTrace();
		}
	}
}
