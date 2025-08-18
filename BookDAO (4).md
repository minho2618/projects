package com.service.spring.dao;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.service.spring.dao.BookDAO;
import com.service.spring.domain.Book;

@Repository
public class BookDAO {
	public static final String NS = "ns.sql.BookMapper.";
	
	@Autowired	
	private SqlSession sqlSession;
	
	public void insertBook(Book vo) throws Exception{	
		
	}
	
	public List<Book> getBooks() throws Exception{
          

        return null;
	}
	
	public List<Book> searchByTitle(String word) throws Exception{
       return null;
	}

	public List<Book> searchByPublisher(String word) throws Exception{
		return null;
	}
	
	public List<Book> searchByPrice(int price) throws Exception{
		return null;
	}

	public Book searchByIsbn(String word) throws Exception{        
		return null;
	}

	
	public void delete(String word) throws Exception{
		
	}
	
	
	public Book getIsbn(String word) throws Exception{
		
        return null;
	}
	
	public void update(Book vo) throws Exception{
		
	}	
	
}
