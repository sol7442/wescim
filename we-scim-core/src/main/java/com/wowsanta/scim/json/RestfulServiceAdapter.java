package com.wowsanta.scim.json;

import java.io.IOException;
import java.util.Set;
import java.util.Map.Entry;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.wowsanta.scim.config.Configuration;
import com.wowsanta.scim.config.ConfigurationBuilder;
import com.wowsanta.scim.config.DomainKey;
import com.wowsanta.scim.service.RestfulService;

public class RestfulServiceAdapter extends TypeAdapter<RestfulService> {

	@Override
	public void write(JsonWriter out, RestfulService value) throws IOException {
//		if(value != null) {
//			out.beginObject();	
//			out.name(value.getName());
//			out.value(value.getMethod() + ":>" +  value.getUrl());			
//			out.endObject();
//		}
		out.value(value.getMethod() + ":>" +  value.getUrl());	
	}

	@Override
	public RestfulService read(JsonReader in) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

}
