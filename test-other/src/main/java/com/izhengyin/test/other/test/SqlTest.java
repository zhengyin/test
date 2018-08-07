package com.izhengyin.test.other.test;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by zhengyin on 2017/4/12.
 */
public class SqlTest {

    public static void main(String[] args) {
        Timestamp timestamp = new Timestamp(new Date().getTime());


        System.out.println(timestamp.getTime());


    }
}
