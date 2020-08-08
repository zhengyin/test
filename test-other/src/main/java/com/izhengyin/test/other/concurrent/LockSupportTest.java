package com.izhengyin.test.other.concurrent;

import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport与JDK中的区别
 * （1）wait和notify都是Object中的方法,在调用这两个方法前必须先获得锁对象，但是park不需要获取某个对象的锁就可以锁住线程。
 * （2）notify只能随机选择一个线程唤醒，无法唤醒指定的线程，unpark却可以唤醒一个指定的线程。
 * @author zhengyin zhengyinit@outlook.com
 * Created on 2020-08-04 08:41
 */
public class LockSupportTest {
    private static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException{
        jdkWait();
        lockSupport();
        SleepUtils.second(3);
    }

    /**
     * lockSupport
     * @throws InterruptedException
     */
    private static void lockSupport() throws InterruptedException{
        Thread thread1 = new Thread(() -> {
            long time = System.currentTimeMillis();
            LockSupport.park();
            System.out.println("lockSupport read consume "+(System.currentTimeMillis() - time));
        });
        thread1.start();
        SleepUtils.second(1);
        LockSupport.unpark(thread1);
    }

    /**
     * jdk wait ， notify 通过加锁获取对象的监视器 （多线程可以使用对象的wait,notify实现数据同步，而wait，notify不是线程本身的方法）
     * @throws InterruptedException
     */
    private static void jdkWait() throws InterruptedException{
        Thread thread1 = new Thread(() -> {
            long time = System.currentTimeMillis();
            synchronized (lock){
                try {
                    lock.wait();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            System.out.println("jdkWait read consume "+(System.currentTimeMillis() - time));
        });
        thread1.start();
        SleepUtils.second(1);
        synchronized (lock){
            lock.notify();
        }
    }
}
