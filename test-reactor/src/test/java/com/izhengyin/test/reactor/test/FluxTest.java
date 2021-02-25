package com.izhengyin.test.reactor.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author zhengyin zhengyinit@outlook.com
 * Created on 2020-12-29 16:27
 */
@Slf4j
public class FluxTest {
    /**
     * 创建 flux 的方法
     */
    @Test
    public void generate(){
        Flux.just("Hello", "World").subscribe(System.out::println);
        Flux.fromArray(new Integer[] {1, 2, 3}).subscribe(System.out::println);
        Flux.empty().subscribe(System.out::println);
        Flux.range(1, 10).subscribe(System.out::println);
    }

    @Test
    public void generate2() throws InterruptedException{
        Flux<Long> flux = Flux.interval(Duration.of(500, ChronoUnit.MILLIS));
        CountDownLatch downLatch = new CountDownLatch(1);
        Executors.newFixedThreadPool(1).execute(() -> {
            flux.doOnNext(v -> System.out.println("doOnNext "+v))
                    .subscribe(v -> System.out.println("subscribe "+v));
            SleepUtils.sleep(3000);
            downLatch.countDown();
        });
        downLatch.await();
    }

    @Test
    public void generate3() {
        Flux<String> flux1 = Flux.generate(sink -> {
            sink.next("Hello 1");
            sink.complete();
        });

        log.info("p1");
        flux1
        .doOnNext(v -> {
            log.info("doOnNext "+v);
        })
        .doOnComplete(() -> {
            log.info("doOnComplete ");
        })
        .subscribe(v -> {
            log.info("subscribe "+v);
        });

        log.info("p2");
    }

    @Test
    public void generate4(){
        final Random random = new Random();
        Flux.generate(ArrayList::new, (list, sink) -> {
            int value = random.nextInt(100);
            list.add(value);
            sink.next(value);
            if (list.size() == 10) {
                sink.complete();
            }
            return list;
        }).subscribe(v -> {
            log.info("subscribe {}",v);
            SleepUtils.sleep(100);
        });
    }
}


