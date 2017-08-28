package com.izhengyin.test.mybatis.springboot.annotation.beans;

/**
 * Created by zhengyin on 2017/8/26 下午2:00.
 * Email  <zhengyin.name@gmail.com> .
 */
public class RepeatBean {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String hello(){
        return "My name is "+name;
    }
}
