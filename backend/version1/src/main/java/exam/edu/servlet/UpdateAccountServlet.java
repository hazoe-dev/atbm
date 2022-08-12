package exam.edu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exam.edu.connection.DbCon;
import exam.edu.dao.UserDao;
import exam.edu.model.User;

@WebServlet("/update-account")
public class UpdateAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String msgCheckout = null;
	private Boolean msgStatus = null;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			String fullname = (request.getParameter("name") != null ? request.getParameter("name") : null);
			String address = (request.getParameter("address") != null ? request.getParameter("address") : null);
			String phone = (request.getParameter("phone") != null ? request.getParameter("phone") : null);
			
			User auth = (User) request.getSession().getAttribute("auth");
			if (auth == null) {
				response.sendRedirect("user-login");
				return;
			}
			if (auth.getFullname() != fullname || auth.getAddress() != address || auth.getPhone() != phone) {
				UserDao userDao = new UserDao(DbCon.getConnection());
				auth = userDao.updateInfo(fullname, address, phone, auth.getId());
				if (auth == null) {
					this.msgCheckout = "Quá trình xử lý xảy ra lỗi. Vui lòng thực hiện lại!";
					this.msgStatus = false;
					request.setAttribute("msgStatus", msgStatus);
					request.setAttribute("msgCheckout", msgCheckout);
					RequestDispatcher rd = request.getRequestDispatcher("/checkout");
					rd.forward(request, response);
					return;
				} 
				request.getSession().setAttribute("auth", auth);
			}
			response.sendRedirect("/account");
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
