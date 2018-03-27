<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="<%=basePath%>js/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#test").click(function() {
			 $.ajax({
			type : 'POST',
			url : '/SsmTemplate/Bar/select',
			data : {
				id : $("#testVal").val()
			},
			dataType : 'JSON',
			success : function(data) {
				alert(data.netbarName);
			}
			});   
		});
	});
</script>
</head>
<body>
	<h1>Hello Word</h1>
	<input type="button" value="TEST SOU SUO" id="test">:
	<input id="testVal">
	<a href="Bar/refuse">无权访问</a>
</body>
</html>