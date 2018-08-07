package com.izhengyin.test.hadoop.parquet;

import org.apache.parquet.schema.MessageType;
import org.apache.parquet.schema.MessageTypeParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhengyin on 2018/6/9 下午5:18.
 * Email  <zhengyin.name@gmail.com> .
 *
 */
public class HiveParquet {

    /**
     * hive table parquet_test
     * CREATE TABLE parquet_test (
         id int,
         str string,
         mp MAP<STRING,STRING>,
         lst ARRAY<STRING>,
         strct STRUCT<A:STRING,B:STRING>)
         PARTITIONED BY (part string)
         STORED AS PARQUET;
     */

    private static Logger logger = LoggerFactory.getLogger(HiveParquet.class);
    private static String dataPath = "/user/hive/warehouse/parquet_test";
    private static MessageType schema = MessageTypeParser.parseMessageType("parquet_test {" +
            "required int32 int (UTF8); " +
            "required binary str (UTF8);" +
            "repeated group mp {" +
                    "repeated group map { "+
                        "required binary key; " +
                        "required binary value;" +
                    "}" +
            "}"+
    "}");
    public static void main(String[] args){

    }

}
