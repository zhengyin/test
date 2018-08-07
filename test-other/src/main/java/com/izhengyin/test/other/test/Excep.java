package com.izhengyin.test.other.test;

public class Excep {
	public static void main(String[] args) throws Exception {
		int i = 0;
		try {
			int b = 10/i;
			System.out.println(">>> try end");
		} catch (Exception e) {
			System.out.println(">>> catch");
			throw new Exception(e);
		}finally{
			System.out.println(">>> finally");
		}
		
		System.out.println(">>> end!");
	}
}
