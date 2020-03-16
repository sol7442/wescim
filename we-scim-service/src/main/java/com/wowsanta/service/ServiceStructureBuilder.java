package com.wowsanta.service;

import java.util.Properties;
import java.util.Set;
import java.util.Map.Entry;

import com.wowsanta.repository.RepositoryConfig;
import com.wowsanta.repository.RepositoryManager;
import com.wowsanta.repository.SessionFactory;
import com.wowsanta.scim.ScimException;
import com.wowsanta.scim.annotation.AnnotationHandler;
import com.wowsanta.scim.config.Configuration;
import com.wowsanta.scim.config.ConfigurationBuilder;
import com.wowsanta.scim.config.DomainKey;
import com.wowsanta.util.file.FileFinder;

import lombok.Data;

@Data
public class ServiceStructureBuilder {
		private FileFinder classFileFinder = new FileFinder();
		private Set<Entry<DomainKey, Configuration>> repository_set;
		private Properties settings;
		
		public static ServiceStructureBuilder builder() {
			return new ServiceStructureBuilder();
		}
		public void build() throws ScimException {
			classFileFinder.run();
			
			RepositoryManager repositoryManager = RepositoryManager.getInstance();
			repositoryManager.setDefault(settings.getProperty("DOMAIN"),settings.getProperty("REPOSITORY"));
			
			for (Entry<DomainKey, Configuration> entry : repository_set) {
				RepositoryConfig configuration = (RepositoryConfig) ConfigurationBuilder.load(entry.getValue());
				SessionFactory session_factory = configuration.build(ServiceStructure.getInstance().getEntitySet());
				repositoryManager.addRepository(entry.getKey(),session_factory);
				ServiceStructure.getInstance().addRepository(entry.getKey(), entry.getValue());
			}
			
			ServiceStructure.getInstance().setProperty(this.settings);
			
		}
		public ServiceStructureBuilder setProperty(Properties settings) {
			String[] class_path_array = settings.getProperty(ServiceStructure.CLASSES).split(";");
			for (String class_path : class_path_array) {
				classFileFinder.addDirectory(class_path);	
			}
			
			this.settings = settings;
			return this;
		}
		public ServiceStructureBuilder addAnnotationHandler(AnnotationHandler annotationHandler) {
			classFileFinder.addHandler(annotationHandler);
			return this;
		}
		public ServiceStructureBuilder setRepository(Set<Entry<DomainKey, Configuration>> entrySet) {
			repository_set  = entrySet;
			return this;
		}

	
}
