package com.service.spring.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.spring.domain.Member;


@Service
public class MemberServiceImpl{
	
	@Autowired
	private MemberDAOImpl memberDAO;
		
	
	public Member getMember(String id) throws Exception {
		return memberDAO.getMember(id);
	}


	public List<Member> showAllMember() throws Exception {
		return memberDAO.showAllMember();
	}
	
	public Member login(Member vo) throws Exception {
		return memberDAO.login(vo);
	}

}
