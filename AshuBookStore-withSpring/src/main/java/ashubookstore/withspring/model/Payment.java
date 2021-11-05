package ashubookstore.withspring.model;

import java.util.Date;

public class Payment {

	private Long id;
	private Long orderedId;
	private String transactionId;
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
