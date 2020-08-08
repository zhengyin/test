package com.izhengyin.test.other.concurrent;

import com.izhengyin.test.other.concurrent.mock.ConcurrentHashMapMock;
import scala.collection.mutable.HashTable;

import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhengyin zhengyinit@outlook.com
 * Created on 2020-08-08 09:54
 */
public class ConcurrentHashMapTest {

    public static void main(String[] args){

        ConcurrentHashMapMock<String,String> curHashMap = new ConcurrentHashMapMock<>(10);

        System.out.println(curHashMap.put("a","1"));

        System.out.println(curHashMap.get("a"));

        System.out.println(curHashMap.size());


    }
}
