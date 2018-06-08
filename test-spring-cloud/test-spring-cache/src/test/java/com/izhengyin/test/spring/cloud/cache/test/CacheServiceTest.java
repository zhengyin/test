package com.izhengyin.test.spring.cloud.cache.test;

import com.izhengyin.test.spring.cloud.cache.Application;
import com.izhengyin.test.spring.cloud.cache.CacheService;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * Created by zhengyin on 2018/1/18 下午5:27.
 * Email  <zhengyin.name@gmail.com> .
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@EnableAutoConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestPropertySource(locations = {"classpath:application.properties"})
public class CacheServiceTest {

    @Autowired
    private CacheService cacheService;

    @Before
    public void setUp(){

    }

    @Test
    public void cacheable() throws InterruptedException {
        cacheService.cache1();
        cacheService.cache1("test1");
        cacheService.cache2("test2");
        cacheService.cache3("test3_1");
        cacheService.cache3("test3_2");
        cacheService.CacheEvict3("test3_1");
        cacheService.dynamicCache(1);

        System.out.println(cacheService.getTableInfo());

        TimeUnit.SECONDS.sleep(3);

        System.out.println(cacheService.getTableInfo());
    }

}
