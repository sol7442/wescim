package test.wowsata.map;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.wowsanta.scim.ScimException;
import com.wowsanta.scim.config.Configuration;
import com.wowsanta.scim.config.ConfigurationBuilder;
import com.wowsanta.scim.config.DomainKey;
import com.wowsanta.scim.json.DomainKeyTypeAdapter;
import com.wowsanta.util.log.LOGGER;

import lombok.Data;

public class MapKeyTest {
	
	@Data
	public class Key{
		public Key(String domain, String name) {
			this.domain = domain;
			this.name   = name;
		}
		public String toString() {
			return domain + "@" + name;
		}
		public String domain;
		public String name;
	}
	public class Value{
		public Value(String type, Object data) {
			this.type = type;
			this.data  = data;
		}
		public String type;
		public Object data;
	}
	public class KeyMap{
		private Map<Key, Value> keyMap = new HashMap<MapKeyTest.Key, MapKeyTest.Value>();

		public void put(Key key, Value value) {
			keyMap.put(key, value);
		}
		
		public Value getValue(Key key) {
			return this.keyMap.get(key);
		}
	}
	
	public class ValueTypeAdapter extends TypeAdapter<Value>{

		@Override
		public void write(JsonWriter out, Value value) throws IOException {
			if(value != null && value.data != null) {
				out.beginObject();	

				out.name("type");
				out.value(value.data.getClass().getName());
				
				out.name("value");
				out.value(value.data.toString());
				
				out.endObject();
			}
		}

		@Override
		public Value read(JsonReader in) throws IOException {
			Value value = null;
			
			String type_name = null;
        	TypeAdapter<?> value_adapter = null;
			in.beginObject();
	        while ( in.hasNext() ) {
				JsonToken token = in.peek();
	        	if(token.equals(JsonToken.NAME)) {
	        		String next_name = in.nextName();
	        		System.out.println("next_name : " +  next_name);
	        		if(next_name.equals("type")) {
	        			String class_name = in.nextString();
	        			try {
							value_adapter = new GsonBuilder().create().getAdapter(Class.forName(class_name));
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						} 
	        		}
	        		else if (next_name.equals("value")) {
	        			System.out.println("value..... : " + value_adapter.getClass());
	        			if(value_adapter != null) {
	        				value = new Value(type_name, value_adapter.read(in));	
	        			}
	        		}
	        	}
	        }
	        in.endObject();
	        return value;
		}
		
	}
	public class KeyTypeAdapter extends TypeAdapter<Map<Key, Value>>{
		@Override
		public void write(JsonWriter out, Map<Key, Value> value) throws IOException {
			if(value != null) {
				out.beginObject();	
				Set<Entry<Key,Value>> entry_set = value.entrySet();
				for (Entry<Key,Value> entry : entry_set) {
					Key 	key = entry.getKey();
					Value   val = entry.getValue();
					
					TypeAdapter<Value> value_adapter = new GsonBuilder().create().getAdapter(Value.class);
					
					StringBuffer buffer = new StringBuffer();
					buffer.append(key.domain).append("@").append(key.name);

					out.name(buffer.toString());
					value_adapter.write(out, val);
				}
				
				out.endObject();
			}
			
		}

		@Override
		public Map<Key, Value> read(JsonReader in) throws IOException {
			
			Map<Key, Value> key_map = new HashMap<>();

			Key 	key = null;    
			Value 	val = null;
			
			GsonBuilder builder = new GsonBuilder();
			builder.registerTypeAdapter(Value.class,new ValueTypeAdapter());
			TypeAdapter<Value> value_adapter = builder.create().getAdapter(Value.class);
			
			in.beginObject();
			while ( in.hasNext() ) {
				JsonToken token = in.peek();
	        	if(token.equals(JsonToken.NAME)) {
	        		String next_name = in.nextName();
	        		String[] key_value = next_name.split("@");
	        		key = new Key(key_value[0],key_value[1]);
	        	}else {
	        		val = value_adapter.read(in);
	        		System.out.println("val : " +  val.data);
	        		if(key != null && val != null) {
	        			key_map.put(key,val);
	            	}
	        	}
	        }
	        in.endObject();
	        
	        return key_map;
		}
		
	}
	
	private final String file_name = "../config/test.mapkey.json";
	@Test
	public void save_test() {
		
		try (FileWriter writer = new FileWriter(file_name)) {
			GsonBuilder builder = new GsonBuilder();
			
			builder.registerTypeAdapter(Value.class,new ValueTypeAdapter());
			
			builder.setPrettyPrinting();
			Gson gson = builder.create();

			KeyMap key_map = new KeyMap();
			key_map.put(new Key("local.host","user"), new Value("type1",new Date()));
			key_map.put(new Key("www.domain","user"), new Value("type2",new Date()));
			
			
			LOGGER.system.info("================================== : {}",new Date());
			LOGGER.system.info("save configuration : {}", file_name);
			LOGGER.system.info("{}",gson.toJson(key_map));
			
			writer.write(gson.toJson(key_map));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void load_test() {
		File file = new File(file_name);
		try (JsonReader reader = new JsonReader(new FileReader(file))) {
			GsonBuilder builder = new GsonBuilder();
			
			Type type = new TypeToken<Map<Key, Value>>(){}.getType();
			builder.registerTypeAdapter(type, new KeyTypeAdapter());
			builder.registerTypeAdapter(Value.class,new ValueTypeAdapter());
			
			builder.setPrettyPrinting();
			Gson gson = builder.create();
			
			KeyMap key_map = gson.fromJson(reader, KeyMap.class);
			
			LOGGER.system.info("================================== : {}",new Date());
			LOGGER.system.info("load configuration : {}", file.getName());
			LOGGER.system.info("{}",gson.toJson(key_map));
			
			
			//System.out.println("value : " + key_map.getValue(new Key("local.host","user")).date.getClass());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
