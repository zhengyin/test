package com.izhengyin.test.kafka.item1;

import com.izhengyin.test.kafka.conf.Const;
import kafka.utils.ShutdownableThread;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;
import java.util.Properties;

/**
 * Created by zhengyin on 2017/8/21 下午3:56.
 * Email  <zhengyin.name@gmail.com> .
 */
public class Consumer extends ShutdownableThread {
    private static volatile boolean runing = true;
    private final KafkaConsumer<Integer, String> consumer;
    private final String topic;
    private final String groupId;

    public Consumer(String topic,String groupId) {
        super(Consumer.class.getName(), false);
        this.topic = topic;
        this.groupId = groupId;
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, Const.BORKER_0);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "10000");
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, Const.KEY_DESERIALIZER_CLASS_CONFIG);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, Const.VALUE_DESERIALIZER_CLASS_CONFIG);
        consumer = new KafkaConsumer<>(props);
    }


    @Override
    public void doWork() {
        consumer.subscribe(Collections.singletonList(this.topic));
        ConsumerRecords<Integer,String> consumerRecords = consumer.poll(1000);
        for(ConsumerRecord<Integer,String> consumerRecord : consumerRecords){
            System.out.println(groupId +" Thread : "+ Thread.currentThread().getName()+ " Received message: (" + consumerRecord.key() + " => " +consumerRecord.value()+ ") at offset " + consumerRecord.offset());
        }
    }

    public static void main(String[] args){

        new Thread(new Consumer(Topic.NAME,Producer.class.getPackage().getName())).start();

        synchronized (Consumer.class){
            while (runing){
                try {
                    Consumer.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void setRuning(boolean runing) {
        Consumer.runing = runing;
    }
}
