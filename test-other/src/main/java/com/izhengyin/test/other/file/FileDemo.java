package com.izhengyin.test.other.file;

import java.io.File;

public class FileDemo {
	
	static String  filename = "/tmp/path/to/file.log";
	
	public static void main(String[] args) {
		
		
		
		
		File f = new File(filename);
		
		
		System.out.println(f.exists());
		
		System.out.println(f.getPath());
		
		System.out.println(f.getParent());
		
		System.out.println(f.getParentFile());
		
		System.out.println(f.isDirectory());
		
		System.out.println(f.canWrite());
		
		System.out.println("========");
		
		String[] dirs = filename.split("/");
		
		mkdir(dirs);
		
	}
	
	public static boolean mkdir(String[] dirs){
		
		String dirname = "/";
		
		for (int i = 0; i < dirs.length; i++) {
			
			System.out.println(dirs[i]);
			
			if(dirs[i].isEmpty()) continue;
			
			dirname = dirname+dirs[i]+"/";
			
			System.out.println(dirname);
		
			File f = new File(dirname);
			
			if(!f.isDirectory()){
				if(!f.mkdir()){
					return false;
				}
			}
			
		}
		return true;
	}
}
