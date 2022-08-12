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

			if (auth == null) {
				response.sendRedirect("user-login");
			} else {
				if (sessionCart == null || sessionCart.size() < 1 || summary == null || shipCost == null
						|| total == null) {
					response.sendRedirect("/cart");
				} else {
					if (isEmpty(fullname) || isEmpty(address) || isEmpty(phone)) {
						RequestDispatcher rd = request.getRequestDispatcher("checkout.jsp");
						rd.forward(request, response);
					} else {
						List<CartItem> products = productDao.getCartProducts(sessionCart);
						Order order = new Order();
						order.setNote(note);
						order.setShipcost(shipCost + 0L);
						order.setTotal(total + 0L);
						order.setDate(formatter.format(date));
						order.setUserId(auth.getId());
						order.setVerified(false);

						handlePlaceOrder(request, response, fullname, address, phone, note, products, auth, order);

					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void handlePlaceOrder(HttpServletRequest request, HttpServletResponse response, String fullname,
			String address, String phone, String note, List<CartItem> products, User auth, Order order) {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {

			// hỏi xác thực user với user.toString() và public key
			// tiếp tục tạo db xuống csdl với order verified = true, status = đang kiểm tra
			// cập nhật info-bill = user.toString + order.toString()

			if (auth.getFullname() != fullname || auth.getAddress() != address || auth.getPhone() != phone) {
				UserDao userDao = new UserDao(DbCon.getConnection());
				auth = userDao.updateInfo(fullname, address, phone, auth.getId());
				if (auth == null) {
					handleError(request, response);
					return;
				} 
				request.getSession().setAttribute("auth", auth);
			}
			OrderDao orderDao = new OrderDao(DbCon.getConnection());
			Long orderId = orderDao.insertOrder(order);
			if (orderId == 0) {
				handleError(request, response);
				return;
			}
			// Người dùng được active 1 public key
			// thì set public key field của user bằng key này.
			// tạo cập khóa thì thêm khóa public key của người dùng vào bảng key với status
			// là chưa kích hoạt=false

			// hỏi xác thực đơn hàng với user.toString + order.toString(), public key trong
			// user = auth logging in
			// Xác thực thành công;
			/*
			 * Cách xác thực: 1. hiển thị popup nhập public key người dùng 2. Nhấn nút gửi:
			 * -hash
			 * (user(id-username-fullname-phone-address-status-role-public_key+key=secret):
			 * lấy user trong request session -Dùng private key trong input người dùng nhập
			 * => mã hóa hashcode ở trên: thu được signature -Gửi user và signature lên
			 * server -----Client-End--------- 1. Lấy user người dùng gửi + key = secret
			 * -Thực hiện hash => thu được hashcode 2. Dùng public key của người dùng mã hóa
			 * => Thu được hashcode' 3. Thực hiện so sánh 2 mã hashcode => -Equal=> ok -Not
			 * equal => Thông báo xác nhật user thất bại chuyển về trang checkout với
			 * msgAuth
			 */
			/*
			 * Tương tự với: xác thực order: thông tin là order
			 * (id-total-shipcost-userid-note-date-verified-address-phone-fullname)
			 */
			/*
			 * Có thể dùng giải thuật khác để mã hóa! Hoặc chung 1 giải thuật.
			 */
			// +update order với status: đang giao
			// + insert order detail
			// + update quantity type of product
			// + hiển thị giao diện đơn hàng.

			OrderDetailDao detailDao = new OrderDetailDao(DbCon.getConnection());
			for (CartItem item : products) {
				OrderDetail orderDetail = new OrderDetail();
				orderDetail.setProductId(item.getProductId());
				orderDetail.setOrderId(orderId);
				orderDetail.setQuantity(item.getQuantity());
				Long detailId = detailDao.insertOrderDetail(orderDetail);
				if (detailId == 0L) {
					break;
				}
			}

			request.getSession().setAttribute("cart", null);
			RequestDispatcher rd = request.getRequestDispatcher("/order?id=" + orderId);
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

	private void handleError(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			this.msgCheckout = "Quá trình xử lý xảy ra lỗi. Vui lòng thực hiện lại!";
			this.msgStatus = false;
			request.setAttribute("msgStatus", msgStatus);
			request.setAttribute("msgCheckout", msgCheckout);
			RequestDispatcher rd = request.getRequestDispatcher("/checkout");
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
