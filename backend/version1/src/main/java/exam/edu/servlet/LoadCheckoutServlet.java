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
import javax.servlet.http.HttpSession;

import exam.edu.connection.DbCon;
import exam.edu.dao.ProductDao;
import exam.edu.model.CartItem;
import exam.edu.model.User;

@WebServlet("/checkout")
public class LoadCheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			
			ProductDao productDao = new ProductDao(DbCon.getConnection());
			
			HttpSession session = request.getSession();
			List<CartItem> sessionCart = (List<CartItem>) session.getAttribute("cart");
			Integer summary = (Integer) session.getAttribute("summary");
			Integer shipCost = (Integer) session.getAttribute("shipCost");
			Integer total = (Integer) session.getAttribute("total");

			User auth = (User) session.getAttribute("auth");
			
			if (auth!= null && sessionCart != null && sessionCart.size() > 0 && summary != null && shipCost != null && total != null) {
				// load products
				List<CartItem> products = productDao.getCartProducts(sessionCart);

				request.setAttribute("products", products);
				
				request.setAttribute("fullname",auth.getFullname() );
				request.setAttribute("address", auth.getAddress());
				request.setAttribute("phone", auth.getPhone());
				
				RequestDispatcher rd = request.getRequestDispatcher("checkout.jsp");
				rd.include(request, response);

			} else {
				if(auth == null) {
					response.sendRedirect("/user-login");
				}
				else {
					response.sendRedirect("/cart");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			doGet(request, response);
		}
	}

}
