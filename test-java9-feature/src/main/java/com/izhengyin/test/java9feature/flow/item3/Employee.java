package com.izhengyin.test.java9feature.flow.item3;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * @author zhengyin zhengyinit@outlook.com
 * Created on 2020-08-08 12:11
 */
@Data
@ToString
@AllArgsConstructor
public class Employee {
    private int id;
    private String name;
}