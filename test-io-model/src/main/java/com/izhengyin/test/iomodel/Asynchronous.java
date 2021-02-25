package com.izhengyin.test.iomodel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * @author zhengyin zhengyinit@outlook.com
 * Create on 2020/11/1 12:47 下午
 */
public class Asynchronous {
    private static ExecutorService executors = Executors.newFixedThreadPool(1);
    public static void main(String[] args) {
        //主线程不等待返回结果
        asyncCall( res -> {
            System.out.println(res);
        });
    }
    public static void asyncCall(Consumer<String> consumer){
        executors.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException e){

            }
            consumer.accept("ready");
        });
    }
}
