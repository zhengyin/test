package com.izhengyin.test.es;

import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author zhengyin  <zhengyin.name@gmail.com>
 * @date Created on 2018/9/13 下午4:58
 */
public class IndexApi {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("org.apache.logging.log4j.simplelog.StatusLogger.level","TRACE");

        TransportClient client = null;
        try {
            Settings settings = Settings.builder()
                    .put("cluster.name", "elk").build();
            client = new PreBuiltTransportClient(settings)
                    .addTransportAddress(new TransportAddress(InetAddress.getByName("hadoop-worker1"), 9192))
                    .addTransportAddress(new TransportAddress(InetAddress.getByName("hadoop-worker2"), 9192))
                    .addTransportAddress(new TransportAddress(InetAddress.getByName("hadoop-worker3"), 9192));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }



        for(int i=0;i<1000;i++){
            int mod = new Random().nextInt(10)+1;
            Map<String,Object> json = new HashMap<>();
            long timestamp = System.currentTimeMillis();
            json.put("point","event"+ (i%mod));
            json.put("application","application"+ (i%mod));
            json.put("client","client"+ (i%mod));
            json.put("dateMinute",new Date(timestamp));
            json.put("pv",i%mod);
            json.put("timestamp",timestamp);
            IndexResponse response = client.prepareIndex("biz-monitor", "doc")
                    .setSource(json)
                    .get();
            System.out.println(JSON.toJSON(response));
            TimeUnit.MICROSECONDS.sleep(100);
        }




        client.close();
    }
}
