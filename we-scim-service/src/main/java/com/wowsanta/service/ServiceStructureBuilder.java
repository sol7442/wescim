package com.wowsanta.service;

import java.util.Properties;

import com.wowsanta.scim.ScimException;
import com.wowsanta.scim.annotation.AnnotationHandler;
import com.wowsanta.scim.json.JsonUtil;
import com.wowsanta.util.file.FileFinder;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ServiceStructureBuilder extends FileFinder {
		
		private Properties settings;
		
		public static ServiceStructureBuilder builder() {
			return new ServiceStructureBuilder();
		}
		public ServiceStructure build() throws ScimException {
			ServiceStructure structure = ServiceStructure.getInstance();
			structure.setProperty(settings);
			setClassPath(settings.getProperty("CLASSES"));
			
			addHandler(structure)
			.run();
			
			return structure;
		}
		public ServiceStructureBuilder setSettings(Properties settings) {
			this.settings = settings;
			return this;
		}
		public ServiceStructureBuilder setClassPath(String path) {
			String[] class_path_array = path.split(";");
			for (String class_path : class_path_array) {
				addDirectory(class_path);	
			}
			return this;
		}
		public ServiceStructureBuilder addAnnotationHandler(AnnotationHandler annotationHandler) {
			addHandler(annotationHandler);
			return this;
		}
}
