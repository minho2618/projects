
package com.service.spring.dao;
import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.service.spring.dao.UserDAO;
import com.service.spring.domain.User;


@Repository
public class UserDAO{	
	public static final String NS = "ns.sql.UserMapper.";

	@Autowired
	private SqlSession sqlSession;
	

	public User getUser(User user) throws SQLException{
	  return null;
		
	}

	
	public void addUser(User user) throws Exception {
		
	}
	
}







