package ashubookstore.withspring.model;

import java.util.Date;

import ashubookstore.withspring.model.enums.RoleType;

public class User {

	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Date dateCreated;
	private RoleType userRole;
	
	
	
	public User() {
		super();
	}



	public User(Long id, String firstName, String lastName, String email, String password, Date dateCreated,
			RoleType userRole) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.dateCreated = dateCreated;
		this.userRole = userRole;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public Date getDateCreated() {
		return dateCreated;
	}



	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}



	public RoleType getUserRole() {
		return userRole;
	}



	public void setUserRole(RoleType userRole) {
		this.userRole = userRole;
	}



	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", dateCreated=" + dateCreated + "]";
	}
	
	
}
