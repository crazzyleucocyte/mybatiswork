package com.study.mybatis.board.service;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import com.study.mybatis.board.dao.BoardDao;
import com.study.mybatis.board.vo.Board;
import com.study.mybatis.board.vo.Reply;
import com.study.mybatis.common.template.Template;
import com.study.mybatis.common.vo.PageInfo;

public class BoardServiceImpl implements BoardService {
	
	private BoardDao bDao = new BoardDao();
	
	/*
	 * public int selectTotalRecord() { SqlSession sqlSession =
	 * Template.getSqlSession();
	 * 
	 * int totalRecord = bDao.selectTotalRecord(sqlSession);
	 * System.out.println("totalRecord : "+totalRecord); sqlSession.close(); return
	 * totalRecord; }
	 */
	//검색조건을 포함한 메소드
	public int selectTotalRecord(String keyField, String keyWord) {
		SqlSession sqlSession = Template.getSqlSession();
		
		int totalRecord = bDao.selectTotalRecord(sqlSession, keyField, keyWord);
		System.out.println("totalRecord : "+totalRecord);
		sqlSession.close();
		return totalRecord;
	}

	public ArrayList<Board> selectList(PageInfo pi) {
		SqlSession sqlSession=Template.getSqlSession();
		ArrayList list = bDao.selectList(sqlSession,pi);
		sqlSession.close();
		return list;
	}

	@Override
	public int increaseCount(int boardNo) {
		SqlSession sqlSession=Template.getSqlSession();
		int result = bDao.increaseCount(sqlSession, boardNo);
		
		//update 성공일때 수동커밋
		if(result>0) {
			sqlSession.commit();
		}
		
		sqlSession.close();
		return result;
	}

	@Override
	public Board selectBoard(int boardNo) {
		SqlSession sqlSession = Template.getSqlSession();
		Board result = bDao.selectBoard(sqlSession, boardNo);
		
		return result;
	}

	@Override
	public ArrayList<Reply> selectReplyList(int boardNo) {
		SqlSession sqlSession = Template.getSqlSession();
		ArrayList<Reply> list = bDao.selectReplyList(sqlSession, boardNo);
		return list;
	}

	public boolean insertReply(Reply reply) {
		SqlSession sqlSession = Template.getSqlSession();
		int result = bDao.insertReply(sqlSession, reply);
		System.out.println(result);
		boolean flag=false;
		if((result>0)) {
			flag=true;
			sqlSession.commit();
		}
		sqlSession.close();
		return flag;
	}

}
