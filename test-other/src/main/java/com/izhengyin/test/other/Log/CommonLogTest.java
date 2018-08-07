package com.izhengyin.test.other.Log;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by zhengyin on 2017/3/17.
 */
public class CommonLogTest {
    private static Log log = LogFactory.getLog(CommonLogTest.class);
    public static void  main(String[] args){
        log.debug("debug");
        log.info("info");
        log.warn("warn");
        log.error("error");
    }
}


