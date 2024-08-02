package com.study.mybatis.member.service;

import org.apache.ibatis.session.SqlSession;

import com.study.mybatis.common.template.Template;
import com.study.mybatis.member.dao.MemberDao;
import com.study.mybatis.member.vo.Member;

public class MemberServiceImpl implements MemberService {
	private MemberDao mDao=new MemberDao();
	@Override
	public int checkId(String userId) {
		//System.out.println("MemberServiceImpl통과");
		SqlSession sqlSession = Template.getSqlSession();
		int checkid = mDao.checkId(sqlSession, userId);
		sqlSession.close();
		return checkid;
	}	
	public int insertMember(Member m) {
		SqlSession sqlSession = Template.getSqlSession();
		//System.out.println("imple1"+m.getEmail());
		int result = mDao.insertMember(sqlSession,m);
		//System.out.println("imple2"+m.getEmail());
		
		if(result>0) {
			sqlSession.commit();
		}
		
		sqlSession.close();
		return result;
	}
	@Override
	public Member loginMember(Member m) {
		SqlSession sqlSession = Template.getSqlSession();
		Member loginUser= mDao.loginMember(sqlSession, m);
		sqlSession.close();
		return loginUser;
		
		
	}
	

}
