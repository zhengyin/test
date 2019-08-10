package com.izhengyin.test.pulsar.broadcastExample;

import com.izhengyin.test.pulsar.Constant;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.Schema;

/**
 * @author zhengyin  <zhengyin.name@gmail.com>
 * @date Created on 2019-08-10 13:36
 */
public class ProducerExample {
    public static void main(String[] args) throws PulsarClientException {
        PulsarClient client = PulsarClient.builder()
                .serviceUrl("pulsar://"+ Constant.BROKERS)
                .build();
        Producer<String> stringProducer = client.newProducer(Schema.STRING)
                .topic("my-topic-3")
                .create();

        Long sTime = System.currentTimeMillis();
        for (int i = 0; i < Constant.TEST_MSG_NUM; i++) {
            long sendTimeMs = System.currentTimeMillis();
            stringProducer.send(sendTimeMs+"-"+i);
            System.out.println("send "+i+" , "+sendTimeMs);
        }
        System.out.println("Elapsed time "+(System.currentTimeMillis()-sTime) +" ms");
        stringProducer.close();
        client.close();
    }
}
