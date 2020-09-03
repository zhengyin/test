package com.izhengyin.test.other.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author zhengyin zhengyinit@outlook.com
 * Created on 2020-08-15 14:04
 */
@Slf4j
public class RunnableAndCallableTest {

    public static void main(String[] args) throws InterruptedException,ExecutionException{
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                1,
                1,
                101,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<>(10),
                new ThreadPoolExecutor.AbortPolicy()
        );
        //runnable
        executor.execute(new RunnableTask());
        //callable
        Future<String> future = executor.submit(new CallableTask());

        log.info("CallableTask result "+future.get());
    }

    private static class RunnableTask implements Runnable{
        @Override
        public void run() {
            log.info("RunnableTask run!");
            SleepUtils.second(1);
        }
    }

    private static class CallableTask implements Callable<String> {
        @Override
        public String call() throws Exception {
            log.info("RunnableTask call!");
            SleepUtils.second(1);
            return "complete!";
        }
    }

}
