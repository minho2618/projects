package mybatis.services.user.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import mybatis.services.domain.User;
import mybatis.services.user.UserDAO;
//SqlSession을 주입한 의존성 객체로 작성
public class MyBatisUserDAOImpl12 implements UserDAO{
	
	private SqlSession sqlSession;
	private static final String NS= "UserMapper10.";
		
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}//Injection 마무리됨.

	@Override
	public int addUser(User user) throws Exception {
		int result=sqlSession.insert(NS+"addUser", user);
		
		return result;
	}

	@Override
	public int removeUser(String userId) throws Exception {
		int result=sqlSession.delete(NS+"removeUser", userId);
		
		return result;
	}
	@Override
	public int updateUser(User user) throws Exception {
		int result=sqlSession.update(NS+"updateUser", user);
		
		return result;
	}
	@Override
	public User getUser(String userId) throws Exception {		
		return sqlSession.selectOne(NS+"getUser", userId);
	}

	@Override
	public List<User> getUserList(User user) throws Exception {
		return sqlSession.selectList(NS+"getUserList", user);
	}
}
