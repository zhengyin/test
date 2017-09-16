package com.izhengyin.test.spring.cloud.eureka.client;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by zhengyin on 2017/9/15 下午4:58.
 * Email  <zhengyin.name@gmail.com> .
 */
@EnableDiscoveryClient
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        new SpringApplicationBuilder(
                Application.class)
                .web(true).run(args);
    }
}