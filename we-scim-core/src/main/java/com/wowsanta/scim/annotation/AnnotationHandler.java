package com.wowsanta.scim.annotation;

import java.io.File;

import com.wowsanta.util.file.FileFindHandler;

public abstract class AnnotationHandler implements FileFindHandler{
	
	@Override
	public boolean condition(File file) {
		return file.getName().endsWith(".class");
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
