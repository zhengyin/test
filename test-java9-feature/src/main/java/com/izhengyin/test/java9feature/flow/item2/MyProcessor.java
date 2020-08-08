package com.izhengyin.test.java9feature.flow.item2;

/**
 * @author zhengyin zhengyinit@outlook.com
 * Created on 2020-08-08 14:46
 */

import java.util.concurrent.Flow.Processor;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.SubmissionPublisher;
import java.util.function.Function;


public class MyProcessor extends SubmissionPublisher<Freelancer> implements Processor<Employee, Freelancer> {

    private Subscription subscription;
    private Function<Employee,Freelancer> function;
    private final OnNext onNext;

    public MyProcessor(Function<Employee,Freelancer> function , OnNext onNext) {
        super();
        this.function = function;
        this.onNext = onNext;
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(Employee emp) {
        onNext.doOnNext(this.subscription,(Freelancer) function.apply(emp),this);
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
    }

    @Override
    public void onComplete() {
        System.out.println("Done");
    }
    public static interface OnNext{
        void doOnNext(Subscription subscription, Freelancer item , MyProcessor publisher);
    }
}