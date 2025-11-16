package com.movie.controller;

import java.io.IOException;
import java.util.*;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import com.movie.dao.MovieDAO;
import com.movie.dao.ReviewDAO; 
import com.movie.dto.Movie;
import com.movie.dto.Review;    

public class MovieDetailController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // 요청 영화 파라미터 받아오기
        String movieIdStr = req.getParameter("movie_id");
        int movieId = 0;
        if(movieIdStr != null) {
            movieId = Integer.parseInt(movieIdStr);
        }
        // 요청 영화 상세정보 가져오기
        MovieDAO movieDao = new MovieDAO();
        Movie movie = movieDao.getMovie_id(movieId);
        
        // 요청 영화 리뷰 목록 가져오기
        ReviewDAO reviewDao = new ReviewDAO();
        List<Review> reviewList = reviewDao.getReviews(movieId);
        
        // [추가할 코드] 평균 평점 계산 후 Movie 객체에 저장
        double avgRate = reviewDao.getAverageRate(movieId);
        movie.setAvgRating(avgRate);
        
        // 데이터 담기 (영화정보 + 리뷰목록)
        req.setAttribute("movie", movie);
        req.setAttribute("reviewList", reviewList); // JSP로 전달!
        // 5. 이동
        RequestDispatcher rd = req.getRequestDispatcher("/movie/movieDetail.jsp");
        rd.forward(req, resp);
    }
}