package com.wowsanta.scim.config;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

public class DomainKeyTypeAdapter extends TypeAdapter<Map<DomainKey,Configuration>> {

	@Override
	public void write(JsonWriter out, Map<DomainKey,Configuration> value) throws IOException {
		
		if(value != null) {
			out.beginObject();	
			Set<Entry<DomainKey,Configuration>> entry_set = value.entrySet();
			for (Entry<DomainKey, Configuration> entry : entry_set) {
				DomainKey 		key = entry.getKey();
				Configuration   cfg = entry.getValue();
				
				//TypeAdapter<DomainKey>	   key_adapter = ConfigurationBuilder.builder.create().getAdapter(DomainKey.class);
				TypeAdapter<Configuration> cfg_adapter = ConfigurationBuilder.builder.create().getAdapter(Configuration.class);
				
				StringBuffer buffer = new StringBuffer();
				buffer.append(key.getDomain()).append(":").append(key.getName());

				out.name(buffer.toString());
				cfg_adapter.write(out, cfg);
			}
			
			out.endObject();
		}
	}

	@Override
	public Map<DomainKey,Configuration> read(JsonReader in) throws IOException {
		Map<DomainKey,Configuration> map = new HashMap<DomainKey, Configuration>();
		
		in.beginObject();
        while ( in.hasNext() ) {
			TypeAdapter<Configuration> cfg_adapter = ConfigurationBuilder.builder.create().getAdapter(Configuration.class);
			
			DomainKey 		key = null;    
			Configuration 	cfg = null;
			
			JsonToken token = in.peek();
        	if(token.equals(JsonToken.NAME)) {
        		String next_name = in.nextName();
        		System.out.println("next_name : " +  next_name);
        		String[] key_value = next_name.split(":");
        		key = new DomainKey(key_value[0],key_value[1]);
        		System.out.println("key : " +  key);
        		
        		cfg = cfg_adapter.read(in);
        		System.out.println("cfg : " +  cfg);
        		
        		if(key != null && cfg != null) {
            		map.put(key,cfg);
            	}
        	}
        	
        }
        in.endObject();
        
		return map;
	}

	

}
