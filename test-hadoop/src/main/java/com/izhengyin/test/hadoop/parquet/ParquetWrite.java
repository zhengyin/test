package com.izhengyin.test.hadoop.parquet;

import com.alibaba.fastjson.JSON;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.column.ParquetProperties;
import org.apache.parquet.example.data.Group;
import org.apache.parquet.example.data.GroupFactory;
import org.apache.parquet.example.data.simple.SimpleGroupFactory;
import org.apache.parquet.hadoop.ParquetFileWriter;
import org.apache.parquet.hadoop.ParquetWriter;
import org.apache.parquet.hadoop.example.ExampleParquetWriter;
import org.apache.parquet.hadoop.metadata.CompressionCodecName;
import org.apache.parquet.schema.MessageType;
import org.apache.parquet.schema.MessageTypeParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by zhengyin on 2018/6/8 上午10:42.
 * Email  <zhengyin.name@gmail.com> .
 */
public class ParquetWrite {

    private static Logger logger = LoggerFactory.getLogger(ParquetWrite.class);
    private static MessageType schema = MessageTypeParser.parseMessageType("message Pair {" +
            "required binary city (UTF8); " +
            "required binary ip (UTF8);" +
            "repeated group time {" +
                 "required int32 ttl; " +
                 "required binary ttl2;" +
            "}" +
            "}");
    public static void main(String[] args) throws IOException {
        String filename = args[0];
        logger.info("out file : "+filename);
        GroupFactory factory = new SimpleGroupFactory(schema);
        Path file = new Path(filename);
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS","hdfs://hadoop-master2:9000");
        configuration.set("parquet.example.schema",schema.toString());
        ExampleParquetWriter.Builder builder = ExampleParquetWriter
                .builder(file).withWriteMode(ParquetFileWriter.Mode.CREATE)
                .withWriterVersion(ParquetProperties.WriterVersion.PARQUET_1_0)
                .withCompressionCodec(CompressionCodecName.SNAPPY)
                .withConf(configuration)
                .withType(schema);

        ParquetWriter<Group> writer = builder.build();



        for (int i=0;i<10;i++){
            Group group = factory.newGroup();
            group.append("city","city_"+i);
            group.append("ip","ip_"+i);
            Group groupTime = group.addGroup("time");
            groupTime.append("ttl",i);
            groupTime.append("ttl2","ttl_"+i);
            writer.write(group);
            logger.info("write group : "+ JSON.toJSONString(group));
        }

        writer.close();


        logger.info("end!");



    }
}
