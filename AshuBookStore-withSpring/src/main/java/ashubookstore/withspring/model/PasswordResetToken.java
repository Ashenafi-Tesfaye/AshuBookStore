package ashubookstore.withspring.model;

import java.util.Date;

public class PasswordResetToken {

	private Long id;
	private User user;
	private String token;
	private Date expirationDate;
	
	
	public PasswordResetToken() {
		super();
	}


	public PasswordResetToken(Long id, User user, String token, Date expirationDate) {
		super();
		this.id = id;
		this.user = user;
		this.token = token;
		this.expirationDate = expirationDate;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	public Date getExpirationDate() {
		return expirationDate;
	}


	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}


	@Override
	public String toString() {
		return "PasswordResetToken [id=" + id + ", user=" + user + ", token=" + token + ", expirationDate="
				+ expirationDate + "]";
	}
	
	
}
