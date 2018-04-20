package com.cn.hnust.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cn.hnust.exception.CustomException;

@Controller
public class LoginController {

	@RequestMapping("/login")
	public String login(HttpServletRequest request) throws Exception{

		//�����½ʧ�ܴ�request�л�ȡ��֤�쳣��Ϣ��shiroLoginFailure����shiro�쳣���ȫ�޶���
		String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");
		//����shiro���ص��쳣��·���жϣ��׳�ָ���쳣��Ϣ
		if(exceptionClassName!=null){
			if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
				//���ջ��׸��쳣������
				throw new CustomException("�˺Ų�����");
			} else if (IncorrectCredentialsException.class.getName().equals(
					exceptionClassName)) {
				throw new CustomException("�û���/�������");
			} else if("randomCodeError".equals(exceptionClassName)){
				throw new CustomException("��֤����� ");
			}else {
				throw new Exception();//�������쳣����������δ֪����
			}
		}
		//�˷����������½�ɹ�����֤�ɹ�����shiro��֤�ɹ����Զ���ת����һ������·��
		//��½ʧ�ܻ���loginҳ��
		return "login";
	}
	
	@RequestMapping("/list")
	public String list(){
		return "main";
	}
}
