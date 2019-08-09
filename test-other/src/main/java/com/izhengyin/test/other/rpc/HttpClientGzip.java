package com.izhengyin.test.other.rpc;

import com.alibaba.fastjson.JSON;
import org.apache.commons.compress.compressors.gzip.GzipUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.zip.GZIPOutputStream;

/**
 * @author zhengyin  <zhengyin.name@gmail.com>
 * @date Created on 2018/10/15 下午2:31
 */
public class HttpClientGzip {

    public static void main(String[] args) throws IOException {


        String str = "{\n" +
                "  \"apn\": \"string\",\n" +
                "  \"client\": \"string\",\n" +
                "  \"deviceId\": \"string\",\n" +
                "  \"event\": \"string\",\n" +
                "  \"eventAttr\": {},\n" +
                "  \"eventTime\": 0,\n" +
                "  \"network\": \"string\",\n" +
                "  \"refPage\": \"string\",\n" +
                "  \"resolution\": \"string\",\n" +
                "  \"selfPage\": \"string\",\n" +
                "  \"type\": \"string\",\n" +
                "  \"userId\": 0,\n" +
                "  \"utmSource\": \"string\"\n" +
                "}";



        HttpClient httpClient = new DefaultHttpClient();
        HttpPost method = new HttpPost("http://127.0.0.1:10110/app/collect");
        method.addHeader("Content-type","application/json; charset=ISO-8859-1");
        method.setHeader("Accept", "application/json");
        method.setHeader("Content-Encoding", "gzip");


        ByteArrayOutputStream originalContent = new ByteArrayOutputStream();
        originalContent.write(str.getBytes(Charset.forName("UTF-8")));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        GZIPOutputStream gzipOut = new GZIPOutputStream(baos);
        originalContent.writeTo(gzipOut);
        gzipOut.finish();

        method.setEntity(new ByteArrayEntity(baos.toByteArray(),ContentType.create("text/plain",Charset.forName("utf-8"))));

        HttpResponse response = httpClient.execute(method);


        System.out.println(JSON.toJSONString(response));

        int statusCode = response.getStatusLine().getStatusCode();

        if (statusCode != HttpStatus.SC_OK) {
            System.out.println("error...");
        }

        String body = EntityUtils.toString(response.getEntity());

        System.out.println(body);
    }
}
