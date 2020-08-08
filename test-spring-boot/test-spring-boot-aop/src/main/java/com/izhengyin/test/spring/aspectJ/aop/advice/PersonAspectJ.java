package com.izhengyin.test.spring.aspectJ.aop.advice;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import java.lang.reflect.Method;


/**
 * Created by zhengyin on 2017/8/30 下午12:18.
 * Email  <zhengyin.name@gmail.com> .
 */
@Aspect
public class PersonAspectJ {

    private static final Log logger = LogFactory.getLog(PersonAspectJ.class);

    @Before(value = "execution(* com.izhengyin.test.spring.aspectJ.aop.advice.* .*(..))" )
    public void beforeAdviceAll(JoinPoint joinPoint){
        logger.info(joinPoint);
    }

    @Before("execution(* com.izhengyin.test.spring.aspectJ.aop.advice.* .sayName*(..))")
    public void beforeAdviceSayName(){
        logger.info("beforeAdviceSayName -> What`s your name?");
    }

    @Before("execution(* com.izhengyin.test.spring.aspectJ.aop.advice.* .*From*(..))")
    public void beforeAdviceSayFrom(JoinPoint joinPoint){
        if(joinPoint.getSignature().getName().equals("sayFromCity")){
            logger.info("beforeAdviceSayName -> Where are you from which city?");
        }else if(joinPoint.getSignature().getName().equals("sayFromWhichCountry")){
            logger.info("beforeAdviceSayName -> Where are you from which county?");
        }
    }

    @AfterReturning("execution(* com.izhengyin.test.spring.aspectJ.aop.advice.* .sayName*(..))")
    public void afterReturningAdvive(JoinPoint joinPoint){
        logger.info("afterReturningAdvive -> nice too meet you . "+joinPoint.getTarget().getClass().getSimpleName());
    }

    @Around("execution(* com.izhengyin.test.spring.aspectJ.aop.advice.* .do*(..))")
    public Object aroundAdvice(ProceedingJoinPoint point) throws Throwable {
        logger.info("aroundAdvice#before -> What are you doing?");
        Object result = point.proceed();
        logger.info("aroundAdvice#after -> It`s Good!");
        return result;
    }

    // 匹配所有执行的异常
    @AfterThrowing(value = "execution(* com.izhengyin.test.spring.aspectJ.aop.advice.* .*(..))" ,throwing = "runTime")
    public void exceptionAdvice(RuntimeException runTime){
        logger.error("exceptionAdvice -> "+runTime.getMessage(),runTime);
    }

    // 注解
    @Before("@annotation(com.izhengyin.test.spring.aspectJ.aop.advice.Games)")
    public void beforeAdiveForAnnotation(JoinPoint joinPoint) throws NoSuchMethodException {
        logger.info(joinPoint);
        Class clzz = joinPoint.getTarget().getClass();
        @SuppressWarnings("unchecked")   //无参方法,忽略警告
        Method method = clzz.getMethod(joinPoint.getSignature().getName());
        Games games = method.getAnnotation(Games.class);
        if(games != null){
            logger.info("Play Games "+games.value());
        }
    }
}
