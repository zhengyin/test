package com.izhengyin.test.reactor.test;

import org.junit.Test;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhengyin zhengyinit@outlook.com
 * Created on 2020-12-19 10:24
 */
public class SyncTest {

    @Test
    public void test1(){
        List<String> values = Flux.just("a","b","c","d")
                .toStream()
                .collect(Collectors.toList());

        System.out.println(values);

    }
}
