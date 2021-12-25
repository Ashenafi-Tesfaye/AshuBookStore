package com.ashenafiAG.spring.demo.AshuBookStorewithSpring.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "ordered_books")
public class OrderedBook {

	@Id
	@Column(nullable = false, updatable =true, unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderedBooksId;
	
	@Column(nullable = false)
	private Long orderId;
	
	@Column(nullable = false)
	private Long bookId;
	
	@Column(nullable = false)
	private Integer quantity;
	
	@Column(nullable = false)
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
