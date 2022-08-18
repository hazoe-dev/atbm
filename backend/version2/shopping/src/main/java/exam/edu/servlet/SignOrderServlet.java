package exam.edu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exam.edu.model.Order;
import exam.edu.model.User;
import exam.edu.utils.RSAUtil;
import exam.edu.utils.SHA256Hashing;

@WebServlet("/sign-order")
public class SignOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private boolean hasSign = false;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			Boolean first = (boolean) request.getSession().getAttribute("first");
			if (first != null && first == false) {
				doPost(request, response);
				return;
			}
			request.getSession().setAttribute("first", false);

			String inforBill = (String) request.getSession().getAttribute("inforBill");
			request.setAttribute("inforBill", inforBill);
			hasSign = false;
			request.setAttribute("hasSign", hasSign);
			RequestDispatcher rd = request.getRequestDispatcher("signature-order.jsp");
			rd.include(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			Boolean first = (boolean) request.getSession().getAttribute("first");
			if (first != null && first == true) {
				doGet(request, response);
				return;
			}
			String error = null;
			String inforBill = request.getParameter("sign-order");
			String key = request.getParameter("sign-key") != null ? request.getParameter("sign-key") : null;
			String signature = request.getParameter("sign-signature") != null ? request.getParameter("sign-signature")
					: null;
			User auth = (User) request.getSession().getAttribute("auth");

			if (isEmpty(signature)) {
				hasSign = false;
			}
			if (auth == null) {
				response.sendRedirect("user-login");
				return;
			}

			Order order = (Order) request.getSession().getAttribute("order");
			if (order == null) {
				response.sendRedirect("/cart");
				return;
			}
			if ((!hasSign && (isEmpty(inforBill) || isEmpty(key)))
					|| (hasSign && (isEmpty(inforBill) || isEmpty(signature)))) {
				hasSign = false;
				error = "Thông tin đơn hàng hoặc private key không hợp lệ!";
				request.setAttribute("hasSign", hasSign);
				request.setAttribute("error", error);
				RequestDispatcher rd = request.getRequestDispatcher("signature-order.jsp");
				rd.forward(request, response);
				return;
			}

			// create signature
			if (!hasSign && signature == null) {
				// hash infoBill
				String content = inforBill + SHA256Hashing.getSecret();
				String hashcode = SHA256Hashing.HashWithJavaMessageDigest(content);
				// encrypt the hashcode by private key
				signature = Base64.getEncoder().encodeToString(RSAUtil.encryptByPrivateKey(hashcode, key));

				request.setAttribute("signature", signature);
				hasSign = true;
				request.setAttribute("error", "");
				request.getSession().setAttribute("isVerifiedOrder", false);
				request.setAttribute("hasSign", hasSign);
				request.setAttribute("inforBill", inforBill);
				RequestDispatcher rd = request.getRequestDispatcher("signature-order.jsp");
				rd.include(request, response);
				return;
			}

			// sign order
			if (hasSign && !isEmpty(signature) && !isEmpty(inforBill)) {
				// create hashcode
				String hashcode1 = SHA256Hashing.HashWithJavaMessageDigestWithSecret(inforBill);

				// decrypt signature
				String publicKey = auth.getPublicKey();
				String hashcode2 = RSAUtil.decryptByPublicKey(signature, publicKey);

				// compare
				if (hashcode1.equals(hashcode2)) {
					// move to place-order with isVerifiedOrder=true
					request.getSession().setAttribute("isVerifiedOrder", true);
				} else {
					request.getSession().setAttribute("isVerifiedOrder", false);
				}
				request.setAttribute("signature", null);
				RequestDispatcher rd = request.getRequestDispatcher("/place-order");
				rd.include(request, response);
				return;
			}

			request.setAttribute("infoBill", inforBill);
			request.setAttribute("hasSign", hasSign);
			RequestDispatcher rd = request.getRequestDispatcher("signature-order.jsp");
			rd.include(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean isEmpty(String s) {
		if (s == null || s == "") {
			return true;
		}
		return false;
	}
}
