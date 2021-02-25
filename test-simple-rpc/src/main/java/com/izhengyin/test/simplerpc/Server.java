package com.izhengyin.test.simplerpc;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.util.IOUtils;
import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhengyin zhengyinit@outlook.com
 * Created on 2021-02-25 09:41
 */
public class Server {

    private final static ExecutorService EXECUTOR = Executors.newFixedThreadPool(10);
    private final static Map<String,Class<?>> serverRegistry =  new HashMap<>();
    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        //注册服务接口的实现类
        serverRegistry.put(Api.class.getName(),ApiImpl.class);

        ServerSocket serverSocket = new ServerSocket(8100);
        while (true){
            Socket socket = serverSocket.accept();
            EXECUTOR.execute(() -> {
                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter writer = new PrintWriter(socket.getOutputStream());
                    String message;
                    while ((message = reader.readLine())!=null) {
                        System.out.println("receive message "+message);
                        try {
                            //反序列化数据
                            RpcJsonSerialize rpcJsonSerialize = JSON.parseObject(message,new TypeReference<RpcJsonSerialize>(){});
                            //实例化接口实现类并调用具体的方法
                            Class<?> clazz = serverRegistry.get(rpcJsonSerialize.getApi());
                            Object obj = clazz.newInstance();
                            Method method = clazz.getDeclaredMethod(rpcJsonSerialize.getMethod(),rpcJsonSerialize.getParameterTypes());
                            String result = method.invoke(obj,rpcJsonSerialize.getArgs()).toString();
                            //结果返回给客户端
                            writer.write(result);
                            writer.flush();
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                    reader.close();
                    writer.close();
                    socket.close();
                    System.out.println("Socket closed");
                }catch (Exception e){
                    e.printStackTrace();
                }
            });
        }
    }

    public static class ApiImpl implements Api{
        @Override
        public String sayHello(String name) {
            return "Hello "+name;
        }
    }
}
