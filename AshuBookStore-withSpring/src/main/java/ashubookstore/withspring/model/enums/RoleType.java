package ashubookstore.withspring.model.enums;

public enum RoleType {

	CUSTOMER("CUSTOMER"), ADMIN("ADMIN");
	
	private String value;

	private RoleType(String value) {
		this.value = value;
	}
	
	
	
}
