package com.izhengyin.test.pulsar.broadcastExample;

import com.izhengyin.test.pulsar.Constant;
import org.apache.pulsar.client.api.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhengyin  <zhengyin.name@gmail.com>
 * @date Created on 2019-08-10 13:37
 */
public class Consumer2Example {
    public static void main(String[] args) throws PulsarClientException {
        PulsarClient client = PulsarClient.builder()
                .serviceUrl("pulsar://"+ Constant.BROKERS)
                .build();
        Consumer consumer = client.newConsumer()
                .topic("my-topic-3")
                .subscriptionName(Consumer2Example.class.getName().replace(".","-"))
                .subscriptionType(SubscriptionType.Exclusive)
                .subscribe();

        List<Long> delays = new ArrayList<>();
        while (true) {
            // Wait for a message
            Message msg = consumer.receive();
            try {
                String[] arr = new String(msg.getData()).split("-");
                Long sendTimeMs = Long.valueOf(arr[0]);
                Integer messageId = Integer.valueOf(arr[1]);
                Long delay = (System.currentTimeMillis()-sendTimeMs);
                System.out.println(messageId+ " delay , "+ delay);
                consumer.acknowledge(msg);
                delays.add(delay);
                if(delays.size() == Constant.TEST_MSG_NUM){
                    print(delays);
                    System.exit(1);
                }
            } catch (Exception e) {
                System.out.println(e.getClass().getName()+" , "+e.getMessage());
                // Message failed to process, redeliver later
                consumer.negativeAcknowledge(msg);
            }
        }
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
