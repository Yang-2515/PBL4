package model.bean;

public class FileCate {
	private int fileCateID;
	private int fileID;
	private int cateID;
	public FileCate(int cateid, int fileid) {
		this.setCateID(cateid);
		this.setFileID(fileid);
	}
	public int getFileCateID() {
		return fileCateID;
	}
	public void setFileCateID(int fileCateID) {
		this.fileCateID = fileCateID;
	}
	public int getFileID() {
		return fileID;
	}
	public void setFileID(int fileID) {
		this.fileID = fileID;
	}
	public int getCateID() {
		return cateID;
	}
	public void setCateID(int cateID) {
		this.cateID = cateID;
	}
}
