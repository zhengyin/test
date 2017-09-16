package com.izhengyin.test.spring.cloud.eureka.consumer.ribbon.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by zhengyin on 2017/9/15 下午5:45.
 * Email  <zhengyin.name@gmail.com> .
 */
@RestController
public class ApiController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/consumer")
    public String dc() {
        return restTemplate.getForObject("http://eureka-client/dc", String.class);
    }

}
