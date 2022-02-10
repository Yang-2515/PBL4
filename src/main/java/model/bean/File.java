package model.bean;

import java.io.InputStream;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import model.bo.CategoryBO;
import model.bo.FileCateBO;
import model.bo.UserBO;

public class File {
	private int fileID;
	private String name;
	private InputStream content;
	private String status;
	private Date createAt;
	private Date modifyAt;
	private Date deleteAt;
	private String description;
	private String size = "0";
	private int downloadCount;
	private int userID;
	private int folderID;
	public File(String name,String status, InputStream content, String description,String size,int userID, int folderID) {
		this.setName(name);
		this.setStatus(status);
		this.setSize(size);
		this.setDescription(description);
		this.setUserID(userID);
		this.setFolderID(folderID);
		this.setContent(content);
	}
	public int getFolderID() {
		return folderID;
	}
	public void setFolderID(int folderID) {
		this.folderID = folderID;
	}
	public File(String name,String status,Date createAt,Date modifyAt,Date deleteAt,String decription,String size,int downloadCount,int userID) {
		this.setName(name);
		this.setStatus(status);
		this.setSize(size);
		this.setCreateAt(createAt);
		this.setModifyAt(modifyAt);
		this.setDeleteAt(deleteAt);
		this.setDescription(decription);
		this.setDownloadCount(downloadCount);
		this.setUserID(userID);
	}
	public int getFileID() {
		return fileID;
	}
	public void setFileID(int fileID) {
		this.fileID = fileID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	public void setContent(InputStream content) {
		this.content = content;
	}
	public Date getModifyAt() {
		return modifyAt;
	}
	public void setModifyAt(Date modifyAt) {
		this.modifyAt = modifyAt;
	}
	public Date getDeleteAt() {
		return deleteAt;
	}
	public void setDeleteAt(Date deleteAt) {
		this.deleteAt = deleteAt;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String decription) {
		this.description = decription;
	}
	public int getDownloadCount() {
		return downloadCount;
	}
	public void setDownloadCount(int downloadCount) {
		this.downloadCount = downloadCount;
	}
	public String getSize() {
		return size;
	}
	public InputStream getContent() {
		return this.content;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public User getUser() {
		UserBO bo = new UserBO();
		return bo.getUserByUserID(userID);
	}
	
	public String getReadableFileSize() {
		if (!size.equals("0")){
			int size = Integer.valueOf(this.size);
		    final String[] units = new String[] { "B", "KB", "MB", "GB", "TB" };
		    int digitGroups = (int) (Math.log10(size)/Math.log10(1024));
		    return new DecimalFormat("#,##0.#").format(size/Math.pow(1024, digitGroups)) + " " + units[digitGroups];
		}
		return "0 KB";
	}
	public String getFileNameShortern() {
		if (this.name.length() < 30) return this.name;
		return this.name.substring(0, 29) + "...";
	}
	
	public List<Category> getListCategories() {
		FileCateBO boFC = new FileCateBO();
		CategoryBO cateBO = new CategoryBO();
		List<FileCate> listFC = boFC.getListFileCateByFileID(fileID);
		List<Integer> listCategoryID = new ArrayList<Integer>();
		for (int i = 0; i < listFC.size(); i++) {
			listCategoryID.add(listFC.get(i).getCateID());
		}
		return cateBO.getCategoriesByCateID(listCategoryID);
	}
	
	public String getCategoryString() {
		List<Category> listCate = getListCategories();
		
		if (listCate.size() == 0) return "";
		
		String st = listCate.get(0).getName();
		if (listCate.size() == 1) return st;
		
		for (int i=1 ; i < listCate.size(); i++) {
			st += ", " + listCate.get(1).getName();
		}
		
		return st;
	}
}
