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

		// 1. �� AuthenticationToken ת��Ϊ UsernamePasswordToken
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;

		// 2. �� UsernamePasswordToken ������ȡ username
		String username = upToken.getUsername();

		// 3. �������ݿ�ķ���, �����ݿ��в�ѯ username ��Ӧ���û���¼

		// 4. ���û�������, ������׳� UnknownAccountException �쳣
		if ("lisi".equals(username)) {
			throw new UnknownAccountException("�û�������!");
		}

		// 5. �����û���Ϣ�����, �����Ƿ���Ҫ�׳������� AuthenticationException �쳣.
		if ("zhangsan".equals(username)) {
			throw new LockedAccountException("�û�������");
		}
		String pass = "123456";
		// ����
		ByteSource salt = ByteSource.Util.bytes(username);

		String password = GetMD5(pass, username);
		// 6. �����û������, ������ AuthenticationInfo ���󲢷���. ͨ��ʹ�õ�ʵ����Ϊ:
		// SimpleAuthenticationInfo

		// new SimpleAuthenticationInfo(username,password , getName());
		AuthenticationInfo info = new SimpleAuthenticationInfo(username, password, salt, getName());
		return info;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		// 1.��principalCollection�л�ȡ��¼�û�����Ϣ
		Object principal = principals.getPrimaryPrincipal();

		// 2.���õ�¼���û���Ϣ���û���õ�ǰ�û��Ľ�ɫ��Ȩ��(ͨ�����ݿ��ѯ)
		Set<String> roles = new HashSet<String>();
		roles.add("user");
		if ("admin".equals(principal)) {
			roles.add("admin");
		}
		List<String> permissions = new ArrayList<String>();
		permissions.add("user:query");

		// 3. ���� SimpleAuthorizationInfo, �������� reles ����.
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
		info.addStringPermissions(permissions);
		// 4. ���� SimpleAuthorizationInfo ����.
		return info;
	}

	// �������
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
