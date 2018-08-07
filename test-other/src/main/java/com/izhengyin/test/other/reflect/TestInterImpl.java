package com.izhengyin.test.other.reflect;

import java.util.HashMap;

public class TestInterImpl implements TestInterface {

	@Override
	public int a(int parmas_1) {
		return parmas_1*2;
	}

	@Override
	public String b(String parmas_1, int parmas_2) {
		return parmas_1+parmas_2;
	}

	@Override
	public String c(HashMap<String, Integer> params_1) {
		
		StringBuilder result = new StringBuilder();
		
		for(String key: params_1.keySet()){
		    int i = params_1.get(key);
		    result.append(key);
		    result.append("=>");
		    result.append(i);
		    result.append(" ");
	  	}
		
		return result.toString();
	}

}
