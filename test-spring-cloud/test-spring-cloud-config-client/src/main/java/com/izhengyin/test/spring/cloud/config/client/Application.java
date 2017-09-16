package com.izhengyin.test.spring.cloud.config.client;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * Created by zhengyin on 2017/9/16 下午1:36.
 * Email  <zhengyin.name@gmail.com> .
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class).web(true).run(args);
    }
}