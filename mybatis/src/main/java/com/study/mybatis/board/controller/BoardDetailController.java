package com.study.mybatis.board.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.study.mybatis.board.service.*;
import com.study.mybatis.board.vo.Board;
import com.study.mybatis.board.vo.Reply;

public class BoardDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	BoardService bService=new BoardServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardNo = Integer.parseInt(request.getParameter("bno"));
		//HttpSession session = request.getSession();
		//1. 조회수 증가
		//int insertedReply=Integer.parseInt((String)session.getAttribute("insertedReply"));
		//System.out.println("insertReply"+insertedReply);
		int result=1;
		if(request.getAttribute("insertedReply")!="-1") {
			result=bService.increaseCount(boardNo);
		}
		
		if(result>0) {
			//2. 상세조회
			Board b = bService.selectBoard(boardNo);
			//System.out.println("ReplyListBoard"+b);
			//3. 해당 글에 달린 댓글 리스트 조회
			ArrayList <Reply> list = bService.selectReplyList(boardNo);
			//System.out.println("ReplyList" + list);
			request.setAttribute("boardDetail", b);
			request.setAttribute("replyList", list);
			
			request.getRequestDispatcher("WEB-INF/views/board/boardDetailView.jsp").forward(request, response);
		}else {
			request.setAttribute("errorMsg", "상세조회 실패");
			request.getRequestDispatcher("WEB-INF/views/common/errorPage.jsp").forward(request, response);
			
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardNo = Integer.parseInt(request.getParameter("bno"));
		PrintWriter out = response.getWriter();
		//HttpSession session = request.getSession();
		//1. 조회수 증가
		//int insertedReply=Integer.parseInt((String)session.getAttribute("insertedReply"));
		//System.out.println("insertReply"+insertedReply);
		
		
			//2. 상세조회
			Board b = bService.selectBoard(boardNo);
			//System.out.println("ReplyListBoard"+b);
			//3. 해당 글에 달린 댓글 리스트 조회
			ArrayList <Reply> list = bService.selectReplyList(boardNo);
			//System.out.println("ReplyList" + list);
			response.setContentType("application/json; charset=UTF-8");
			new Gson().toJson(list,out);
			
			
		
		
	}

}
