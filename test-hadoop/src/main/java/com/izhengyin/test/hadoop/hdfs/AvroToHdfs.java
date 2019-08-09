package com.izhengyin.test.hadoop.hdfs;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.file.FileReader;
import org.apache.avro.file.SeekableInput;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.mapred.FsInput;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;


/**
 * @author zhengyin  <zhengyin.name@gmail.com>
 * @date Created on 2019/4/17 11:11 AM
 */
public class AvroToHdfs {


    public static void main(String[] args) throws IOException{
       // Schema schema = parseSchema();
       // writeToAvroFile(schema);
        readAvroFromHDFS("/tmp/product/item_1.avro/part-m-00000.avro");
    }

    // Method to parse the schema
    private static Schema parseSchema() {
        Schema.Parser parser = new    Schema.Parser();
        Schema schema = null;
        try {
            // Path to schema file
            schema = parser.parse(ClassLoader.getSystemResourceAsStream("avro/schema.avsc"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return schema;
    }


    private static void writeToAvroFile(Schema schema) {

        DatumWriter datumWriter = new GenericDatumWriter(schema);
        DataFileWriter dataFileWriter = null;

        try {

            Configuration configuration = new Configuration();
            configuration.set("fs.defaultFS","hdfs://hadoop-master2:9000");
            FileSystem fs = FileSystem.get(configuration);
            Path filePath = new Path("/tmp/test-avro/emp.avro");
            System.out.println(fs.getContentSummary(filePath).getLength());
            dataFileWriter = new DataFileWriter(datumWriter);

            // for compression
            //dataFileWriter.setCodec(CodecFactory.snappyCodec());
            OutputStream out = fs.create(filePath);
            dataFileWriter.create(schema, out);

            for (int i=100;i<20000000;i++){
                GenericRecord emp1 = new GenericData.Record(schema);
                emp1.put("id", i);
                emp1.put("empName", "Batista "+i);
                emp1.put("age", i*33);
                dataFileWriter.append(emp1);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            if(dataFileWriter != null) {
                try {
                    dataFileWriter.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }
    }

    public static void readAvroFromHDFS(String pathstr ) throws IOException {
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS","hdfs://hadoop-master2:9000");
        Path path = new Path(pathstr);
        SeekableInput input = new FsInput(path, configuration);

        DatumReader<GenericRecord> reader = new GenericDatumReader<>();
        FileReader<GenericRecord> fileReader = DataFileReader.openReader(input,reader);
        Schema avroSchema = fileReader.getSchema();
        System.out.println("avroSchema : "+avroSchema);
        System.out.println(fileReader.tell());
        for (GenericRecord datum : fileReader) {
            System.out.println(datum);
        }
        fileReader.close();
        System.exit(0);
    }

}
