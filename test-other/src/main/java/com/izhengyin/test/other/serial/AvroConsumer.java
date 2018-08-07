package com.izhengyin.test.other.serial;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhengyin on 2018/7/30 下午3:05.
 * Email  <zhengyin.name@gmail.com> .
 */
public class AvroConsumer  {
    private static final String BOOTSTRAP_SERVER = "hadoop-worker1:9092";

    private Properties initConfig(){
        Properties config = new Properties();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,BOOTSTRAP_SERVER);
        config.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,true);
        config.put(ConsumerConfig.GROUP_ID_CONFIG,"avro-group");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, AvorDeserializer.class.getName());
        return config;
    }
    public void doWork() {
        KafkaConsumer<String,Events> kafkaConsumer = new KafkaConsumer<String, Events>(initConfig());
        while (true){
            kafkaConsumer.subscribe(Collections.singletonList(AvroProducer.TOPIC));
            ConsumerRecords<String,Events> consumerRecords = kafkaConsumer.poll(1000);
            for(ConsumerRecord<String,Events> consumerRecord : consumerRecords){


                System.out.println(" Received message: (" + consumerRecord.partition() + " =>  " + consumerRecord.offset() +" ) "+consumerRecord.value());
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args){

        new AvroConsumer().doWork();

        synchronized (AvroConsumer.class){
            while (true){
                try {
                    AvroConsumer.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}