package servlet.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlet.model.User;
import servlet.model.UserDAOImpl;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 한글 처리
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		String path = "loginError.jsp";
		
		try {
			User user=UserDAOImpl.getInstance().login(id, password);
			if(user != null) { //로그인이 정상적으로 성공
				request.setAttribute("user", user);
				request.getRequestDispatcher("loginSuccess.jsp").forward(request, response);
			}else { //로그인 실패
				response.sendRedirect("./error/error.html");
			}
		}catch(SQLException e) {
			
		}		
	}
}





















