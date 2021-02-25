package com.izhengyin.test.spring;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;

/**
 * @author zhengyin zhengyinit@outlook.com
 * Created on 2021-02-19 16:05
 */
public class PathMatchingResourcePatternResolverTest {
    public static void main(String[] args) throws IOException {
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resourcePatternResolver.getResources("classpath:configs/*.xml");
        for(Resource r : resources){
            System.out.println(r.getFile().getPath());
        }
    }
}
