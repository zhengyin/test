package com.izhengyin.test.other.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhengyin zhengyinit@outlook.com
 * Created on 2020-06-28 08:32
 */
public class AtomicTest {
    public static void main(String[] args){
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.incrementAndGet();
    }
}
