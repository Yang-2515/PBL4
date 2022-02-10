package model.bo;

import java.util.List;

import model.bean.File;
import model.bean.ShareFolder;
import model.dao.*;

public class ShareFolderBO {
	DAOShareFolder daosharefolder = new DAOShareFolder();
	DAOFileShare daoShareFile = new DAOFileShare();
	DAOFile daoFile = new DAOFile();
	
	public void AddShareFolder(int folderid, int userid) {
		daosharefolder.AddShareFolder(folderid, userid);
		List<File> listFile = daoFile.getFileByFolderID(folderid);
		for (int i = 0; i < listFile.size(); i++) {
			daoShareFile.insertOne(listFile.get(i).getFileID(), userid);
		}
	}
	public void setPrivate(int folderid) {
		daosharefolder.delShareFolder(folderid);
		List<File> listFile = daoFile.getFileByFolderID(folderid);
		for (int i = 0; i < listFile.size(); i++) {
			daoShareFile.deleteFileID(listFile.get(i).getFileID());
		}
	}
	public void UpdateShareFolder(ShareFolder shareFolder) {
		daosharefolder.UpdateShareFolder(shareFolder);
	}
	public List<ShareFolder> getShareFolderByUserID(int userid) {
		return daosharefolder.getShareFolderByUserID(userid);
	}
	public List<ShareFolder> getAllShareFolder() {
		return daosharefolder.getAllShareFolder();
	}
	
	public void sharePublic(int folderid) {
		daosharefolder.delShareFolder(folderid);
		AddShareFolder(folderid, 0);
	}
	
	public void shareGroup(int folderID, List<Integer> listUserID) {
		for (int i = 0; i < listUserID.size(); i++) {
			AddShareFolder(folderID, listUserID.get(i));
		}
	}
	
	public boolean isValidUser(int usrID, int folderID) {
		return daosharefolder.isValidUser(usrID, folderID);
	}
	
	public boolean isSharePublic(int folderID) {
		return daosharefolder.isSharePublic(folderID);
	}
	
	public boolean isSharePrivate(int folderID) {
		return daosharefolder.isSharePrivate(folderID);
	}
	
	public boolean isShareGroup(int folderID) {
		return daosharefolder.isShareGroup(folderID);
	}
	
	public String getShareMode(int folderID) {
		String mode = null;
		if (isSharePublic(folderID)) mode = "public";
		if (isSharePrivate(folderID)) mode = "private";
		if (isShareGroup(folderID)) mode = "group";
		return mode;
	}
	
	public List<String> getListShareID(int folderID){
		return daosharefolder.getListShareID(folderID);
	}

}
