package exam.edu.model;

import java.io.Serializable;
import java.util.Objects;

public class Type implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private Integer price;
	private Integer quantity;
	private Long productId;

	public Type() {
	}

	public Type(Long id, String name, Integer price, Integer quantity, Long productId) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.productId = productId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Type other = (Type) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Type [id=" + id + ", name=" + name + ", price=" + price + ", quantity=" + quantity + ", productId="
				+ productId + "]";
	}

}
