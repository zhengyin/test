package com.izhengyin.test.hadoop;


import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhengyin on 2018/1/30 下午2:39.
 * Email  <zhengyin.name@gmail.com> .
 */
public class Test {
    public static void main(String[] args){
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 5, 10L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(1));




        for(int i = 0; i < 30 ; i++){
            System.out.println("threadPoolExecutor.getMaximumPoolSize():"+threadPoolExecutor.getMaximumPoolSize());
            System.out.println("threadPoolExecutor.getActiveCount():"+threadPoolExecutor.getActiveCount());
            System.out.println("threadPoolExecutor.getLargestPoolSize():"+threadPoolExecutor.getLargestPoolSize());
            System.out.println("threadPoolExecutor.getCompletedTaskCount():"+threadPoolExecutor.getCompletedTaskCount());
            System.out.println("threadPoolExecutor.getTaskCount():"+threadPoolExecutor.getTaskCount());

            if(threadPoolExecutor.getActiveCount() < threadPoolExecutor.getMaximumPoolSize()){
                threadPoolExecutor.execute(new Thread(new _Runnable(i) ,"myThread"));

            }

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(" --- --- --- --- --- --- --- --- --- --- ---");
        }
    }

    public static class _Runnable implements Runnable{
        private int index;
        public _Runnable(int index){
            this.index = index;
        }
        @Override
        public void run() {
            try {
                System.out.println("["+index+"]threadPoolExecutor.execute : "+ Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
