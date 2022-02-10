package model.bo;

import java.util.List;

import model.bean.FileCate;
import model.dao.DAOFileCate;

public class FileCateBO {
	DAOFileCate fileCateDAO = new DAOFileCate();
	
	public List<FileCate> getAllFileCate() {
		return fileCateDAO.getAllFileCate();
	}
	
	public FileCate getFileCateByFileCateID(int id) {
		return fileCateDAO.getFileCateByFileCateID(id);
	}
	
	public List<FileCate> getListFileCateByFileID(int id) {
		return fileCateDAO.getListFileCateByFileID(id);
	}
	
	public void AddFileCate(FileCate u) {
		fileCateDAO.AddFileCate(u);
	}
	
    public void UpdateFileCate(FileCate u) {
		fileCateDAO.UpdateFileCate(u);
    }
    
    public void DelFileCate(int id) {
    	fileCateDAO.DelFileCate(id);
    }
}
