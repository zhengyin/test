package com.izhengyin.test.quartz.jobs;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Created by zhengyin on 2017/11/22 上午10:44.
 * Email  <zhengyin.name@gmail.com> .
 */
public class HelloQuartzJob implements Job {
    private static Logger logger = LoggerFactory.getLogger(HelloQuartzJob.class);
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDetail detail = jobExecutionContext.getJobDetail();
        String name = detail.getJobDataMap().getString("name");
        logger.info("Say hello to " + name + " at " + new Date());
    }
}