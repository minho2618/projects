package com.service.spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.service.spring.domain.Book;
import com.service.spring.service.BookService;

/*
	@RequestMapping 부분은 클라이언트 요청부분을 직접 확인해서
	@GetMapping | @PostMapping으로 수정합니다.
*/
@Controller
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@RequestMapping("bookList")
	public ModelAndView getBooks() throws Exception{
		List<Book> list=bookService.getBooks();
		
		return new ModelAndView("book/bookList","list",list);
	}
	
	@RequestMapping("bookRegister")
	public ModelAndView register()throws Exception{
		System.out.println("register BookVO before:: "+book);//?
		
		
		
		String msg = "";
		String path = "Error.jsp";
		try {
			
			
		}catch(Exception e) {
			
		}
	
		return new ModelAndView();
	}
	
	@RequestMapping("bookSearch")
    public ModelAndView search() throws Exception{ 
		
		List<Book> list = null;
		String path = "find_fail";
		
		try {

			
			
		}catch(Exception e) {
			
		}
		return new ModelAndView();
	}
	@RequestMapping("bookView")
    public ModelAndView bookview()throws Exception{
			
		try {
			
		}catch(Exception e) {
			
		}
		return new ModelAndView();
	}	
	

}//





