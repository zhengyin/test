package com.izhengyin.test.kafka.item3;

import com.izhengyin.test.kafka.conf.Const;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Kafka Producer
 * Created by zhengyin on 2017/8/21 下午3:05.
 * Email  <zhengyin.name@gmail.com> .
 */
public class Producer implements Runnable{
    private static volatile boolean runing = true;
    private final KafkaProducer<String,String> producer;
    private final String topic;
    private final int id;
    public Producer(int id , String topic){
        Properties props = new Properties();
        props.put("bootstrap.servers", Const.BORKER_LIST );
        props.put("client.id", Producer.class.getName()+"."+id);
        props.put("key.serializer", Const.KEY_SERIALIZER_CLASS_CONFIG);
        props.put("value.serializer", Const.VALUE_SERIALIZER_CLASS_CONFIG);
        this.producer = new KafkaProducer<>(props);
        this.topic = topic;
        this.id = id;
    }

    @Override
    public void run() {

        while (true){
            String curTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            String message = "Message_"+curTime;
            long startTime = System.currentTimeMillis();
            ProducerRecord<String,String> record = new ProducerRecord<>(topic,curTime+"",message);
            producer.send(record,(metadata,exception) -> {
                long elapsedTime = System.currentTimeMillis() - startTime;
                if(metadata != null){
                    System.out.println(
                            "Producer["+id+"] Success: message(" +record.key()+" => "+ record.value() + ") sent to partition(" + metadata.partition() +
                                    "), " +
                                    "offset(" + metadata.offset() + ") in " + elapsedTime + " ms");
                }else{
                    System.out.println(
                            "Producer["+id+"] Error: message(" + record.value() + ")  " +
                                    " in " + elapsedTime + " ms  ");
                }
                if(exception !=  null){
                    exception.printStackTrace();
                }
            });
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        for(int i =1;i<=3;i++){
            new Thread(new Producer(i,Topic.NAME)).start();
        }

        synchronized (Producer.class){
            while (runing){
                try {
                    Producer.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("2");
        }

    }

    public static void setRuning(boolean runing) {
        Producer.runing = runing;
    }
}
