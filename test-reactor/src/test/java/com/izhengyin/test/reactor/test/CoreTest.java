package com.izhengyin.test.reactor.test;

import org.junit.Test;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * @author zhengyin zhengyinit@outlook.com
 * Created on 2020-09-09 17:32
 */
public class CoreTest {

    @Test
    public void test1(){
        MySubscriber<Integer> ss = new MySubscriber<Integer>();
        Flux<Integer> ints = Flux.range(1, 4).delayElements(Duration.ofSeconds(1));
        ints.subscribe(i -> System.out.println(i),
                error -> System.err.println("Error " + error),
                () -> {System.out.println("Done");},
                s -> ss.request(10));
      //  ints.subscribe(ss);


        SleepUtils.sleep(6000);
    }




    private static class MySubscriber<T> extends BaseSubscriber<T>{
        @Override
        protected void hookOnSubscribe(Subscription subscription) {
            System.out.println("hookOnSubscribe");
            request(1);
        }

        @Override
        protected void hookOnNext(T value) {
            System.out.println("hookOnNext "+value);
            request(1);
        }

        @Override
        protected void hookOnComplete() {
            System.out.println("hookOnComplete");
        }
    }
}
