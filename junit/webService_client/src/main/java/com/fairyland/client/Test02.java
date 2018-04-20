package com.fairyland.client;

import org.junit.Test;

import com.feiji.client.MobileCodeWS;
import com.feiji.client.MobileCodeWSSoap;

public class Test02 {
	@Test
	public void Test03() {
		// 创建服务视图
		MobileCodeWS mobileCodeWS = new MobileCodeWS();

		// 获取实现类
		MobileCodeWSSoap mobileCodeWSSoap = mobileCodeWS.getPort(MobileCodeWSSoap.class);

		// 调用方法
		String message = mobileCodeWSSoap.getMobileCodeInfo("18684818956", null);

		System.out.println(message);
	}
}
