package com.http;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

public class Test2 {

	public static void main(String[] args) throws Exception {
		URL url = new URL("http://localhost:8080/JinJi-S/httpUrl");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST"); // 提交模式

		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); // 设置请求属性

		conn.setRequestProperty("User-Agent", "Autoyol_gpsCenter");
		conn.setConnectTimeout(100000); // 连接超时 单位毫秒
		conn.setReadTimeout(100000); // 读取超时 单位毫秒
		conn.setDoOutput(true); // 是否输入参数
		conn.setDoInput(true); // 是否读取参数
		conn.connect();
		PrintWriter outStrm=new PrintWriter(conn.getOutputStream());
		Map<String, String> params = new HashMap<String, String>();
		params.put("name", "hello word");
		outStrm.write("name=helloword"); // 输入参数
		outStrm.flush();
		outStrm.close();
		InputStream in = conn.getInputStream();
		byte[] buffer = new byte[521];
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		for (int len = 0; (len = in.read(buffer)) > 0;) {
			baos.write(buffer, 0, len);
		}
		String returnValue = new String(baos.toByteArray(), "utf-8");
		System.out.println(returnValue);
		// reg= JSON. parseObject(returnValue, ReturnMessage.class );
		baos.flush();
		baos.close();
		in.close();
		conn.disconnect();
		System.out.println("连接完成");
	}
}
