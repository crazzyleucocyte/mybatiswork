package com.study.mybatis.member.dao;

import org.apache.ibatis.session.SqlSession;

import com.study.mybatis.member.vo.Member;

public class MemberDao {
	public int checkId(SqlSession sqlSession, String userId) {
		//System.out.println("dao통과");
		return sqlSession.selectOne("memberMapper.checkId", userId);
		//sqlSession.selectOne("memberMapper.checkId", userId);여기에서 괄호의 첫번째 매개변수에 입력하는 애는 xml의 별칭.해당 쿼리가 있는 코드의 id
		//그래서 return값은 쿼리문의 결과가 된다.
	}

	public int insertMember(SqlSession sqlSession, Member m) {
		// TODO Auto-generated method stub
		//System.out.println("Dao"+m.getEmail());
		return sqlSession.insert("memberMapper.insertMember", m);
	}

	public Member loginMember(SqlSession sqlSession, Member m) {
	
		return sqlSession.selectOne("memberMapper.loginMember", m);
	}
	
	
}
