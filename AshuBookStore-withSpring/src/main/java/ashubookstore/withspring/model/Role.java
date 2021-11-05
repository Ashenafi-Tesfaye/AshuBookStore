package ashubookstore.withspring.model;

import ashubookstore.withspring.model.enums.RoleType;

public class Role {

	private Integer id;
	private RoleType roleName;
	
	public Role() {


	}

	public Role(Integer id, RoleType roleName) {


		this.id = id;
		this.roleName = roleName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public RoleType getRoleName() {
		return roleName;
	}

	public void setRoleName(RoleType roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", roleName=" + roleName + "]";
	}
	
	
}
