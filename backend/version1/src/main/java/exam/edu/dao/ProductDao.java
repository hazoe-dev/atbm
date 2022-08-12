package exam.edu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import exam.edu.dto.DetailProductDto;
import exam.edu.dto.ProductDto;
import exam.edu.model.CartItem;
import exam.edu.model.Type;

public class ProductDao {
	private Connection con;
	private ResultSet rs;
	private PreparedStatement pst;
	private String query;
	private DecimalFormat formatter = new DecimalFormat("###,###,###");

	public ProductDao(Connection con) {
		this.con = con;
	}

	public List<ProductDto> getAllProducts(String group, String category, String order) {
		if (group == null) {
			group = "%%";
		}
		group = group.equals("tea") ? "%Sản phẩm trà%" : "%Dụng cụ trà%";
		category = (category != null ? "%" + category + "%" : "%%");

		switch (order) {
		case "1":
			order = "ORDER BY p.priceKg asc";
			break;
		case "2":
			order = "ORDER BY p.priceKg desc";
			break;
		case "3":
			order = "ORDER BY p.soldProduct desc";
			break;
		default:
			order = "";
			break;
		}

		List<ProductDto> results = new ArrayList<>();
		try {
			query = "SELECT p.id, p.name, p.priceKg ,i.link\r\n" + "FROM category c\r\n"
					+ "INNER JOIN product p ON p.category_id=c.id\r\n" + "INNER JOIN image i ON p.id=i.product_id\r\n"
					+ "WHERE c.name like ? and c.group like ? and status =1\r\n" + "GROUP BY p.id " + order;
			pst = this.con.prepareStatement(query);
			pst.setString(1, category);
			pst.setString(2, group);
			rs = pst.executeQuery();

			while (rs.next()) {
				ProductDto row = new ProductDto();
				row.setId(rs.getLong("id"));
				row.setName(rs.getString("name"));
				row.setUrlImg(rs.getString("link"));
				row.setPriceKg(formatter.format(rs.getInt("priceKg")) + "đ/ 1kg");
				results.add(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return results;
	}

	public DetailProductDto getDetailProduct(Long id) {
		DetailProductDto row = new DetailProductDto();
		try {
			query = "SELECT p.id, p.name, p.priceKg, p.description, p.info, c.group, c.name as category\r\n"
					+ "FROM category c INNER JOIN product p ON p.category_id=c.id\r\n" + "WHERE p.id=? and p.status =1";
			pst = this.con.prepareStatement(query);
			pst.setLong(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				row.setId(rs.getLong("id"));
				row.setName(rs.getString("name"));
				row.setCategory(rs.getString("category"));
				row.setGroup(rs.getString("group"));
				row.setDescription(rs.getString("description"));
				row.setInfo(rs.getString("info"));
				row.setPriceKg(formatter.format(rs.getInt("priceKg")) + "đ/ 1kg");
			}

			query = "SELECT i.link\r\n" + "FROM image i INNER JOIN product p ON p.id= i.product_id\r\n"
					+ "WHERE p.id=? and p.status =1 ";
			pst = this.con.prepareStatement(query);
			pst.setLong(1, id);
			rs = pst.executeQuery();
			List<String> urlImgs = new ArrayList<String>();
			while (rs.next()) {
				urlImgs.add(rs.getString("link"));
			}
			row.setUrlImgs(urlImgs);

			query = "SELECT t.*\r\n" + "FROM type t INNER JOIN product p ON p.id= t.product_id\r\n"
					+ "WHERE p.id=? and p.status =1 ;";
			pst = this.con.prepareStatement(query);
			pst.setLong(1, id);
			rs = pst.executeQuery();
			List<Type> types = new ArrayList<>();
			List<Integer> typePrices = new ArrayList<>();
			while (rs.next()) {
				Type type = new Type();
				type.setId(rs.getLong("id"));
				type.setName(rs.getString("name"));
				type.setPrice(rs.getInt("price"));
				type.setQuantity(rs.getInt("quantity"));

				types.add(type);
				typePrices.add(type.getPrice());
			}
			row.setTypes(types);

			Integer min = Collections.min(typePrices);
			Integer max = Collections.max(typePrices);

			if (min != null && max != null) {
				if (min != max) {
					row.setAboutPrice(formatter.format(min) + "đ - " + formatter.format(max) + "đ");
				} else {
					row.setAboutPrice(formatter.format(min) + "đ");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return row;
	}

	public List<ProductDto> getProducts(String group, int size) {
		List<ProductDto> results = new ArrayList<>();

		try {
			query = "SELECT p.id, p.name, p.priceKg ,i.link\r\n" + "FROM category c\r\n"
					+ "INNER JOIN product p ON p.category_id=c.id\r\n" + "INNER JOIN image i ON p.id=i.product_id\r\n"
					+ "WHERE c.group like ? and status =1\r\n" + "GROUP BY p.id LIMIT ?";
			pst = this.con.prepareStatement(query);
			pst.setString(1, group);
			pst.setInt(2, size);
			rs = pst.executeQuery();

			while (rs.next()) {
				ProductDto row = new ProductDto();
				row.setId(rs.getLong("id"));
				row.setName(rs.getString("name"));
				row.setUrlImg(rs.getString("link"));
				row.setPriceKg(formatter.format(rs.getInt("priceKg")) + "đ/ 1kg");
				results.add(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return results;
	}

	public ProductDto getProduct(Long id) {
		ProductDto row = new ProductDto();

		try {
			query = "SELECT p.id, p.name, p.priceKg ,i.link\r\n" + "FROM category c\r\n"
					+ "INNER JOIN product p ON p.category_id=c.id\r\n" + "INNER JOIN image i ON p.id=i.product_id\r\n"
					+ "WHERE c.group like ? and status =1\r\n" + "GROUP BY ORDER BY p.soldProduct desc p.id LIMIT ?";
			pst = this.con.prepareStatement(query);
			pst.setLong(1, id);
			rs = pst.executeQuery();

			if (rs.next()) {
				row.setId(rs.getLong("id"));
				row.setName(rs.getString("name"));
				row.setUrlImg(rs.getString("link"));
				row.setPriceKg(formatter.format(rs.getInt("priceKg")) + "đ/ 1kg");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return row;
	}

	public List<ProductDto> getProducts(String group, String category, int size) {
		List<ProductDto> results = new ArrayList<>();

		try {
			query = "SELECT p.id, p.name, p.priceKg ,i.link\r\n" + "FROM category c\r\n"
					+ "INNER JOIN product p ON p.category_id=c.id\r\n" + "INNER JOIN image i ON p.id=i.product_id\r\n"
					+ "WHERE c.name like ? and c.group like ? and status =1\r\n" + "GROUP BY p.id LIMIT ?";
			pst = this.con.prepareStatement(query);
			pst.setString(1, "%" + category + "%");
			pst.setString(2, group);
			pst.setInt(3, size);
			rs = pst.executeQuery();

			while (rs.next()) {
				ProductDto row = new ProductDto();
				row.setId(rs.getLong("id"));
				row.setName(rs.getString("name"));
				row.setUrlImg(rs.getString("link"));
				row.setPriceKg(formatter.format(rs.getInt("priceKg")) + "đ/ 1kg");
				results.add(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return results;
	}

	public List<CartItem> getCartProducts(List<CartItem> sessionCart) {
		List<CartItem> results = new ArrayList<CartItem>();
		try {
			if (sessionCart != null && sessionCart.size() > 0) {
				for (CartItem item : sessionCart) {
					query = "SELECT p.id, p.name, p.priceKg, i.link, t.id as type_id, t.name as type_name, t.quantity, t.price\r\n"
							+ "FROM product p \r\n"
							+ "INNER JOIN image i ON p.id=i.product_id INNER JOIN `type` t ON p.id=t.product_id\r\n"
							+ "WHERE p.id= ? and p.status =1 and t.id = ?\r\n" + "GROUP BY p.id;";
					pst = this.con.prepareStatement(query);
					pst.setLong(1, item.getProductId());
					pst.setLong(2, item.getTypeId());
					rs = pst.executeQuery();

					if (rs.next()) {
						CartItem row = new CartItem();
						row.setProductId(rs.getLong("id"));
						row.setName(rs.getString("name"));
						row.setUrlImg(rs.getString("link"));
						row.setPriceKg(formatter.format(rs.getInt("priceKg")) + "đ/ 1kg");

						row.setPrice(rs.getInt("price"));
						row.setTypeId(rs.getLong("type_id"));
						row.setTypeName(rs.getString("type_name"));
						row.setMaxQuantity(rs.getInt("quantity"));
						
						Integer maxQuantity = row.getMaxQuantity();
						Integer quantity = item.getQuantity();
						Integer price = row.getPrice();

						if (quantity != null && price != null) {

							if (quantity > maxQuantity && maxQuantity > 0) {
								quantity = maxQuantity;
							}
							
							if(quantity > 0 && price > 0) {
								row.setTmpPrice(quantity*price);
								row.setQuantity(quantity);
							}
						}
						
						results.add(row);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	public Integer getMaxQuantityProduct(Long typeId) {
		Integer row = 0;

		try {
			query = "SELECT t.quantity FROM  `type` t WHERE  t.id = ?";
			pst = this.con.prepareStatement(query);
			pst.setLong(1, typeId);
			rs = pst.executeQuery();

			if (rs.next()) {
				row=rs.getInt("quantity");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return row;
	}
}
