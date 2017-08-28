package com.izhengyin.test.mybatis.springboot.annotation;

import com.izhengyin.test.mybatis.springboot.annotation.beans.MyBean;
import com.izhengyin.test.mybatis.springboot.annotation.beans.RepeatBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.stereotype.Component;

/**
 * Created by zhengyin on 2017/8/26 下午1:41.
 * Email  <zhengyin.name@gmail.com> .
 */
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class Application implements ApplicationRunner{

    private static final Log logger = LogFactory.getLog(Application.class);

    private MyBean myBean1;
    private MyBean myBean2;

    public Application(MyBean myBean1, MyBean myBean2){
        this.myBean1 = myBean1;
        this.myBean2 = myBean2;
    }
    public static void main(String[] args){
        SpringApplication.run(Application.class,args);
    }
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        logger.info("myBean1 Hello: "+this.myBean1.hello());
        logger.info("myBean2 Hello: "+this.myBean2.hello());
    }

    @Component
    public static class TestRepeatBean implements CommandLineRunner{


        private RepeatBean repeatBean1;
        private RepeatBean repeatBean2;
        private RepeatBean repeatBean3;

        public TestRepeatBean(RepeatBean repeatBean1, @Qualifier("myRepeatBean2")  RepeatBean repeatBean2, RepeatBean repeatBean3) {
            this.repeatBean1 = repeatBean1;
            this.repeatBean2 = repeatBean2;
            this.repeatBean3 = repeatBean3;
        }

        @Override
        public void run(String... strings) throws Exception {
            logger.info("repeatBean1 Hello: "+this.repeatBean1.hello());
            logger.info("repeatBean2 Hello: "+this.repeatBean2.hello());
            logger.info("repeatBean3 Hello: "+this.repeatBean3.hello());
        }
    }


}
