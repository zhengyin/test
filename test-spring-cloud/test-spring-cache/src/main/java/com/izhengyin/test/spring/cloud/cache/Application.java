package com.izhengyin.test.spring.cloud.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Created by zhengyin on 2018/1/18 下午1:42.
 * Email  <zhengyin.name@gmail.com> .
 */
@EnableCaching
@SpringBootApplication
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class,args);
    }
}
