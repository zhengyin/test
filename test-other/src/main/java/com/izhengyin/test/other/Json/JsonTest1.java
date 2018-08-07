package com.izhengyin.test.other.Json;

import com.google.gson.Gson;
import com.kongfz.service.res.Response;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhengyin on 2017/3/21.
 */
public class JsonTest1 {

    public static void main(String args[]){

        Response response = new Response();
        response.setStatus(true);

        Map<String,Object> map = new HashMap<>();
        Member member = new Member();
        map.put("userInfo",member);

        member.setUserId(111);
        member.setLastActive("2017-22-12 12:02:12");

        response.setResult(map);

        System.out.println(new Gson().toJson(response));



        Timestamp timestamp = new Timestamp(1490065900);

        System.out.println(Timestamp.valueOf("yyyy-mm-dd hh:mm:ss"));

    }
}
