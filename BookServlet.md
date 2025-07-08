package servlet.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlet.model.BookDAOImpl;

@WebServlet("/book/BookServlet")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BookServlet() {
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
		
		String[] bookNoList = request.getParameterValues("bookNo");
		String isbn = bookNoList[0] + "-" + bookNoList[1] + "-" + bookNoList[2];
		String bookTitle = request.getParameter("bookTitle");
		String bookCategory = request.getParameter("bookCategory");
		
		String bookCountry = request.getParameter("bookCountry");
		String bookDate = request.getParameter("bookDate");
		String bookPublisher = request.getParameter("bookPublisher");
		
		String bookAuthor = request.getParameter("bookAuthor");
		String bookPrice = request.getParameter("bookPrice");
		String bookCurrency = request.getParameter("currency");
		String bookSummary = request.getParameter("bookSummary");
		
		try {
			BookDAOImpl.getInstance().registerBook(isbn, bookTitle, bookCategory, bookCountry, 
					bookDate, bookPublisher, bookAuthor, Integer.parseInt(bookPrice), bookCurrency, bookSummary);
			
			request.getRequestDispatcher("bookSuccess.jsp").forward(request, response);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			response.sendRedirect("../error/error.html");
		}
	}
}
