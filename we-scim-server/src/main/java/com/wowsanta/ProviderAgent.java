package com.wowsanta;

import java.util.Map.Entry;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.google.gson.annotations.SerializedName;
import com.wowsanta.repository.RepositoryConfig;
import com.wowsanta.repository.RepositoryManager;
import com.wowsanta.repository.SessionFactory;
import com.wowsanta.scim.ScimException;
import com.wowsanta.scim.annotation.AnnotationHandler;
import com.wowsanta.scim.annotation.ENTITY;
import com.wowsanta.scim.config.Configuration;
import com.wowsanta.scim.config.ConfigurationBuilder;
import com.wowsanta.scim.config.Domain;
import com.wowsanta.scim.config.DomainKey;
import com.wowsanta.scim.type.SCIM_ENTITY;
import com.wowsanta.server.ServiceStructure;
import com.wowsanta.server.ServiceStructure.ServiceStructureBuilder;
import com.wowsanta.server.handler.impl.EntityHandler;
import com.wowsanta.server.handler.impl.ServiceHandler;
import com.wowsanta.server.spark.SparkServer;
import com.wowsanta.server.spark.SparkServiceConfig;
import com.wowsanta.util.log.LOGGER;

import lombok.Data;

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
		} catch (Exception e) {
			LOGGER.error("{} : ",e.getMessage(),e);
		}
	}

	public void addRepository(DomainKey key, Configuration config) {
		repositoris.put(key, config);
	}
	
	
	public void build() throws ScimException {
		ServiceStructureBuilder builder = ServiceStructure.builder()
				.setProperty(settings)
				.addAnnotationHandler(new EntityHandler())
				.addAnnotationHandler(new ServiceHandler());
		
		builder.build();
		
		RepositoryManager repositoryManager = RepositoryManager.getInstance();
		Set<Entry<DomainKey, Configuration>> repository_set = repositoris.entrySet();
		for (Entry<DomainKey, Configuration> entry : repository_set) {
			RepositoryConfig configuration = (RepositoryConfig) ConfigurationBuilder.load(entry.getValue());
			
			SessionFactory session_factory = configuration.build(ServiceStructure.getInstance().getEntitySet());
			repositoryManager.addRepository(entry.getKey(),session_factory);
			
			ServiceStructure.getInstance().addRepository(entry.getKey(), entry.getValue());
		}

		server = (SparkServer) ConfigurationBuilder.load(serverConfig);
		LOGGER.system.info("==SYSTEM STRUCTURE == \n{}",ConfigurationBuilder.toJson(ServiceStructure.getInstance()));
	}

	public void initialize() throws ScimException {
		server.initialize();
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
