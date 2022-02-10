package model.bo;

import java.io.FileInputStream;
import java.util.List;

import model.bean.File;
import model.dao.DAOFile;

public class FileBO {
	DAOFile fileDAO = new DAOFile();
	
	public void addFile(File file) {
		fileDAO.addFile(file);
	}
	
	public File getFileByID(int idFile) {
		return fileDAO.getFileByID(idFile);
	}
	
	public void addContentFile(FileInputStream content, String name) {
		fileDAO.AddContentFile(content, name);
		return;
	}
	
	public File download(int idFile) {
		return fileDAO.download(idFile);
	}
	
	public List<File> getAllFile() {
		return fileDAO.getAllFile();
	}
	
	public List<File> getListFilesByName(String name, int folderID) {
		return fileDAO.getListFilesByName(name, folderID);
	}
	
	public List<File> getListFilesByName(String name) {
		return fileDAO.getListFilesByName(name);
	}
	
	public List<File> sortFile(int folderID, String status) {
		return fileDAO.sortFile(folderID, status);
	}
	
	public List<File> getFilesByUserID(int userID) {
		return fileDAO.getFilesByUserID(userID);
	}
	
	public List<File> getFilesByCategory(int cateID){
		return fileDAO.getFilesByCategory(cateID);
	}
	
	public List<File> getFileByFolderID(int fid) {
		return fileDAO.getFileByFolderID(fid);
	}
	
	public File getFileByName(String fileName, int folderID) {
        return fileDAO.getFileByName(fileName, folderID);
    }
	
	public void deleteFiles(List<Integer> arrIds) {
		StringBuilder arr = new StringBuilder();
		for (int i : arrIds) {
			arr.append(i + " ");
			if ( i!= arrIds.get(arrIds.size()-1)) arr.append(',');
		}
		fileDAO.deleteFiles(arr);
	}
	
	public boolean isFileExist(String fileName, int folderID) {
		File file = fileDAO.getFileByName(fileName, folderID);
		if(file != null) return true;
		return false;
	}
	
	public void updateFile(String field, String value, int idFile, int folderID) {
		fileDAO.updateFile(field, value, idFile);
	}
}
