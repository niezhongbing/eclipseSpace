package com.cn.hnust.shiro;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.crypto.hash.SimpleHashRequest;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;

import com.sun.mail.imap.protocol.ListInfo;

public class CustomRealm extends AuthorizingRealm {

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("realm: " + token.hashCode());

		// 1. 把 AuthenticationToken 转换为 UsernamePasswordToken
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;

		// 2. 从 UsernamePasswordToken 中来获取 username
		String username = upToken.getUsername();

		// 3. 调用数据库的方法, 从数据库中查询 username 对应的用户记录

		// 4. 若用户不存在, 则可以抛出 UnknownAccountException 异常
		if ("lisi".equals(username)) {
			throw new UnknownAccountException("用户不存在!");
		}

		// 5. 根据用户信息的情况, 决定是否需要抛出其他的 AuthenticationException 异常.
		if ("zhangsan".equals(username)) {
			throw new LockedAccountException("用户被锁定");
		}
		String pass = "123456";
		// 加盐
		ByteSource salt = ByteSource.Util.bytes(username);

		String password = GetMD5(pass, username);
		// 6. 根据用户的情况, 来构建 AuthenticationInfo 对象并返回. 通常使用的实现类为:
		// SimpleAuthenticationInfo

		// new SimpleAuthenticationInfo(username,password , getName());
		AuthenticationInfo info = new SimpleAuthenticationInfo(username, password, salt, getName());
		return info;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		// 1.从principalCollection中获取登录用户的信息
		Object principal = principals.getPrimaryPrincipal();

		// 2.利用登录的用户信息来用户获得当前用户的角色和权限(通过数据库查询)
		Set<String> roles = new HashSet<String>();
		roles.add("user");
		if ("admin".equals(principal)) {
			roles.add("admin");
		}
		List<String> permissions = new ArrayList<String>();
		permissions.add("user:query");

		// 3. 创建 SimpleAuthorizationInfo, 并设置其 reles 属性.
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
		info.addStringPermissions(permissions);
		// 4. 返回 SimpleAuthorizationInfo 对象.
		return info;
	}

	// 清除缓存
	public void clearCached() {
		PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
		super.clearCache(principals);
	}

	public static String GetMD5(String pass, String userName) {
		String name = "MD5";
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
