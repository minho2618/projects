package web.servlet.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.servlet.model.Book;
import web.servlet.model.BookDAOImpl;
import web.servlet.model.User;
import web.servlet.model.UserDAOImpl;


@WebServlet("/Front")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command = request.getParameter("command");
		String path = "login.html";
		
		if(command.equals("login")) {
			path = login(request, response);
		}else if(command.equals("register")) {
			path = register(request,response);
		}else if(command.equals("allBook")) {
			path = allBook(request,response);
		}
		
		request.getRequestDispatcher(path).forward(request, response);
	}

	private String allBook(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		try {
			ArrayList<Book> books = BookDAOImpl.getInstance().showAllBook();
			request.setAttribute("books", books);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "/book/showAll.jsp";
		
	}

	private String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		String path = "./error/error.html";
		
		try {
			User user = UserDAOImpl.getInstance().login(id, password);
			if(user != null) {
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				path = "loginSuccess.jsp";
			}else {
				response.sendRedirect(path);
			}
		} catch (SQLException e) {
		}
		return path;
	}
	private String register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String[] bookNoList = request.getParameterValues("bookNo");
		String isbn = "";
		for(int i=0; i<bookNoList.length; i++) {
			if(i==bookNoList.length-1) {
				isbn += bookNoList[i];
			} else {
				isbn += bookNoList[i] + "-";
			}
		}
		String title = request.getParameter("bookTitle");
		String catalogue = request.getParameter("bookCategory");
		String nation = request.getParameter("bookCountry");
		String publish_date = request.getParameter("bookDate");
		String publisher = request.getParameter("bookPublisher");
		String author = request.getParameter("bookAuthor");
		int price = Integer.parseInt(request.getParameter("bookPrice"));
		String description = request.getParameter("bookSummary");
		
		Book book = new Book(isbn, title, catalogue, nation, publish_date, publisher, author, price, description);
				
		try {
			BookDAOImpl.getInstance().registerBook(book);			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "book/bookSuccess.jsp";
		
	}
}
