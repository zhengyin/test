package com.izhengyin.test.kafka.item5;

/**
 * Created by zhengyin on 2018/7/5 下午1:42.
 * Email  <zhengyin.name@gmail.com> .
 */
import com.twitter.bijection.Injection;
import com.twitter.bijection.avro.GenericAvroCodecs;
import kafka.utils.ShutdownableThread;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.ByteArrayDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.*;

public class SimpleAvroConsumer extends ShutdownableThread {
    private static Logger logger = LoggerFactory.getLogger(SimpleAvroConsumer.class);
    private KafkaConsumer<String,byte[]> consumer;
    public SimpleAvroConsumer(){
        super(SimpleAvroConsumer.class.getName(), false);
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "hadoop-worker1:9092");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "10000");
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
        props.put(ConsumerConfig.GROUP_ID_CONFIG,this.getClass().getName());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ByteArrayDeserializer.class);
        this.consumer = new KafkaConsumer<>(props);
    }


    public static void main(String[] args) {
        new Thread(new SimpleAvroConsumer()).start();


        synchronized (SimpleAvroConsumer.class){
            while (true){
                try {
                    SimpleAvroConsumer.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void doWork() {
        consumer.subscribe(Collections.singletonList("SimpleAvroTopic"));
        ConsumerRecords<String,byte[]> consumerRecords = consumer.poll(1000);
        for(ConsumerRecord<String,byte[]> consumerRecord : consumerRecords){

            System.out.println(consumerRecord.key()+" -> "+consumerRecord.value().length);

        //    Schema.Parser parser = new Schema.Parser();
        //    Schema schema = parser.parse(SimpleAvroProducer.USER_SCHEMA);
            Injection<User, byte[]> recordInjection = GenericAvroCodecs.toBinary(User.getClassSchema());
            User record = recordInjection.invert(consumerRecord.value()).get();

            System.out.println(record.toString());

            /**
             *   user.setFavoriteColor("red_"+i);
             user.setFavoriteNumber(i);
             user.setName("name_"+i);
             */
            System.out.println("favorite_color= " + record.get("favorite_color")
                    + ", name= " + record.get("name")
                    + ", favorite_number=" + record.get("favorite_number"));
        }
    }
}