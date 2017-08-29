package com.izhengyin.test.spring.aop.advisor.composablePointcut;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * Created by zhengyin on 2017/8/28 下午4:28.
 * Email  <zhengyin.name@gmail.com> .
 */
public class PersonBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        if(method.getName().equals("sayName")){
            System.out.println("PersonBeforeAdvice : what`s your name?");
        }else if(method.getName().equals("sayFromCity")){
            System.out.println("PersonBeforeAdvice : Where are you from which city?");
        }else if(method.getName().equals("sayFromWhichCountry")){
            System.out.println("PersonBeforeAdvice : Where are you from which country?");
        }
    }
}
