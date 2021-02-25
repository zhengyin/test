package com.izhengyin.test.simplerpc;

import com.alibaba.fastjson.JSON;

import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author zhengyin zhengyinit@outlook.com
 * Created on 2021-02-25 09:41
 */
public class Client {
    public static void main(String[] args) {
        ApiStub api = new ApiStub();
        System.out.println("sayHello => " + api.sayHello("name"));
    }

    private static class ApiStub implements Api{
        @Override
        public String sayHello(String name) {
            //JDK动态代理，添加拦截
            Api api = (Api) Proxy.newProxyInstance(
                    Api.class.getClassLoader(),
                    new Class[]{Api.class},
                    new InvocationHandler() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            //远程接口调用序列化数据
                            RpcJsonSerialize rpcJsonSerialize = new RpcJsonSerialize();
                            rpcJsonSerialize.setApi(Api.class.getName());
                            rpcJsonSerialize.setMethod(method.getName());
                            rpcJsonSerialize.setParameterTypes(method.getParameterTypes());
                            rpcJsonSerialize.setArgs(args);

                            System.out.println("rpcJsonSerialize "+JSON.toJSONString(rpcJsonSerialize));
                            Socket socket = new Socket();
                            socket.connect(new InetSocketAddress(8100));
                            String message = null;
                            try {
                                //发送数据，等待返回
                                PrintWriter writer = new PrintWriter(socket.getOutputStream());
                                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                                writer.write(JSON.toJSONString(rpcJsonSerialize));
                                writer.flush();
                                socket.shutdownOutput();
                                message = reader.readLine();
                                System.out.println("receive message , "+message);
                                writer.close();
                                reader.close();
                                socket.close();
                                System.out.println("Socket closed");
                            }catch (IOException e){
                                e.printStackTrace();
                            }
                            return message;
                        }
                    }
            );
            return api.sayHello(name);
        }
    }
}
