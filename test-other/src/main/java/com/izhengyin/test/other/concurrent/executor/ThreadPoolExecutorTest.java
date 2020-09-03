package com.izhengyin.test.other.concurrent.executor;

import com.izhengyin.test.other.concurrent.SleepUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhengyin zhengyinit@outlook.com
 * Created on 2020-08-14 16:43
 */
@Slf4j
public class ThreadPoolExecutorTest {

    public static void main(String[] args){
        fixed();
        single();
        cached();
        System.out.println("main end!");
    }

    /**
     * 固定大小线程池， coreSize 与 maxSize 相同，任务队列可以无限增加，如果线程处理速度不及线程任务增加速度，会导致内存泄露的发生
     */
    public static void fixed(){
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i=0;i<10;i++){
            executorService.execute(new Task(i));
            log.info(i+"");
        }
    }

    /**
     * 与固定线程池一样，只是只创建了一个线程
     */
    public static void single(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i=0;i<10;i++){
            executorService.execute(new Task(i));
            log.info(i+"");
        }
    }

    /**
     * 使用一个无容量的队列，当有新任务增加时直接创建一个新的线程处理，因此当任务过多，会导致资源耗尽
     */
    public static void cached(){
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i=0;i<100;i++){
            executorService.execute(new Task(i));
            log.info(i+"");
        }
    }

    private static class Task implements Runnable{
        private Integer id;
        public Task(Integer id){
            this.id = id;
        }

        @Override
        public void run() {
            SleepUtils.second(1);
            log.info(Thread.currentThread().getName()+" "+Thread.currentThread().isDaemon()+" -> "+this.id);
        }
    }
}
