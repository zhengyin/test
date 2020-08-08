package com.izhengyin.test.java9feature.flow.item1;

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
                    //SleepUtils.milliSecond(200); 消费的慢一点
                    subscription.request(1);
                })
        );

        //关联消费者
        publisher.subscribe(subs);


        List<Employee> emps = EmpHelper.getEmps();

        // Publish items
        log.info("Publishing Items to Subscriber");
        emps.stream().forEach(item -> {
            //SleepUtils.milliSecond(100); 发送的慢一点
            log.info("Publish {} , {}",item.toString(), publisher.submit(item));
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
