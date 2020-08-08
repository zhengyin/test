package com.izhengyin.test.other.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * @author zhengyin  <zhengyin.name@gmail.com>
 * @date Created on 2019-05-17 14:58
 */
public class Sync {

    public static void main(String[] args){
        new Thread(()->{
            echo("Thread1");
        }).start();

        new Thread(()->{
            echo("Thread2");
        }).start();

        new Thread(()->{
            echo2("Thread2");
        }).start();

        for(long i = 0; i < 200000; i++) {
            for(long j = 0; j < 100000; j++) {}
        }
    }


    public synchronized static void echo(String name){
        System.out.println("Prepare echo "+name);
        try {
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException e){}
        System.out.println("Echo "+name);
    }

    public static void echo2(String name){
        System.out.println("Prepare echo2 "+name);
        try {
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException e){}
        System.out.println("Echo2 "+name);
    }
}
