<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오류 발생</title>
</head>
<body>
	 <div>
        <%
    			if (request.getAttribute("error") != null) {
        			out.println(request.getAttribute("errorMsg"));
    			} else {
        			out.println("알 수 없는 오류가 발생했습니다.");
    			}
		%>
        <a href="<%= request.getContextPath() %>/index.jsp">목록으로 돌아가기</a>
    </div>
</body>
</html>