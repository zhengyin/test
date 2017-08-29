package com.izhengyin.test.spring.aop.advice.introduce;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;

/**
 * Created by zhengyin on 2017/8/29 上午9:41.
 * Email  <zhengyin.name@gmail.com> .
 */
public class PersonIntroduceAdvice extends DelegatingIntroductionInterceptor implements ActivePerson{

    private ThreadLocal<Boolean> personActiveMap = new ThreadLocal<>(); // 线程安全处理

    @Override
    public void setActive(boolean status) {
        personActiveMap.set(status);
    }

    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        Object result = null;
        if(personActiveMap.get() != null && personActiveMap.get()){
            result = super.invoke(mi);
            System.out.println("I am an active person! I have many hobbies , I like balabalala ..");
        }else{
            result = super.invoke(mi);
        }
        return result;
    }
}
