package exam.edu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import exam.edu.dto.ProductDto;

public class CategoryDao {
	private Connection con;
	private ResultSet rs;
	private PreparedStatement pst;
	private String query;
	
	public CategoryDao(Connection con) {
		this.con = con;
	}
	
	public List<String> getCategories(String group) {
		group = group.equals("tea")?"Sản phẩm trà":"Dụng cụ trà";
		
		List<String> results = new ArrayList<>();
		try {
			query = "SELECT c.name \r\n"
					+ "FROM category c\r\n"
					+ "WHERE c.group = ? \r\n"
					+ "GROUP BY c.id;";
			pst = this.con.prepareStatement(query);
			pst.setString(1, group);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				String row = rs.getString("name");
				results.add(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return results;
	}
}
