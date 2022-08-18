package exam.edu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import exam.edu.model.CartItem;

@WebServlet("/remove-from-cart")
public class RemoveFromCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			Long productId = request.getParameter("productId") != null
					? Long.parseLong(request.getParameter("productId"))
					: null;
			Long typeId = request.getParameter("typeId") != null && request.getParameter("typeId") != ""
					? Long.parseLong(request.getParameter("typeId"))
					: null;

			if (productId != null && typeId != null) {

				HttpSession session = request.getSession();
				List<CartItem> sessionCart = (List<CartItem>) session.getAttribute("cart");

				if (sessionCart != null && sessionCart.size() > 0) {
					for (CartItem item : sessionCart) {
						if (item.getProductId() == productId && item.getTypeId() == typeId) {
							sessionCart.remove(item);
							session.setAttribute("cart", sessionCart);
							break;
						}
					}
				}
			}
			response.sendRedirect("/cart");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
