package com.movie.controller;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import com.movie.dao.MemberDAO;
import com.movie.dto.Member;

public class JoinController extends HttpServlet {
	
	@Override  // method="get" 방법으로 입력양식 데이터 전달할 때
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}
	
    @Override  // method="post" 방법으로 입력양식 데이터 전달할 때
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String userid = req.getParameter("userid");
        String pwd = req.getParameter("pwd");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String birth = req.getParameter("birth"); // yyyy-mm-dd

        Member member = new Member(userid, pwd, name, email, birth);
        MemberDAO dao = new MemberDAO();
        
        if(dao.isUserExists(userid)) {
        		req.setAttribute("error", "이미 존재하는 아이디입니다.");
        		RequestDispatcher rd = req.getRequestDispatcher("member/joinFail.jsp");
        		rd.forward(req, resp);
        		return;
        }
        
        int result = dao.insertMember(member);
        if(result > 0) {
            resp.sendRedirect("member/joinSuccess.jsp");
        } else {
            req.setAttribute("error", "회원가입 실패! 오류가 발생했습니다.");
            RequestDispatcher rd = req.getRequestDispatcher("member/joinFail.jsp");
            	rd.forward(req, resp);
        }
    }
}
