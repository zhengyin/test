package com.izhengyin.test.mybatis.springboot.mulitds;

import com.izhengyin.test.mybatis.springboot.mulitds.Service.Demo1Service;
import com.izhengyin.test.mybatis.springboot.mulitds.dao.mapper.demo1.NewsMapperFromDemo1;
import com.izhengyin.test.mybatis.springboot.mulitds.dao.mapper.demo2.NewsMapperFromDemo2;
import com.izhengyin.test.mybatis.springboot.mulitds.dimain.News;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Created by zhengyin on 2017/8/26 上午9:55.
 * Email  <zhengyin.name@gmail.com> .
 */
@SpringBootApplication
public class Application implements ApplicationRunner{

    private static final Log logger = LogFactory.getLog(Application.class);

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {

    }

    @Component
    public static class testDemo1 implements CommandLineRunner{
        private NewsMapperFromDemo1 newsMapperFromDemo1;
        @Autowired
        private Demo1Service demo1Service;

        public testDemo1(NewsMapperFromDemo1 newsMapperFromDemo1) {
            this.newsMapperFromDemo1 = newsMapperFromDemo1;
        }

        @Override
        public void run(String... strings) throws Exception {
            printNews(1);
            testTransaction();
        }


        public void printNews(int id){
            News news = this.newsMapperFromDemo1.select(id);
            logger.info("Select Demo1 News ["+id+"] : "+news);
        }

        public void testTransaction() throws InterruptedException {
            News news = null;


            int curTime = (int)(System.currentTimeMillis()/1000);
            logger.debug("add --- --- --- --- --- --- --- --- --- ---");
            news = demo1Service.add("title_"+curTime,"content_"+curTime);
            TimeUnit.SECONDS.sleep(1);
            logger.debug("modify --- --- --- --- --- --- --- --- --- ---");
            demo1Service.modify(news.getId(),"title_"+news.getId(),"content_"+news.getId());
            TimeUnit.SECONDS.sleep(1);
            try {
                logger.debug("del --- --- --- --- --- --- --- --- --- ---");
                demo1Service.del(news.getId(),true);
            }catch (Exception e){
                e.printStackTrace();
            }
            logger.debug("<<< <<< <<< <<< <<< <<< <<< <<< <<< <<< <<< <<< <<< ");
            TimeUnit.SECONDS.sleep(1);
            printNews(news.getId());
        }
    }

    @Component
    public static class testDemo2 implements CommandLineRunner{
        private NewsMapperFromDemo2 newsMapperFromDemo2;
        public testDemo2(NewsMapperFromDemo2 newsMapperFromDemo2){
            this.newsMapperFromDemo2 = newsMapperFromDemo2;
        }

        @Override
        public void run(String... strings) throws Exception {
            News news = this.newsMapperFromDemo2.select(1);
            logger.info("Select Demo2 News : "+news);
        }
    }

    public static void main(String[] args){
        SpringApplication.run(Application.class,args);
    }

}
