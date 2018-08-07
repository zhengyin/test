package com.izhengyin.test.other.serial;

import com.alibaba.fastjson.JSON;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.runtime.RuntimeSchema;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.ByteArrayDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhengyin on 2018/7/30 下午3:05.
 * Email  <zhengyin.name@gmail.com> .
 */
public class ProtostuffConsumer {
    private static final String BOOTSTRAP_SERVER = "hadoop-worker1:9092";
    private static RuntimeSchema<EventsStream> schema = RuntimeSchema.createFrom(EventsStream.class);

    private Properties initConfig(){
        Properties config = new Properties();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,BOOTSTRAP_SERVER);
        config.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,true);
        config.put(ConsumerConfig.GROUP_ID_CONFIG,"avro-group");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ByteArrayDeserializer.class.getName());
        return config;
    }
    public void doWork() {
        KafkaConsumer<String,byte[]> kafkaConsumer = new KafkaConsumer<String, byte[]>(initConfig());
        while (true){
            kafkaConsumer.subscribe(Collections.singletonList(ProtostuffProducer.TOPIC));
            ConsumerRecords<String,byte[]> consumerRecords = kafkaConsumer.poll(1000);
            for(ConsumerRecord<String,byte[]> consumerRecord : consumerRecords){


                /**
                 * 反序列化
                 */
                EventsStream newEvents = schema.newMessage();
                ProtostuffIOUtil.mergeFrom(consumerRecord.value(),newEvents,schema);
                System.out.println(JSON.toJSONString(newEvents));

                System.out.println(" Received message: (" + consumerRecord.partition() + " =>  " + consumerRecord.offset() +" ) "+newEvents.toString());
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args){

        new ProtostuffConsumer().doWork();

        synchronized (ProtostuffConsumer.class){
            while (true){
                try {
                    ProtostuffConsumer.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}