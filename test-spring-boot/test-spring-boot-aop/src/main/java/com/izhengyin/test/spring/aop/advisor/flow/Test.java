package com.izhengyin.test.spring.aop.advisor.flow;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by zhengyin on 2017/8/28 下午4:26.
 * Email  <zhengyin.name@gmail.com> .
 */
public class Test {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/aop/advisor/flow/aop.xml");
        Person jack = (Person) context.getBean("jack");
        jack.sayName();
        jack.sayFromWhichCountry();
        jack.sayFromCity();
        System.out.println("--- --- --- --- --- --- --- --- --- --- --- --- ");
        Person xiaoIce = (Person) context.getBean("xiaoIce");
        xiaoIce.sayName();
        xiaoIce.sayFromWhichCountry();
        xiaoIce.sayFromCity();

        // 直接通过get Bean 切面不会生效   context.getBean("asker");
        Asker asker = new Asker();
        asker.setPerson(jack);
        asker.ask();

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

        @Override
        public void sayFromCity() {
            System.out.println("I am from San Francisco.");
        }
    }

    public static class XiaoIce implements Person {
        @Override
        public void sayName() {
            System.out.println("My name is Xiao Ice!");
        }
        @Override
        public void sayFromWhichCountry() {
            System.out.println("I`m a Robot , I`m not have Country . I am from Microsoft.");
        }

        @Override
        public void sayFromCity() {
            System.out.println("I am from Washington.");
        }
    }
}
