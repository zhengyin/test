package com.izhengyin.test.quartz.JobDetials;

import com.izhengyin.test.quartz.jobs.HelloQuartzJob;
import com.izhengyin.test.quartz.triggers.HelloQuartzTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import sun.management.ManagementFactoryHelper;


import static org.quartz.JobBuilder.newJob;

/**
 * Created by zhengyin on 2017/11/22 上午11:53.
 * Email  <zhengyin.name@gmail.com> .
 */
public class HelloQuartzJobDetial {
    public static void main(String[] args) throws SchedulerException {
        String operation = System.getProperty("operation");
        if(operation == null){
            System.out.print("请指定 -Doperation=[add|cancel]");
            System.exit(1);
        }
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        String name = ManagementFactoryHelper.getRuntimeMXBean().getName();
        JobDetail job = newJob(HelloQuartzJob.class)
                .withIdentity("helloQuartzJob", "group1")
                .usingJobData("name", name)
                .build();
        Trigger trigger = new HelloQuartzTrigger().getTrigger();
        if(operation.equalsIgnoreCase("add")){
            add(scheduler,job,trigger);
        }else if(operation.equalsIgnoreCase("cancel")){
            cancel(scheduler,job);
        }else{
            System.out.print("请指定 -Doperation=[add|cancel]");
            System.exit(1);
        }
        scheduler.shutdown();
    }

    /**
     * 添加任务
     * @param scheduler
     * @param job
     * @param trigger
     * @throws SchedulerException
     */
    private static void add(Scheduler scheduler , JobDetail job , Trigger trigger) throws SchedulerException {
        scheduler.scheduleJob(job, trigger);
    }

    /**
     * 取消任务
     * @param scheduler
     * @param job
     * @throws SchedulerException
     */
    private static void cancel(Scheduler scheduler , JobDetail job) throws SchedulerException {
        scheduler.deleteJob(job.getKey());
    }
}
