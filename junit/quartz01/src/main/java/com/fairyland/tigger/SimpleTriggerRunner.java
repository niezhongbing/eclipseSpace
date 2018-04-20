package com.fairyland.tigger;

import java.util.Date;

import org.quartz.CronScheduleBuilder;
import org.quartz.DateBuilder;
import org.quartz.JobBuilder;    
import org.quartz.JobDetail;    
import org.quartz.Scheduler;    
import org.quartz.SchedulerException;  
import org.quartz.SchedulerFactory;    
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;    
import org.quartz.TriggerBuilder;    
import org.quartz.impl.StdSchedulerFactory;

import com.fairyland.jobdetail.jobEat;    
 
public class SimpleTriggerRunner {    
	
   public static void main(String[] args) throws Exception {    
	   // 通过SchedulerFactory获取一个调度器实例
	    StdSchedulerFactory sf = new StdSchedulerFactory();
	    // 代表一个Quartz的独立运行容器
	    Scheduler scheduler = sf.getScheduler();
	    // 获取当前时间15秒之后的时间
	    Date startDate = DateBuilder.nextGivenSecondDate(null,5);
	   // 创建一个JobDetail实例,此版本JobDetail已经作为接口（interface）存在，通过JobBuilder创建
       // 并指定Job在Scheduler中所属组及名称
       JobDetail jobDetail = JobBuilder.newJob(jobEat.class).withIdentity("job1","group1").build();

       // SimpleTrigger实现Trigger接口的子接口。此处只指定了开始执行定时任务的时间，使用默认的重复次数（0次）和重复间隔（0秒）
       SimpleTrigger simpleTrigger = (SimpleTrigger) TriggerBuilder.newTrigger().withIdentity("trigger1","group1").
               startAt(startDate).build();
       // 添加JobDetail到Scheduler容器中，并且和Trigger进行关联，返回执行时间
       Date date = scheduler.scheduleJob(jobDetail,simpleTrigger);
       // 启动调度    
       scheduler.start();   
   }    
   
}
