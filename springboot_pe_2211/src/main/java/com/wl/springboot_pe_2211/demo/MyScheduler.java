package com.wl.springboot_pe_2211.demo;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

public class MyScheduler {

    public static void main(String[] args) throws SchedulerException {
        //创建任务
        JobDetail jobDetail = JobBuilder.newJob(PrintJob.class)
                .withIdentity("job1", "group1")
                .build();
        //创建触发器
        SimpleTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow() //立即触发
                .withSchedule(simpleSchedule()
                        .withIntervalInSeconds(3) //执行的间隔时间 3s
                        .withRepeatCount(10))//执行的次数  10次
                .build();

        //触发器和任务结合
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.scheduleJob(jobDetail,trigger);
        //开始
        scheduler.start();
    }
}
