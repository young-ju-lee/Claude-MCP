package com.kt.csai.global.util;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DirectoryUtil {
	@Value("${FILE.CHAT}")
	private String basePath;
	
	public boolean exists() {
		File dir = new File(basePath);
		return dir.exists();
	}
	
	public boolean initDirectory() {
		File dir = new File(basePath);
		return dir.mkdirs();
	}
}
