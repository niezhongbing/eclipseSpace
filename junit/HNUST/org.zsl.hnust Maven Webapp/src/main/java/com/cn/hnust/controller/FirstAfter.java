package com.cn.hnust.controller;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class FirstAfter extends QuartzJobBean{

	private AnotherBean anotherBean;

	public void setAnotherBean(AnotherBean anotherBean) {
		this.anotherBean = anotherBean;
	}

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		System.out.println("FirstAfter");
		this.anotherBean.printMessage();
	}

}
