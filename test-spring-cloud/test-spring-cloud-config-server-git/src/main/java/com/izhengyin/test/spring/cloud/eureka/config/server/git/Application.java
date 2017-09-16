package com.izhengyin.test.spring.cloud.eureka.config.server.git;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Created by zhengyin on 2017/9/16 下午1:20.
 * Email  <zhengyin.name@gmail.com> .
 */
@EnableConfigServer
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class).web(true).run(args);
    }
}