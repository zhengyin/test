package com.izhengyin.test.kafka.conf;

/**
 * Created by zhengyin on 2017/8/21 下午3:06.
 * Email  <zhengyin.name@gmail.com> .
 */
public interface Const {
    public static final String BORKER_0 = "kafka:9092";
    public static final String BORKER_1 = "kafka:9093";
    public static final String BORKER_2 = "kafka:9094";
    public static final String BORKER_LIST = "kafka:9092,kafka:9093,kafka:9094";
    public static final String ZOOKEEPER_LIST = "zoo1:2181,zoo2:2182,zoo3:2183";
    public static final String KEY_SERIALIZER_CLASS_CONFIG = "org.apache.kafka.common.serialization.StringSerializer";
    public static final String VALUE_SERIALIZER_CLASS_CONFIG = "org.apache.kafka.common.serialization.StringSerializer";
    public static final String KEY_DESERIALIZER_CLASS_CONFIG = "org.apache.kafka.common.serialization.StringDeserializer";
    public static final String VALUE_DESERIALIZER_CLASS_CONFIG = "org.apache.kafka.common.serialization.StringDeserializer";
}
