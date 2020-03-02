package com.wowsanta.server.handler.impl;

import java.io.File;

import com.wowsanta.scim.annotation.ENTITY;
import com.wowsanta.server.handler.AnnotationHandler;
import com.wowsanta.service.ServiceStructure;

public class EntityHandler extends AnnotationHandler {

	@Override
	public boolean visit(File root, File file) {
		String class_name = createClassName(root,file);
		try {
			Class<?> clazz = Class.forName(class_name);
			ENTITY entity_annotation = (ENTITY) clazz.getAnnotation(ENTITY.class);
			if(entity_annotation != null) {
				System.out.println(" : " +  entity_annotation.domain());
				System.out.println(" : " +  entity_annotation.name());
				
				//ServiceStructure.getInstance().addEntity
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	protected String createClassName(File root, File file) {
		StringBuffer sb = new StringBuffer();
        String fileName = file.getName();
        sb.append(fileName.substring(0, fileName.lastIndexOf(".class")));
        file = file.getParentFile();
        while (file != null && !file.equals(root)) {
            sb.insert(0, '.').insert(0, file.getName());
            file = file.getParentFile();
        }
        return sb.toString();
	}
}
