package com.izhengyin.test.other.concurrent.utils;

import com.izhengyin.test.other.concurrent.SleepUtils;
import lombok.extern.log4j.Log4j;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhengyin zhengyinit@outlook.com
 * Created on 2020-08-11 08:52
 */
@Log4j
public class ExchangerTest {
    /**
     * 两个线程之间数据交换，如果一个线程未准备好，另外一个线程将一直等待（比如测试中的线程1）
     */
    private static final Exchanger<String> exger = new Exchanger<>();

    private static final ExecutorService executor = Executors.newFixedThreadPool(2);
    public static void main(String[] args){
        executor.execute(() -> {
            try {
                log.info("#1 "+Thread.currentThread().getName()+" prepare exchange ");
                log.info("#4 "+Thread.currentThread().getName()+ " exger.exchange "+exger.exchange(Thread.currentThread().getName()));
                log.info("#6 "+Thread.currentThread().getName()+" complete exchange ");
            }catch (InterruptedException e){

            }

        });

        executor.execute(() -> {
            try {
                SleepUtils.second(3);
                log.info("#2 "+Thread.currentThread().getName()+" prepare exchange ");
                log.info("#3 "+Thread.currentThread().getName()+ " exger.exchange "+exger.exchange(Thread.currentThread().getName()));
                log.info("#5 "+Thread.currentThread().getName()+" complete exchange ");
            }catch (InterruptedException e){

            }
        });

        SleepUtils.second(3);
    }
}
