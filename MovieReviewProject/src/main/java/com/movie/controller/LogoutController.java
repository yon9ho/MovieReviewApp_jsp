package com.movie.controller;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class LogoutController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        HttpSession session = req.getSession(false); // 기존 세션 가져오기
        if (session != null) {
            session.invalidate(); // 세션 삭제 → 로그아웃
        }

        // 로그아웃 안내 JSP로 이동
        RequestDispatcher rd = req.getRequestDispatcher("/member/logout.jsp");
        rd.forward(req, resp);
    }
}
