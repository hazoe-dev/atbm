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
import exam.edu.dao.OrderDetailDao;
import exam.edu.dto.OrderDetailDto;
import exam.edu.model.Order;
import exam.edu.model.User;

@WebServlet("/order")
public class LoadOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			Long id = (request.getParameter("id") != null ? Long.parseLong(request.getParameter("id")) : null);
			User auth = (User) request.getSession().getAttribute("auth");
			if (id == null || auth == null) {
				response.sendRedirect("/cart");
				return;
			} else {
				if (auth != null) {
					response.sendRedirect("/account");
					return;
				} 
			}
			OrderDao orderDao = new OrderDao(DbCon.getConnection());
			Order order = orderDao.getOrder(id);
			if (order == null) {
				response.sendRedirect("/account");
				return;
			} else {
				request.setAttribute("order", order);
				OrderDetailDao detailDao = new OrderDetailDao(DbCon.getConnection());
				List<OrderDetailDto> details = detailDao.getOrderDetail(id);
				if (details.size() > 0) {
					request.setAttribute("details", details);
				}
				RequestDispatcher rd = request.getRequestDispatcher("order.jsp");
				rd.include(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
