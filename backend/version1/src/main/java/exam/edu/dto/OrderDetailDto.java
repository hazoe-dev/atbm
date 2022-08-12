package exam.edu.dto;

public class OrderDetailDto extends ProductDto {
	private Integer quantity;
	private Long orderId;
	private Long productId;
	private Integer price;
	private String type;

	public OrderDetailDto() {
	}

	public OrderDetailDto(Integer quantity, Long orderId, Long productId, Integer price, String type) {
		super();
		this.quantity = quantity;
		this.orderId = orderId;
		this.productId = productId;
		this.price = price;
		this.type = type;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

}
