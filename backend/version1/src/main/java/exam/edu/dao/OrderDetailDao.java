package exam.edu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import exam.edu.dto.OrderDetailDto;
import exam.edu.model.OrderDetail;

public class OrderDetailDao {
	private Connection con;
	private ResultSet rs;
	private PreparedStatement pst;
	private String query;
	private DecimalFormat formatter = new DecimalFormat("###,###,###");

	public OrderDetailDao(Connection con) {
		this.con = con;
	}

	public Long insertOrderDetail(OrderDetail orderDetail) {
		Long generatedKey = 0l;

		try {
			query = "INSERT INTO `order_detail` (`quantity`, `order_id`, `product_id`)\r\n"
					+ " VALUES (?, ?, ?);";
			pst = con.prepareStatement(query,
			        Statement.RETURN_GENERATED_KEYS);
			pst.setInt(1, orderDetail.getQuantity());
			pst.setLong(2, orderDetail.getOrderId());
			pst.setLong(3, orderDetail.getProductId());

			int affectedRows= pst.executeUpdate();
			if (affectedRows == 0) {
	            throw new SQLException("Creating user failed, no rows affected.");
	        }
			try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	            	generatedKey = generatedKeys.getLong(1);
	            }
	            else {
	                throw new SQLException("Creating user failed, no ID obtained.");
	            }
			}
			 
			System.out.println("Inserted record's ID: " + generatedKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return generatedKey;
	}

	public List<OrderDetailDto> getOrderDetail(Long orderId) {
		List<OrderDetailDto> results = new ArrayList<OrderDetailDto>();
		try {
			query = "select d.*, i.link, p.name, p.priceKg, t.price, t.name as type \r\n"
					+ "from order_detail d \r\n"
					+ "INNER JOIN product p ON p.id = d.product_id \r\n"
					+ "INNER JOIN image i ON p.id = i.product_id\r\n"
					+ "INNER JOIN type t ON p.id = t.product_id    \r\n"
					+ "where order_id =?\r\n"
					+ "GROUP BY d.id, p.id";
			pst = this.con.prepareStatement(query);
			pst.setLong(1, orderId);
			rs = pst.executeQuery();

			while (rs.next()) {
				OrderDetailDto detail = new OrderDetailDto();
				detail.setOrderId(orderId);
				detail.setProductId(rs.getLong("product_id"));
				detail.setQuantity(rs.getInt("quantity"));
				detail.setName(rs.getString("name"));
				detail.setPriceKg(formatter.format(rs.getInt("priceKg")) + "đ/ 1kg");
				detail.setUrlImg(rs.getString("link"));
				detail.setPrice(rs.getInt("price"));
				detail.setType(rs.getString("type"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return results;
	}
	
}
