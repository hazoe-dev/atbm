package exam.edu.model;

import java.io.Serializable;
import java.util.Objects;

public class Key implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;
	private Integer status;
	private String publicKey;
	private Long userId;

	public Key() {
	}

	public Key(Long id, Integer status, String publicKey, Long userId) {
		this.id = id;
		this.status = status;
		this.publicKey = publicKey;
		this.userId = userId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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
		Key other = (Key) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Key [id=" + id + ", status=" + status + ", publicKey=" + publicKey + ", userId=" + userId + "]";
	}

}
