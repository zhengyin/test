package com.izhengyin.test.java9feature.flow.item2;

/**
 * @author zhengyin zhengyinit@outlook.com
 * Created on 2020-08-08 14:47
 */
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.function.Consumer;

@Slf4j
public class MyFreelancerSubscriber implements Subscriber<Freelancer> {

    private Subscription subscription;

    private int counter = 0;


    private final Consumer<Subscription> subscriptionConsumer;

    private final OnNext onNext;


    public MyFreelancerSubscriber(Consumer<Subscription> subscriptionConsumer , OnNext onNext){
        this.subscriptionConsumer = subscriptionConsumer;
        this.onNext = onNext;
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        log.info("Subscribed for Freelancer");
        this.subscription = subscription;
        subscriptionConsumer.accept(subscription);
    }

    @Override
    public void onNext(Freelancer item) {
        counter++;
        onNext.doOnNext(this.subscription,item);
    }

    @Override
    public void onError(Throwable e) {
        System.out.println("Some error happened in MyFreelancerSubscriber");
        e.printStackTrace();
    }

    @Override
    public void onComplete() {
        System.out.println("All Processing Done for MyFreelancerSubscriber");
    }

    public int getCounter() {
        return counter;
    }


    public static interface OnNext{
        void doOnNext(Subscription subscription, Employee item);
    }

}