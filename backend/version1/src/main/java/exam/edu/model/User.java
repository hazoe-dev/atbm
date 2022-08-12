package exam.edu.model;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String username;
	private String password;
	private Integer status;
	private Integer role;
	private String publicKey;
	private String fullname;
	private String address;
	private String phone;

	public User() {
	}

	public User(Long id, String username, String password, Integer status, Integer role, String publicKey,
			String fullname, String address, String phone) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.status = status;
		this.role = role;
		this.publicKey = publicKey;
		this.fullname = fullname;
		this.address = address;
		this.phone = phone;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
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
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", status=" + status + ", role=" + role + ", publicKey="
				+ publicKey + ", fullname=" + fullname + ", address=" + address + ", phone=" + phone + "]";
	}

	

}
