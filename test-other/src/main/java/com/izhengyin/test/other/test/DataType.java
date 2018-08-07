package com.izhengyin.test.other.test;

public class DataType {
	public static void main(String[] args) {
		Double double1 = new Double(100);
		System.out.println(double1);
		
		Long long1 = double1.longValue();
		System.out.println(long1);
		
		System.out.println(double1 == double1.longValue());

		Long long2 = 1000000L;
		System.out.println(long2.intValue());
		
	}
}
