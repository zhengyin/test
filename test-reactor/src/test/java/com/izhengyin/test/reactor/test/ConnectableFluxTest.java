package com.izhengyin.test.reactor.test;

import org.junit.Test;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

/**
 * @author zhengyin  <zhengyin.name@gmail.com>
 * @date Created on 2019-12-18 11:13
 */
public class ConnectableFluxTest {
    @Test
    public void test() throws InterruptedException{
        Flux<Integer> source = Flux.range(1, 3)
                .doOnSubscribe(s -> System.out.println("subscribed to source"));
        ConnectableFlux<Integer> co = source.publish();
        co.subscribe(System.out::println, e -> {}, () -> {});
        co.subscribe(System.out::println, e -> {}, () -> {});

        System.out.println("done subscribing");
        Thread.sleep(5000);
        System.out.println("will now connect");

        co.connect();
    }
}
