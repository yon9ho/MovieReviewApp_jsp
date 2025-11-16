package com.movie.util;

import java.sql.*;


public class DBUtil {
	private static final String URL = "jdbc:mysql://localhost:3306/movie_review_project?serverTimezone=UTC";
	private static final String USER = "root";
	private static final String PWD = "11223344";
	
	// DB 접속 클래스
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(URL, USER, PWD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	// DB 접속 해제 메서드
	public static void close(AutoCloseable...autoCloseables) {
		for (AutoCloseable ac : autoCloseables) {
			try {
				if (ac != null) ac.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}