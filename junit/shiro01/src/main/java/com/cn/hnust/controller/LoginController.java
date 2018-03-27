package com.cn.hnust.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.hnust.exception.CustomException;
import com.cn.hnust.shiro.CustomRealm;

@Controller
public class LoginController {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	      
	@Autowired
	private CustomRealm customRealm;

	@RequestMapping("/login")
	public String login(@RequestParam Map<String, String> map,HttpServletRequest request) throws Exception{
		String username = map.get("username");
		String password = map.get("password");
		String randomcode = map.get("randomcode");
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = null;
	      if (!currentUser.isAuthenticated()) {
	        	// 把用户名和密码封装为 UsernamePasswordToken 对象
	             token = new UsernamePasswordToken(username, password);
	            token.setRememberMe(true);
	            System.out.println("controller: " + token.hashCode());
	            try {
	            	// 执行登录. 
	                currentUser.login(token);
	                System.out.println("认证：" + currentUser.isAuthenticated());
	            } 
	            // 若没有指定的账户, 则 shiro 将会抛出 UnknownAccountException 异常. 
	            catch (UnknownAccountException uae) {
	                log.info("----> There is no user with username of " + token.getPrincipal());
	                return "login";
	            } 
	            // 若账户存在, 但密码不匹配, 则 shiro 会抛出 IncorrectCredentialsException 异常。 
	            catch (IncorrectCredentialsException ice) {
	                log.info("----> Password for account " + token.getPrincipal() + " was incorrect!");
	                return "login";
	            } 
	            // 用户被锁定的异常 LockedAccountException
	            catch (LockedAccountException lae) {
	                log.info("The account for username " + token.getPrincipal() + " is locked.  " +
	                        "Please contact your administrator to unlock it.");
	                return "login";

	            }catch (Exception e) {
					// TODO: handle exception
	            	System.out.println("Exception " + e.getMessage());
	            	  return "login";
				}
	        }
	      if(token != null){
	    	  HttpSession session = request.getSession();
	    	  session.setAttribute("username", token.getPrincipal());
	      }
		return "list";
	}
	
	@RequestMapping("/list")
	public String list(){
		return "main";
	}
	
	@RequestMapping("/admin")
	public String admin(){
		return "admin";
	}
	
	@RequestMapping("/user")
	public String user(){
		return "user";
	}
	
	//缓存清空
	@RequestMapping("/clearCached")
	@ResponseBody
	public String clearCached() {
		customRealm.clearCached();
		return "";
	}

	
}
