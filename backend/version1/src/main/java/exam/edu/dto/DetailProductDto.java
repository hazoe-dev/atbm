package exam.edu.dto;

import java.util.List;

import exam.edu.model.Type;

public class DetailProductDto {
	private Long id;
	private String name;
	private List<String> urlImgs;
	private List<Type> types;
	private String priceKg;
	private String description;
	private String info;
	private String category;
	private String group;
	private String aboutPrice;
	
	public DetailProductDto() {
	}

	public DetailProductDto(Long id, String name, List<String> ulrImgs, List<Type> types, String priceKg,
			String description, String info, String category, String group, String aboutPrice) {
		super();
		this.id = id;
		this.name = name;
		this.urlImgs = ulrImgs;
		this.types = types;
		this.priceKg = priceKg;
		this.description = description;
		this.info = info;
		this.category = category;
		this.group = group;
		this.aboutPrice = aboutPrice;
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

	public List<String> getUrlImgs() {
		return urlImgs;
	}

	public void setUrlImgs(List<String> urlImgs) {
		this.urlImgs = urlImgs;
	}

	public List<Type> getTypes() {
		return types;
	}

	public void setTypes(List<Type> types) {
		this.types = types;
	}

	public String getPriceKg() {
		return priceKg;
	}

	public void setPriceKg(String priceKg) {
		this.priceKg = priceKg;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getAboutPrice() {
		return aboutPrice;
	}

	public void setAboutPrice(String aboutPrice) {
		this.aboutPrice = aboutPrice;
	}

	@Override
	public String toString() {
		return "DetailProductDto [id=" + id + ", name=" + name + ", ulrImgs=" + urlImgs + ", types=" + types
				+ ", priceKg=" + priceKg + ", description=" + description + ", info=" + info + ", category=" + category
				+ ", group=" + group + "]";
	}

	
}
