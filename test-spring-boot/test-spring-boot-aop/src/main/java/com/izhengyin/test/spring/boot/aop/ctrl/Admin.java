package com.izhengyin.test.spring.boot.aop.ctrl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhengyin on 2017/8/28 下午3:00.
 * Email  <zhengyin.name@gmail.com> .
 */
@RestController
public class Admin {

    @RequestMapping("w")
    public String access(){
        return "ok";
    }

}
