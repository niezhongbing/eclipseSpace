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
	   // ͨ��SchedulerFactory��ȡһ��������ʵ��
	    StdSchedulerFactory sf = new StdSchedulerFactory();
	    // ����һ��Quartz�Ķ�����������
	    Scheduler scheduler = sf.getScheduler();
	    // ��ȡ��ǰʱ��15��֮���ʱ��
	    Date startDate = DateBuilder.nextGivenSecondDate(null,5);
	   // ����һ��JobDetailʵ��,�˰汾JobDetail�Ѿ���Ϊ�ӿڣ�interface�����ڣ�ͨ��JobBuilder����
       // ��ָ��Job��Scheduler�������鼰����
       JobDetail jobDetail = JobBuilder.newJob(jobEat.class).withIdentity("job1","group1").build();

       // SimpleTriggerʵ��Trigger�ӿڵ��ӽӿڡ��˴�ָֻ���˿�ʼִ�ж�ʱ�����ʱ�䣬ʹ��Ĭ�ϵ��ظ�������0�Σ����ظ������0�룩
       SimpleTrigger simpleTrigger = (SimpleTrigger) TriggerBuilder.newTrigger().withIdentity("trigger1","group1").
               startAt(startDate).build();
       // ���JobDetail��Scheduler�����У����Һ�Trigger���й���������ִ��ʱ��
       Date date = scheduler.scheduleJob(jobDetail,simpleTrigger);
       // ��������    
       scheduler.start();   
   }    
   
}
