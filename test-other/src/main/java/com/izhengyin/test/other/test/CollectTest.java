package com.izhengyin.test.other.test;

import com.alibaba.fastjson.JSON;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

/**
 * Created by zhengyin on 2017/6/12.
 */
public class CollectTest {

    public static void main(String[] args) {
        ConcurrentHashMap<Integer,Integer> c = new ConcurrentHashMap<>();
        IntStream.range(1,10).forEach(i -> c.put(i,i));
        System.out.println(JSON.toJSONString(c));
        c.values().removeIf(v -> v > 5);
        System.out.println(JSON.toJSONString(c));
    }


    public static void test1(){
        Map<Integer,String> params = new HashMap<>();

        params.put(1,"a");
        params.put(3,"c");
        params.put(2,"b");
        params.put(5,"e");
        params.put(4,"d");

        params.forEach( (k,v) -> {
            System.out.println(k + "=>" + v);
        });

        System.out.println(" = = = = = = = = = = = = = = = = =>");

        Map<String,Integer> params2 = new HashMap<>();
        params2.put("a",2);
        params2.put("c",1);
        params2.put("b",3);
        params2.put("e",5);
        params2.put("1",4);
        params2.put("3",7);
        params2.put("2",6);


        for(String key: params2.keySet()){
            System.out.println(key + "=>" + params2.get(key));
        }

        System.out.println(" = = = = = = = = = = = = = = = = =>");


        params2.forEach( (k,v) -> {
            System.out.println(k + "=>" + v);
        });

        System.out.println(" = = = = = = = = = = = = = = = = =>");


        Iterator<String> iter = params2.keySet().iterator();
        while (iter.hasNext()){
            String key = iter.next();
            System.out.println(key + "=>" + params2.get(key));
            continue;
        }


        System.out.println(" = = = = = = = = = = = = = = = = =>");

        SortedMap<String,Integer> params3 = new TreeMap<>(params2);

        params3.forEach( (k,v) -> {
            System.out.println(k + "=>" + v);
        });



        SortedMap<String,Integer> params4 = new TreeMap<>();
    }


    public static void testConcurrentHashMap(){
        ConcurrentHashMap map = new ConcurrentHashMap();
        map.size();

        ForkJoinPool forkJoinPool = new ForkJoinPool();

    }
}
