package com.izhengyin.test.other.test;

import java.util.regex.Pattern;

/**
 * @author zhengyin zhengyinit@outlook.com
 * Created on 2020-10-28 10:40
 */
public class PatternTest {
    public static void main(String[] args) {
        String a = "com.kongfz.cloud.pmservice.controller.v1.biddingitemlistcontroller$$enhancerbyspringcglib$$6e0ab6c0.getcatsublist";
        Pattern pattern = Pattern.compile("$$enhancerbyspringcglib$$\\w+\\.");
        Pattern pattern2 = Pattern.compile("\\$\\$enhancerbyspringcglib\\$\\$\\w+");
        System.out.println(pattern2.matcher(a).replaceAll(""));

    }
}
