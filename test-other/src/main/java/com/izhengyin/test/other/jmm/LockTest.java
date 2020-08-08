package com.izhengyin.test.other.jmm;
import sun.misc.Unsafe;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhengyin zhengyinit@outlook.com
 * Created on 2020-07-02 10:27
 */
public class LockTest {
    private static volatile long i;
    private static ReentrantLock lock = new ReentrantLock();
    public static void main(String[] args) throws InterruptedException{
        for (int n =0;n<10;n++){
            new Thread(() -> {
                exec(100);
                //观察共享变量 i 的值，确定锁占用的时间(也就是CAS自旋的时间)
                System.out.println(i);
            }).start();
        }
        TimeUnit.SECONDS.sleep(3);
    }

    /**
     * exec
     * @param delay
     */
    public static void exec(long delay){
        lock.lock();
        try {
            i = System.currentTimeMillis();
            TimeUnit.MILLISECONDS.sleep(delay);
        }catch (InterruptedException e){

        }finally {
            lock.unlock();
        }
    }
}
