package com.izhengyin.test.spring.aspectJ.aop.advice.persons;

import com.izhengyin.test.spring.aspectJ.aop.advice.Games;
import com.izhengyin.test.spring.aspectJ.aop.advice.Person;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by zhengyin on 2017/8/30 下午2:34.
 * Email  <zhengyin.name@gmail.com> .
 */
public class XiaoMing implements Person {
    private static final Log logger = LogFactory.getLog(XiaoMing.class);

    @Override
    public void sayName() {
        logger.info("My name is XiaoMing!");
    }

    @Override
    public void sayFromWhichCountry() {
        logger.info("I am from Chain.");
    }

    @Override
    public void sayFromCity() {
        logger.info("I am from Shang Hai.");
    }

    @Override
    public void doWork() {
        logger.info("I'm writing, It`s my job.");
    }

    @Override
    public void doSports() {
        logger.info("I am playing ping-pong, It`s my hobby.");
    }

    @Override
    @Games("王者荣耀")
    public void playGames() {

    }

}
