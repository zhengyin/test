package com.izhengyin.test.reactor.test;

import org.junit.Test;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.*;
import reactor.util.context.Context;

import java.time.Duration;
import java.util.Arrays;
import java.util.Optional;
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

    @Test
    public void test2(){
        Mono<String> mono = Mono.just("a")
                .delayElement(Duration.ofSeconds(1))
                .doOnNext(System.out::println)
                .filter(v -> v.equals("b"))
                .transform(new Transformer<String>());
        mono.subscribe();
        SleepUtils.sleep(
                1200
        );
    }


    @Test
    public void test3(){
        Flux<String> mono = Flux.just("a","b","c")
                .delayElements(Duration.ofMillis(100))
                .doOnNext(System.out::println)
                .doOnNext(v -> {
                    if(v.equals("c")){
                        throw new RuntimeException("c");
                    }
                })
                .transform(new Transformer<String>());
        mono.subscribe();
        SleepUtils.sleep(
                3200
        );
    }

    /**
     * A transformer that transforms given {@code Publisher} to a wrapped Sentinel reactor operator.
     *
     * @author Eric Zhao
     * @since 1.5.0
     */
    public class Transformer<T> implements Function<Publisher<T>, Publisher<T>> {

        @Override
        public Publisher<T> apply(Publisher<T> publisher) {
            if (publisher instanceof Mono) {
                return new MonoOperator<T,T>((Mono<? extends T>)publisher) {
                    @Override
                    public void subscribe(CoreSubscriber<? super T> coreSubscriber) {
                        publisher.subscribe(new MySubscriber<>(coreSubscriber));
                    }
                };
            }
            if(publisher instanceof Flux){
                return new FluxOperator<T,T>((Flux<? extends T>) publisher) {
                    @Override
                    public void subscribe(CoreSubscriber<? super T> coreSubscriber) {
                        publisher.subscribe(new MySubscriber<>(coreSubscriber));
                    }
                };
            }
            throw new IllegalArgumentException("publisher type error");
        }
    }



    private static class MySubscriber<T> extends BaseSubscriber<T>{

        private final CoreSubscriber<? super T> actual;

        public MySubscriber(CoreSubscriber<? super T> actual){
            this.actual = actual;
        }

        @Override
        protected void hookOnSubscribe(Subscription subscription) {
            System.out.println("hookOnSubscribe");
            this.actual.onSubscribe(subscription);
        }

        @Override
        protected void hookOnNext(T value) {
            System.out.println("hookOnNext");
            actual.onNext(value);
        }

        @Override
        protected void hookOnComplete() {
            System.out.println("hookOnComplete");
            actual.onComplete();
        }

        @Override
        protected void hookOnError(Throwable t) {
            System.out.println("hookOnError");
            t.printStackTrace();
            actual.onError(t);
        }


    }
}
