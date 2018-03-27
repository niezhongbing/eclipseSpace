<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<form action="login" method="post">
		用戶名：<input name="username"><br/>
		密码：<input type="password" name="password"><br/>
		<input type="hidden" value="99999" name="randomcode">
		记住我：<input name="rememberMe" type="checkbox"><br/>
		<input type="submit" value="提交">
	</form>
</body>
</html>