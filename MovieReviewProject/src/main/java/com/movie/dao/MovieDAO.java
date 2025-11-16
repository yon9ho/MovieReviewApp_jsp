package com.movie.dao;

import java.sql.*;
import java.util.*;

import com.movie.dto.Movie;
import com.movie.util.DBUtil;

public class MovieDAO {

    // 전체 영화 목록 조회
    public ArrayList<Movie> getMovies(String sort) {
        ArrayList<Movie> movieList = new ArrayList<>();
        String sql = "SELECT m.movie_id, m.title, m.director, m.release_date, "
                + "IFNULL(AVG(r.rate), 0) AS avg_score "
                + "FROM movie m LEFT JOIN review r "
                + "ON m.movie_id = r.movie_id "
                + "GROUP BY m.movie_id ";
        // 정렬 조건 
        if (sort != null && sort.equals("rating")) {
            sql += "ORDER BY avg_score DESC";   // 평점 높은 순
        } else {
            sql += "ORDER BY m.release_date DESC";  // 최신순
        }
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
        		 conn = DBUtil.getConnection();
             pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery();

             while (rs.next()) {
                 Movie m = new Movie(
                     rs.getInt("movie_id"),
                     rs.getString("title"),
                     rs.getString("director"),
                     rs.getDate("release_date")
                 );
                 // 계산된 평균 평점을 DTO에 저장
                 m.setAvgRating(rs.getDouble("avg_score"));
                 
                 movieList.add(m);
             }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movieList;
    }

    public Movie getMovie_id(int movie_id) {
        Movie movie = null;
        String sql = "SELECT * FROM movie WHERE movie_id = ?"; // ID로 조회하는 쿼리
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, movie_id); // 매개변수로 받은 ID를 쿼리에 설정
            rs = pstmt.executeQuery();

            if (rs.next()) {
                // DB에서 찾은 데이터를 Movie 객체에 담기
                movie = new Movie(
                    rs.getInt("movie_id"),
                    rs.getString("title"),
                    rs.getString("director"),
                    rs.getDate("release_date")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return movie;
    }
}
