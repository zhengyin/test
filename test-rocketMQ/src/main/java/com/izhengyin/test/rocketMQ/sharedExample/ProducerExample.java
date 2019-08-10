package com.izhengyin.test.rocketMQ.sharedExample;

import com.izhengyin.test.rocketMQ.Constant;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

/**
 * @author zhengyin  <zhengyin.name@gmail.com>
 * @date Created on 2019-08-09 13:56
 */
public class ProducerExample {
    public static void main(String[] args) throws MQClientException , UnsupportedEncodingException , InterruptedException , MQBrokerException , RemotingException {
        DefaultMQProducer producer = new
                DefaultMQProducer("please_rename_unique_group_name");
        producer.setNamesrvAddr(Constant.NAME_SERVICE_ADDRESS);
        producer.start();
        Long sTime = System.currentTimeMillis();
        for (int i = 0; i < Constant.TEST_MSG_NUM; i++) {
            long sendTimeMs = System.currentTimeMillis();
            Message msg = new Message("TopicTest2" ,"TagA" ,  (sendTimeMs+"-"+i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            producer.send(msg);
            System.out.println("send "+i+" , "+sendTimeMs);
        }
        System.out.println("Elapsed time "+(System.currentTimeMillis()-sTime) +" ms");
        producer.shutdown();
    }
}
