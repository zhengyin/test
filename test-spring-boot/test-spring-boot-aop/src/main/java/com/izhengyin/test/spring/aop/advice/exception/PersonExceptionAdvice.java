package com.izhengyin.test.spring.aop.advice.exception;
import org.springframework.aop.ThrowsAdvice;
import java.lang.reflect.Method;


/**
 * Created by zhengyin on 2017/8/28 下午4:28.
 * Email  <zhengyin.name@gmail.com> .
 */
public class PersonExceptionAdvice implements ThrowsAdvice {
    public void afterThrowing(Method method, Object[] args, Object target,
                              Exception ex) throws Throwable {
        System.out.println("Oh My God! are you ok? ");
    }
}
