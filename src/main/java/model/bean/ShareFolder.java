package model.bean;

public class ShareFolder {

	private int shareFolderID;
	private int folderID;
	private int userID;
	
	public ShareFolder(int folderid, int userid) {
		this.setFolderID(folderid);
		this.setUserID(userid);
	}
	public int getShareFolderID() {
		return shareFolderID;
	}
	public void setShareFolderID(int shareFolderID) {
		this.shareFolderID = shareFolderID;
	}
	public int getFolderID() {
		return folderID;
	}
	public void setFolderID(int folderID) {
		this.folderID = folderID;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
}
