package com.service.spring.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.service.spring.domain.Member;


@Repository
public class MemberDAOImpl{
	
	public static final String NS ="MemberMapper.";
	
	@Autowired
	private SqlSession sqlSession;
	

	public int registerMember(Member vo) throws Exception {
		int result = sqlSession.insert(NS+"registerMember",vo);
		return result;
	}

	
	public int deleteMember(String id) throws Exception {
		int result = sqlSession.delete(NS+"deleteMember",id);
		return result;
	}

	
	public int updateMember(Member vo) throws Exception {
		int result = sqlSession.update(NS+"updateMember",vo);
		return result;
	}

	
	public Member getMember(String id) throws Exception {
		return sqlSession.selectOne(NS+"getMember",id);
	}


	public List<Member> showAllMember() throws Exception {
		return sqlSession.selectList(NS+"showAllMember");
	}

	
	public Member login(Member vo) throws Exception {
		return sqlSession.selectOne(NS+"login",vo);
	}


	public String idExist(String id) throws Exception {
		return sqlSession.selectOne(NS+"idExist",id);
	}
}
