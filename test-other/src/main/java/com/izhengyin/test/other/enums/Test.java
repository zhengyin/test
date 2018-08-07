package com.izhengyin.test.other.enums;

public class Test {
	public static void main(String[] args) {
		System.out.println(Enum1.RED);
		
		
		Enum2 enum2 = Enum2.GREEN;
		enum2.setName("Enum2.GREEN");
		System.out.println(enum2.getIndex()+"=>"+enum2.getName());
		
		System.out.println(Enum2.GREEN.getIndex()+"=>"+Enum2.GREEN.getName());
		
	}
}
