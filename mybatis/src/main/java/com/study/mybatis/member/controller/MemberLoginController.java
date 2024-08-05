package com.study.mybatis.member.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.study.mybatis.member.service.MemberServiceImpl;
import com.study.mybatis.member.vo.Member;

public class MemberLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Member m = new Member();
		String id = request.getParameter("userId");
		String pwd = request.getParameter("userPwd");
		m.setUserId(id);
		m.setUserPwd(pwd);
		
		Member loginUser = new MemberServiceImpl().loginMember(m);
		HttpSession session = request.getSession();
		
		if(loginUser==null) { //로그인 실패
			request.setAttribute("errorMsg", "로그인 실패");
			request.getRequestDispatcher("WEB-INF/views/common/errorPage.jsp").forward(request, response);
		}else {
		
		session.setAttribute("loginUser", loginUser);
		//System.out.println("request.getContextPath()"+request.getContextPath());
		response.sendRedirect(request.getContextPath());
		}
	}

}
