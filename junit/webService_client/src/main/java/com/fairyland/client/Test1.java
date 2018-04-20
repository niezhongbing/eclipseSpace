package com.fairyland.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.junit.Test;

import cn.cad.mobile.MobileCodeWS;
import cn.cad.mobile.MobileCodeWSSoap;

public class Test1 {

	@Test
	public void Test01(){
		//创建服务视图
		MobileCodeWS mobileCodeWS = new MobileCodeWS();
		
		//获取实现类
		MobileCodeWSSoap mobileCodeWSSoap = mobileCodeWS.getPort(MobileCodeWSSoap.class);
		
		//调用方法
		String message = mobileCodeWSSoap.getMobileCodeInfo("18684818956", null);
		
		System.out.println(message);
	}
	
	@Test
	public void test02(){
		try
	      {
	         URL url = new URL("http://www.runoob.com");
	         URLConnection urlConnection = url.openConnection();
	         HttpURLConnection connection = null;
	         if(urlConnection instanceof HttpURLConnection)
	         {
	            connection = (HttpURLConnection) urlConnection;
	         }
	         else
	         {
	            System.out.println("请输入 URL 地址");
	            return;
	         }
	         BufferedReader in = new BufferedReader(
	         new InputStreamReader(connection.getInputStream()));
	         String urlString = "";
	         String current;
	         while((current = in.readLine()) != null)
	         {
	            urlString += current;
	         }
	         System.out.println(urlString);
	      }catch(IOException e)
	      {
	         e.printStackTrace();
	      }
	}
	
	
}
