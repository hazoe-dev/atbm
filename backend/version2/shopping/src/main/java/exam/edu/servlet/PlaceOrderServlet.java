package exam.edu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import exam.edu.connection.DbCon;
import exam.edu.dao.OrderDao;
import exam.edu.dao.OrderDetailDao;
import exam.edu.dao.ProductDao;
import exam.edu.dao.UserDao;
import exam.edu.model.CartItem;
import exam.edu.model.Order;
import exam.edu.model.OrderDetail;
import exam.edu.model.User;

@WebServlet("/place-order")
public class PlaceOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String msgCheckout = null;
	private Boolean msgStatus = null;
	private boolean isVerifiedUser = false;
	private boolean isVerifiedOrder = false;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {

			String fullname = (request.getParameter("fullname") != null ? request.getParameter("fullname") : null);
			String address = (request.getParameter("address") != null ? request.getParameter("address") : null);
			String phone = (request.getParameter("phone") != null ? request.getParameter("phone") : null);
			String note = (request.getParameter("note") != null ? request.getParameter("note") : null);

			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			Date date = new Date();

			ProductDao productDao = new ProductDao(DbCon.getConnection());

			HttpSession session = request.getSession();
			List<CartItem> sessionCart = (List<CartItem>) session.getAttribute("cart");
			Integer summary = (Integer) session.getAttribute("summary");
			Integer shipCost = (Integer) session.getAttribute("shipCost");
			Integer total = (Integer) session.getAttribute("total");
			User auth = (User) request.getSession().getAttribute("auth");
			isVerifiedUser = (boolean) (session.getAttribute("isVerifiedUser") != null
					? session.getAttribute("isVerifiedUser")
					: false);
			isVerifiedOrder = (boolean) (session.getAttribute("isVerifiedOrder") != null
					? session.getAttribute("isVerifiedOrder")
					: false);
			if (!isVerifiedUser) {
				session.setAttribute("fullname", fullname);
				session.setAttribute("address", address);
				session.setAttribute("phone", phone);
				session.setAttribute("note", note);
			}

			if (auth == null) {
				response.sendRedirect("user-login");
				return;
			} else {
				if (sessionCart == null || sessionCart.size() < 1 || summary == null || shipCost == null
						|| total == null) {
					response.sendRedirect("/cart");
					return;
				} else {
					fullname = (String) request.getSession().getAttribute("fullname");
					address = (String) request.getSession().getAttribute("address");
					phone = (String) request.getSession().getAttribute("phone");
					note = (String) session.getAttribute("note");
					if (!isVerifiedUser && (isEmpty(fullname) || isEmpty(address) || isEmpty(phone))) {
						RequestDispatcher rd = request.getRequestDispatcher("checkout.jsp");
						rd.forward(request, response);
					} else {
						/* bắt lặp */
						Order existedOrder = (Order) request.getSession().getAttribute("order");
						if (existedOrder == null || existedOrder.getId() == null) {

							List<CartItem> products = productDao.getCartProducts(sessionCart);
							Order order = new Order();
							order.setNote(note);
							order.setShipcost(shipCost + 0L);
							order.setTotal(total + 0L);
							order.setDate(formatter.format(date));
							order.setUserId(auth.getId());
							order.setVerified(false);
							order.setStatus("Chưa xác thực");
							session.setAttribute("order", order);
							session.setAttribute("cart", products);
						}
						handlePlaceOrder(request, response);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void handlePlaceOrder(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			// xác nhận người dùng
			if (isVerifiedUser) {
				User auth = (User) request.getSession().getAttribute("auth");
				String fullname = (String) request.getSession().getAttribute("fullname");
				String address = (String) request.getSession().getAttribute("address");
				String phone = (String) request.getSession().getAttribute("phone");

				if (isEmpty(auth.getAddress()) || isEmpty(auth.getPhone()) || isEmpty(auth.getFullname())
						|| !auth.getFullname().equals(fullname) || !auth.getAddress().equals(address)
						|| !auth.getPhone().equals(phone)) {
					UserDao userDao = new UserDao(DbCon.getConnection());
					auth = userDao.updateInfo(fullname, address, phone, auth.getId());
					if (auth == null) {
						handleError(request, response, "Quá trình xử lý xảy ra lỗi. Vui lòng thực hiện lại!",
								"/checkout");
						return;
					}
					request.getSession().setAttribute("auth", auth);
				}
			} else {
				request.getSession().setAttribute("first", true);
				handleError(request, response, "Tài khoản của bạn chưa xác thực", "/sign-user");
				return;
			}
			/* bắt lặp */

			OrderDao orderDao = new OrderDao(DbCon.getConnection());
			Order order = (Order) request.getSession().getAttribute("order");
			order.setVerified(isVerifiedUser);
			String inforBill = order.getUserId() + "-User:" + request.getSession().getAttribute("fullname")
					+ "-Address:" + request.getSession().getAttribute("address") + "-Phone:"
					+ request.getSession().getAttribute("phone") + "-ShipCost:" + order.getShipcost() + "-Total:"
					+ order.getTotal();
			order.setInfor_bill(inforBill);
			if ( isVerifiedUser && !isVerifiedOrder) {
				Long orderId = orderDao.insertOrder(order);
				request.getSession().setAttribute("orderId", orderId);
				if (orderId == 0) {
					handleError(request, response, "Quá trình xử lý xảy ra lỗi. Vui lòng thực hiện lại!", "/checkout");
					return;
				}
				order.setId(orderId);
				request.getSession().setAttribute("order", order);
				request.getSession().setAttribute("inforBill", inforBill);
			}
			

			// Xác nhận đơn hàng
			if (isVerifiedOrder) {
				Long orderId= (Long) request.getSession().getAttribute("orderId");
				boolean updatedOrder = orderDao.updateStatusOrder(orderId, isVerifiedOrder);
				if (!updatedOrder) {
					handleError(request, response, "Quá trình xử lý xảy ra lỗi. Vui lòng thực hiện lại!", "/checkout");
					return;
				}
				OrderDetailDao detailDao = new OrderDetailDao(DbCon.getConnection());
				List<CartItem> products = (List<CartItem>) request.getSession().getAttribute("cart");
				for (CartItem item : products) {
					OrderDetail orderDetail = new OrderDetail();
					orderDetail.setProductId(item.getProductId());
					orderDetail.setOrderId(orderId);
					orderDetail.setQuantity(item.getQuantity());
					Long detailId = detailDao.insertOrderDetail(orderDetail);
					if (detailId == 0L) {
						break;
					}
					// cập nhật số lượng sp
				}
//				request.getSession().setAttribute("cart", null);
//				request.getSession().setAttribute("total", null);
//				request.getSession().setAttribute("shipCost", null);
//				request.getSession().setAttribute("summary", null);
//				request.getSession().setAttribute("order", null);
//				request.getSession().setAttribute("fullname", null);
//				request.getSession().setAttribute("address", null);
//				request.getSession().setAttribute("phone", null);
				
				User auth = (User) request.getSession().getAttribute("auth");
				request.getSession().invalidate();
				request.getSession().setAttribute("auth", auth);
				request.getSession().setAttribute("orderId", orderId);
			} else {
				request.getSession().setAttribute("first", true);
				handleError(request, response, "Đơn hàng của bạn chưa xác thực", "/sign-order");
				return;
			}
			Long orderId= (Long) request.getSession().getAttribute("orderId");
			if(orderId!=null) {
				RequestDispatcher rd = request.getRequestDispatcher("/order?id=" +orderId);
				request.getSession().setAttribute("orderId", null);
				rd.include(request, response);
			}
			RequestDispatcher rd = request.getRequestDispatcher("/checkout");
			rd.include(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean isEmpty(String value) {
		if (value == null || value == "") {
			return true;
		}
		return false;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void handleError(HttpServletRequest request, HttpServletResponse response, String msg, String servlet) {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			this.msgCheckout = msg;
			this.msgStatus = false;
			request.setAttribute("msgStatus", msgStatus);
			request.setAttribute("msgCheckout", msgCheckout);
			RequestDispatcher rd = request.getRequestDispatcher(servlet);
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
