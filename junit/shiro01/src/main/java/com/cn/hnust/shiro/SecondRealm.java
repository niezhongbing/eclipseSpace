package com.cn.hnust.shiro;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.crypto.hash.SimpleHashRequest;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;

import com.sun.mail.imap.protocol.ListInfo;

public class SecondRealm extends AuthorizingRealm {

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("SecondRealm: doGetAuthenticationInfo");

		//1. 把 AuthenticationToken 转换为 UsernamePasswordToken 
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		
		//2. 从 UsernamePasswordToken 中来获取 username
		String username = upToken.getUsername();
		
		//3. 调用数据库的方法, 从数据库中查询 username 对应的用户记录
		//System.out.println("从数据库中获取 username: " + username + " 所对应的用户信息.");
		
		//4. 若用户不存在, 则可以抛出 UnknownAccountException 异常
		String pass = "12346";
		// 加盐
		ByteSource salt = ByteSource.Util.bytes(username);

		String password = GetMD5(pass,username) ;
		//6. 根据用户的情况, 来构建 AuthenticationInfo 对象并返回. 通常使用的实现类为: SimpleAuthenticationInfo
		
		//new SimpleAuthenticationInfo(username,password , getName());
		AuthenticationInfo info = new SimpleAuthenticationInfo(username, password, salt, getName());
		return info;
	}
	
	
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}
	public  static String GetMD5(String pass,String userName){
		String name = "SHA1";
		ByteSource salt = ByteSource.Util.bytes(userName);
		int count = 1024;
		Object result = new SimpleHash(name, pass, salt, count);
		System.out.println(result);
		return result.toString();
	}
	
	public static void main(String[] args) {
		String username = "admin";
		String pass = "123456";
		GetMD5(pass, username);
		
	}
	
}
