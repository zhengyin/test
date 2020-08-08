package com.izhengyin.test.java9feature.flow.item3;

/**
 * @author zhengyin zhengyinit@outlook.com
 * Created on 2020-08-08 13:48
 */
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.function.Consumer;

@Slf4j
public class MySubscriber implements Subscriber<Employee> {

    private Subscription subscription;

    private final Consumer<Subscription> subscriptionConsumer;

    private final OnNext onNext;

    private int counter = 0;

    public MySubscriber(Consumer<Subscription> subscriptionConsumer , OnNext onNext){
        this.subscriptionConsumer = subscriptionConsumer;
        this.onNext = onNext;
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        log.info("Subscribed");
        this.subscription = subscription;
        subscriptionConsumer.accept(subscription);
    }

    @Override
    public void onNext(Employee item) {
        counter++;
        onNext.doOnNext(this.subscription,item);
    }

    @Override
    public void onError(Throwable e) {
        log.info("Some error happened");
        e.printStackTrace();
    }

    @Override
    public void onComplete() {
        log.info("All Processing Done");
    }

    public int getCounter() {
        return counter;
    }

    public static interface OnNext{
        void doOnNext(Subscription subscription, Employee item);
    }


}