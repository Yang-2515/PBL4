package model.bo;

import java.util.List;

import model.bean.File;
import model.bean.User;
import model.dao.DAOFileShare;

public class ShareFileBO {
	DAOFileShare shareFileDAO = new DAOFileShare();
	FileBO fileBO = new FileBO();
	UserBO userBO = new UserBO();
	
	private void share(int fileID, int status, int userID) {
		File file = fileBO.getFileByID(fileID);
		if (file == null) return;
		if(status == 0) {
			shareFileDAO.insertOne(fileID, 0);
		}
		else if (status == -1) { // private
			shareFileDAO.deleteFileID(fileID);
		}
		else {
			User user = userBO.getUserByUserID(userID);
			if (user == null ) return;
			shareFileDAO.insertOne(fileID, userID);	// just share one person
		}
	}
	
	public void sharePublic(int fileID) {
		shareFileDAO.deleteFileID(fileID);
		share(fileID, 0, 0);
	}
	
	public void sharePrivate(int fileID) {
		share(fileID, -1, 0);
	}
	
	public void shareGroup(int fileID, List<Integer> listUserID) {
		shareFileDAO.deleteFileID(fileID);
		for (int i = 0; i < listUserID.size(); i++) {
			share(fileID, 2, listUserID.get(i));
		}
	}
	
	public boolean isValidUser(int usrID, int fileID) {
		return shareFileDAO.isValidUser(usrID, fileID);
	}
	
	public boolean isSharePublic(int fileID) {
		return shareFileDAO.isSharePublic(fileID);
	}
	
	public boolean isSharePrivate(int fileID) {
		return shareFileDAO.isSharePrivate(fileID);
	}
	
	public boolean isShareGroup(int fileID) {
		return shareFileDAO.isShareGroup(fileID);
	}
	
	public String getShareMode(int fileID) {
		String mode = null;
		if (isSharePrivate(fileID)) mode = "private";
		if (isSharePublic(fileID)) mode = "public";
		if (isShareGroup(fileID)) mode = "group";
		return mode;
	}
	
	public List<String> getListShareID(int fileID){
		return shareFileDAO.getListShareID(fileID);
	}
}