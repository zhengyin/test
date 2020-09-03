package com.izhengyin.test.other.concurrent.executor;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.izhengyin.test.other.concurrent.SleepUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.Date;
import java.util.concurrent.*;

/**
 * @author zhengyin zhengyinit@outlook.com
 * Created on 2020-08-15 09:54
 */
@Slf4j
public class RecommentedCreateThreadPoolOptionExample {

    public static void main(String[] args){
        //createScheduledThreadPool();
        createThreadPool();
    }

    private static void createThreadPool(){

        ThreadFactory factory = new ThreadFactoryBuilder()
                .setNameFormat("custom-xxx-")
                .build();


        // 驳回策略
        RejectedExecutionHandler rejectedExecutionHandler = new ThreadPoolExecutor.AbortPolicy();

        ThreadPoolExecutor executorService = new ThreadPoolExecutor(
                1,
                1,
                1000,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<>(10),
                factory,
                rejectedExecutionHandler
         );
        // 最后一个任务将被驳回
        for (int i =0;i<12;i++){
            try {
                executorService.execute(() -> {
                    log.info(new Date()+"");
                    SleepUtils.second(1);
                });
                log.info(executorService.getActiveCount()+" , "+executorService.getCorePoolSize()+" , "+executorService.getLargestPoolSize()+" , "+executorService.getTaskCount());
            }catch (RejectedExecutionException e){
                log.error("RejectedExecutionException "+e.getMessage());
            }

        }
    }

    private static void createScheduledThreadPool(){
        // apache common basic thread factory
        BasicThreadFactory basicThreadFactory = new BasicThreadFactory
                .Builder()
                .namingPattern("custom-xxx-")
                .build();

        // 驳回策略
        RejectedExecutionHandler rejectedExecutionHandler = new ThreadPoolExecutor.AbortPolicy();

        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(
                1, basicThreadFactory, rejectedExecutionHandler);
        // schedule
        executorService.scheduleAtFixedRate(
                () -> log.info(new Date()+""),
                1000,
                1000,
                TimeUnit.MILLISECONDS
        );
    }


}
