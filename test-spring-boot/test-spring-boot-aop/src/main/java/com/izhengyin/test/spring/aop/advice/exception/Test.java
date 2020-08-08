package com.izhengyin.test.spring.aop.advice.exception;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by zhengyin on 2017/8/28 下午4:26.
 * Email  <zhengyin.name@gmail.com> .
 */
public class Test {
    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/aop/advice/exception/aop-advice-exception.xml");
        Person jack = (Person) context.getBean("jack");
        jack.sayName();
    }

    public static class Jack implements Person {
        @Override
        public void sayName() {
            System.out.println("Help Me!!");
            throw new RuntimeException();
        }
    }
}
