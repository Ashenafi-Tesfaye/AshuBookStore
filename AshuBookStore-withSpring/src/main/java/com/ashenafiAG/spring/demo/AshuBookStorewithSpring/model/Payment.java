package com.ashenafiAG.spring.demo.AshuBookStorewithSpring.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "payments")
public class Payment {

	@Id
	@Column(name= "payment_id", nullable = false, updatable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, updatable = false)
	private Long orderedId;
	
	@Column(nullable = false, updatable = false)
	private String transactionId;
	
	@Column(nullable = false, updatable = false)
	private Date dateCreated;
	
	public Payment() {
		
	}

	public Payment(Long orderedId, String transactionId, Date dateCreated) {
		super();
		this.orderedId = orderedId;
		this.transactionId = transactionId;
		this.dateCreated = dateCreated;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderedId() {
		return orderedId;
	}

	public void setOrderedId(Long orderedId) {
		this.orderedId = orderedId;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Override
	public String toString() {
		return "Payment [id=" + id + ", orderedId=" + orderedId + ", transactionId=" + transactionId + ", dateCreated="
				+ dateCreated + "]";
	}

	
	
	
	
}
