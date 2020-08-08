package com.izhengyin.test.other.jmm;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author zhengyin zhengyinit@outlook.com
 * Created on 2020-07-01 08:56
 */
public class LongTest {
    private static long l = 0L;
    public static void main(String[] args) throws InterruptedException{

        new Thread(() -> {
            while (true){
                l = 123L;
            }
        }).start();

        new Thread(() -> {
            while (true){
                l = 456L;
            }
        }).start();

        while (true){
            System.out.println(new Date()+" , l -> "+l);
            TimeUnit.MILLISECONDS.sleep(100);
        }

    }


}
