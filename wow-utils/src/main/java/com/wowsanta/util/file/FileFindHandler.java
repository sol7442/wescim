package com.wowsanta.util.file;

import java.io.File;

public interface FileFindHandler {
	public boolean visit(File root, File file);
	public boolean condition(File file); 
}
