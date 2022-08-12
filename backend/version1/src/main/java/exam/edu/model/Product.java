package exam.edu.model;

import java.io.Serializable;
import java.util.Objects;

public class Product implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String description;
	private Integer status;
	private Long categoryId;
	private String info;
	private Integer priceKg;
	private String soldProduct;

	public Product() {
	}

	public Product(Long id, String name, String description, Integer status, Long categoryId, String info,
			Integer priceKg, String soldProduct) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.status = status;
		this.categoryId = categoryId;
		this.info = info;
		this.priceKg = priceKg;
		this.soldProduct = soldProduct;
	}

	public String getSoldProduct() {
		return soldProduct;
	}

	public void setSoldProduct(String soldProduct) {
		this.soldProduct = soldProduct;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Integer getPriceKg() {
		return priceKg;
	}

	public void setPriceKg(Integer priceKg) {
		this.priceKg = priceKg;
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
		Product other = (Product) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", status=" + status
				+ ", categoryId=" + categoryId + ", info=" + info + ", priceKg=" + priceKg + "]";
	}

}
