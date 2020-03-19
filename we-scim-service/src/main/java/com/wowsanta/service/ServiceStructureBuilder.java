package com.wowsanta.service;

import java.util.Set;

import com.wowsanta.scim.ScimException;
import com.wowsanta.scim.annotation.AnnotationHandler;
import com.wowsanta.scim.config.DomainKey;
import com.wowsanta.util.file.FileFinder;

import lombok.Data;

@Data
public class ServiceStructureBuilder {
		private FileFinder classFileFinder = new FileFinder();
		private Set<DomainKey> repositoris;
		
		public static ServiceStructureBuilder builder() {
			return new ServiceStructureBuilder();
		}
		public void build() throws ScimException {
			classFileFinder.run();
		}
		public ServiceStructureBuilder setClassPath(String path) {
			String[] class_path_array = path.split(";");
			for (String class_path : class_path_array) {
				classFileFinder.addDirectory(class_path);	
			}
			return this;
		}
		public ServiceStructureBuilder addAnnotationHandler(AnnotationHandler annotationHandler) {
			classFileFinder.addHandler(annotationHandler);
			return this;
		}
}
