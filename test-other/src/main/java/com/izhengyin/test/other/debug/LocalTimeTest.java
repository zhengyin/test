package com.izhengyin.test.other.debug;


import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author zhengyin zhengyinit@outlook.com
 * Created on 2020-07-22 17:27
 */
public class LocalTimeTest {
    public static void  main(String[] args) throws InterruptedException{

        IntStream.range(1,100).forEach(v -> System.out.println(v+" -> "+(v % 20)));



        List<Integer> arr = new ArrayList<>();
        arr.add(0,100);
        arr.add(1,200);

        System.out.println(JSON.toJSONString(arr));
        arr.set(0,1000);
        arr.set(1,2000);
        System.out.println(JSON.toJSONString(arr));



       // LocalDateTime t = LocalDateTime.ofInstant(Instant.ofEpochSecond(epochSecond), ZoneId.of("UTC"));

        System.out.println(86400 - (Instant.now(Clock.system(ZoneId.of("PRC"))).getEpochSecond() % 86400 + 8 * 3600)) ;


        String a = "aaa";

        System.out.println(JSON.toJSONString(a.split("\\|")));

        while (true){



            StringBuilder builder = new StringBuilder();
            builder.append("1s -> "+(Instant.now().getEpochSecond())).append(" , ");
            builder.append("1m -> "+(Instant.now().getEpochSecond()/60)).append(" , ");
            builder.append("5m -> "+Instant.now().getEpochSecond()/300).append(" , ");
            builder.append("10m -> "+Instant.now().getEpochSecond()/600).append(" , ");
            builder.append("1h -> "+Instant.now().getEpochSecond()/3600).append(" , ");
            System.out.println(builder.toString());
            TimeUnit.SECONDS.sleep(1);
        }

    }
}
