package com.izhengyin.test.spring.aop.advisor.staticMethod;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by zhengyin on 2017/8/28 下午4:26.
 * Email  <zhengyin.name@gmail.com> .
 */
public class Test {
    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/aop/advisor/staticMethod/aop.xml");
        Person jack = (Person) context.getBean("jack");
        jack.sayName();
        jack.sayFromWhichCountry();
        System.out.println("--- --- --- --- --- --- --- --- --- --- --- --- ");
        Person xiaoIce = (Person) context.getBean("xiaoIce");
        xiaoIce.sayName();
        xiaoIce.sayFromWhichCountry();
    }

    public static class Jack implements Person {
        @Override
        public void sayName() {
            System.out.println("My name is Jack!");
        }

        @Override
        public void sayFromWhichCountry() {
            System.out.println("I am from The US.");
        }
    }

    public static class XiaoIce implements Person{
        @Override
        public void sayName() {
            System.out.println("My name is Xiao Ice!");
        }
        @Override
        public void sayFromWhichCountry() {
            System.out.println("I`m a Robot , I`m not have Country . I am from Microsoft.");
        }
    }
}
