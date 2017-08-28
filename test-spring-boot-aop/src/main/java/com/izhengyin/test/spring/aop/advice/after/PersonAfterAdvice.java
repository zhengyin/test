package com.izhengyin.test.spring.aop.advice.after;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * Created by zhengyin on 2017/8/28 下午4:28.
 * Email  <zhengyin.name@gmail.com> .
 */
public class PersonAfterAdvice implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
        System.out.println("It`s nice too meet you.");
    }
}
