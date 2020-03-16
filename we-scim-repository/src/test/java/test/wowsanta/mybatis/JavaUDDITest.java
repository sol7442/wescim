package test.wowsanta.mybatis;

import java.util.UUID;

import org.junit.Test;

public class JavaUDDITest {
	@Test
	public void create_uddi_test() {
		UUID id = UUID.randomUUID();
		System.out.println(id.hashCode());
		System.out.println(id.toString());
	}
}
