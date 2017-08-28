package com.izhengyin.test.spring.aop.advice.before;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * Created by zhengyin on 2017/8/28 下午4:28.
 * Email  <zhengyin.name@gmail.com> .
 */
public class PersonBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.println("What`s your name?");
    }
}
