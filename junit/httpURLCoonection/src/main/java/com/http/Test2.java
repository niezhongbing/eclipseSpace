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
		conn.setRequestMethod("POST"); // �ύģʽ

		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); // ������������

		conn.setRequestProperty("User-Agent", "Autoyol_gpsCenter");
		conn.setConnectTimeout(100000); // ���ӳ�ʱ ��λ����
		conn.setReadTimeout(100000); // ��ȡ��ʱ ��λ����
		conn.setDoOutput(true); // �Ƿ��������
		conn.setDoInput(true); // �Ƿ��ȡ����
		conn.connect();
		PrintWriter outStrm=new PrintWriter(conn.getOutputStream());
		Map<String, String> params = new HashMap<String, String>();
		params.put("name", "hello word");
		outStrm.write("name=helloword"); // �������
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
		System.out.println("�������");
	}
}
