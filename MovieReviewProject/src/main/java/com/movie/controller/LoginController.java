package com.movie.controller;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import com.movie.dao.MemberDAO;

public class LoginController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String userid = req.getParameter("userid");
        String pwd = req.getParameter("pwd");

        MemberDAO dao = new MemberDAO();
        boolean login = dao.loginCheck(userid, pwd);

        if(login) {
            HttpSession session = req.getSession();
            session.setAttribute("userid", userid);
            resp.sendRedirect("index.jsp"); // 로그인 성공 후 메인 페이지
        } else {
            req.setAttribute("error", "아이디 또는 비밀번호가 잘못되었습니다.");
            RequestDispatcher rd = req.getRequestDispatcher("/member/login.jsp");            
            	rd.forward(req, resp);
        }
    }
}
