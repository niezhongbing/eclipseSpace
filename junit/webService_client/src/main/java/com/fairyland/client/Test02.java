package com.fairyland.client;

import org.junit.Test;

import com.feiji.client.MobileCodeWS;
import com.feiji.client.MobileCodeWSSoap;

public class Test02 {
	@Test
	public void Test03() {
		// ����������ͼ
		MobileCodeWS mobileCodeWS = new MobileCodeWS();

		// ��ȡʵ����
		MobileCodeWSSoap mobileCodeWSSoap = mobileCodeWS.getPort(MobileCodeWSSoap.class);

		// ���÷���
		String message = mobileCodeWSSoap.getMobileCodeInfo("18684818956", null);

		System.out.println(message);
	}
}
