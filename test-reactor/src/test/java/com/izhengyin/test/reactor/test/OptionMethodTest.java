package com.izhengyin.test.reactor.test;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author zhengyin  <zhengyin.name@gmail.com>
 * @date Created on 2019-12-18 12:04
 */
public class OptionMethodTest {
    @Test
    public void test1(){
        StepVerifier.create(
                Flux.fromIterable(Arrays.asList("1","2","3"))
                .log()
                .map(Integer::valueOf)
        ).expectNext(1,2,3)
        .verifyComplete();
    }

    @Test
    public void test2(){
        StepVerifier.create(
                Flux.fromIterable(Arrays.asList("1","2","3"))
                        .map(Integer::valueOf)
                        .startWith(0)
                        .concatWith(Flux.just(4))
                        .delayElements(Duration.ofMillis(100))
                        .doOnNext(System.out::println)
                        .collectList()
                        .log()
        ).expectNext(Arrays.asList(0,1,2,3,4))
                .verifyComplete();
    }

    @Test
    public void test3(){
        StepVerifier.create(
                Flux.fromIterable(Arrays.asList("1","2","3"))
                        .map(Integer::valueOf)
                        .startWith(0)
                        .concatWith(Flux.just(4))
                        .delayElements(Duration.ofMillis(100))
                        .doOnNext(System.out::println)
                        .reduce(0, (i1,i2) -> i1 + i2)
                        .log()
        ).expectNext(10)
                .verifyComplete();
    }

    /**
     * 连接数据流，按序连接 , 串联执行
     * @throws InterruptedException
     */
    @Test
    public void test4() throws InterruptedException{
        Flux.just(Arrays.asList(1,2,3)).delayElements(Duration.ofSeconds(1)).flatMap(Flux::fromIterable).doOnNext(v -> System.out.println(System.currentTimeMillis()+" fluxOne -> "+v)).concatWith(
                Flux.just(4,5,6).delayElements(Duration.ofSeconds(1)).doOnNext(v -> System.out.println(System.currentTimeMillis()+" fluxTwo -> "+v))
        )
                .doOnNext(System.out::println)
                .subscribe(v -> System.out.println("subscribe "+v));

        TimeUnit.SECONDS.sleep(10);
    }


    /**
     * 连接数据流，按元素发出的顺序合并 , 并行执行
     * @throws InterruptedException
     */
    @Test
    public void test5() throws InterruptedException{
        Flux.just(Arrays.asList(1,2,3)).delayElements(Duration.ofSeconds(1)).flatMap(Flux::fromIterable).doOnNext(v -> System.out.println(System.currentTimeMillis()+" fluxOne -> "+v)).mergeWith(
                Flux.just(4,5,6).delayElements(Duration.ofSeconds(1)).doOnNext(v -> System.out.println(System.currentTimeMillis()+" fluxTwo -> "+v))
        )
                .doOnNext(System.out::println)
                .subscribe(v -> System.out.println("subscribe "+v));
        TimeUnit.SECONDS.sleep(4);
    }


    /**
     * 连接数据流，按元素发出的顺序合并 , 并行执行. 会改变元素类型 (一对一合并,3并不会输出)
     * @throws InterruptedException
     */
    @Test
    public void test6() throws InterruptedException{
        Flux.just(Arrays.asList(0,1,2,3))
                .delayElements(Duration.ofSeconds(1))
                .flatMap(Flux::fromIterable)
                .doOnNext(v -> System.out.println(System.currentTimeMillis()+" fluxOne -> "+v))
                .zipWith(
                    Flux.just(4,5,6).delayElements(Duration.ofSeconds(1)).doOnNext(v -> System.out.println(System.currentTimeMillis()+" fluxTwo -> "+v))
                )
                .doOnNext(System.out::println)
                .subscribe(v -> System.out.println("subscribe "+v));
        TimeUnit.SECONDS.sleep(4);
    }
    @Test
    public void test7() throws InterruptedException{
        Flux.zip(
                Flux.just(Arrays.asList(0,1,2,3)).delayElements(Duration.ofSeconds(1)).flatMap(Flux::fromIterable).doOnNext(v -> System.out.println(System.currentTimeMillis()+" fluxOne -> "+v)),
                Flux.just(4,5,6).delayElements(Duration.ofSeconds(1)).doOnNext(v -> System.out.println(System.currentTimeMillis()+" fluxTwo -> "+v)),
                Flux.just("a","b","c").delayElements(Duration.ofSeconds(1)).doOnNext(v -> System.out.println(System.currentTimeMillis()+" fluxThree -> "+v))

        ).doOnNext(System.out::println).flatMap(v -> {
            Map<String,Object> map = new HashMap<>();
            map.put("t1",v.getT1());
            map.put("t2",v.getT2());
            map.put("t3",v.getT3());
            return Mono.just(map);
        }).subscribe();
        TimeUnit.SECONDS.sleep(4);
    }

    /**
     * 终止序列
     */
    @Test
    public void test8(){
        /**
         * Mono#then 终止序列
         */
        Mono.just("a").concatWith(Mono.just("b")).concatWith(Mono.just("c")).then().subscribe(System.out::println);

        /**
         * Mono#thenEmpty 终止后执行其它任务
         */
        Mono.just("a").concatWith(Mono.just("b")).concatWith(Mono.just("c")).thenEmpty(v -> System.out.println("execute other task")).subscribe(System.out::println);


        /**
         * Mono#thenMany 终止后返回一个Flux , 没闹明白为什么返回 a , b
         *
         */
         Mono.just("a").concatWith(Mono.just("b")).concatWith(Mono.just("c")).log().thenMany(v -> Flux.just("Flux Value1","Flux Value2")).log()
         .subscribe(System.out::println,throwable -> throwable.printStackTrace(),() -> System.out.println("complete"));

        /**
         * Mono#empty , 不会终止
         */
        Mono.just("a").concatWith(Mono.empty()).concatWith(Mono.just("c")).subscribe(System.out::println);

    }

    /**
     * take ， 拆分流
     */
    @Test
    public void test9() throws InterruptedException{
        Flux<String> flux = Flux.fromIterable(Arrays.asList("a","B","c","D","e","f"));
        flux.take(1).doOnNext(System.out::println).subscribe();
        flux.takeLast(3).doOnNext(System.out::println).subscribe();
        System.out.println("mergeWith -----");
        flux.take(1).delayElements(Duration.ofMillis(100)).mergeWith(
                flux.count().flux().flatMap(n -> flux.takeLast(n.intValue()-1))
        ).doOnNext(System.out::println).subscribe();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("concatWith -----");
        flux.take(1).delayElements(Duration.ofMillis(100)).concatWith(
                flux.count().flux().flatMap(n -> flux.takeLast(n.intValue()-1))
        ).doOnNext(System.out::println).subscribe();
        TimeUnit.SECONDS.sleep(1);
    }



}
