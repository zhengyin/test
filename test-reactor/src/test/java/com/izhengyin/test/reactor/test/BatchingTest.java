package com.izhengyin.test.reactor.test;

import org.junit.Test;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.GroupedFlux;
import reactor.core.publisher.Hooks;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.stream.IntStream;

/**
 * @author zhengyin  <zhengyin.name@gmail.com>
 * @date Created on 2019-12-18 11:19
 */
public class BatchingTest {

    @Test
    public void test(){
        Hooks.onOperatorDebug();
        StepVerifier.create(
                Flux.just(1, 3, 5, 2, 4, 6, 11, 12, 13)
                        .groupBy(i -> i % 2 == 0 ? "even" : "odd")
                        .concatMap(this::option) //以该组的 key 开头
                                .doOnNext(System.out::println)
        )
                .expectNext("odd", "1", "3", "5", "11", "13")
                .expectNext("even", "2", "4", "6", "12")
                .verifyComplete();

    }
    @Test
    public void test1(){

        StepVerifier.create(
                Flux.range(1, 10)
                        .window(5, 3) //overlapping windows
                        .concatMap(g -> g.defaultIfEmpty(-1)) //将 windows 显示为 -1
        )
                .expectNext(1, 2, 3, 4, 5)
                .expectNext(4, 5, 6, 7, 8)
                .expectNext(7, 8, 9, 10)
                .expectNext(10)
                .verifyComplete();

    }



    private Publisher<String> option(GroupedFlux<String,Integer> g){
        return g.defaultIfEmpty(-1) //如果组为空，显示为 -1
                .map(String::valueOf) //转换为字符串
                .delayElements(Duration.ofSeconds(1))
                .startWith(g.key());
    }


}
