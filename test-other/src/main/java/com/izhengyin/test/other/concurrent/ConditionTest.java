package com.izhengyin.test.other.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhengyin zhengyinit@outlook.com
 * Created on 2020-08-05 08:54
 */
public class ConditionTest {
    private static final ReentrantLock lock = new ReentrantLock();
    private static final Condition condition = lock.newCondition();
    public static void main(String[] args) throws InterruptedException{
        conditionWait();
        SleepUtils.second(3);
        conditionSignal();
        //防止退出
        SleepUtils.second(3);
    }

    /**
     *
     * @throws InterruptedException
     */
    public static void conditionWait() throws InterruptedException{
        new Thread(() -> {
            long time = System.currentTimeMillis();
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " 拿到锁了");
                System.out.println(Thread.currentThread().getName() + " 等待信号");
                condition.await();
                System.out.println(Thread.currentThread().getName() + " 拿到信号");
            }catch (InterruptedException e){

            }finally {
                lock.unlock();
            }
            System.out.println("condition read consume "+(System.currentTimeMillis() - time));
        },"conditionWait").start();
    }

    /**
     *
     */
    public static void conditionSignal(){
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " 拿到锁了");
                condition.signal();
                System.out.println(Thread.currentThread().getName() + " 发出信号");
            }finally {
                lock.unlock();
            }

        },"conditionSignal").start();


    }
}
