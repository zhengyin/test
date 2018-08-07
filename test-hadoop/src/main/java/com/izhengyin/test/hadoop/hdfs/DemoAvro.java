package com.izhengyin.test.hadoop.hdfs;

import com.izhengyin.test.hadoop.avro.User;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * Created by zhengyin on 2017/6/16.
 */
public class DemoAvro {
    public static void main(String[] args) throws IOException, InterruptedException {
        File file = writer();
        reader(file);

        User user = new User("a",1,"b");

        appendTo(file,user);

        reader(file);

    }

    private static File writer() throws IOException {
        User user1 = new User();
        user1.setName("iphone");
        user1.setFavoriteColor("red");
        user1.setFavoriteNumber(10);
        User user2 = new User("xiaomi",3,"green");
        User user3 = User.newBuilder()
                .setName("smartisan")
                .setFavoriteColor("black")
                .setFavoriteNumber(100).build();
        File file = new File("/tmp/data/avro/"+new Date().getMinutes()+"_users.avro");
        DatumWriter<User> userDatumWriter = new SpecificDatumWriter<User>(User.class);
        DataFileWriter<User> dataFileWriter = new DataFileWriter<User>(userDatumWriter);
        dataFileWriter.create(user1.getSchema(), file);
        dataFileWriter.append(user1);
        dataFileWriter.append(user2);
        dataFileWriter.append(user3);
        dataFileWriter.close();
        System.out.println(" writer >>> >>> >>> >>> >>> >>> ");
        System.out.println(user1);
        System.out.println(user2);
        System.out.println(user3);
        System.out.println(" <<< <<< <<< <<< <<< <<< <<< writer ");

        return file;
    }

    private static void appendTo(File file,User user) throws IOException {
        DatumWriter<User> userDatumWriter = new SpecificDatumWriter<User>(User.class);
        DataFileWriter<User> dataFileWriter = new DataFileWriter<User>(userDatumWriter);
        if(file.exists()){
            dataFileWriter.appendTo(file);
        }else{
            dataFileWriter.create(user.getSchema(),file);
        }
        dataFileWriter.append(user);
        dataFileWriter.close();
        System.out.println(" appendTo >>> >>> >>> >>> >>> >>> ");
        System.out.println(user);
        System.out.println(" <<< <<< <<< <<< <<< <<< <<< appendTo ");
    }

    private static void reader(File file) throws IOException {
        DatumReader<User> userDatumReader = new SpecificDatumReader<User>(User.class);
        DataFileReader<User> dataFileReader = new DataFileReader<User>(file, userDatumReader);
        User user = null;
        System.out.println(" reader >>> >>> >>> >>> >>> >>> ");
        while (dataFileReader.hasNext()) {
            user = dataFileReader.next(user);
            System.out.println(user);
        }
        System.out.println(" <<< <<< <<< <<< <<< <<< <<< reader ");
    }


}
