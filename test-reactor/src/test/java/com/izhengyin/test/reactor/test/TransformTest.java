package com.izhengyin.test.reactor.test;

import org.junit.Test;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.function.Function;

/**
 * @author zhengyin  <zhengyin.name@gmail.com>
 * @date Created on 2019-12-18 10:51
 */
public class TransformTest {
    @Test
    public void test1(){
        Function<Flux<String>, Flux<String>> filterAndMap =
                f -> f.filter(color -> !color.equals("orange"))
                        .map(String::toUpperCase);
        Flux.fromIterable(Arrays.asList("blue", "green", "orange", "purple"))
                .doOnNext(System.out::println)
                .transform(filterAndMap)
                .subscribe(d -> System.out.println("Subscriber to Transformed MapAndFilter: "+d));
    }
}
