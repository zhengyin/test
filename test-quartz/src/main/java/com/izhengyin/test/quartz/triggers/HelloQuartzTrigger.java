package com.izhengyin.test.quartz.triggers;

import org.quartz.Trigger;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Created by zhengyin on 2017/11/22 上午10:45.
 * Email  <zhengyin.name@gmail.com> .
 */
public class HelloQuartzTrigger implements MyTrigger{

    private static String GROUP = "MyTrigger";
    private Trigger trigger;

    @Override
    public Trigger getTrigger() {
        createTrigger();
        return this.trigger;
    }

    /**
     * 定义Trigger
     * trigger 属性
     *  1. 立即生效
     *  2. 每隔10秒执行一次
     */
    private void createTrigger(){
        this.trigger = newTrigger().withIdentity(HelloQuartzTrigger.class.getName(), GROUP)
        .startNow()
        .withSchedule(simpleSchedule()
        .withIntervalInSeconds(10)
        .repeatForever())
        .build();
    }
}
