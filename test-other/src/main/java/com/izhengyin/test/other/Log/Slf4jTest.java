package com.izhengyin.test.other.Log;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhengyin on 2017/3/17.
 */
public class Slf4jTest {
    private static Logger logger = LoggerFactory.getLogger(Slf4jTest.class);
    public static void  main(String[] args){
        logger.debug("debug");
        logger.info("info");
        logger.warn("warn");
        logger.error("error");
    }
}


