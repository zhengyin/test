package com.izhengyin.test.spring.cloud.eureka.consumer.feign.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by zhengyin on 2017/9/16 下午12:09.
 * Email  <zhengyin.name@gmail.com> .
 */
@FeignClient("eureka-client")
public interface ApiClient {
    @GetMapping("/dc")
    String dc();
}
