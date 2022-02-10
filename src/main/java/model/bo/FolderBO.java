package model.bo;

import model.dao.DAOFolder;

import java.util.ArrayList;
import java.util.List;

import model.bean.File;
import model.bean.Folder;

public class FolderBO {
	DAOFolder daoFolder = new DAOFolder();
	
	public void DelFolder(int fid) {
		daoFolder.DelFolder(fid);
		FileBO fileBO = new FileBO();
		List<File> files = fileBO.getFileByFolderID(fid);
		List<Integer> fileID = new ArrayList<Integer>();
		for(int i = 0; i < files.size(); i++) {
			fileID.add(files.get(i).getFileID());
		}
		fileBO.deleteFiles(fileID);
	}
	
//	public void shareFolderPublic(int folderid) {
//		ShareFolderBO shareFolderBO = new ShareFolderBO();
//		shareFolderBO.sharePublic(folderid);
//		FileBO filebo = new FileBO();
//		ShareFileBO shareFileBO = new ShareFileBO();
//		List<File> files = filebo.getFileByFolderID(folderid);
//		for(int i = 0; i < files.size(); i++) {
//			shareFileBO.sharePublic(files.get(i).getFileID());
//		}
//	}
//	
//	public void shareGroup(int folderid, List<Integer> listUserID) {
//		ShareFolderBO shareFolderBO = new ShareFolderBO();
//		shareFolderBO.shareGroup(folderid, listUserID);
//		FileBO fileBO = new FileBO();
//		ShareFileBO shareFileBO = new ShareFileBO();
//		List<File> files = fileBO.getFileByFolderID(folderid);
//		for (int i = 0; i < files.size(); i++) {
//			shareFileBO.shareGroup(files.get(i).getFileID(), listUserID);
//		}
//	}
//	
//	
//	public void setPrivate(int folderid) {
//		ShareFolderBO shareFolderBO = new ShareFolderBO();
//		shareFolderBO.setPrivate(folderid);
//	}
	
	public List<Folder> getFolderByUserID(int userid) {
		return daoFolder.getFolderByUserID(userid);
	}
	
	public Folder getFolderByID(int id) {
		return daoFolder.getFolderByID(id);
	}
	
	public void AddFolder(Folder f) {
		daoFolder.AddFolder(f);
	}
	
	public void UpdateFolder(Folder f) {
		daoFolder.UpdateFolder(f);
	}
}
