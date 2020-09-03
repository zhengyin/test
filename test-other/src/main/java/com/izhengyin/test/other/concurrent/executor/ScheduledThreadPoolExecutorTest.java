package com.izhengyin.test.other.concurrent.executor;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhengyin zhengyinit@outlook.com
 * Created on 2020-08-14 17:10
 */
public class ScheduledThreadPoolExecutorTest {

    public static void main(String[] args){
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(() -> System.out.println(new Date()),1000, 1000,TimeUnit.MILLISECONDS);
    }
}
