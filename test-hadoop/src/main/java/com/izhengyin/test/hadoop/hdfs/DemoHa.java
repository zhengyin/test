package com.izhengyin.test.hadoop.hdfs;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.izhengyin.test.hadoop.AvroWriterSupport;
import com.izhengyin.test.hadoop.avro.User;
import org.apache.avro.Schema;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.avro.specific.SpecificRecord;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhengyin on 2018/7/11 下午2:56.
 * Email  <zhengyin.name@gmail.com> .
 */
public class DemoHa {

    private static final String testrecod = "{\n" +
            "    \"namespace\": \"com.kongfz.bigdata.transferservice.entity.test\",\n" +
            "    \"type\":    \"record\",\n" +
            "    \"name\":    \"TestRecord\",\n" +
            "    \"doc\":    \"Test records\",\n" +
            "    \"fields\":\n" +
            "        [{\n" +
            "            \"name\":    \"id\",\n" +
            "            \"type\":    \"int\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"name\":    \"name\",\n" +
            "            \"type\":    \"string\"\n" +
            "        }"+
            "    ]\n" +
            "}";

    public static void main(String[] args){



        Configuration conf = new Configuration();

        //设置配置相关的信息，分别对应hdfs-site.xml core-site.xml
        conf.set("fs.defaultFS", "hdfs://kongfz");
        conf.set("dfs.nameservices", "kongfz");
        conf.set("dfs.ha.namenodes.kongfz", "nn1,nn2");
        conf.set("dfs.namenode.rpc-address.kongfz.nn1", "hadoop-master1:9000");
        conf.set("dfs.namenode.rpc-address.kongfz.nn2", "hadoop-master2:9000");
        conf.set("dfs.client.failover.proxy.provider.kongfz", "org.apache.hadoop.hdfs.server.namenode.ha.ConfiguredFailoverProxyProvider");
        //设置实现类，因为会出现类覆盖的问题
        conf.set("fs.hdfs.impl", org.apache.hadoop.hdfs.DistributedFileSystem.class.getName());
        conf.set("fs.file.impl", org.apache.hadoop.fs.LocalFileSystem.class.getName());


        AvroSCHEMA avroSCHEMA = JSON.parseObject(testrecod,new TypeReference<AvroSCHEMA>(){});
        Map<String,String> age = new HashMap<>();
        age.put("name","age");
        age.put("type","int");
        avroSCHEMA.getFields().add(age);

        Map<String,String> partition = new HashMap<>();
        partition.put("name","partition");
        partition.put("type","int");
        avroSCHEMA.getFields().add(partition);

        String TEST_RECORD_SCHEMA = JSON.toJSONString(avroSCHEMA);
        System.out.println(TEST_RECORD_SCHEMA);

        try {
            FileSystem fileSystem = FileSystem.get(conf);
            Path path = new Path("hdfs://kongfz/tmp/test/testrecord/2019/4/23/test.avro");
            if(fileSystem.exists(path.getParent())){
                fileSystem.mkdirs(path.getParent());
            }
            System.out.println("Begin Write file into hdfs");
            Schema schema = new org.apache.avro.Schema.Parser().parse(TEST_RECORD_SCHEMA);
            GenericRecord record = new GenericData.Record(schema);
            record.put("id",1);
            record.put("name","name");
            record.put("age",1);
            AvroWriterSupport<GenericRecord> avroWriterSupport = new AvroWriterSupport<GenericRecord>(conf,path,schema);
            avroWriterSupport.write(1,1,record);
            avroWriterSupport.close();
            /*
            TestRecord testRecord = new TestRecord(1,"a",2,1,1L);
            FSDataOutputStream outputStream = fileSystem.create(path);
            outputStream.writeBytes(testRecord.toString());
            outputStream.close();
            */
            System.out.println("End Write file into hdfs");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class AvroSCHEMA{
        private String namespace;
        private String type;
        private String name;
        private String doc;
        private List<Map<String,String>> fields;

        public String getNamespace() {
            return namespace;
        }

        public void setNamespace(String namespace) {
            this.namespace = namespace;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDoc() {
            return doc;
        }

        public void setDoc(String doc) {
            this.doc = doc;
        }

        public List<Map<String, String>> getFields() {
            return fields;
        }

        public void setFields(List<Map<String, String>> fields) {
            this.fields = fields;
        }

        @Override
        public String toString() {
            return "AvroSCHEMA{" +
                    "namespace='" + namespace + '\'' +
                    ", type='" + type + '\'' +
                    ", name='" + name + '\'' +
                    ", doc='" + doc + '\'' +
                    ", fields=" + fields +
                    '}';
        }
    }
}
