package com.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Test {

	public static void main(String[] args) throws Exception {
		//��һ�������������ַ������WSDL��ַ
        URL url = new URL("http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx");
        //�ڶ�������һ��ͨ������ַ������
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        //�����������ò���
        //3.1���ͷ�ʽ���ã�POST�����д
        connection.setRequestMethod("POST");
        //3.2�������ݸ�ʽ��content-type
        connection.setRequestProperty("content-type", "text/xml;charset=UTF-8");
        //3.3���������������ΪĬ���´�����connectionû�ж�дȨ�ޣ�
        connection.setDoInput(true);
        connection.setDoOutput(true);

        //���Ĳ�����֯SOAP���ݣ���������
        String soapXML = getXML("18273610921");
        OutputStream os = connection.getOutputStream();
        os.write(soapXML.getBytes());
        //���岽�����շ������Ӧ����ӡ
        int responseCode = connection.getResponseCode();
        if(200 == responseCode){//��ʾ�������Ӧ�ɹ�
            InputStream is = connection.getInputStream();
            //���ֽ���ת��Ϊ�ַ���
            InputStreamReader isr = new InputStreamReader(is,"utf-8");
            //ʹ�û�����
            BufferedReader br = new BufferedReader(isr);

            StringBuilder sb = new StringBuilder();
            String temp = null;
            while(null != (temp = br.readLine())){
                sb.append(temp);
            }
            System.out.println(sb.toString());

            is.close();
            isr.close();
            br.close();
        }

        os.close();
	}
	//��֯���ݣ�������ƴ��һ��
    public static String getXML(String phoneNum){
        String soapXML = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
        +"<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
            +"<soap:Body>"
            +"<getMobileCodeInfo xmlns=\"http://WebXml.com.cn/\">"
                +"<mobileCode>"+phoneNum+"</mobileCode>"
              +"<userID></userID>"
            +"</getMobileCodeInfo>"
          +"</soap:Body>"
        +"</soap:Envelope>";
        return soapXML;
    }
}
