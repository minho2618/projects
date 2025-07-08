package servlet.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BookDAOImpl implements BookDAO {
	private static BookDAOImpl dao = new BookDAOImpl();
	private DataSource ds;
	
	private BookDAOImpl() {
		try {
			//1. JNDI 서비스 코드로 수정...
			InitialContext ic = new InitialContext();
			ds = (DataSource)ic.lookup("java:comp/env/jdbc/mysql");
			
			System.out.println("DataSource Lookup....success!!!");
		} catch(NamingException e) {
			e.printStackTrace();
			System.out.println("DataSource Lookup....Fail!!!");
		}
	}
	
	public static BookDAOImpl getInstance() {
		return dao;
	}
	
	@Override
	public Connection getConnection() throws SQLException {
		Connection conn = ds.getConnection();
		System.out.println("디비연결 성공...");
		return conn;
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

	// 비지니스 로직
	@Override
	public void registerBook(String isbn, String title, String catalogue, String nation, String publish_date,
			String publisher, String author, int price, String currency, String bookSummary) throws SQLException {
		Connection conn = getConnection();
		
		String query = 
				"""
				INSERT INTO book
				(isbn, title, catalogue, nation, publish_date, publisher, author, price, currency, description)
				VALUE (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
				""";
				
		PreparedStatement ps = conn.prepareStatement(query);
		if (isbn.length() != 12)
			throw new SQLException();
		
		ps.setString(1, isbn);
		ps.setString(2, title);
		ps.setString(3, catalogue);
		
		ps.setString(4, nation);
		ps.setString(5, publish_date.equals("") ? null : publish_date);
		ps.setString(6, publisher);
		
		ps.setString(7, author);
		ps.setInt(8, Integer.valueOf(price) != null ? price : null);
		ps.setString(9, currency);
		ps.setString(10, bookSummary);
		
		ps.executeUpdate();
		
		close(ps, conn);
	}
	
	@Override
	public ArrayList<Book> getAllBook() throws SQLException {
		ArrayList<Book> bookList = new ArrayList<Book>();
		
		Connection conn = getConnection();
		
		String query = 
				"""
				SELECT isbn, title, catalogue, nation, publish_date, publisher, author, price, currency, description
				FROM
				book
				""";
				
		PreparedStatement ps = conn.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			bookList.add(new Book(rs.getString("isbn"), rs.getString("title"), rs.getString("catalogue"), rs.getString("nation"), 
					rs.getString("publish_date"), rs.getString("publisher"), rs.getString("author"), 
					rs.getInt("price"),  rs.getString("description")));
		}
		
		close(rs, ps, conn);
		
		return bookList;
	}
	
}
