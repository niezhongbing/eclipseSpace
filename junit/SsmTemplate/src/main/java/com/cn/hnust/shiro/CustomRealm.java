package com.cn.hnust.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;

public class CustomRealm extends AuthorizingRealm {

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// token���û������
		// ��һ����token��ȡ�������Ϣ
		String userCode = (String) token.getPrincipal();
		// �����ѯ��������null
		//���ݿ����û��˺���zhangsansan
		/*if(!userCode.equals("zhangsansan")){//
			return null;
		}*/
		
		
		// ģ������ݿ��ѯ������
		String password = "111111";

		// �����ѯ��������֤��ϢAuthenticationInfo

		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
				userCode, password, this.getName());

		return simpleAuthenticationInfo;
	}
	
	
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
