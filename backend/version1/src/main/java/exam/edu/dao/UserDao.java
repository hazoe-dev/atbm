package exam.edu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import exam.edu.model.User;

public class UserDao {
	private Connection con;
	private ResultSet rs;
	private PreparedStatement pst;
	private String query;

	public UserDao(Connection con) {
		this.con = con;
	}

	public User login(String username, String password) {
		User user = null;
		try {
			query = "select * from user where username = ? and password = ? and status = 1";
			pst = this.con.prepareStatement(query);
			pst.setString(1, username);
			pst.setString(2, password);
			rs = pst.executeQuery();

			if (rs.next()) {
				user = new User();
				user.setId(rs.getLong("id"));
				user.setUsername(rs.getString("username"));
				user.setFullname(rs.getString("fullname"));
				user.setPhone(rs.getString("phone"));
				user.setAddress(rs.getString("address"));
				user.setStatus(rs.getInt("status"));
				user.setRole(rs.getInt("role"));
				user.setPublicKey(rs.getString("public_key"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return user;
	}

	public User updateInfo(String fullname, String address, String phone, Long id) {
		User user = null;
		try {

			query = "UPDATE user SET fullname =?, address =?, phone=? WHERE id =?";
			pst = this.con.prepareStatement(query);
			pst.setString(1, fullname);
			pst.setString(2, address);
			pst.setString(3, phone);
			pst.setLong(4, id);
			int rowsUpdated = pst.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println("An existing user was updated successfully!");

				query = "SELECT * FROM user WHERE id = ?";
				pst = this.con.prepareStatement(query);
				pst.setLong(1, id);
				rs = pst.executeQuery();

				if (rs.next()) {
					user = new User();
					user.setId(rs.getLong("id"));
					user.setUsername(rs.getString("username"));
					user.setFullname(rs.getString("fullname"));
					user.setPhone(rs.getString("phone"));
					user.setAddress(rs.getString("address"));
					user.setStatus(rs.getInt("status"));
					user.setRole(rs.getInt("role"));
					user.setPublicKey(rs.getString("public_key"));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return user;
	}

}
