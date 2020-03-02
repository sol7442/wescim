package com.wowsanta.util.file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileFinder {

	private List<File> directoris = new ArrayList<File>();
	private List<FileFindHandler> handlers = new ArrayList<FileFindHandler>();
	public void addDirectory(String file_name) {
		
		File directory = new File(file_name);
		if(directory.exists() && directory.isDirectory()) {
			this.directoris.add(directory);
		}else {
			System.out.println("is not directory : " + file_name);
		}
	}

	public void addHandler(FileFindHandler handler) {
		this.handlers.add(handler);
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
					return handler.visit(root, file);
				}
			}
		}
		
		return false;
	}
}
