package com.izhengyin.test.other.lambda;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Demo1 {
	public static void main(String[] args) {
		
		Map<String, Object> student = new HashMap<String, Object>();
		student.put("name", "张三");
		student.put("age", 24);
		student.put("sex", "男");
		student.forEach((k,v)->{
			System.out.println(k+"=>"+v);
		});
		
		System.out.println("---------------");
		
		List<Integer> nums = new ArrayList<>();
		nums.add(1);
		nums.add(10);
		nums.add(100);
		nums.forEach(i->System.out.println(i));
		
	}
}
