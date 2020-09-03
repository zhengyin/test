package com.izhengyin.test.other.concurrent.utils;

import com.izhengyin.test.other.concurrent.SleepUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author zhengyin zhengyinit@outlook.com
 * Created on 2020-08-11 08:18
 */
@Slf4j
public class CountDownLatchTest {

    private final static CountDownLatch cdl = new CountDownLatch(2);
    //内部计数器不会清0，不能复用 cdl
    private final static CountDownLatch cdl2 = new CountDownLatch(2);

    private final static ExecutorService executorService = Executors.newFixedThreadPool(2);
    public static void main(String[] args) throws InterruptedException{
        test1();
        log.info("-----------------------------------");
        test2();
    }

    /**
     * 一直等待 , 直到地老天荒
     * @throws InterruptedException
     */
    private static void test1() throws InterruptedException{
        executorService.execute(() -> {
            SleepUtils.second(1);
            log.info(Thread.currentThread().getName());
            cdl.countDown();
        });
        executorService.execute(() -> {
            SleepUtils.second(2);
            log.info(Thread.currentThread().getName());
            cdl.countDown();
        });
        cdl.await();
        log.info("completed!");
    }

    /**
     * 超时退出
     * @throws InterruptedException
     */
    private static void test2() throws InterruptedException{
        executorService.execute(() -> {
            SleepUtils.second(1);
            log.info("test2 "+Thread.currentThread().getName());
            cdl2.countDown();
        });
        executorService.execute(() -> {
            SleepUtils.second(2);
            log.info("test2 "+Thread.currentThread().getName());
            cdl2.countDown();
        });
        cdl2.await(1000, TimeUnit.MILLISECONDS);
        log.info("test2 completed!");
    }

}
