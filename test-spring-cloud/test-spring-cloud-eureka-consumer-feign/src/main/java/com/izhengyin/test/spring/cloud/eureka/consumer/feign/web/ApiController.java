package com.izhengyin.test.spring.cloud.eureka.consumer.feign.web;

import com.izhengyin.test.spring.cloud.eureka.consumer.feign.api.ApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhengyin on 2017/9/15 下午5:45.
 * Email  <zhengyin.name@gmail.com> .
 */
@RestController
public class ApiController {

    @Autowired
    private ApiClient apiClient;

    @GetMapping("/consumer")
    public String dc() {
        return apiClient.dc();
    }
}
