package com.andy.security.entity;

/**
 * @author Leone
 * @since: 2018-01-07 13:18
 **/
public class FileInfo {

	private String path;

	
	public FileInfo(String path){
		this.path = path;
	}
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
}
