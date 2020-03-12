package com.wowsanta.scim.obj;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.wowsanta.scim.ScimException;
import com.wowsanta.scim.config.ConfigData;
import com.wowsanta.util.log.LOGGER;

import lombok.Data;

@Data
public class ScimObject extends ConfigData{
	private String fileName;
	private String implClss;
	public Object build() throws ScimException {
		if(implClss != null) {
			try {
				return Class.forName(implClss).cast(this);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
		}
		throw new ScimException("BUILDER NOT IMPLIMENT EXCEPTION : ");
	}
    
	public static class MapTypeAdapter implements JsonSerializer<Map<String, Object>>{
		@Override
		public JsonElement serialize(Map<String, Object> src, Type typeOfSrc, JsonSerializationContext context) {
			JsonObject json_map = new JsonObject();
			Set<Entry<String,Object>> entry_set = src.entrySet();
			for (Entry<String, Object> entry : entry_set) {
				String key   = entry.getKey();
				Object value = entry.getValue();
				
				if (value instanceof Integer) {
					Integer int_value = (Integer) value;
					json_map.add(key,new JsonPrimitive(int_value));
				}else if (value instanceof Double) {
					Double double_value = (Double) value;
					json_map.add(key,new JsonPrimitive(double_value.longValue()));
				}else if (value instanceof Long) {
					Long long_value = (Long) value;
					json_map.add(key,new JsonPrimitive(long_value));
				}else if(value instanceof Boolean) {
					Boolean boolean_value = (Boolean) value;
					json_map.add(key,new JsonPrimitive(boolean_value));
				}else if(value instanceof String) {
					String str_value = (String) value;
					json_map.add(key,new JsonPrimitive(str_value));
				}else {
					System.out.println("--- serialize failed value -----");
					System.out.println("key   : " + key);
					System.out.println("value : " + value);
					System.out.println("class : " + value.getClass().getName());
				}
			}
			return json_map;
		}
	}
	public static class ScimObjectTypeAdapter extends TypeAdapter<ScimObject>{

		@Override
		public void write(JsonWriter out, ScimObject value) throws IOException {
			System.out.println("---- ScimObject write--- ");
			out.beginObject();
				out.name("fileName").value(value.getFileName());
				out.name("implClss").value(value.getImplClss());
			out.endObject();
		}

		@Override
		public ScimObject read(JsonReader in) throws IOException {
			
			ScimObject object = null;
			System.out.println("---- ScimObject read--- ");
			in.beginObject();
			
			String fileName = "";
			String clssName = "";
			
			while(in.hasNext()) {
				String name = in.nextName();
				switch(name) {
				case "fileName":
					fileName = in.nextString();
					break;
				case "implClss":
					clssName = in.nextString();
					break;
				default :
					LOGGER.error("UNKOWN NAME : {} ",name);
				}
			}
			
			try {
				object = (ScimObject) load(Class.forName(clssName), fileName);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (ScimException e) {
				e.printStackTrace();
			}
			
			in.endObject();
			return object;
		}
		
	}
	public void save(String file_name) throws ScimException {
		this.implClss = this.getClass().getName();

		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(new TypeToken<Map<String, Object>>() {}.getType(), new MapTypeAdapter());
		builder.registerTypeAdapter(new TypeToken<ScimObject>() {}.getType(), new ScimObjectTypeAdapter());
		
				
		Gson gson = builder.disableHtmlEscaping().setPrettyPrinting().create();
		try (FileWriter writer = new FileWriter(file_name)) {
			LOGGER.system.info("================================== : {}",new Date());
			LOGGER.system.info("save configuration : {}", file_name);
			LOGGER.system.info("{}",this);
			
			writer.write(gson.toJson(this));
		} catch (Exception e) {
			throw new ScimException(e) ;
		}
	}
	
	public static <T> T load (Class<T> type, String file_name) throws ScimException {
		File file = new File(file_name);
		if(file.exists() && file.isFile()) {
			
			GsonBuilder builder = new GsonBuilder();
			builder.registerTypeAdapter(new TypeToken<Map<String, Object>>() {}.getType(), new MapTypeAdapter());
			builder.registerTypeAdapter(new TypeToken<ScimObject>() {}.getType(), new ScimObjectTypeAdapter());
			
			
			Gson gson = builder.disableHtmlEscaping().create();
			try (JsonReader reader = new JsonReader(new FileReader(file))) {
				T config = gson.fromJson(reader, type);
				
				LOGGER.system.info("================================== : {}",new Date());
				LOGGER.system.info("- load configuration : {}", file.getName());
				LOGGER.system.info("{}",config);
				
				return config;
			}catch (Exception e) {
				throw new ScimException(e);
			}
		}else {
			throw new ScimException("FILE NOT FOUND : " + file_name);
		}
	}
	
	public String toJson(boolean pretty) {
		
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(new TypeToken<Map<String, Object>>() {}.getType(), new MapTypeAdapter());
		builder.registerTypeAdapter(new TypeToken<ScimObject>() {}.getType(), new ScimObjectTypeAdapter());
		
		builder.registerTypeAdapter(new TypeToken<Map<String, Object>>() {}.getType(), new MapTypeAdapter());
		if(pretty) {
			builder.setPrettyPrinting();
		}
		return builder.create().toJson(this);
	}
}
