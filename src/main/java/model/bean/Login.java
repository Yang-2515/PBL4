package model.bean;

public class Login {
	private int loginID;
	private int userID;
	private String username;
	private String password;
	public Login(String user, String pass, int userid) {
		this.setUsername(user);
		this.setPassword(pass);
		this.setUserID(userid);
	}

	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getLoginID() {
		return loginID;
	}
	public void setLoginID(int loginID) {
		this.loginID = loginID;
	}
	
}
