<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="com.movie.dto.Movie"%>
<%@ page import="java.util.*"%>
<%@ page import="com.movie.dao.MovieDAO" %>
<%
	// 사용자가 선택한 정렬 기준 받기 
	String sort = request.getParameter("sort");
	if (sort == null) {
    		sort = "date";
	}

	// 정렬 기준 DAO 호출
	MovieDAO dao = new MovieDAO(); 
	List<Movie> movieList = dao.getMovies(sort); // 수정된 메서드 호출
	
	// 사용자 아이디 호출을 위한 변수 정의
    String userid = null;
    if(session != null) {
        userid = (String) session.getAttribute("userid");
    }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영화 리뷰와 평점 사이트</title>
</head>
<body style="margin: 20px; padding: 10; background-color: #f9f9f9;">
	<div>
        <h1>영화 리뷰 및 평점 사이트</h1>
            <% if(userid != null) { %>
                환영합니다, <b><%= userid %></b>님!
                <a href="<%=request.getContextPath()%>/LogoutController">로그아웃</a>
            <% } else { %>
                <button onclick="location.href='member/login.jsp'">로그인</button>
                <button onClick="location.href='member/joinForm.jsp'">회원가입</button>
            <% } %>

        <h2>등록된 영화 목록</h2>
        <div>
            <a href="index.jsp?sort=date">📅최신순</a>
            <a href="index.jsp?sort=rating">⭐평점순</a>
        </div>
        <% if(movieList != null && !movieList.isEmpty()) { %>
            <% for(Movie m : movieList) { %>
                <div style="border: 1px solid #ddd; padding: 10px; background-color: white;">
                    <h3><%= m.getTitle() %></h3>
        				<p>감독: <%= m.getDirector() %></p>
        				<p>개봉일: <%= m.getRelease_date() %></p>
        				<p>평점: 
                        <% if(m.getAvgRating() > 0) { %>
                            <span>⭐ <%= String.format("%.1f", m.getAvgRating()) %></span>
                        <% } else { %>
                            <span style="color:#999;">(평가 없음)</span>
                        <% } %>
                    </p>
					<a href="<%=request.getContextPath()%>/MovieDetailController?movie_id=<%=m.getMovie_id()%>">상세보기</a>
                </div>
            <% } %>
        <% } else { %>
            <p>등록된 영화가 없습니다.</p>
        <% } %>
     </div>
</body>
</html>
