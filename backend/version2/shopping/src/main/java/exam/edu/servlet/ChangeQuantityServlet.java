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

import exam.edu.connection.DbCon;
import exam.edu.dao.ProductDao;
import exam.edu.model.CartItem;

@WebServlet("/change-quantity")
public class ChangeQuantityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			Long productId = request.getParameter("productId") != null ? Long.parseLong(request.getParameter("productId")) : null;
			Long typeId = request.getParameter("typeId") != null && request.getParameter("typeId")!="" ? Long.parseLong(request.getParameter("typeId")) : null;
			String action = request.getParameter("action");
			
			String msgQuantity = null;
			Boolean status = null;

			HttpSession session = request.getSession();
			List<CartItem> sessionCart = (List<CartItem>) session.getAttribute("cart");
			
			if (productId != null && typeId != null && sessionCart != null && sessionCart.size()>0) {
				boolean existed = false;
				boolean enableIncreated = false;
				for (CartItem item : sessionCart) {
					if(item.getProductId() ==productId && item.getTypeId() == typeId) {
						ProductDao productDao = new ProductDao(DbCon.getConnection());
						
						Integer max = productDao.getMaxQuantityProduct(typeId);
						Integer quantity = item.getQuantity();
						
						if(action.equals("inc") ) {
							if(max!=null && max >= (quantity+1)) {
								item.setQuantity(item.getQuantity()+1);
								session.setAttribute("cart", sessionCart);
								msgQuantity = "Đã giảm sản phẩm thành công!";
								status = true;
								existed = true;
							}else {
								msgQuantity = "Sản phẩm không đủ số lượng!";
								status = false;
							}
							
						}else {
							if(action.equals("dec") && item.getQuantity() > 1) {
								item.setQuantity(item.getQuantity()-1);
								session.setAttribute("cart", sessionCart);
								msgQuantity = "Đã thêm sản phẩm thành công!";
								status = true;
								existed = true;
							} else {
								msgQuantity = "Sản phẩm phải đạt số lượng tối thiểu !";
								status = false;
							}
						}
					}
				}
//				if(!existed) {
//					msgQuantity = "Không tìm thấy sản phẩm!";
//					status = false;
//				}
				
			} else {
				msgQuantity = "Đã xảy ra lỗi. Vui lòng thực hiện lại!";
				status = false;
			}
			
			request.setAttribute("msgQuantity", msgQuantity);
			request.setAttribute("status", status);
			RequestDispatcher rd = request.getRequestDispatcher("/cart");
			rd.include(request, response);
//			response.sendRedirect("/cart");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
