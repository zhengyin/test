package com.izhengyin.test.java9feature.flow.item2;

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

        //将 Employee 转换为 Freelancer
        MyProcessor transformProcessor = new MyProcessor(
                employee -> {
                    log.info("#2 接受 Employee 转换为 Freelancer "+employee.toString());
                    return new Freelancer(employee.getId(), employee.getId() + 100, employee.getName());
                },
                ((subscription, item,transformPublisher) -> {
                    SleepUtils.milliSecond(1000);
                    log.info("#3 发布 Freelancer  "+item.toString());
                    transformPublisher.submit(item);
                    subscription.request(1);
                })
        );

        // Register Subscriber
        MyFreelancerSubscriber subs = new MyFreelancerSubscriber(
                //订阅成功事件通知(如果不请求数据流，将不会消费)
                subscription -> subscription.request(1),
                //接受推送数据事件通知
                ((subscription, item) -> {
                    SleepUtils.milliSecond(1000);
                    log.info("#4 消费 Freelancer  "+item.toString());
                    subscription.request(1);
                })
        );

        //关联 Employee  消费者
        publisher.subscribe(transformProcessor);
        //关联 Freelancer 消费者
        transformProcessor.subscribe(subs);

        List<Employee> emps = EmpHelper.getEmps();

        // Publish items
        log.info("Publishing Items to Subscriber");
        emps.stream().forEach(item -> {

            SleepUtils.milliSecond(1000);
            log.info("#1 发布 Employee {} , {}",item.toString(), publisher.submit(item));
        });

        // logic to wait till processing of all messages are over
        while (emps.size() != subs.getCounter()) {
            SleepUtils.second(3);
            log.info("waiting sleep ...");
        }
        // close the Publisher
        log.info("close the Publisher");
        publisher.close();
        SleepUtils.second(1);
        log.info("Exiting the app");
    }
}
