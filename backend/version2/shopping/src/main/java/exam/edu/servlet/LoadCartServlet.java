package exam.edu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import exam.edu.connection.DbCon;
import exam.edu.dao.CategoryDao;
import exam.edu.dao.ProductDao;
import exam.edu.dto.ProductDto;
import exam.edu.model.CartItem;

@WebServlet("/cart")
public class LoadCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String msg = null;
		Integer summary = 0;
		Integer shipCost = 0;
		Integer total = 0;
		try (PrintWriter out = response.getWriter()) {
			try {

				ProductDao productDao = new ProductDao(DbCon.getConnection());

				HttpSession session = request.getSession();
				List<CartItem> sessionCart = (List<CartItem>) session.getAttribute("cart");
				List<CartItem> products = productDao.getCartProducts(sessionCart);

				if (products != null && products.size() > 0) {
					request.setAttribute("products", products);
					for (CartItem item : products) {
						summary += item.getTmpPrice();
					}
					shipCost = (summary > 250000 ? 0 : 25000);
					total = summary + shipCost;
				} else {
					msg = "Giỏ hàng hiện chưa có sản phẩm";
				}

				request.setAttribute("uri", request.getRequestURI());
				request.setAttribute("msg", msg);
				request.getSession().setAttribute("summary", summary);
				request.getSession().setAttribute("shipCost", shipCost);
				request.getSession().setAttribute("total", total);
				RequestDispatcher rd = request.getRequestDispatcher("cart.jsp");
				rd.include(request, response);

			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
