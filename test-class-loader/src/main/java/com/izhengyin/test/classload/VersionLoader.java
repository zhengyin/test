package com.izhengyin.test.classload;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author zhengyin zhengyinit@outlook.com
 * Created on 2020-11-14 13:52
 */
public class VersionLoader {
    private static final String SOME_CLASS = "SomeClass";

    public static void main(String[] args) throws Exception {
        Version1Loader version1Loader = new Version1Loader();
        Class<?> clzss = version1Loader.findClass(SOME_CLASS);
        Object o = clzss.newInstance();
        Method mainMethod = clzss.getDeclaredMethod("hello");
        System.out.println(mainMethod.invoke(o));

        Version2Loader version2Loader1 = new Version2Loader();
        Class<?> clzss2 = version2Loader1.findClass(SOME_CLASS);
        Object o2 = clzss2.newInstance();
        Method mainMethod2 = clzss2.getDeclaredMethod("hi");
        System.out.println(mainMethod2.invoke(o2));

    }


    static class Version1Loader extends ClassLoader{
        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            File file = new File("/Users/zhengyin/project/my/test/test-class-loader/libs/someclass/version1/"+name+".class");
            byte[] classBytes = getClassData(file);
            if (classBytes == null || classBytes.length == 0) {
                throw new ClassNotFoundException();
            }
            return defineClass(classBytes, 0, classBytes.length);
        }
    }

    static class Version2Loader extends ClassLoader{
        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            File file = new File("/Users/zhengyin/project/my/test/test-class-loader/libs/someclass/version2/"+name+".class");
            byte[] classBytes = getClassData(file);
            if (classBytes == null || classBytes.length == 0) {
                throw new ClassNotFoundException();
            }
            return defineClass(classBytes, 0, classBytes.length);
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
