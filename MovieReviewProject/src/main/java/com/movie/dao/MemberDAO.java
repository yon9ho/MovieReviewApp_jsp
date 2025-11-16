package com.movie.dao;

import java.sql.*;

import com.movie.dto.Member;
import com.movie.util.DBUtil;


// DB 접속해서 member 테이블에 대한 CRUD 처리하는 데이터 접근 클래스
public class MemberDAO {

    // 회원 아이디의 중복 체크하기
    public boolean isUserExists(String uid) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean isExists = false;
        String sql = "SELECT userid FROM member WHERE userid = ?";
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, uid);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
            		isExists = true;
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(pstmt, conn);
        }
        return isExists;
    }

    // 회원 데이터 추가
    public int insertMember(Member m) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;

        String sql = "INSERT INTO member (userid, name, pwd, email, birth) VALUES (?, ?, ?, ?, ?)";
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, m.getUserid()); // 괄호() 빠졌던 부분 수정
            pstmt.setString(2, m.getName());
            pstmt.setString(3, m.getPwd());
            pstmt.setString(4, m.getEmail());
            pstmt.setString(5, m.getBirth());

            result = pstmt.executeUpdate(); // int 앞에 다시 선언하지 않음 (이미 위에 있음)
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(pstmt, conn);
        }
        return result;
    }
    
    // 로그인
    public boolean loginCheck(String userid, String pwd) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean login = false;

        String sql = "SELECT * FROM member WHERE userid = ? AND pwd = ?";

        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userid);
            pstmt.setString(2, pwd);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                login = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pstmt, conn);
        }
        
        return login;
    }
}
