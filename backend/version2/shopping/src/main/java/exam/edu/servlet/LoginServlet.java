package exam.edu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exam.edu.connection.DbCon;
import exam.edu.dao.UserDao;
import exam.edu.model.User;

@WebServlet("/user-login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			User auth = (User) request.getSession().getAttribute("auth");
			if(auth!= null) {
				response.sendRedirect("/home");
			}else {
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.include(request, response);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			String username = request.getParameter("login-email");
			String password = request.getParameter("login-password");
			String error = null;

			try {
				UserDao udao = new UserDao(DbCon.getConnection());
				User user = udao.login(username, password);
				if (user != null) {
					request.getSession().setAttribute("auth", user);
					request.getSession().setAttribute("error", error );

					// response.sendRedirect("home.jsp");
					RequestDispatcher rd = request.getRequestDispatcher("/home"); // not update url
					rd.forward(request, response);
					
				} else {
					//out.print("Sorry UserName or Password Error!");
					error = "Sorry UserName or Password Error!";
					request.getSession().setAttribute("error", error );
					RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
					rd.include(request, response);
				}
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}

		}
	}

}
