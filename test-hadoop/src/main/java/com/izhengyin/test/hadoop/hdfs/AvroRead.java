package com.izhengyin.test.hadoop.hdfs;

import com.izhengyin.test.hadoop.avro.User;
import com.izhengyin.test.hadoop.avro.WebEvent;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.io.DatumReader;
import org.apache.avro.specific.SpecificDatumReader;

import java.io.File;
import java.io.IOException;

/**
 * Created by zhengyin on 2018/7/12 下午2:24.
 * Email  <zhengyin.name@gmail.com> .
 */
public class AvroRead {

    public static void main(String[] args) throws IOException {
        String filePath = "/data/analysis/2018/7/13/dataFile_WebEvent_localhost_16.avro";
        File file = new File(filePath);
        DatumReader<WebEvent> userDatumReader = new SpecificDatumReader<WebEvent>(WebEvent.class);
        DataFileReader<WebEvent> dataFileReader = new DataFileReader<WebEvent>(file, userDatumReader);
        WebEvent user = null;
        System.out.println(" reader >>> >>> >>> >>> >>> >>> ");
        while (dataFileReader.hasNext()) {
            user = dataFileReader.next(user);
            System.out.println(user);
        }
        System.out.println(" <<< <<< <<< <<< <<< <<< <<< reader ");

    }
}
