package com.izhengyin.test.spring.aop.advisor.introduce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by zhengyin on 2017/8/29 上午10:05.
 * Email  <zhengyin.name@gmail.com> .
 */
public class Test {
    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/aop/advisor/introduce/aop.xml");

        Jack jack = (Jack) context.getBean("jack");
        jack.sayName();

        System.out.println("--- --- --- --- --- --- --- --- --- --- ");

        ActivePerson activeJack = (ActivePerson)jack;
        activeJack.setActive(true);
        jack.sayName();

    }

    public static class Jack implements Person {
        @Autowired
        public void sayName() {
            System.out.println("My name is jack.");
        }
    }
}
