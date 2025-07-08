package servlet.model;
/*
 1. 싱글톤
 2. 생성자에서 JNDI서비스로 DataSource받아옴
 3. 공통로직 구현
 4. login 구현.....단위테스트
    Context.xml
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class UserDAOImpl implements UserDAO{
	private DataSource ds=  null;
	private static UserDAOImpl dao = new UserDAOImpl();
	private UserDAOImpl() {
		try {
			//1. JNDI 서비스 코드로 수정...
			InitialContext ic = new InitialContext();
			ds = (DataSource)ic.lookup("java:comp/env/jdbc/mysql");
			System.out.println("DataSource Lookup....success!!!");
		}catch(NamingException e) {
			System.out.println("DataSource Lookup....Fail!!!");
		}
	}
	public static UserDAOImpl getInstance() {
		return dao;
	}
	@Override
	public Connection getConnection() throws SQLException {
		System.out.println("디비연결 성공...");
		return ds.getConnection();
	}

	@Override
	public void close(PreparedStatement ps, Connection conn) throws SQLException {
		if(ps!=null) ps.close();
		if(conn!=null) conn.close();	
	}
	@Override
	public void close(ResultSet rs, PreparedStatement ps, Connection conn) throws SQLException {
		if(rs!=null) rs.close();
		close(ps, conn);	
	}
	@Override
	public User login(String userId, String password) throws SQLException {
		User user = null;
		Connection conn=  getConnection();
		String query = 
		"SELECT userId, password, name, email FROM userinfo WHERE userid=? and password=?";
		PreparedStatement ps= conn.prepareStatement(query);
		ps.setString(1, userId);
		ps.setString(2, password);
		
		ResultSet rs = ps.executeQuery();
		if(rs.next())
			user = new User(userId, 
					        password, 
					        rs.getString("name"), 
					        rs.getString("email"));
		return user;
	}
	
	public static void main(String[] args)throws Exception {
		//System.out.println(UserDAOImpl.getInstance().login("java", "java"));
	}

}
