package ashubookstore.withspring.model;

import java.util.Date;

public class OrderedBook {

	private Long orderedBooksId;
	private Long orderId;
	private Long bookId;
	private Integer quantity;
	private Date dateCreated;
	
	
	public OrderedBook() {
		super();
	}


	public OrderedBook(Long orderedBooksId, Long orderId, Long bookId, Integer quantity, Date dateCreated) {
		super();
		this.orderedBooksId = orderedBooksId;
		this.orderId = orderId;
		this.bookId = bookId;
		this.quantity = quantity;
		this.dateCreated = dateCreated;
	}


	public Long getOrderedBooksId() {
		return orderedBooksId;
	}


	public void setOrderedBooksId(Long orderedBooksId) {
		this.orderedBooksId = orderedBooksId;
	}


	public Long getOrderId() {
		return orderId;
	}


	public void setOrderId(Long orderId) {
		this.orderId = orderId;
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
		return "OrderedBook [orderedBooksId=" + orderedBooksId + ", orderId=" + orderId + ", bookId=" + bookId
				+ ", quantity=" + quantity + ", dateCreated=" + dateCreated + "]";
	}
	
	
}
