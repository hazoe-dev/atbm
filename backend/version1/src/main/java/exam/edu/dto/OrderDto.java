package exam.edu.dto;

public class OrderDto {
	private Long id;
	private String date;
	public OrderDto() {
	}
	
	public OrderDto(Long id, String date) {
		super();
		this.id = id;
		this.date = date;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

}
