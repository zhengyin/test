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
       // SampleSubscriber<Integer> ss = new SampleSubscriber<Integer>();
       // Flux<Integer> ints = Flux.range(1, 4);
        SampleSubscriber<Long> ss = new SampleSubscriber<>();
        Flux<Long> ints = Flux.interval(Duration.ofMillis(300), Schedulers.newSingle("test"));
        /*
        ints.subscribe(i -> System.out.println(i),
                error -> System.err.println("Error " + error),
                () -> {System.out.println("Done");},
                s -> ss.request(10));

         */
        ints.subscribe(ss);
    }

    public class SampleSubscriber<T> extends BaseSubscriber<T> {
        public void hookOnSubscribe(Subscription subscription) {
            System.out.println("Subscribed");
            request(1);
        }

        public void hookOnNext(T value) {
            System.out.println("hookOnNext "+value);
            request(1);
        }
    }

}
