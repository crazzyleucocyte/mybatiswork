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

import com.study.mybatis.board.service.BoardServiceImpl;
import com.study.mybatis.board.vo.Board;
import com.study.mybatis.common.template.Pagination;
import com.study.mybatis.common.vo.PageInfo;

public class BoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		String keyField =String.valueOf(request.getParameter("keyField"));
		String keyWord=request.getParameter("keyWord");
		//System.out.println(keyWord);
		int totalRecord = new BoardServiceImpl().selectTotalRecord(keyField,keyWord);
		int nowPage = Integer.parseInt(request.getParameter("nowPage"));
		
		PageInfo pi = Pagination.getPageInfo(totalRecord, nowPage, 5, 3);
		
		//검색 조건을 pi에 넣음
		pi.setKeyField(keyField);
		pi.setKeyWord(keyWord);
		
		//System.out.println("pi : " + pi);
		
		ArrayList<Board> list = new BoardServiceImpl().selectList(pi);
		//System.out.println("controller List : "+list);
		//검색결과가 없다면 nowPage를 0으로 해서 이전, 다음이 보이지 않도록함
		if(list.size()==0) {
			pi.setNowPage(0);
			out.println("<script>");
			out.println("alert('검색결과가 존재하지 않습니다.')");
			out.println("</script>");
		}
		request.setAttribute("pi", pi);
		request.setAttribute("list", list);
		/*
		 * if(request.getParameter("keyWord")!=null) {
		 * request.setAttribute("keyWord",keyWord);
		 * request.setAttribute("keyField",keyField);
		 * 
		 * }
		 */
		
		request.getRequestDispatcher("WEB-INF/views/board/boardListView.jsp").forward(request, response);
	}

}
