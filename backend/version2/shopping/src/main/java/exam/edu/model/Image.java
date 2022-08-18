package exam.edu.model;

import java.io.Serializable;
import java.util.Objects;

public class Image implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;
	private String link;
	private Long productId;

	public Image() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Image(Long id, String link, Long productId) {
		super();
		this.id = id;
		this.link = link;
		this.productId = productId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
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
		Image other = (Image) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Image [id=" + id + ", link=" + link + ", productId=" + productId + "]";
	}

}
