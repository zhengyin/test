package com.izhengyin.test.other.concurrent.utils;

import com.izhengyin.test.other.concurrent.SleepUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhengyin zhengyinit@outlook.com
 * Created on 2020-08-10 08:56
 */
@Slf4j
public class CyclicBarrierTest {

    private final static ExecutorService executorService = Executors.newFixedThreadPool(2);


    //执行前可以做一些初始化的工作
    public static final CyclicBarrier C = new CyclicBarrier(2,() ->{
        log.info("c");
    });

    public static void main(String[] args) throws InterruptedException{
        test1();
        log.info("--- --- --- --- --- --- --- --- --- --- --- --- ---");
        test2();

    }

    /**
     * 两个线程都就绪后会被同时调度执行
     */
    private static void test1(){
        executorService.execute(() -> {
            try {
                SleepUtils.second(1);
                // 标记为就绪
                C.await();
                log.info(Thread.currentThread().getName());
            }catch (InterruptedException | BrokenBarrierException e){

            }
        });

        executorService.execute(() -> {
            try {
                SleepUtils.second(2);
                // 标记为就绪
                C.await();
                log.info(Thread.currentThread().getName());
            }catch (InterruptedException | BrokenBarrierException e){

            }
        });
        SleepUtils.second(3);
    }

    private static void test2(){
        executorService.execute(() -> {
            try {
                SleepUtils.second(2);
                // 标记为就绪
                C.await();
                log.info(Thread.currentThread().getName());
            }catch (InterruptedException | BrokenBarrierException e){

            }
        });

        executorService.execute(() -> {
            try {
                SleepUtils.second(1);
                // 标记为就绪
                C.await();
                log.info(Thread.currentThread().getName());
            }catch (InterruptedException | BrokenBarrierException e){

            }
        });
        SleepUtils.second(3);
    }
}
