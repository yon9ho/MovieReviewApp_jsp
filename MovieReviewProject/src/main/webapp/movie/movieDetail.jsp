<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.movie.dto.Movie"%>
<%@ page import="com.movie.dto.Review"%>
<%@ page import="java.util.List"%>

<%
    Movie movie = (Movie) request.getAttribute("movie");
    List<Review> reviewList = (List<Review>) request.getAttribute("reviewList");
    String userid = (String) session.getAttribute("userid");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><%= movie != null ? movie.getTitle() : "영화 상세" %></title>
    <style>
        .review-box {
            border-bottom: 1px solid #ddd;
            padding: 10px 0;
        }
        
        .btn-link {
            cursor:pointer;
            font-size:0.9em;
        }
        .btn-edit { color:blue; }
        .btn-del { color:red; }
    </style>
</head>
<body>

    <!-- 영화 정보 -->
    <% if (movie != null) { %>
        <h2><%= movie.getTitle() %></h2>
        <p><strong>감독 : <%= movie.getDirector() %></strong></p>
        <p>개봉일 : <%= movie.getRelease_date() %></p>
        <p><strong>평균 평점:</strong> 
    <% if (movie.getAvgRating() != null && movie.getAvgRating() > 0) { %>
       		<span style="color: orange; font-size: 1.2em;">⭐<%= String.format("%.1f", movie.getAvgRating()) %></span> 
        		<small style="color: #888;"> / 5.0</small>
    <% } else { %>
        		<span style="color: #999;">(평가 없음)</span>
    <% } %>
			</p>
    <% } %>

    <hr>

    <h3>리뷰 작성</h3>

    <% if (userid != null) { %>

        <form action="<%= request.getContextPath() %>/ReviewController" method="post">
            <input type="hidden" name="action" value="등록">
            <input type="hidden" name="movie_id" value="<%= movie.getMovie_id() %>">

            평점 :
            <select name="rate">
                <option value="5">⭐⭐⭐⭐⭐</option>
                <option value="4">⭐⭐⭐⭐</option>
                <option value="3">⭐⭐⭐</option>
                <option value="2">⭐⭐</option>
                <option value="1">⭐</option>
            </select>

            <br>
            <br>

            내용<br>
            <textarea name="content" rows="3" cols="50" required></textarea>
            <br>
            <br>
            <button type="submit">등록하기</button>
        </form>

    <% } else { %>
        <p>
            <a href="<%= request.getContextPath() %>/member/login.jsp">로그인</a> 후 작성 가능합니다.
        </p>
    <% } %>

    <hr>

    <h3>리뷰 목록</h3>

    <% if (reviewList != null && !reviewList.isEmpty()) { %>

        <% for (Review r : reviewList) { %>

            <div >

                <strong><%= r.getUserId() %></strong>
                <span>
                    <% for (int i = 0; i < r.getRate(); i++) { %>
                        ⭐
                    <% } %>
                </span>
                (<%= r.getRate() %>점)

                <p><%= r.getContent() %></p>

                <% if (userid != null && userid.equals(r.getUserId())) { %>
                    <div >

                        <!-- 수정 -->
                        <form action="review/editReview.jsp" method="post" style="display:inline;">
                            <input type="hidden" name="reviewId" value="<%= r.getReviewId() %>">
                            <input type="hidden" name="movie_id" value="<%= r.getMovieId() %>">
                            <input type="hidden" name="rate" value="<%= r.getRate() %>">
                            <input type="hidden" name="content" value="<%= r.getContent() %>">

                            <button class="btn-link btn-edit" type="submit">수정</button>
                        </form>

                        <!-- 삭제 -->
                        <form action="<%= request.getContextPath() %>/ReviewController" method="post" style="display:inline;">
                            <input type="hidden" name="action" value="삭제">
                            <input type="hidden" name="reviewId" value="<%= r.getReviewId() %>">
                            <input type="hidden" name="movie_id" value="<%= r.getMovieId() %>">

                            <button class="btn-link btn-del" type="submit"
                                    onclick="return confirm('정말 삭제하시겠습니까?')">
                                삭제
                            </button>
                        </form>
						
                    </div>
                <% } %>
				<hr/>
            </div>

        <% } %>

    <% } else { %>
        <p>등록된 리뷰가 없습니다.</p>
    <% } %>

    <br>
    <a href="<%= request.getContextPath() %>/index.jsp">영화 목록 페이지 이동</a>

</body>
</html>
