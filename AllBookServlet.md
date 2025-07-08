package servlet.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlet.model.Book;
import servlet.model.BookDAO;
import servlet.model.BookDAOImpl;

@WebServlet("/book/AllBookServlet")
public class AllBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AllBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text:html/charset=utf-8;");
		
		ArrayList<Book> bookList = new ArrayList<Book>();
		
		try {
			bookList = BookDAOImpl.getInstance().getAllBook();
			request.setAttribute("bookList", bookList);
			request.getRequestDispatcher("showAll.jsp").forward(request, response);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			response.sendRedirect("../error/error.html");
			
		}
	}
}
