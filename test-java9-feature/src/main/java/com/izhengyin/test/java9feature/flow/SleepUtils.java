package com.izhengyin.test.java9feature.flow;

import java.util.concurrent.TimeUnit;

/**
 * @author zhengyin zhengyinit@outlook.com
 * Created on 2020-08-04 08:54
 */
public class SleepUtils {
    public static void second(int second){
        try {
            TimeUnit.SECONDS.sleep(second);
        }catch (InterruptedException e){

        }
    }

    public static void milliSecond(int mill){
        try {
            TimeUnit.MILLISECONDS.sleep(mill);
        }catch (InterruptedException e){

        }
    }
}
