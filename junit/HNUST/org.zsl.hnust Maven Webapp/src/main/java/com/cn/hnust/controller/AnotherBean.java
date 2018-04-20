package com.cn.hnust.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;
@Component("anotherBean")
public class AnotherBean {
	public void printMessage() {
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		System.out.println("anotherBean executes!"+sdf.format(date));
	}
}
