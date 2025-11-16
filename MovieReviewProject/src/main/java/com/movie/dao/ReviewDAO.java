package com.movie.dao;

import java.sql.*;
import java.util.*;
import com.movie.dto.Review;
import com.movie.util.DBUtil;

public class ReviewDAO {

    // Read. 특정 영화의 리뷰 목록 조회
    public ArrayList<Review> getReviews(int movieId) {
        ArrayList<Review> list = new ArrayList<>();
        String sql = "SELECT review_id, movie_id, userid, rate, content " +
                     "FROM review WHERE movie_id = ? ORDER BY review_id DESC";
        Connection conn = null;
        PreparedStatement pstmt = null;
        		
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, movieId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    list.add(new Review(
                        rs.getInt("review_id"),
                        rs.getInt("movie_id"),
                        rs.getString("userid"),
                        rs.getInt("rate"),
                        rs.getString("content")
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Create. 리뷰 작성
    public int insertReview(Review r) {
    		Connection conn = null;
    		PreparedStatement pstmt = null;
        String sql = "INSERT INTO review (movie_id, userid, rate, content) VALUES (?, ?, ?, ?)";
        try {
        		conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, r.getMovieId());
            pstmt.setString(2, r.getUserId());
            pstmt.setInt(3, r.getRate());
            pstmt.setString(4, r.getContent());
            return pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    // Update. 리뷰 수정
    public int updateReview(Review r) {
        String sql = "UPDATE review SET rate = ?, content = ? WHERE review_id = ? AND userid = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, r.getRate());
            pstmt.setString(2, r.getContent());
            pstmt.setInt(3, r.getReviewId());
            pstmt.setString(4, r.getUserId());
            return pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    // Delete. 리뷰 삭제
    public int deleteReview(int reviewId, String userId) {
        String sql = "DELETE FROM review WHERE review_id = ? AND userid = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, reviewId);
            pstmt.setString(2, userId);
            return pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    // 특정 영화의 평균 평점 조회
    public double getAverageRate(int movieId) {
        String sql = "SELECT AVG(rate) AS avg_rate FROM review WHERE movie_id = ?";
        double avgRate = 0.0;

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, movieId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    avgRate = rs.getDouble("avg_rate");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return avgRate;
    }
}
