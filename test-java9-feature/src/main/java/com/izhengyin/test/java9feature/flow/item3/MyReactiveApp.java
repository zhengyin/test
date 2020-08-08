package com.izhengyin.test.java9feature.flow.item3;

import com.izhengyin.test.java9feature.flow.SleepUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.SubmissionPublisher;

/**
 * @author zhengyin zhengyinit@outlook.com
 * Created on 2020-08-08 13:48
 */
@Slf4j
public class MyReactiveApp {
    public static void main(String args[]) throws InterruptedException {

        // Create Publisher
        SubmissionPublisher<Employee> publisher = new SubmissionPublisher<>();

        // Register Subscriber
        MySubscriber subs = new MySubscriber(
                //订阅成功事件通知(如果不请求数据流，将不会消费)
                subscription -> subscription.request(1),
                //接受推送数据事件通知
                ((subscription, item) -> {
                    log.info("Subscribe "+item.toString());
                    // 消费的慢一点,模拟消费延迟
                    SleepUtils.milliSecond(100);
                    subscription.request(1);
                })
        );

        //关联消费者
        publisher.subscribe(subs);


        List<Employee> emps = EmpHelper.getEmps();

        // Publish items
        log.info("Publishing Items to Subscriber");
        emps.stream().forEach(item -> {
            int lag = publisher.submit(item);
            log.info("Publish {} , lag {}",item.toString(), lag);
            if(lag > 1){
                //控制发送速率
                log.info("Publish sleep {}",lag * 100);
                SleepUtils.milliSecond(lag * 100);
            }
        });

        // logic to wait till processing of all messages are over
        while (emps.size() != subs.getCounter()) {
            Thread.sleep(1000);
            log.info("waiting sleep ...");
        }
        // close the Publisher
        log.info("close the Publisher");
        publisher.close();
        Thread.sleep(1000);
        log.info("Exiting the app");
    }
}
