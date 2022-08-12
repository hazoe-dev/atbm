package exam.edu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exam.edu.connection.DbCon;
import exam.edu.dao.OrderDao;
import exam.edu.dto.OrderDto;
import exam.edu.model.User;

@WebServlet("/account")
public class LoadAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String msg = null;
		try (PrintWriter out = response.getWriter()) {
			User auth = (User) request.getSession().getAttribute("auth");
			if(auth==null) {
				response.sendRedirect("user-login");
				return;
			}
			OrderDao orderDao = new OrderDao(DbCon.getConnection());
			List<OrderDto> orders = orderDao.getOrdersByUserId(auth.getId());
			
			request.setAttribute("orders", orders);
			RequestDispatcher rd = request.getRequestDispatcher("account.jsp");
			rd.include(request, response);
			
		}catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("404.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
