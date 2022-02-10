package model.bean;

import model.bo.LoginBO;

//import org.apache.catalina.util.ToStringUtil;

public class User {
	private int userID;
	private String name;
	private String email;
	private String room;
	private String mssv;
	private int roleID;
	
	public User(String name, String email, String room, String mssv, int roleID) {
		this.setName(name);
		this.setEmail(email);
		this.setRoom(room);
		this.setMssv(mssv);
		this.setRoleID(roleID);
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMssv() {
		return mssv;
	}

	public void setMssv(String mssv) {
		this.mssv = mssv;
	}

	public int getRoleID() {
		return roleID;
	}

	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}
	public String toString() {
		return "Name: " + name + ", email: " + email;
	}
	
	public Login getLogin() {
		LoginBO lgBO = new LoginBO();
		return lgBO.getLoginByUserID(userID);
	}
}
