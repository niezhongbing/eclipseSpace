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
		// token是用户输入的
		// 第一步从token中取出身份信息
		String userCode = (String) token.getPrincipal();
		// 如果查询不到返回null
		//数据库中用户账号是zhangsansan
		/*if(!userCode.equals("zhangsansan")){//
			return null;
		}*/
		
		
		// 模拟从数据库查询到密码
		String password = "111111";

		// 如果查询到返回认证信息AuthenticationInfo

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
