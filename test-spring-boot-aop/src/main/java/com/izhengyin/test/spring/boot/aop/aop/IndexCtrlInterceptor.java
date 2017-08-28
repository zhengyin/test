package com.izhengyin.test.spring.boot.aop.aop;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zhengyin on 2017/8/28 下午3:06.
 * Email  <zhengyin.name@gmail.com> .
 */
@Configuration
public class IndexCtrlInterceptor {

    private static final Log logger = LogFactory.getLog(IndexCtrlInterceptor.class);

    @Pointcut("execution(* com.izhengyin.test.spring.boot.aop.ctrl..Index.*(..) ) ")
    public void pointcut(){}

}
