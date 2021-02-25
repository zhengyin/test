package com.izhengyin.test.simplerpc;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author zhengyin zhengyinit@outlook.com
 * Created on 2021-02-25 12:30
 */
@Data
@ToString
public class RpcJsonSerialize implements Serializable {
    private String api;
    private String method;
    private Class<?>[] parameterTypes;
    private Object[] args;
}
