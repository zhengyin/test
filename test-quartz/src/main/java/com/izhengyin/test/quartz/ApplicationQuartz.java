package com.izhengyin.test.quartz;

import com.izhengyin.test.quartz.jobs.HelloQuartzJob;
import com.izhengyin.test.quartz.triggers.HelloQuartzTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.management.ManagementFactoryHelper;

import java.util.concurrent.TimeUnit;

import static org.quartz.JobBuilder.newJob;

/**
 * Created by zhengyin on 2017/11/21 下午3:54.
 * Email  <zhengyin.name@gmail.com> .
 */
public class ApplicationQuartz {

    private static Logger logger = LoggerFactory.getLogger(ApplicationQuartz.class);

    public static void main(String[] args) throws InterruptedException {

        //创建scheduler
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            //启动之
            scheduler.start();
        }catch (Exception e){
            logger.error( "ApplicationQuartz#main : " , e);
        }

        while (true){
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
