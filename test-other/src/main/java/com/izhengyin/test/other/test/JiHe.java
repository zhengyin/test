package com.izhengyin.test.other.test;

import java.util.Calendar;
import java.util.HashMap;


public class JiHe {
	
	private static HashMap<String, Integer> tokens = new HashMap<String, Integer>();
	
	public static void main(StringTest[] args) {
		
		tokens.put("a",1);
		tokens.put("a",2);
		
		System.out.println(tokens.get("a"));
		
		Calendar calendar = Calendar.getInstance();
		
		int authExpireTime = 86400;
		
		authExpireTime =  (int)(calendar.getTimeInMillis()/1000)+authExpireTime;
		
		
		System.out.println(authExpireTime);	
		
	}
}
