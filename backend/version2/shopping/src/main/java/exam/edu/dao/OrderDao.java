package exam.edu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import exam.edu.dto.OrderDto;
import exam.edu.model.Order;
import exam.edu.model.User;

public class OrderDao {
	private Connection con;
	private ResultSet rs;
	private PreparedStatement pst;
	private String query;
	private DecimalFormat formatter = new DecimalFormat("###,###,###");

	public OrderDao(Connection con) {
		this.con = con;
	}

	public Long insertOrder(Order order) {
		Long generatedKey = 0l;

		try {

			query = "INSERT INTO `order` (`total`, `shipcost`, `user_id`, `date`)\r\n" + " VALUES (?, ?, ?, ?);";
			pst = this.con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			pst.setLong(1, order.getTotal());
			pst.setLong(2, order.getShipcost());
			pst.setLong(3, order.getUserId());
			pst.setString(4, order.getDate());

			int affectedRows = pst.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("Creating user failed, no rows affected.");
			}
			try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					generatedKey = generatedKeys.getLong(1);
				} else {
					throw new SQLException("Creating user failed, no ID obtained.");
				}
			}

			System.out.println("Inserted record's ID: " + generatedKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return generatedKey;
	}

	public Order getOrder(Long id) {
		Order order = null;
		try {
			query = "select * from `order` where id =?";
			pst = this.con.prepareStatement(query);
			pst.setLong(1, id);
			rs = pst.executeQuery();

			if (rs.next()) {
				order = new Order();
				order.setId(rs.getLong("id"));
				order.setDate(rs.getString("date"));
				order.setNote(rs.getString("note"));
				order.setShipcost(rs.getLong("shipcost"));
				order.setTotal(rs.getLong("total"));
				order.setStatus(rs.getString("status"));
				order.setInfor_bill(rs.getString("infor_bill"));
				order.setUserId(rs.getLong("user_id"));
				order.setVerified(rs.getBoolean("verified"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return order;
	}

	public List<OrderDto> getOrdersByUserId(Long userId) {
		List<OrderDto> results = new ArrayList<OrderDto>();
		try {
			query = "select id, date from `order` where user_id =?";
			pst = this.con.prepareStatement(query);
			pst.setLong(1, userId);
			rs = pst.executeQuery();

			while (rs.next()) {
				OrderDto orderDto = new OrderDto();
				orderDto.setId(rs.getLong("id"));
				orderDto.setDate(rs.getString("date"));
				results.add(orderDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return results;
	}

	public boolean updateStatusOrder(Long orderId, boolean isVerifiedOrder) {
		try {
			query = "UPDATE `order` SET status=? WHERE id=?";
			 
			pst = this.con.prepareStatement(query);
			if(isVerifiedOrder) {
				pst.setString(1, "Đang giao");
			}else {
				pst.setString(1, "Chưa xác thực");
			}
			pst.setLong(2, orderId);
			 
			int rowsUpdated = pst.executeUpdate();
			if (rowsUpdated > 0) {
			    System.out.println("An status order was updated successfully!");
			    return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
