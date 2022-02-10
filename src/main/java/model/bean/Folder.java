package model.bean;

import model.bo.UserBO;

public class Folder {

	private int folderID;
	private String  name;
	private int userID;
	private String description;
	private String status;
	public Folder(String name, int userid, String des, String status) {
		this.setName(name);
		this.setUserID(userid);
		this.setDescription(des);
		this.setStatus(status);
	}
	public int getFolderID() {
		return folderID;
	}
	public void setFolderID(int folderID) {
		this.folderID = folderID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public User getUser() {
		UserBO bo = new UserBO();
		return bo.getUserByUserID(userID);
	}
	

}
