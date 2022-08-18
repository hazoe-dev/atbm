package exam.edu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
import exam.edu.dto.ProductDto;

@WebServlet("/list")
public class LoadListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String msg = null;
		try (PrintWriter out = response.getWriter()) {
			try {
				String group = (request.getParameter("group") != null ? request.getParameter("group") : null);
				String category = (request.getParameter("category") != null ? request.getParameter("category") : null);
				String order = (request.getParameter("order") != null ? request.getParameter("order") : "0");

				ProductDao productDao = new ProductDao(DbCon.getConnection());
				List<ProductDto> products = productDao.getAllProducts(group, category, order);

				CategoryDao categoryDao = new CategoryDao(DbCon.getConnection());
				List<String> categories = categoryDao.getCategories(group);

				if (products != null && products.size() > 0) {
					request.setAttribute("products", products);
				} else {
					msg = "Không tìm thấy sản phẩm phù hợp";
				}

				request.setAttribute("order", order);
				request.setAttribute("uri", request.getRequestURI());
				request.setAttribute("group", group);
				request.setAttribute("categories", categories);
				request.setAttribute("msg", msg);
				RequestDispatcher rd = request.getRequestDispatcher("list.jsp");
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
