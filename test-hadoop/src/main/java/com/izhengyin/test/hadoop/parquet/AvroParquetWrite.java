package com.izhengyin.test.hadoop.parquet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhengyin on 2018/6/8 下午4:42.
 * Email  <zhengyin.name@gmail.com> .
 */
public class AvroParquetWrite {
    private static Logger logger = LoggerFactory.getLogger(AvroParquetWrite.class);
    public static void main(String[] args){
        String filename = args[0];
        logger.info("write file : "+filename);


    }
}
