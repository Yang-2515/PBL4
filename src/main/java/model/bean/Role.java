package model.bean;

public class Role {
	private int roleID;
	private String name;
	public Role(String name) {
		this.setName(name);
	}
	public int getRoleID() {
		return roleID;
	}
	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
