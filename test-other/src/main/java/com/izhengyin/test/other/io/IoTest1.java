package com.izhengyin.test.other.io;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by zhengyin on 2017/3/10.
 */
public class IoTest1 {
     public  static void   main(String[] args) throws IOException {
         String loadPath = Thread.currentThread().getContextClassLoader().getResource("").getPath().replace("test-classes", "classes");
         String filename = loadPath+"data/file1.html";
         InputStream inputStream = new FileInputStream(new File(filename));
         String body = IOUtils.toString(inputStream,"utf-8");

         System.out.println(body);
     }
}
