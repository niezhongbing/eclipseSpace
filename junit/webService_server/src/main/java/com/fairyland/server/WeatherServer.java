package com.fairyland.server;

import javax.xml.ws.Endpoint;

public class WeatherServer {
	 public static void main(String[] args) {
	        Endpoint.publish("http://127.0.0.1:12345/weather", new WeatherInterfaceImpl());
	        System.out.println("服务已经启动成功");
	    }
}
