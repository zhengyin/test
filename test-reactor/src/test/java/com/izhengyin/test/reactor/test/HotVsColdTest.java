package com.izhengyin.test.reactor.test;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.UnicastProcessor;

import java.util.Arrays;
import java.util.List;

/**
 * @author zhengyin  <zhengyin.name@gmail.com>
 * @date Created on 2019-12-18 11:04
 */
public class HotVsColdTest {

    /**
     *
     */
    @Test
    public void test1(){
        Flux<String> source = Flux.fromIterable(getList())
                .doOnNext(System.out::println)
                .filter(s -> s.startsWith("o"))
                .map(String::toUpperCase);
        source.subscribe(d -> System.out.println("Subscriber 1: "+d));
        source.subscribe(d -> System.out.println("Subscriber 2: "+d));
    }

    private List<String> getList(){
        System.out.println("Flux.fromIterable getList");
        return Arrays.asList("blue", "green", "orange", "purple");
    }

    /**
     * 对比test1
     * 第一个订阅者收到了所有的四个颜色，第二个订阅者由于是在前两个颜色发出之后订阅的， 故而收到了之后的两个颜色，在输出中有两次 "ORANGE" 和 "PURPLE"。从这个例子可见， 无论是否有订阅者接入进来，这个 Flux 都会运行。
     */
    @Test
    public void test2(){
        UnicastProcessor<String> hotSource = UnicastProcessor.create();

        Flux<String> hotFlux = hotSource.publish()
                .autoConnect()
                .map(String::toUpperCase);


        hotFlux.subscribe(d -> System.out.println("Subscriber 1 to Hot Source: "+d));

        hotSource.onNext("blue");
        hotSource.onNext("green");

        hotFlux.subscribe(d -> System.out.println("Subscriber 2 to Hot Source: "+d));

        hotSource.onNext("orange");
        hotSource.onNext("purple");
        hotSource.onComplete();
    }
}
