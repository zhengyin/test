package com.izhengyin.test.spring.aop.advice.around;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * Created by zhengyin on 2017/8/28 下午4:28.
 * Email  <zhengyin.name@gmail.com> .
 */
public class PersonAroundAdvice implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("What`s your name?");
        Object obj = methodInvocation.proceed();
        System.out.println("It`s nice too meet you.");
        return obj;
    }
}
