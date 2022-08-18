package exam.edu.dto;

public class ProductDto {
	private Long id;
	private String name;
	private String urlImg;
	private String priceKg;
	
	public ProductDto() {
	}

	public ProductDto(Long id, String name, String urlImg, String priceKg) {
		super();
		this.id = id;
		this.name = name;
		this.urlImg = urlImg;
		this.priceKg = priceKg;
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

	public String getUrlImg() {
		return urlImg;
	}

	public void setUrlImg(String urlImg) {
		this.urlImg = urlImg;
	}

	public String getPriceKg() {
		return priceKg;
	}

	public void setPriceKg(String priceKg) {
		this.priceKg = priceKg;
	}

	@Override
	public String toString() {
		return "ProductDto [id=" + id + ", name=" + name + ", urlImg=" + urlImg + ", priceKg=" + priceKg + "]";
	}

}
