package com.fairyland.server;

import javax.jws.WebService;

@WebService
public class WeatherInterfaceImpl implements WeatherInterface {

	public String queryWeather(String cityName) {
		  System.out.println("获取城市名"+cityName);
	        String weather="暴雨";    
	        return weather;
	}
	
}
