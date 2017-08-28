package com.izhengyin.test.mybatis.springboot.annotation.beans;

/**
 * Created by zhengyin on 2017/8/26 下午1:42.
 * Email  <zhengyin.name@gmail.com> .
 */
public class MyBean1 implements MyBean {
    @Override
    public String hello() {
        return "MyBean1";
    }
}
