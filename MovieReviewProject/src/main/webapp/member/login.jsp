<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
	<form action="<%=request.getContextPath()%>/LoginController" method="post">
    		아이디: <input type="text" name="userid" required><br>
    		비밀번호: <input type="password" name="pwd" required><br>
    		<input type="submit" value="로그인">
	</form>
	<% 
    	String error = (String) request.getAttribute("error");
    		if(error != null) { 
    	%>
	<p style="color:red;"><%= error %></p>
	<% } %>
</body>
</html>