package com.wowsanta;

import java.util.Map.Entry;
import java.io.File;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.wowsanta.handler.DomainEntityHandler;
import com.wowsanta.handler.ScimEntityHandler;
import com.wowsanta.handler.ServiceHandler;
import com.wowsanta.repository.RepositoryConfig;
import com.wowsanta.repository.RepositoryManager;
import com.wowsanta.repository.SessionFactory;
import com.wowsanta.scim.ScimException;
import com.wowsanta.scim.annotation.AnnotationHandler;
import com.wowsanta.scim.annotation.SCIM_ENTITY;
import com.wowsanta.scim.config.Configuration;
import com.wowsanta.scim.config.ConfigurationBuilder;
import com.wowsanta.scim.config.Domain;
import com.wowsanta.scim.config.DomainKey;
import com.wowsanta.scim.json.DomainKeyTypeAdapter;
import com.wowsanta.scim.json.RestfulServiceAdapter;
import com.wowsanta.scim.service.RestfulService;
import com.wowsanta.server.spark.SparkServer;
import com.wowsanta.server.spark.SparkServiceConfig;
import com.wowsanta.service.ServiceStructure;
import com.wowsanta.service.ServiceStructureBuilder;
import com.wowsanta.util.log.LOGGER;

import lombok.Data;
import spark.Spark;

@Data
public class ProviderAgent {

	@SerializedName(value = "server")
	private Configuration serverConfig;
	
	private Map<DomainKey,Configuration> services = new HashMap<DomainKey, Configuration>();
	private Map<DomainKey,Configuration> repositoris = new HashMap<DomainKey, Configuration>();

	
	@SerializedName(value = "fiter")
	private Configuration filterConfig;
	private Properties settings;
	private String classDirectory;
	private List<String> distLibFiles;

	private transient SparkServer server;
	
	public Configuration save(String file_name) throws ScimException {
		return ConfigurationBuilder.save(this, file_name);
	}
	
	public static void main(String[] args) {
		try {
			String config_file 	= System.getProperty("server.config");
			
			config_file = "../config/dev.provider.json";
			Type type = new TypeToken<Map<DomainKey, Configuration>>(){}.getType();
			ConfigurationBuilder.builder.registerTypeAdapter(type, new DomainKeyTypeAdapter());
			
			Type type2 = new TypeToken<RestfulService>(){}.getType();
			ConfigurationBuilder.builder.registerTypeAdapter(type2, new RestfulServiceAdapter());
			
			ProviderAgent provider = ConfigurationBuilder.load(ProviderAgent.class, config_file);
			
			provider.build();
			provider.initialize();
			provider.start();
			
		} catch (Exception e) {
			LOGGER.error("{} : ",e.getMessage(),e);
		}
	}

	public void addRepository(DomainKey key, Configuration config) {
		repositoris.put(key, config);
	}
	
	
	public ProviderAgent build() throws ScimException {
		
		String class_path = settings.getProperty(ServiceStructure.CLASSES);
		
		ServiceStructureBuilder builder = ServiceStructureBuilder.builder()
				.setClassPath(class_path)
				.addAnnotationHandler(new ScimEntityHandler(ServiceStructure.getInstance()))
				.addAnnotationHandler(new DomainEntityHandler(ServiceStructure.getInstance()))
				.addAnnotationHandler(new ServiceHandler(ServiceStructure.getInstance()));
		builder.build();
		
		
		
		RepositoryManager rep_mgr = RepositoryManager.getInstance();
		for (Entry<DomainKey, Configuration> entry : repositoris.entrySet()) {
			RepositoryConfig configuration = (RepositoryConfig) ConfigurationBuilder.load(entry.getValue());
			SessionFactory session_factory = configuration.build(ServiceStructure.getInstance().getEntitySet());
			rep_mgr.addRepository(entry.getKey(),session_factory);
			
			ServiceStructure.getInstance().addRepository(entry.getKey(), entry.getValue());
		}
		rep_mgr.setDefault(this.settings.getProperty("DOMAIN"), this.settings.getProperty("REPOSITORY"));
		
		
		
		ServiceStructure.getInstance().setProperty(this.settings);

		StringBuffer buffer = new StringBuffer();
		buffer.append(settings.getProperty("HOME"));
		buffer.append(File.separator);
		buffer.append("structer");
		buffer.append(new Date().getTime());
		buffer.append(".json");
		
		String structer_file_name = buffer.toString();
		ConfigurationBuilder.save(ServiceStructure.getInstance(),structer_file_name);
		
		server = (SparkServer) ConfigurationBuilder.load(serverConfig);
		return this;
	}

	public ProviderAgent initialize() throws ScimException {
		server.initialize();
		return this;
	}

	public void start() {
		server.start();
	}
//	
//	public void addService(ServiceBuilder builder) {
//		if(services == null) {
//			services = new HashMap<String, String>();
//		}
//		
//		services.put(builder.getName(),builder.getFileName());
//	}
}
