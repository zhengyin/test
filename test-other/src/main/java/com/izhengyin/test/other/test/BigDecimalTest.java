package com.izhengyin.test.other.test;

import java.math.BigDecimal;

/**
 * @author zhengyin zhengyinit@outlook.com
 * Created on 2020-12-11 11:58
 */
public class BigDecimalTest {

    public static void main(String[] args) {
        BigDecimal a = new BigDecimal("12.13523").setScale(2,BigDecimal.ROUND_HALF_UP);
        System.out.println(a.toString());
        System.out.println(a.toPlainString());

        BigDecimal a1 = new BigDecimal("0.00");
        BigDecimal b1 = new BigDecimal("0");

        System.out.println(a1.equals(b1));
        System.out.println(a1.compareTo(b1));
    }
}
