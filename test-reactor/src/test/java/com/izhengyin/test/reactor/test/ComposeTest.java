package com.izhengyin.test.reactor.test;

import org.junit.Test;
import reactor.core.publisher.Flux;
import sun.misc.Unsafe;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

/**
 * @author zhengyin  <zhengyin.name@gmail.com>
 * @date Created on 2019-12-18 10:58
 */
public class ComposeTest {

    @Test
    public void test(){

        AtomicInteger ai = new AtomicInteger();
        Function<Flux<String>, Flux<String>> filterAndMap = f -> {
            if (ai.incrementAndGet() == 1) {
                return f.filter(color -> !color.equals("orange"))
                        .map(String::toUpperCase);
            }
            return f.filter(color -> !color.equals("purple"))
                    .map(String::toUpperCase);
        };


        Flux<String> composedFlux =
                Flux.fromIterable(Arrays.asList("blue", "green", "orange", "purple"))
                        .doOnNext(System.out::println)
                        .compose(filterAndMap);

        composedFlux.subscribe(d -> System.out.println("Subscriber 1 to Composed MapAndFilter :"+d));
        composedFlux.subscribe(d -> System.out.println("Subscriber 2 to Composed MapAndFilter: "+d));
    }
}
