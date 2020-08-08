package com.izhengyin.test.other.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author zhengyin zhengyinit@outlook.com
 * Created on 2020-07-23 08:57
 */
public class ReentrantLockTest {
    private static Map<String,String> map = new HashMap<>();
    private final static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final static ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
    private final static ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();
    public static void  main(String[] args) throws InterruptedException{
        Thread thread1 = new Thread(() -> {
            long time = System.currentTimeMillis();
            get("k1",1000);
            System.out.println("read consume "+(System.currentTimeMillis() - time));
        });

        Thread thread2 = new Thread(() -> {
            long time = System.currentTimeMillis();
            put("k1","v1",0);
            System.out.println("write consume "+(System.currentTimeMillis() - time));
        });

        //读锁开始
        thread1.start();
        TimeUnit.MILLISECONDS.sleep(10);
        //需要等待读锁释放才能执行
        thread2.start();
        thread1.join();
        thread2.join();
    }


    public static String get(String key , long timeout){
        readLock.lock();
        try {
            if(timeout > 0){
                TimeUnit.MILLISECONDS.sleep(timeout);
            }
            return map.get(key);
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            readLock.unlock();
        }
        return null;
    }

    public static String put(String key , String value , long timeout){
        writeLock.lock();
        try {
            if(timeout > 0){
                TimeUnit.MILLISECONDS.sleep(timeout);
            }
            return map.put(key,value);
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            writeLock.unlock();
        }
        return null;
    }

}

