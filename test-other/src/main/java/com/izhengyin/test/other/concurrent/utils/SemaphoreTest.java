package com.izhengyin.test.other.concurrent.utils;

import com.izhengyin.test.other.concurrent.SleepUtils;
import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author zhengyin zhengyinit@outlook.com
 * Created on 2020-08-11 08:40
 */
@Slf4j
public class SemaphoreTest {
    private static final int THREAD_COUNT = 30;
    private static final ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
    //控制并发量，只允许10个线程执行
    private static final Semaphore semaphore = new Semaphore(10);
    public static void main(String[] args){
        for (int i=0;i<THREAD_COUNT;i++){
            executor.execute(() -> {
                try {
                    semaphore.acquire();
                    log.info(Thread.currentThread().getName()+" executed");
                    //模拟执行耗时
                    SleepUtils.second(1);
                }catch (InterruptedException e){

                }finally {
                    semaphore.release();
                }
            });
        }
        SleepUtils.second(5);
    }
}
