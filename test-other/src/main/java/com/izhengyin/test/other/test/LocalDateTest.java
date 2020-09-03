package com.izhengyin.test.other.test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author zhengyin zhengyinit@outlook.com
 * Created on 2020-08-12 13:49
 */
public class LocalDateTest {
    public static void main(String[] args){
        // 当前日期和时间 System.out.println(DateNow);
        String dateNow = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
        System.out.println(dateNow);


    }
}
