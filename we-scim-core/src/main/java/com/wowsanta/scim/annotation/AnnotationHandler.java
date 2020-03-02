package com.wowsanta.scim.annotation;

import java.io.File;

import com.wowsanta.scim.config.ServiceStructure;
import com.wowsanta.util.file.FileFindHandler;

public abstract class AnnotationHandler implements FileFindHandler{
	
	@Override
	public boolean condition(File file) {
		return file.getName().endsWith(".class");
	}

}
