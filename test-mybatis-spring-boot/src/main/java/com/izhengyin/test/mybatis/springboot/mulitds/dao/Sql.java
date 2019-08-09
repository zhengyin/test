package com.izhengyin.test.mybatis.springboot.mulitds.dao;

import java.util.Map;

/**
 * @author zhengyin  <zhengyin.name@gmail.com>
 * @date Created on 2018/11/1 12:34 PM
 */
public class Sql {
    public static String getList(Map<String,Object> params){
        return params.get("SQL")+"";
    }
}
