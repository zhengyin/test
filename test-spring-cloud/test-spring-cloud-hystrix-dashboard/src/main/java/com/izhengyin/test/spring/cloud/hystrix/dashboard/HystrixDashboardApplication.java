package com.izhengyin.test.spring.cloud.hystrix.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * Created by zhengyin on 2017/9/16 下午3:30.
 * Email  <zhengyin.name@gmail.com> .
 */
@EnableHystrixDashboard
@SpringCloudApplication
public class HystrixDashboardApplication {
    public static void main(String[] args) {
        SpringApplication.run(HystrixDashboardApplication.class, args);
    }
}