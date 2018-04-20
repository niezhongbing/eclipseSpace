package com.fairyland.tigger;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.fairyland.jobdetail.jobEat;

public class CronTriggerRunner {
	public static void main(String[] args) throws Exception {
		  //ͨ��schedulerFactory��ȡһ��������    
	    SchedulerFactory schedulerfactory = new StdSchedulerFactory();    
	    Scheduler scheduler=null;    
	    try{    
	        // ͨ��schedulerFactory��ȡһ��������    
	        scheduler = schedulerfactory.getScheduler();    

	         // ����jobDetailʵ������Jobʵ����    
	         // ָ��job�����ƣ�����������ƣ��Լ���job��    
	        JobDetail job = JobBuilder.newJob(jobEat.class).withIdentity("JobName", "JobGroupName").build();    

	         // ������ȴ�������    

	         // SimpleTrigger   
	//   Trigger trigger=TriggerBuilder.newTrigger().withIdentity("SimpleTrigger", "SimpleTriggerGroup")    
//	                 .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(3).withRepeatCount(6))    
//	                 .startNow().build();    

	         //  corn���ʽ  ÿ����ִ��һ��  
	           Trigger trigger=TriggerBuilder.newTrigger().withIdentity("CronTrigger1", "CronTriggerGroup")    
	           .withSchedule(CronScheduleBuilder.cronSchedule("*/5 * * * * ?"))    
	           .startNow().build();     

	         // ����ҵ�ʹ�����ע�ᵽ���������    
	        scheduler.scheduleJob(job, trigger);    

	        // ��������    
	        scheduler.start();    

	        Thread.sleep(10000);  

	        // ֹͣ����  
	        scheduler.shutdown();  

	    }catch(SchedulerException e){    
	        e.printStackTrace();    
	    }    
	}

}
