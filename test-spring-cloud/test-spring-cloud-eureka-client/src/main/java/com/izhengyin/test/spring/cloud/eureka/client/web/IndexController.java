package com.izhengyin.test.spring.cloud.eureka.client.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhengyin on 2017/9/15 下午4:58.
 * Email  <zhengyin.name@gmail.com> .
 */
@RestController
public class IndexController {
    private int counter;
    @Autowired
    DiscoveryClient discoveryClient;
    @GetMapping("/dc")
    public String dc() throws InterruptedException {
        counter();
        if(counter % 5 == 0){
            Thread.sleep(5000);
        }
        String services = "["+counter+"] Services: " + discoveryClient.getServices();
        System.out.println(services);
        return services;
    }

    private synchronized void counter(){
        counter ++;
    }

}
