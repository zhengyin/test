package com.izhengyin.test.kafka.item5;

/**
 * Created by zhengyin on 2018/7/5 下午1:38.
 * Email  <zhengyin.name@gmail.com> .
 */
import com.twitter.bijection.Injection;
import com.twitter.bijection.avro.GenericAvroCodecs;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class SimpleAvroProducer {

    public static final String USER_SCHEMA = "{"
            + "\"type\":\"record\","
            + "\"name\":\"myrecord\","
            + "\"fields\":["
            + "  { \"name\":\"str1\", \"type\":\"string\" },"
            + "  { \"name\":\"str2\", \"type\":\"string\" },"
            + "  { \"name\":\"int1\", \"type\":\"int\" }"
            + "]}";

    public static void main(String[] args) throws InterruptedException {
        Properties props = new Properties();
        props.put("bootstrap.servers", "hadoop-worker1:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer");

        //Schema.Parser parser = new Schema.Parser();
        //Schema schema = parser.parse(USER_SCHEMA);

        Injection<User, byte[]> recordInjection = GenericAvroCodecs.toBinary(User.getClassSchema());

        KafkaProducer<String, byte[]> producer = new KafkaProducer<>(props);

        for (int i = 0; i < 1000; i++) {




        //    GenericData.Record avroRecord = new GenericData.Record(User.getClassSchema());

            User user = new User();
            user.setFavoriteColor("red_"+i);
            user.setFavoriteNumber(i);
            user.setName("name_"+i);

          //  avroRecord.put("str1", "Str 1-" + i);
          //  avroRecord.put("str2", "Str 2-" + i);
          //  avroRecord.put("int1", i);


            byte[] bytes = recordInjection.apply(user);



            ProducerRecord<String, byte[]> record = new ProducerRecord<>("SimpleAvroTopic", bytes);
            producer.send(record);
            Thread.sleep(250);
        }

        producer.close();
    }
}