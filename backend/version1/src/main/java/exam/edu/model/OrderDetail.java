package exam.edu.model;

import java.io.Serializable;
import java.util.Objects;

public class OrderDetail implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;
	private Integer quantity;
	private Long orderId;
	private Long productId;

	public OrderDetail() {
	}

	public OrderDetail(Long id, Integer quantity, Long orderId, Long productId) {
		this.id = id;
		this.quantity = quantity;
		this.orderId = orderId;
		this.productId = productId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
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
		OrderDetail other = (OrderDetail) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "OrderDetail [id=" + id + ", quantity=" + quantity + ", orderId=" + orderId + ", productId=" + productId
				+ "]";
	}

}
