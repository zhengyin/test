package com.izhengyin.test.other.rpc;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhengyin on 2017/3/29.
 */
public class TestRpc1 {

    private static int counter;

    public static int getCounter() {
        return counter;
    }

    public synchronized static void setCounter(int counter) {
        TestRpc1.counter = counter;
    }
    public synchronized static int incCounter() {
        return ++ TestRpc1.counter;
    }

    public static void main(String args[]){

        ExecutorService cachedThreadPool = Executors.newFixedThreadPool(100);

        for(int i=0;i<100;i++){
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        int num = TestRpc1.incCounter();
                        Map<String,Object> params = new HashMap<>();
                        params.put("loginName","admin999");
                        params.put("loginPass","123456");
                        params.put("clientIp","22.133.123.11");
                        params.put("platform",1);
                        params.put("userAgent","userAgent");

                        try {
                            String response = HttpUnits.post("http://192.168.1.113:8888/login/accounts",params);
                            System.out.println("response -> "+response);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            TimeUnit.MILLISECONDS.sleep(10000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }

    }





}
