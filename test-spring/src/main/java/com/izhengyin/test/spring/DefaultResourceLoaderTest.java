package com.izhengyin.test.spring;

import org.springframework.beans.factory.xml.DefaultBeanDefinitionDocumentReader;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

/**
 * @author zhengyin zhengyinit@outlook.com
 * Created on 2021-02-19 15:45
 */
public class DefaultResourceLoaderTest {
    public static void main(String[] args) {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        String[] paths = new String[]{
                "D:/a/1.xml/c",
                "/a/1.xml/c",
                "file:/xxx",
                "http://a.b.c",
                "classpath:/a/1.xml/c",
                "a/1.xml/c"
        };
        for (String path : paths){
            Resource resource = resourceLoader.getResource(path);
            System.out.println(path +" => "+resource.getClass().getName());
        }



    }
}
