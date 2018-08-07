package com.izhengyin.test.other.serial;

import com.alibaba.fastjson.JSON;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhengyin on 2018/7/30 下午3:05.
 * Email  <zhengyin.name@gmail.com> .
 */
public class AvroProducer {
    private static final String BROKER_LIST = "hadoop-worker1:9092";
    public static final String TOPIC = "test.avro.serializer";

    private static Properties initconfig(){
        Properties config = new Properties();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,BROKER_LIST);//broker_list
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, AvroSerializer.class.getName());
        return config;
    }

    public static void main(String[] args){
        KafkaProducer producer = new KafkaProducer<>(initconfig());
        for (int i = 0; i< 10 ; i ++){
            Events events = new Events();
            events.setId((long) i);
            events.setAppName("孔夫子旧书网");
            ProducerRecord<String,Events> record = new ProducerRecord<>(TOPIC,events);
            producer.send(record, (metadata,exception) -> {
                System.out.println("send");
                System.out.println(metadata.partition()+" "+metadata.offset());
                System.out.println(exception);
            });

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("END");
    }
}