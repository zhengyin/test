package com.izhengyin.test.reactor.test;

import org.junit.Test;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

/**
 * @author zhengyin  <zhengyin.name@gmail.com>
 * @date Created on 2019-12-17 15:22
 */
public class SubscribeTest {

    @Test
    public void test1(){
        Flux<Integer> ints = Flux.range(1, 4)
                .map(i -> {
                    if (i <= 3) return i;
                    throw new RuntimeException("Got to 4");
                });
        ints.subscribe(
                v -> System.out.println("onNext "+v),
                error ->  System.err.println("onError "+error.getMessage()),
                () -> System.out.println("onComplete")
        );
    }
    @Test
    public void test2(){
        SampleSubscriber<Integer> sampleSubscriber = new SampleSubscriber<Integer>();
        Flux<Integer> ints = Flux.range(1, 4);
        ints.subscribe(i -> System.out.println("onNext "+i),
                error -> System.err.println("onError " + error),
                () -> System.out.println("onComplete"),
                s -> sampleSubscriber.request(1));
        ints.subscribe(sampleSubscriber);
        SleepUtils.sleep(1);
    }

    public class SampleSubscriber<T> extends BaseSubscriber<T> {
        public void hookOnSubscribe(Subscription subscription) {
            System.out.println("Subscribed");
            request(2);
        }
        public void hookOnNext(T value) {
            System.out.println("hookOnNext "+value);
            if((Integer) value == 1){
             //   request(1);
            }
        }


    }

}
