package com.izhengyin.test.spring.service;

/**
 * @author zhengyin zhengyinit@outlook.com
 * Created on 2021-02-19 11:23
 */
public class UserServiceImpl implements UserService{
    private final String name;
    private UserServiceImpl(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
