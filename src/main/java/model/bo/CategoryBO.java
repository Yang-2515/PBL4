package model.bo;

import java.util.List;

import model.bean.Category;
import model.bean.FileCate;
import model.dao.DAOCategory;
import model.dao.DAOFileCate;

public class CategoryBO {
	DAOCategory daoCate = new DAOCategory();
	DAOFileCate daoFC = new DAOFileCate();
	
	public List<Category> getAllCategories(){
		return daoCate.getAllCategories();
	}
	
	public List<Category> getCategoriesByCateID(List<Integer> ids) {
    	StringBuilder strId = new StringBuilder();
    	for (int i : ids) {
			strId.append(i);
			if(i != ids.get(ids.size()-1)) strId.append(", ");
		}
    	return daoCate.getCategoriesByCateID(strId.toString());
	}
	
	public Category getCategoryByName(String name) {
        return daoCate.getCategoryByName(name);
    }
	
	public void addCategories(String[] names) {
		for (String name : names) {
			name = name.trim();
			if (getCategoryByName(name) == null) {
				daoCate.AddCategory(new Category(name));
			}
		}
	}
	
	public void addFileCate(int fileID, int cateID) {
		FileCate fc = new FileCate(cateID, fileID);
		daoFC.DelFileCate(fileID);
		daoFC.AddFileCate(fc);
	}
}