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

import exam.edu.connection.DbCon;
import exam.edu.dao.CategoryDao;
import exam.edu.dao.ProductDao;
import exam.edu.dto.DetailProductDto;
import exam.edu.dto.ProductDto;

@WebServlet("/home")
public class LoadHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String msg = null;
		try (PrintWriter out = response.getWriter()) {
			try {
				String group = "Sản phẩm trà";	
				String category ="";
				category = (request.getParameter("category") != null ? request.getParameter("category") : "");
				Integer cat = (request.getParameter("cat") != null ?Integer.parseInt(request.getParameter("cat")) : 0);
				ProductDao productDao = new ProductDao(DbCon.getConnection());
				List<ProductDto> products = new ArrayList<ProductDto>();
				if(category=="") {
					products = productDao.getProducts(group, 6);
				}else {
					products = productDao.getProducts(group,category, 6);
				}


				if (products != null && products.size() > 0) {
					request.setAttribute("products", products);
				} else {
					msg = "Không tìm thấy sản phẩm phù hợp";
				}
				request.setAttribute("cat", cat);
				request.setAttribute("uri", request.getRequestURI());
				request.setAttribute("msg", msg);
				RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
				rd.include(request, response);

			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				response.sendRedirect("404.jsp");
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
