package com.movie.controller;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import com.movie.dao.ReviewDAO;
import com.movie.dto.Review;

public class ReviewController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        String action = req.getParameter("action");
        int movieId = 0;
        try {
        		movieId = Integer.parseInt(req.getParameter("movie_id"));
        } catch (Exception e) {
            req.setAttribute("error", "유효하지 않은 영화 ID입니다.");
            req.getRequestDispatcher("review/reviewFail.jsp").forward(req, resp);
            return;
        }

        HttpSession session = req.getSession(false);
        String userid = null;
        if (session != null) {
            userid = (String) session.getAttribute("userid");
        }

        if (userid == null) {
            resp.sendRedirect(req.getContextPath() + "/member/login.jsp");
            return;
        }

        ReviewDAO dao = new ReviewDAO();

        try {
            if ("등록".equals(action)) {
                String rateStr = req.getParameter("rate");
                String content = req.getParameter("content");

                int rate = Integer.parseInt(rateStr);
                Review review = new Review(0, movieId, userid, rate, content);
                dao.insertReview(review);

            } else if ("수정".equals(action)) {
                String reviewIdStr = req.getParameter("reviewId");
                String rateStr = req.getParameter("rate");
                String content = req.getParameter("content");
                
                // 수정 후 돌아갈 영화 ID 받아오기
                // editReview.jsp에서 name="movie_id"로 보냈으므로 이걸 받아야 함
                String MovieIdStr = req.getParameter("movie_id"); 
                if(MovieIdStr != null) {
                    movieId = Integer.parseInt(MovieIdStr);
                }

                try {
                    int reviewId = Integer.parseInt(reviewIdStr);
                    int rate = Integer.parseInt(rateStr);
                    
                    // DTO 생성 및 업데이트 실행
                    Review review = new Review(reviewId, movieId, userid, rate, content);
                    dao.updateReview(review);
                    
                } catch (Exception e) {
                    req.setAttribute("error", "리뷰 수정 중 오류 발생");
                    req.getRequestDispatcher("review/reviewFail.jsp").forward(req, resp);
                }
            } else if ("삭제".equals(action)) {
                String reviewIdStr = req.getParameter("reviewId");
                try {
                    int reviewId = Integer.parseInt(reviewIdStr);
                    
                    // DAO에 삭제 요청 (reviewId와 현재 로그인한 userid를 같이 보내서 본인 확인)
                    dao.deleteReview(reviewId, userid);
                    
                } catch (Exception e) {
                    // 로그에 남기고 에러 페이지로 이동 (선택사항)
                    e.printStackTrace();
                    req.setAttribute("error", "리뷰 삭제 중 오류 발생");
                    req.getRequestDispatcher("review/reviewFail.jsp").forward(req, resp);
                    return; // 여기서 끊어줌
                }
            }

            // (마지막 리다이렉트 부분)
            // movie_id 파라미터 이름을 꼭 확인하세요!
            resp.sendRedirect(req.getContextPath() + "/MovieDetailController?movie_id=" + movieId);

        } catch (Exception e) {
            // 서버 로그에만 기록
            e.printStackTrace();
            req.setAttribute("error", "리뷰 처리 중 오류가 발생했습니다.");
            req.getRequestDispatcher("review/reviewFail.jsp").forward(req, resp);
        }
    }
}
