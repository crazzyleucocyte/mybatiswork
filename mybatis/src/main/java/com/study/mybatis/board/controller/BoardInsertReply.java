package com.study.mybatis.board.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import com.study.mybatis.board.service.BoardServiceImpl;
import com.study.mybatis.board.vo.Reply;
import com.study.mybatis.member.vo.Member;

public class BoardInsertReply extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		//System.out.println("문제가 어디야!!!  3");


		
		String content=request.getParameter("content");
		int refBno = Integer.parseInt(request.getParameter("boardNo"));
		String writer=((Member)session.getAttribute("loginUser")).getUserId();
		
	
		
		//Impl로 넘겨줄 reply객체 생성
		Reply reply = new Reply(content, refBno, writer, "sysdate", "Y");
		boolean result = new BoardServiceImpl().insertReply(reply);
		
	
		//insert 성공
		if(result) {
			System.out.println("끝까지 갔다가 컨트롤러로 옴");
			//request.setAttribute("insertedReply", 1);
//			request.setAttribute("bno", refBno);
			//request.getRequestDispatcher("detail.bo?bno="+refBno).forward(request, response);
		}
		out.print(result);
	}
	/*

		private int replyNo;  			//댓글 넘
		private String replyContent;	//댓글 내용
		private int refBno;				//seq_rno.nextval
		private String replyWriter;		//세션 아이디
		private String createDate;		//sysdate

		해결
		1. private String status;			//회원 아이디로 조회 했을때 'N'면 추가 불가능?
	 */


	
}


