package com.izhengyin.test.other.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * @author zhengyin zhengyinit@outlook.com
 * Created on 2020-08-04 08:53
 */
public class Interrupted {

    public static void main(String[] args) throws InterruptedException{
        Thread sleepThread = new Thread(new SleepRunner());
        Thread busyThread = new Thread(new BusyRunner());

        sleepThread.start();
        busyThread.start();


        System.out.println(sleepThread.getName()+" , "+sleepThread.isInterrupted());
        System.out.println(busyThread.getName()+" , "+busyThread.isInterrupted());

        SleepUtils.second(3);
        sleepThread.interrupt();
        busyThread.interrupt();

        System.out.println(sleepThread.getName()+" , "+sleepThread.isInterrupted());
        System.out.println(busyThread.getName()+" , "+busyThread.isInterrupted());

        sleepThread.join();
        busyThread.join();
    }

    public static class SleepRunner implements Runnable{
        @Override
        public void run() {
            while (true){
                SleepUtils.second(1);
            }
        }
    }

    public static class BusyRunner implements Runnable{
        @Override
        public void run() {
            while (true){

            }
        }
    }
}
