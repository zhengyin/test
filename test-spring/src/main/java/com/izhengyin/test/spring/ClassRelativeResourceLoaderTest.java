package com.izhengyin.test.spring;

import com.izhengyin.test.spring.a.A;
import org.springframework.core.io.ClassRelativeResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;

/**
 * @author zhengyin zhengyinit@outlook.com
 * Created on 2021-02-19 15:46
 */
public class ClassRelativeResourceLoaderTest {
    public static void main(String[] args) throws IOException {
        ResourceLoader resourceLoader = new ClassRelativeResourceLoader(A.class);
        Resource aResource = resourceLoader.getResource("a.xml");
        Resource bResource = resourceLoader.getResource("1.xml.xml");
        System.out.println("a.xml resource.exists "+aResource.exists()+" , "+aResource.getFile().getPath());
        System.out.println("1.xml.xml resource.exists "+bResource.exists()+" , "+bResource.getFile().getPath());
    }
}
