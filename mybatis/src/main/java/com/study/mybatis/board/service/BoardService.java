package com.study.mybatis.board.service;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import com.study.mybatis.board.vo.Board;
import com.study.mybatis.board.vo.Reply;
import com.study.mybatis.common.vo.PageInfo;

public interface BoardService {
	
//	 int selectTotalRecord();
	
	//게시판 총 글 갯수 조회
	 int selectTotalRecord( String keyField, String keyWord);
	 
	 //게시판 리스트 조회
	 ArrayList<Board> selectList(PageInfo pi);
	 
	 //게시판 상세조회
	int increaseCount(int boardNo);

	Board selectBoard(int boardNo);

	ArrayList<Reply> selectReplyList(int boardNo);
	
	public boolean insertReply(Reply reply);
	
}
