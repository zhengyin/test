package com.izhengyin.test.reactor.test;

import org.junit.Test;
import reactor.core.Exceptions;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.*;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;

/**
 * @author zhengyin  <zhengyin.name@gmail.com>
 * @date Created on 2019-12-17 19:31
 */

public class ErrorHandleTest {

    @Test
    public void test1(){
        Flux<String> s = Flux.range(1, 2)
                .map(v -> (v * v))
                .map(v -> {
                    if(v > 20){
                        throw new IllegalArgumentException(v+" gt 20 ");
                    }
                    return v +"";
                });
        s.subscribe(
            value -> System.out.println("RECEIVED " + value),
            error -> System.err.println("CAUGHT " + error),
            () -> System.out.println("Stream complete! ")
        );
    }

    @Test
    public void test2(){
        Flux.just(10)
                .map(v -> {
                    if(v == 10){
                        throw new RuntimeException();
                    }
                    return v + "";
                })
                .onErrorReturn("RECOVERED")
                .subscribe(System.out::println);
    }

    @Test
    public void test3(){
        Flux.just(10)
                .map(v ->{
                    if(v == 10){
                        throw new RuntimeException("predicateValue");
                    }
                    return v + "";
                })
                .doOnError(err -> err.printStackTrace())
                .onErrorReturn(e -> e.getMessage().equals("predicateValue"), "RECOVERED")
                .subscribe(System.out::println);
    }


    @Test
    public void test4() throws TimeoutException{
        Flux.just("timeout1", "unknown", "key2")
                .flatMap(k -> {
                    if(k.equals("unknown")){
                        throw new RuntimeException("");
                    }
                    return Mono.just(k);
                })
                .doFinally(type -> System.out.println("SignalType "+type))
                .onErrorResume(error -> {
                    if (error instanceof TimeoutException)
                        return Flux.just("TimeoutException");
                    else
                        return Flux.error(error);
                }).subscribe(System.out::println,System.err::println);
    }



    @Test
    public void test5() throws InterruptedException{
        Flux<String> flux =
                Flux.interval(Duration.ofMillis(250))
                        .map(input -> {
                            if (input < 3) return "tick " + input;
                            throw new RuntimeException("boom");
                        })
                        .onErrorReturn("Uh oh");

        flux.subscribe(System.out::println);
        Thread.sleep(2100);
    }

    /**
     * elapsed 记录相对时间
     * @throws InterruptedException
     */
    @Test
    public void test6() throws InterruptedException{
        Flux.interval(Duration.ofMillis(250))
                .map(input -> {
                    if (input < 3) return "tick " + input;
                    throw new RuntimeException("boom");
                })
                .elapsed()
                .retry(2)
                .subscribe(System.out::println, System.err::println);

        Thread.sleep(3100);
    }

    /**
     * 重试3次
     * @throws InterruptedException
     */
    @Test
    public void test7() throws InterruptedException{
        Flux
                .<String> error(new IllegalArgumentException())
                .doOnError(System.out::println)
                .retryWhen(companion -> companion.take(3))
                .subscribe(System.out::println, System.err::println);


    }


    /**
     * 延迟错误从试
     */
    @Test
    public void test8() throws InterruptedException{
        Flux<String> flux =
                Flux.<String>error(new IllegalArgumentException())
                        .retryWhen(companion -> companion
                                .doOnNext(s -> System.out.println(s + " at " + LocalTime.now()))
                                .zipWith(Flux.range(1, 4), (error, index) -> {
                                    if (index < 4) return index;
                                    else throw Exceptions.propagate(error);
                                })
                                .flatMap(index -> Mono.delay(Duration.ofMillis(index * 100)))
                                .doOnNext(s -> System.out.println("retried at " + LocalTime.now()))
                        );
        flux.subscribe();
        TimeUnit.SECONDS.sleep(3);

    }

    @Test
    public void test9() {
        Mono<List<Integer>> integers = Flux.just(1,2,3,4)
                .map(v -> {
                    if(v ==3){
                        return null;
                    }
                    return v ++;
                })
                .collectList();

        integers.doOnNext(System.out::println)
                .subscribe();
    }

    @Test
    public void test10() throws InterruptedException{
        Mono.just("a")
                .map(v -> null)
                .onErrorReturn("b")
                .subscribe(
                        value -> System.out.println("RECEIVED " + value),
                        error -> System.err.println("CAUGHT " + error),
                        () -> System.out.println("Stream complete! ")
                );
        TimeUnit.SECONDS.sleep(1);

    }

}
