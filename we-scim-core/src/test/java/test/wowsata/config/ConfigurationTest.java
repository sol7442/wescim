package test.wowsata.config;

import java.io.IOException;
import java.io.StringWriter;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonWriter;
import com.wowsanta.scim.ScimException;
import com.wowsanta.scim.config.Configuration;

public class ConfigurationTest {

	@Test
	public void save_test() {
		Configuration config = new Configuration("",ConfigurationTest.class);
		
		
		Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
		
		
		TypeAdapter<Configuration> adapter = gson.getAdapter(Configuration.class);
		
		System.out.println(gson.toJson(config));
		System.out.println("------->S");
		StringWriter writer = new StringWriter();
		
		try {
			JsonWriter jsonWriter = new JsonWriter(writer);
			adapter.write(jsonWriter, config);
			jsonWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			System.out.println(writer.toString());
		}
		
		//adapter.toJson(config);
		System.out.println("------->>");
		System.out.println(gson.toJson(config));
				
	}
}
