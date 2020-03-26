package com.wowsanta.scim;

import java.lang.reflect.Type;
import java.util.Map;

import com.google.gson.reflect.TypeToken;
import com.wowsanta.ProviderAgent;
import com.wowsanta.scim.config.Configuration;
import com.wowsanta.scim.config.ConfigurationBuilder;
import com.wowsanta.scim.config.DomainKey;
import com.wowsanta.scim.json.DomainKeyTypeAdapter;
import com.wowsanta.scim.json.RestfulServiceAdapter;
import com.wowsanta.scim.service.RestfulService;


public class Provider {

	public static void main(String[] args) {
		try {
//			Type type = new TypeToken<Map<DomainKey, Configuration>>(){}.getType();
//			ConfigurationBuilder.builder.registerTypeAdapter(type, new DomainKeyTypeAdapter());
//			
//			Type type2 = new TypeToken<RestfulService>(){}.getType();
//			ConfigurationBuilder.builder.registerTypeAdapter(type2, new RestfulServiceAdapter());
			ProviderAgent agent = ConfigurationBuilder.load(ProviderAgent.class, "./config/dev.provider.json");
			
			agent.build()
			.initialize()
			.start();
			
		} catch (ScimException e) {
			e.printStackTrace();
		}
	}

}
