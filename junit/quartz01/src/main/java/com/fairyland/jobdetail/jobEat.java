package com.fairyland.jobdetail;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class jobEat implements Job {

	public void execute(JobExecutionContext jobCtx) throws JobExecutionException {

		System.out.println(jobCtx.getTrigger().getCalendarName() + " triggered. time is:" + (new Date()));

	}

}
