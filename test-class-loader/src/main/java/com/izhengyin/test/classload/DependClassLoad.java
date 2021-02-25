package com.izhengyin.test.classload;

import java.io.*;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhengyin zhengyinit@outlook.com
 * Created on 2020-11-14 13:52
 */
public class DependClassLoad {
    private static final Map<String,String> classMap = new HashMap<>();
    static {
        classMap.put("com.izhengyin.test.classload.DependClassLoad$A","/Users/zhengyin/project/my/test/test-class-loader/target/classes/com/izhengyin/test/classload/DependClassLoad$A.class");
        classMap.put("com.izhengyin.test.classload.DependClassLoad$B","/Users/zhengyin/project/my/test/test-class-loader/target/classes/com/izhengyin/test/classload/DependClassLoad$B.class");
    }
    public static void main(String[] args) throws Exception {
        MyClassLoader myClassLoader = new MyClassLoader();
        Class<?> aClass = myClassLoader.loadClass("com.izhengyin.test.classload.DependClassLoad$A");
        Object o = aClass.newInstance();
        Method mainMethod = aClass.getDeclaredMethod("print");
        mainMethod.invoke(o);
    }


    public static class A {
        public void print() throws Exception{
            System.out.println("A : "+A.class.getClassLoader());
            B b = new B();
            b.print();
        }
    }

    public static class B {
        public void print() throws Exception{
            System.out.println("B : "+B.class.getClassLoader());
            C c = new C();
            c.print();
        }
    }

    public static class C {
        public void print() throws Exception{
            System.out.println("C : "+C.class.getClassLoader());
        }
    }

    static class MyClassLoader extends ClassLoader {
        private final ClassLoader classLoader = DependClassLoad.class.getClassLoader();


        @Override
        protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
            if(classMap.containsKey(name)){
                File file = new File(classMap.get(name));
                byte[] classBytes = getClassData(file);
                if (classBytes == null || classBytes.length == 0) {
                    throw new ClassNotFoundException();
                }
                return defineClass(classBytes, 0, classBytes.length);
            }
            return classLoader.loadClass(name);



        }
    }

    private static byte[] getClassData(File file) {
        try (InputStream ins = new FileInputStream(file); ByteArrayOutputStream baos = new
                ByteArrayOutputStream()) {
            byte[] buffer = new byte[4096];
            int bytesNumRead = 0;
            while ((bytesNumRead = ins.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesNumRead);
            }
            return baos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[] {};
    }

}
