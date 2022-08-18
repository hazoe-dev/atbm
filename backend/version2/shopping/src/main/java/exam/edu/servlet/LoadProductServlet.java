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
import exam.edu.dao.ProductDao;
import exam.edu.dto.DetailProductDto;
import exam.edu.dto.ProductDto;

@WebServlet("/product")
public class LoadProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String msg = null;
		try (PrintWriter out = response.getWriter()) {
			try {

				Long id = (request.getParameter("id") != null ?Long.parseLong(request.getParameter("id")) : null);
				
				if(id==null) {
					response.sendRedirect("404.jsp");
				}
				else {
					ProductDao productDao = new ProductDao(DbCon.getConnection());
					DetailProductDto product = productDao.getDetailProduct(id);
					//check id tồn tại không? status có =1 
					if(product.getId()==null) {
						response.sendRedirect("404.jsp");
						return;
					}
					request.setAttribute("product", product);
					
					List<ProductDto> list = productDao.getProducts(product.getGroup(),4);
					request.setAttribute("list", list);
					request.setAttribute("msg", msg);
					RequestDispatcher rd = request.getRequestDispatcher("product.jsp");
					rd.include(request, response);
				}

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
