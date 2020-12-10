package com.izhengyin.test.other.reflect;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

public class Demo1 {
	public static void main(String[] args) {

        String className = "com.izhengyin.test.other.reflect.TestInterface";

        Class<?> clazz = null;
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("\n \n \n printMethod >>>> \n \n \n ");
        printMethod(clazz);
        System.out.println("\n \n \n printClass >>>> \n \n \n ");
        printClass();
        System.out.println("\n \n \n testInvoking >>>> \n \n \n ");
        testInvoking();
	}
	
	
	private static void printMethod(Class<?> clazz){
		System.out.println("Class : "+clazz.getName());
		Method[] methods =  clazz.getMethods();
		for (int i = 0; i < methods.length; i++) {
            System.out.println((i + 1) + ":" + methods[i].getName());
            Parameter[] parameters = methods[i].getParameters();
            System.out.print(" parameters:");
            for (int n = 0; n < parameters.length; n++) {
            	System.out.print(parameters[n].getName()+"["+parameters[n].getType().getName()+"] ");
            }
            System.out.println();
        }
	}
	
	private static void testInvoking(){
		System.out.println(">>> >>> >>> >>> >>> >>> >>> testInvoking ");
		
		String className = "com.izhengyin.test.other.reflect.TestInterImpl";
		
		Class<?> clazz = null;
		try {
			clazz = Class.forName(className);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Object obj = clazz.newInstance();
			Method m1 = clazz.getMethod("a", int.class);
			for(int i=0;i<10;i++){
				System.out.println("invoke method[a] "+m1.invoke(obj, i));
			}
			
			Method m2 = clazz.getMethod("b", String.class,int.class);
			for(int i=0;i<10;i++){
				System.out.println("invoke method[b] "+m2.invoke(obj, "=> " , i));
			}
			
			Method m3 = clazz.getMethod("c", HashMap.class);
			HashMap<String, Integer> params = new HashMap<String, Integer>();
			
			for(int i=0;i<10;i++){
				params.put("key", i);
				System.out.println("invoke method[c] "+m3.invoke(obj, params));
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void printClass(){
		List<Class<?>> classLists = ClassUtil.getClasses("com.izhengyin.reflect");
		for (Class<?> clazz : classLists) { 
			printMethod(clazz);
		} 
	}
}
