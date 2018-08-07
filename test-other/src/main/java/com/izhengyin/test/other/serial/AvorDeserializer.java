package com.izhengyin.test.other.serial;

/**
 * Created by zhengyin on 2018/7/30 下午2:58.
 * Email  <zhengyin.name@gmail.com> .
 */

import org.apache.avro.io.BinaryDecoder;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Map;

/**
 * 反序列化
 */
public class AvorDeserializer<T extends SpecificRecordBase> implements Deserializer<T> {

    @Override
    public void configure(Map<String, ?> map, boolean b) {}

    @Override
    public T deserialize(String topic, byte[] data) {
        if (data == null){
            return null;
        }
        try {
            //得到主题对应的数据类型
            TopicEnum topicEnum = TopicEnum.getTopicEnum(topic);
            if (topicEnum == null){
                return null;
            }

            SpecificRecordBase record = topicEnum.getRecord();
            DatumReader<T> datumReader = new SpecificDatumReader<>(record.getSchema());
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
            BinaryDecoder decoder = DecoderFactory.get().directBinaryDecoder(byteArrayInputStream,null);
            return  datumReader.read(null,decoder);

        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void close() {}
}
