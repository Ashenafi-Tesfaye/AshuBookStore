package ashubookstore.withspring.model;

import java.util.Date;

public class ShoppingCart {

	private Long userId;
	private Long bookId;
	private Integer quantity;
	private Date dateCreated;
	
	public ShoppingCart() {
		
	}

	public ShoppingCart(Long userId, Long bookId, Integer quantity, Date dateCreated) {
	
		this.userId = userId;
		this.bookId = bookId;
		this.quantity = quantity;
		this.dateCreated = dateCreated;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Override
	public String toString() {
		return "ShoppingCart [userId=" + userId + ", bookId=" + bookId + ", quantity=" + quantity + ", dateCreated="
				+ dateCreated + "]";
	}
	
	
	
}
