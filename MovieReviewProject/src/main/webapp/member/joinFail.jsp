<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Movie App</title>
</head>
<body>
	<div>
		<h1>회원가입 실패</h1>
		<%
		String error = (String) request.getAttribute("error");
		%>
		<p>오류: <%= error %></p>
		<a href="member/joinForm.jsp">회원가입</a>
	</div>
</body>
</html>