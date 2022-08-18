package exam.edu.model;

import java.io.Serializable;
import java.util.Objects;

public class Category implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String group;

	public Category() {
	}

	public Category(Long id, String name, String group) {
		this.id = id;
		this.name = name;
		this.group = group;
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

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
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
		Category other = (Category) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", group=" + group + "]";
	}

}
