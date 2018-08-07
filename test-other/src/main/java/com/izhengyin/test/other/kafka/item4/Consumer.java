package com.izhengyin.test.other.kafka.item4;

import com.izhengyin.test.other.kafka.conf.Const;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.errors.SerializationException;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhengyin on 2017/8/21 下午3:56.
 * Email  <zhengyin.name@gmail.com> .
 */
public class Consumer implements Runnable {
    private static volatile boolean runing = true;
    private final KafkaConsumer<String, String> consumer;
    private final String topic;
    private final String groupId;
    private final int id;

    public Consumer(int id, String topic,String groupId) {
        //super(Consumer.class.getName(), false);
        this.id = id;
        this.topic = topic;
        this.groupId = groupId;
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:9094");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.CLIENT_ID_CONFIG,Consumer.class.getName()+"."+id);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "10000");
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
        props.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG,"15000");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, Const.KEY_DESERIALIZER_CLASS_CONFIG);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, Const.VALUE_DESERIALIZER_CLASS_CONFIG);
        consumer = new KafkaConsumer<>(props);
    }


    @Override
    public void run() {
        consumer.subscribe(Collections.singletonList(this.topic), new ConsumerRebalanceListener() {
            @Override
            public void onPartitionsRevoked(Collection<TopicPartition> collection) {
                Iterator<TopicPartition> iterator = collection.iterator();
                while (iterator.hasNext()){
                    TopicPartition partition = iterator.next();
                    System.out.println("onPartitionsRevoked : "+partition.toString());
                }
            }

            @Override
            public void onPartitionsAssigned(Collection<TopicPartition> collection) {
                Iterator<TopicPartition> partitionIterator = collection.iterator();
                while (partitionIterator.hasNext()){
                    TopicPartition partition = partitionIterator.next();
                    System.out.println("onPartitionsAssigned : "+partition.toString());
                }

            }
        });

        while (true){
            try{
                ConsumerRecords<String,String> consumerRecords = consumer.poll(1000);
                for(ConsumerRecord<String,String> consumerRecord : consumerRecords){
                    System.out.println("Consumer["+id+"] "+groupId +" Thread : "+ Thread.currentThread().getName()+ " Received message: (" + consumerRecord.key() + " => " +consumerRecord.value()+ ") at offset " + consumerRecord.offset()+" partition["+consumerRecord.partition()+"]");
                }

            }catch (SerializationException e){      // Handle Exception  @see https://issues.apache.org/jira/browse/KAFKA-4740
                System.out.println(e.getMessage());
                String[] tmpArr = e.getMessage().split(" ");
                if(tmpArr.length == 9){
                    int partition = Integer.valueOf(tmpArr[tmpArr.length-4].replace(this.topic+"-",""));
                    int offset = Integer.valueOf(tmpArr[tmpArr.length-1]);
                    TopicPartition topicPartition = new TopicPartition(topic,partition);
                    consumer.seek(topicPartition,offset+1);
                    System.out.println("SerializationException :  seek "+topicPartition+" to offset "+(offset+1));
                }
            }
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args){
        System.setProperty("org.apache.kafka","DEBUG");
        new Thread(new Consumer(1, Topic.NAME,Producer.class.getPackage().getName())).start();
        synchronized (Consumer.class){
            while (runing){
                try {
                    Consumer.class.wait();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("2");
        }
    }

    public static void setRuning(boolean runing) {
        Consumer.runing = runing;
    }
}
