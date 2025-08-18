package com.jdbc.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.encore.exception.BookNotFoundException;
import com.encore.exception.DuplicateISBNException;
import com.encore.exception.InvalidInputException;
import com.encore.vo.Book;

public interface BookDAO {
	void registerBook(Book vo) throws DMLException,DuplicateISBNException;//DuplicateISBNException
	void deleteBook(String isbn) throws DMLException,BookNotFoundException;//BookNotFoundException
	Book findByBook(String isbn,String title) throws DMLException;
	ArrayList<Book> findByWriter(String writer) throws DMLException;
	ArrayList<Book> findByPublisher(String publisher) throws DMLException;
	
	//가격대별 검색
	ArrayList<Book> findByPrice(int min, int max) throws DMLException,InvalidInputException;//InvalidInputException
    //출판사별 할인가
	void discountBook(int per, String publisher) throws DMLException;
}
