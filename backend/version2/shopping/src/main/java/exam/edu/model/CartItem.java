package exam.edu.model;

public class CartItem {
	private Long productId;
	private Long typeId;
	private Integer quantity;

	private String urlImg;
	private String name;
	private Integer price;
	private Integer tmpPrice;
	private String priceKg;
	private String typeName;
	private Integer maxQuantity;

	public CartItem() {
	}

	public CartItem(Long productId, Long typeId, Integer quantity, String urlImg, String name, Integer price,
			String priceKg, String typeName, Integer tmpPrice, Integer maxQuantity) {
		super();
		this.productId = productId;
		this.typeId = typeId;
		this.quantity = quantity;
		this.urlImg = urlImg;
		this.name = name;
		this.price = price;
		this.priceKg = priceKg;
		this.typeName = typeName;
		this.tmpPrice = tmpPrice;
		this.maxQuantity = maxQuantity;
	}

	public Integer getMaxQuantity() {
		return maxQuantity;
	}

	public void setMaxQuantity(Integer maxQuantity) {
		this.maxQuantity = maxQuantity;
	}

	public Integer getTmpPrice() {
		return tmpPrice;
	}

	public void setTmpPrice(Integer tmpPrice) {
		this.tmpPrice = tmpPrice;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getUrlImg() {
		return urlImg;
	}

	public void setUrlImg(String urlImg) {
		this.urlImg = urlImg;
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

	public String getPriceKg() {
		return priceKg;
	}

	public void setPriceKg(String priceKg) {
		this.priceKg = priceKg;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "CartItem [productId=" + productId + ", typeId=" + typeId + ", quantity=" + quantity + "]";
	}

}
