<%@ page contentType="text/html;charset=UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>리뷰 수정</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #eee;
            padding: 20px;
        }
        .container {
            width: 500px;
            margin: 0 auto;
            background: #fff;
            padding: 20px;
            border: 1px solid #ccc;
        }
        h2 {
            text-align: center;
            font-size: 20px;
        }
        textarea {
            width: 100%;
            height: 100px;
            margin-top: 10px;
        }
        select {
            margin-top: 10px;
        }
        .btn-group {
            margin-top: 15px;
            text-align: center;
        }
        .btn {
            padding: 7px 15px;
            border: 1px solid #888;
            background: #f2f2f2;
            cursor: pointer;
        }
        .btn:hover {
            background: #ddd;
        }
    </style>
</head>
<body>

<%
    // movieDetail.jsp에서 받아온 데이터
    String reviewId = request.getParameter("reviewId");
    String movieId = request.getParameter("movie_id");
    String content = request.getParameter("content");
    String rate = request.getParameter("rate");

    // 로그인 확인
    String userid = (String) session.getAttribute("userid");
    if(userid == null) {
        out.println("<script>alert('로그인이 필요합니다.'); history.back();</script>");
        return;
    }
%>

<div class="container">
    <h2>리뷰 수정</h2>

    <form action="<%=request.getContextPath()%>/ReviewController" method="post">
        <input type="hidden" name="action" value="수정">
        <input type="hidden" name="reviewId" value="<%= reviewId %>">
        <input type="hidden" name="movie_id" value="<%= movieId %>">

        <p>
            평점:
            <select name="rate">
                <option value="5">⭐⭐⭐⭐⭐</option>
                <option value="4">⭐⭐⭐⭐</option>
                <option value="3">⭐⭐⭐</option>
                <option value="2">⭐⭐</option>
                <option value="1">⭐</option>
            </select>
        </p>

        <textarea name="content"><%= content %></textarea>

        <div style="text-align: center;">
            <button type="submit">수정하기</button>
            <a href="javascript:history.back()">취소</a>
        </div>
    </form>
</div>

</body>
</html>
