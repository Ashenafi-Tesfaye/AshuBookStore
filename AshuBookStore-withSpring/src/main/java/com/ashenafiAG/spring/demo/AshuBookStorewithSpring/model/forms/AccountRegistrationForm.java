package com.ashenafiAG.spring.demo.AshuBookStorewithSpring.model.forms;

public class AccountRegistrationForm {

	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String confirmedPassword;
	
	
	public AccountRegistrationForm() {
		super();
	}


	public AccountRegistrationForm(String firstName, String lastName, String email, String password,
			String confirmedPassword) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.confirmedPassword = confirmedPassword;
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


	public String getConfirmedPassword() {
		return confirmedPassword;
	}


	public void setConfirmedPassword(String confirmedPassword) {
		this.confirmedPassword = confirmedPassword;
	}


	@Override
	public String toString() {
		return "AccountRegistrationForm [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", confirmedPassword=" + confirmedPassword + "]";
	}
	
	
}
