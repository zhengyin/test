package com.izhengyin.test.reactor.test;

import org.junit.Test;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author zhengyin  <zhengyin.name@gmail.com>
 * @date Created on 2019-12-17 18:46
 */
public class GenerateTest {
    @Test
    public void test1(){
        Flux<String> flux = Flux.generate(
                () -> 0,
                (state, sink) -> {
                    sink.next("3 x " + state + " = " + 3*state);
                    if (state == 10) sink.complete();
                    return state + 1;
                });
        flux.subscribe(System.out::println);
    }

    @Test
    public void test2(){
        Flux<String> flux = Flux.generate(
                AtomicLong::new,
                (state, sink) -> {
                    long i = state.getAndIncrement();
                    sink.next("3 x " + i + " = " + 3*i);
                    if (i == 10) sink.complete();
                    return state;
                }, (state) -> System.out.println("state: " + state));

        flux.subscribe(System.out::println);
    }
}
