package exam.edu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import exam.edu.model.CartItem;

@WebServlet("/add-to-cart")
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			Long productId = request.getParameter("productId") != null ? Long.parseLong(request.getParameter("productId")) : null;
			Long typeId = request.getParameter("typeId") != null && request.getParameter("typeId")!="" ? Long.parseLong(request.getParameter("typeId")) : null;
			Integer quantity = request.getParameter("quantity") != null
					? Integer.parseInt(request.getParameter("quantity"))
					: null;
			String msgCart = null;
			boolean status = false;

			if (productId != null && typeId != -1l && quantity != null) {

				List<CartItem> cart = new ArrayList<CartItem>();
				CartItem cartItem = new CartItem();
				cartItem.setProductId(productId);
				cartItem.setTypeId(typeId);
				cartItem.setQuantity(quantity);

				HttpSession session = request.getSession();
				List<CartItem> sessionCart = (List<CartItem>) session.getAttribute("cart");

				if (sessionCart == null) {
					cart.add(cartItem);
					session.setAttribute("cart", cart);
				} else {
					boolean existed = false;
					for (CartItem item : sessionCart) {
						if (item.getProductId() == productId && item.getTypeId() ==typeId) {
							item.setQuantity(item.getQuantity()+quantity);
							existed = true;
						}
					}
					
					if(!existed) {
						sessionCart.add(cartItem);
					}
					
					session.setAttribute("cart", sessionCart);
				}
				msgCart = "Thêm vào giỏ hàng thành công!";
				status = true;
			} else {
				if(typeId==-1l) {
					msgCart = "Bạn chưa chọn cách đóng gói.";
				}else {
					msgCart = "Đã xảy ra lỗi. Vui lòng thực hiện lại!";
				}
			}
			if(typeId==-1l) {
				msgCart = "Bạn chưa chọn cách đóng gói.";
			}
			request.setAttribute("msgCart", msgCart);
			request.setAttribute("status", status);
			RequestDispatcher rd = request.getRequestDispatcher("/product?id="+productId);
			rd.include(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
