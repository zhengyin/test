package com.izhengyin.test.spark.sql;

import com.izhengyin.test.spark.RddTest;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;

/**
 * Created by zhengyin on 2018/8/11 下午2:16.
 * Email  <zhengyin.name@gmail.com> .
 */
public class SparkSqlOnHiveTest {

    public static void main(String[] args){
        SparkConf conf = new SparkConf();
        conf.setMaster("local");
        conf.setAppName(RddTest.class.getName());
        JavaSparkContext jsc = new JavaSparkContext(conf);


    }
}
