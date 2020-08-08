package com.izhengyin.test.spring.aspectJ.aop.advice;
import com.izhengyin.test.spring.aspectJ.aop.advice.persons.XiaoMing;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by zhengyin on 2017/8/28 下午4:26.
 * Email  <zhengyin.name@gmail.com> .
 */
public class Test {

    private static final Log logger = LogFactory.getLog(Test.class);

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/aspect/aop/advice/aop.xml");

        logger.info("--- --- --- --- --- --- --- --- --- --- --- --- ");
        Person jack = (Person) context.getBean("jack");
        jack.sayName();
        jack.sayFromWhichCountry();
        jack.sayFromCity();
        jack.doWork();
        jack.doSports();
        jack.playGames();

        logger.info("--- --- --- --- --- --- --- --- --- --- --- --- ");
        Person xiaoIce = (Person) context.getBean("xiaoIce");
        xiaoIce.sayName();
        xiaoIce.sayFromWhichCountry();
        xiaoIce.sayFromCity();
        xiaoIce.doWork();

        try{
            xiaoIce.doSports();
        }catch(RuntimeException e){

        }


        try{
            xiaoIce.playGames();
        }catch (RuntimeException e){

        }


        logger.info("--- --- --- --- --- --- --- --- --- --- --- --- ");
        Person xiaoMing = (Person) context.getBean("xiaoMing");
        xiaoMing.sayName();
        xiaoMing.sayFromWhichCountry();
        xiaoMing.sayFromCity();
        xiaoMing.doWork();
        xiaoMing.doSports();
        xiaoMing.playGames();

    }


    public static class Jack implements Person {

        private static final Log logger = LogFactory.getLog(Jack.class);

        @Override
        public void sayName() {
            logger.info("My name is Jack!");
        }

        @Override
        public void sayFromWhichCountry() {
            logger.info("I am from The US.");
        }

        @Override
        public void sayFromCity() {
            logger.info("I am from San Francisco.");
        }

        @Override
        public void doWork() {
            logger.info("I'm coding, It`s my job.");
        }

        @Override
        public void doSports() {
            logger.info("I am playing basketball, It`s my hobby.");
        }

        @Override
        @Games("英雄联盟")
        public void playGames() {

        }
    }

    public static class XiaoIce implements Person {

        private static final Log logger = LogFactory.getLog(XiaoIce.class);

        @Override
        public void sayName() {
            logger.info("My name is Xiao Ice!");
        }
        @Override
        public void sayFromWhichCountry() {
            logger.info("I am from Microsoft.");
        }

        @Override
        public void sayFromCity() {
            logger.info("I am from Washington.");
        }

        @Override
        public void doWork() {
            logger.info("I'm talking to you, It`s my job.");
        }

        @Override
        public void doSports(){
            throw new RuntimeException("I`m a Robot , I`m Can't do Sports !");
        }

        @Override
        @Games("")
        public void playGames() {
            throw new RuntimeException("I`m a Robot , I`m Can't Play Games !");
        }
    }
}
