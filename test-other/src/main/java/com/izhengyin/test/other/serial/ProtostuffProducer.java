package com.izhengyin.test.other.serial;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.runtime.RuntimeSchema;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhengyin on 2018/7/30 下午3:05.
 * Email  <zhengyin.name@gmail.com> .
 */
public class ProtostuffProducer {
    private static final String BROKER_LIST = "hadoop-worker1:9092";
    public static final String TOPIC = "test.protostuff.serializer";
    private static RuntimeSchema<EventsStream> schema = RuntimeSchema.createFrom(EventsStream.class);

    private static Properties initconfig(){
        Properties config = new Properties();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,BROKER_LIST);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ByteArraySerializer.class.getName());
        return config;
    }

    public static void main(String[] args){
        KafkaProducer producer = new KafkaProducer<>(initconfig());
        for (int i = 0; i< 10 ; i ++){
            EventsStream events = new EventsStream();
            events.setId((long) i);
            events.setApp_name("空位子旧书网 -> " + i);
            final byte[] bytes = ProtostuffIOUtil.toByteArray(events,schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));

            ProducerRecord<String,byte[]> record = new ProducerRecord<>(TOPIC,bytes);
            producer.send(record, (metadata,exception) -> {
                System.out.println("send");
                System.out.println(metadata.partition()+" "+metadata.offset()+" bytes:"+bytes.length);
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