package com.fairyland.server;

import javax.jws.WebService;

@WebService
public class WeatherInterfaceImpl implements WeatherInterface {

	public String queryWeather(String cityName) {
		  System.out.println("��ȡ������"+cityName);
	        String weather="����";    
	        return weather;
	}
	
}
