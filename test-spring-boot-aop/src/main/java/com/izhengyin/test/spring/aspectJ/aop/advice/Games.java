package com.izhengyin.test.spring.aspectJ.aop.advice;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by zhengyin on 2017/8/30 下午3:31.
 * Email  <zhengyin.name@gmail.com> .
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Games {
    public String value();
}
