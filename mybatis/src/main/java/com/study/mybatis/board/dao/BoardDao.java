package com.study.mybatis.board.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.study.mybatis.board.vo.Board;
import com.study.mybatis.board.vo.Reply;
import com.study.mybatis.common.vo.PageInfo;

public class BoardDao {

	/*
	 * public int selectTotalRecord(SqlSession sqlSession) { // TODO Auto-generated
	 * method stub
	 * 
	 * return sqlSession.selectOne("boardMapper.selectTotalRecord"); }
	 */
	
	public int selectTotalRecord(SqlSession sqlSession, String keyField, String keyWord) {
		//System.out.println(keyField +" | "+ keyWord);
		PageInfo pi = new PageInfo(keyField, "%"+keyWord+"%");
		//System.out.println("keyField : "+pi.getKeyField() + " keyWord : "+pi.getKeyWord());
		return sqlSession.selectOne("boardMapper.searchTotalRecord", pi);
	}

	public ArrayList selectList(SqlSession sqlSession, PageInfo pi) {
		//mybatis에서 페이징처리를 위해서 RowBounds라는 클래스를 제공한다.(RowBounds == DB에서 Rownum)
		//offset : 몇 개의 게시글(레코드)을 건너뛰고 조회할것인지에 대한 값

		/*
		 * ex) numPerPage : 5
		 * 						 offset(건너뛸 숫자)	|  limit(조회할 숫자)
		 * 							-------------------------------------
		 * nowPage : 1      1~5				0		|		5
		 * nowPagt : 2		6~10			5		|		5
		 * nowPage : 3		11~15			10		|		5
		 */

		int limit=pi.getNumPerPage(); 
		int offset=(pi.getNowPage()-1)*pi.getNumPerPage();
		RowBounds rowBounds = new RowBounds(offset,limit);
		ArrayList list;
		
		//검색조건이 추가된 후 추가한 코드
		//pi.setKeyWord(pi.getKeyWord());
		//System.out.println("keyField : "+pi.getKeyField() + " keyWord : "+pi.getKeyWord());
		list=(ArrayList)sqlSession.selectList( "boardMapper.selectList", pi, rowBounds);
		//System.out.println(list);
		
		return list;
	}
	public int increaseCount(SqlSession sqlSession, int boardNo) {
		
		return sqlSession.update("boardMapper.increaseCount", boardNo);
	}
	public Board selectBoard(SqlSession sqlSession, int boardNo) {
		
		return sqlSession.selectOne("boardMapper.selectBoard", boardNo);
	}
	public ArrayList<Reply> selectReplyList(SqlSession sqlSession, int boardNo) {

		return (ArrayList)sqlSession.selectList("boardMapper.selectReplyList", boardNo);
	}

	public int insertReply(SqlSession sqlSession, Reply reply) {
		return sqlSession.insert("boardMapper.insertReply", reply);
	}

}
