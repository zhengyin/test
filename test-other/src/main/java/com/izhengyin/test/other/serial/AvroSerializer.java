package com.izhengyin.test.other.serial;

/**
 * Created by zhengyin on 2018/7/30 下午2:56.
 * Email  <zhengyin.name@gmail.com> .
 */

import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.common.serialization.Serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

/**
 * 序列化
 * @param <T>
 */
public class AvroSerializer<T extends SpecificRecordBase> implements Serializer<T> {
    @Override
    public void configure(Map<String, ?> map, boolean b) {}

    @Override
    public byte[] serialize(String topic, T data) {
        if (data == null){
            return null;
        }
        DatumWriter<T> writer = new SpecificDatumWriter<>(data.getSchema());  //将data的schema装进去
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        BinaryEncoder encoder = EncoderFactory.get().directBinaryEncoder(outputStream,null);
        try {
            writer.write(data,encoder);
        }catch (IOException e){
            e.printStackTrace();
        }
        return outputStream.toByteArray();
    }

    @Override
    public void close() {}
}
