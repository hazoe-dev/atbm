package exam.edu.model;

import java.io.Serializable;
import java.util.Objects;

public class Order implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long total;
	private Long shipcost;
	private String infor_bill;
	private Long userId;
	private boolean verified;
	private String date;
	private String note;
	private String status;

	public Order() {
	}

	public Order(Long id, Long total, Long shipcost, String infor_bill, Long userId, boolean verified, String date,
			String note, String status) {
		super();
		this.id = id;
		this.total = total;
		this.shipcost = shipcost;
		this.infor_bill = infor_bill;
		this.userId = userId;
		this.verified = verified;
		this.date = date;
		this.note = note;
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Long getShipcost() {
		return shipcost;
	}

	public void setShipcost(Long shipcost) {
		this.shipcost = shipcost;
	}

	public String getInfor_bill() {
		return infor_bill;
	}

	public void setInfor_bill(String infor_bill) {
		this.infor_bill = infor_bill;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
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
		Order other = (Order) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", total=" + total + ", shipcost=" + shipcost + ", infor_bill=" + infor_bill
				+ ", userId=" + userId + ", verified=" + verified + ", date=" + date + ", note=" + note + "]";
	}

}
