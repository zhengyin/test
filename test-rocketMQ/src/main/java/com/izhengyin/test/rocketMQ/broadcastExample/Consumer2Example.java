package com.izhengyin.test.rocketMQ.broadcastExample;

import com.izhengyin.test.rocketMQ.Constant;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhengyin  <zhengyin.name@gmail.com>
 * @date Created on 2019-08-09 14:38
 */
public class Consumer2Example {
    public static void main(String[] args) throws InterruptedException, MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("broadcastExample");
        consumer.setNamesrvAddr(Constant.NAME_SERVICE_ADDRESS);
        consumer.subscribe("TopicTest3", "*");
        consumer.setMessageModel(MessageModel.BROADCASTING);
        List<Long> delays = new ArrayList<>();
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,ConsumeConcurrentlyContext context) {
                for(MessageExt messageExt : msgs){
                    String[] arr = new String(messageExt.getBody()).split("-");
                    Long sendTimeMs = Long.valueOf(arr[0]);
                    Integer messageId = Integer.valueOf(arr[1]);
                    Long delay = (System.currentTimeMillis()-sendTimeMs);
                    System.out.println(messageId+ " delay , "+ delay);
                    delays.add(delay);
                    if(delays.size() == Constant.TEST_MSG_NUM){
                        print(delays);
                        System.exit(1);
                    }
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
        System.out.printf("Consumer1Example Started.%n");
    }
    private static void print(List<Long> delays){
        System.out.println("max : "+delays.stream()
                .mapToLong(v -> v)
                .max());
        System.out.println("min : "+delays.stream()
                .mapToLong(v -> v)
                .min());
        System.out.println("average : "+delays.stream()
                .mapToLong(v -> v)
                .average().getAsDouble());

    }
}
