package com.izhengyin.test.reactor.test;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.reactivestreams.Subscription;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxOperator;
import reactor.core.publisher.SignalType;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

/**
 * @author zhengyin zhengyinit@outlook.com
 * Created on 2020-09-22 12:00
 */
public class CustomOperatorTest {

    @Test
    public void test1(){
        Flux<Integer> integerFlux = Flux.range(1,4)
                .doOnNext(i -> System.out.println("doOnNext "+i))
                .transform(publish -> new TimeCountOperator<Integer>(publish));
        integerFlux.subscribe();
    }

    @Test
    public void test2(){
        Flux<Integer> integerFlux = Flux.range(1,4)
                .doOnNext(i -> System.out.println("doOnNext "+i))
                .doOnNext(i -> {
                    if(i > 3){
                        throw new RuntimeException("i > 3");
                    }
                })
                .transform(publish -> new TimeCountOperator<Integer>(publish))
                .doOnError(throwable -> System.out.println("doOnError "+throwable.getMessage()))
                .doOnSubscribe(subscription -> System.out.println("doOnSubscribe "+subscription.toString()))
                .doOnComplete(() -> System.out.println("doOnComplete "+Thread.currentThread().toString()))
                .doOnRequest(l -> System.out.println("doOnRequest "+l));
        integerFlux.subscribe();
    }

    private static class TimeCountOperator<T> extends FluxOperator<T, T> {
        private final Flux<? extends T> source;
        public TimeCountOperator(Flux<? extends T> source){
            super(source);
            this.source = source;
        }

        @Override
        public void subscribe(CoreSubscriber<? super T> coreSubscriber) {
            source.subscribe(new TimeCountSubscriber<>(coreSubscriber));
        }


        static class TimeCountSubscriber<T> extends BaseSubscriber<T> {

            private final CoreSubscriber<? super T> actual;

            public TimeCountSubscriber(CoreSubscriber<? super T> actual){
                this.actual = actual;
                System.out.println("TimeCountSubscriber "+actual.getClass().toString());
                System.out.println("context " + currentContext().hashCode());
            }

            @Override
            protected void hookOnSubscribe(Subscription subscription) {
                System.out.println("hookOnSubscribe "+subscription);
                this.actual.onSubscribe(subscription);

            }

            @Override
            protected void hookOnNext(T value) {
                System.out.println("hookOnNext "+value);
                actual.onNext(value);
            }

            @Override
            protected void hookOnComplete() {
                System.out.println("hookOnComplete ");
                actual.onComplete();
            }

            @Override
            protected void hookOnError(Throwable throwable) {
                System.out.println("hookOnError "+throwable.getMessage());
                throwable.printStackTrace();
                actual.onError(throwable);
            }

            @Override
            protected void hookOnCancel() {
                System.out.println("hookOnCancel ");
            }

            @Override
            protected void hookFinally(SignalType type) {
                System.out.println("hookFinally "+type);
            }
        }
    }


}
