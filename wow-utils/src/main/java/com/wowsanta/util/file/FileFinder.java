package com.wowsanta.util.file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.wowsanta.util.log.LOGGER;

public class FileFinder {

	private List<File> directoris = new ArrayList<File>();
	private List<FileFindHandler> handlers = new ArrayList<FileFindHandler>();
	public void addDirectory(String file_name) {
		
		File directory = new File(file_name);
		if(directory.exists() && directory.isDirectory()) {
			this.directoris.add(directory);
		}else {
			LOGGER.system.info("local path : {} " , System.getProperty("user.dir"));
			LOGGER.system.info("is not directory : {} ", file_name);
		}
	}

	public FileFinder addHandler(FileFindHandler handler) {
		this.handlers.add(handler);
		return this;
	}
	
	public void run() {
		for (File directory : directoris) {
			findFile(directory,directory);
		}
	}

	private boolean findFile(File root, File file) {
		if(file.isDirectory()) {
			for (File child : file.listFiles()) {
				if(findFile(root,child)) {
					return true;
				}
			}
		}else {
			for (FileFindHandler handler : handlers) {
				if(handler.condition(file)) {
					handler.visit(root, file);
				}
			}
		}
		
		return false;
	}
}
