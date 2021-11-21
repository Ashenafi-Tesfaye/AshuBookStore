package ashubookstore.withspring.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Table;



@Entity
@Table(name="Orders")
public class Order {

	private Long orderId;
	private String hash;
	private BigDecimal total;
	private Date dateOrdered;
	private Long addressId;
	private Long userId;
	private Boolean payed;
	
	
	
	public Order() {
		
	}



	public Order(Long orderId, String hash, BigDecimal total, Date dateOrdered, Long addressId, Long userId,
			Boolean payed) {
	
		this.orderId = orderId;
		this.hash = hash;
		this.total = total;
		this.dateOrdered = dateOrdered;
		this.addressId = addressId;
		this.userId = userId;
		this.payed = payed;
	}



	public Long getOrderId() {
		return orderId;
	}



	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}



	public String getHash() {
		return hash;
	}



	public void setHash(String hash) {
		this.hash = hash;
	}



	public BigDecimal getTotal() {
		return total;
	}



	public void setTotal(BigDecimal total) {
		this.total = total;
	}



	public Date getDateOrdered() {
		return dateOrdered;
	}



	public void setDateOrdered(Date dateOrdered) {
		this.dateOrdered = dateOrdered;
	}



	public Long getAddressId() {
		return addressId;
	}



	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}



	public Long getUserId() {
		return userId;
	}



	public void setUserId(Long userId) {
		this.userId = userId;
	}



	public Boolean getPayed() {
		return payed;
	}



	public void setPayed(Boolean payed) {
		this.payed = payed;
	}



	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", hash=" + hash + ", total=" + total + ", dateOrdered=" + dateOrdered
				+ ", addressId=" + addressId + ", userId=" + userId + ", payed=" + payed + "]";
	}
	
	
	
}
