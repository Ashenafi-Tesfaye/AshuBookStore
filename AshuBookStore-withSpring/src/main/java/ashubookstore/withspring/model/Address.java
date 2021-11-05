package ashubookstore.withspring.model;

import java.util.Date;

public class Address {

	private long addressId;
	private String firstName;
	private String lastName;
	private Long userId;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String zip;
	private Date dateAdded;
	
	public Address() {
		
	}

	public Address(long addressId, String firstName, String lastName, Long userId, String address1, String address2,
			String city, String state, String zip, Date dateAdded) {
		super();
		this.addressId = addressId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userId = userId;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.dateAdded = dateAdded;
	}

	
	public long getAddressId() {
		return addressId;
	}

	public void setAddressId(long addressId) {
		this.addressId = addressId;
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}
	
	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", firstName=" + firstName + ", lastName=" + lastName + ", userId="
				+ userId + ", address1=" + address1 + ", address2=" + address2 + ", city=" + city + ", state=" + state
				+ ", zip=" + zip + ", dateAdded=" + dateAdded + "]";
	}

	
}
