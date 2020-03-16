package test.wowsanta.type;

import org.junit.Test;

import com.wowsanta.scim.SCIM_EXT_SCHEMA_TYPES;
import com.wowsanta.scim.type.SCIM_SCHEMA_TYPES;

public class ConstTypeTest {

	@Test
	public void type_list_test () {
		
		System.out.println(SCIM_EXT_SCHEMA_TYPES.SCIM_USER_SCHEMA);
		System.out.println(SCIM_SCHEMA_TYPES.SCIM_USER_SCHEMA_URI);
		System.out.println(SCIM_EXT_SCHEMA_TYPES.SCIM_USER_SCHEMA_URI);
		
		
		System.out.println(SCIM_SCHEMA_TYPES.SCIM_USER_SCHEMA  + " : " + SCIM_SCHEMA_TYPES.SCIM_USER_SCHEMA_URI.equals(SCIM_EXT_SCHEMA_TYPES.SCIM_USER_SCHEMA_URI));
		System.out.println(SCIM_SCHEMA_TYPES.SCIM_GROUP_SCHEMA + " : " + SCIM_SCHEMA_TYPES.SCIM_GROUP_SCHEMA_URI.equals(SCIM_EXT_SCHEMA_TYPES.SCIM_GROUP_SCHEMA_URI));
	}
}
