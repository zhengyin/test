package com.izhengyin.test.other.jmm;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhengyin zhengyinit@outlook.com
 * Created on 2020-07-02 08:44
 */
public class VolatileTest {

    static volatile int i = 0;
    static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException{

        Thread watchThread = new Thread(() -> {
            int mark = i;
            int counter = 0;
            while (true){
                int newValue = read();
                if(mark != newValue){
                    counter ++;
                    System.out.println("["+counter+"] newValue "+newValue);
                    mark = newValue;
                }
            }
        });

        Thread writeThread = new Thread(() -> {
            for(int n=0;n<1000;n++){
                write();
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                }catch (InterruptedException e){

                }
            }

        });

        watchThread.start();
        writeThread.start();
        watchThread.join();
        writeThread.join();
    }


    public  static void write(){
    //    lock.lock();
        try {
            i++;
        }finally {
    //        lock.unlock();
        }
    }

    public static int read(){
        return i;
    }
}
