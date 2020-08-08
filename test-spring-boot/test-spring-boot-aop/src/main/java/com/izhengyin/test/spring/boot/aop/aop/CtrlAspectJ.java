package com.izhengyin.test.spring.boot.aop.aop;

import com.alibaba.fastjson.JSON;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.AccessException;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zhengyin on 2017/8/28 下午3:06.
 * Email  <zhengyin.name@gmail.com> .
 */
@Configuration
@Aspect
public class CtrlAspectJ {

    private static final Log logger = LogFactory.getLog(CtrlAspectJ.class);

    @Pointcut("execution(* com.izhengyin.test.spring.boot.aop.ctrl.* .*(..))")
    public void pointcutAllCtrl(){}

    @Pointcut("execution(* com.izhengyin.test.spring.boot.aop.ctrl.Admin .*(..))")
    public void pointcutAdminCtrl(){}

    @Before("pointcutAdminCtrl()")
    public void CtrlBefore(JoinPoint point) throws AccessException {
        logger.info("CtrlBefore -> "+point);
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes)requestAttributes).getResponse();
        logger.info(JSON.toJSONString(request.getCookies()));
        response.setStatus(500);
    }

    @Around("pointcutAllCtrl()")
    public Object CtrlAround(ProceedingJoinPoint point){

        Object obj = null;
        try{
            obj = point.proceed();
        }catch (Throwable e){
            logger.error(e);
            obj = e.getMessage();
        } finally {
            logger.info("CtrlAround -> "+point);
        }
        return obj;
    }

}
