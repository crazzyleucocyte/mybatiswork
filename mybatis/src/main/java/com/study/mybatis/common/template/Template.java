package com.study.mybatis.common.template;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Template {
	
	public static SqlSession getSqlSession() {
		SqlSession sqlSession = null;
		
		//SqlSession을 생성하기 위한 자바 클래스 xml을 리소스로해서 sql세션을 생성한다.
		
		//SqlSession을 생성하기 위해서 SqlSesionFactory 필요
		//SqlSessionFactory를 사용하기 위해서는 SqlSessionFactoryBuilder가 필요하다.
		
		String resource = "/mybatis-config.xml";
		
		try {
			InputStream stream = Resources.getResourceAsStream(resource);
			sqlSession = new SqlSessionFactoryBuilder().build(stream).openSession(false);
			//openSession() : 자동커밋의 여부
			//openSession() : 기본값 false => 개발자가 수동커밋 || true => 자동으로 커밋
			//opensession에 false를 넣으면 커밋은 수동으로 하겠다는 뜻, true는 자동으로 커밋을 하겠다는 뜻 mybatis-config.xml 파일에 managed와 같이 맞춰야한다.
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sqlSession;
	}
}
