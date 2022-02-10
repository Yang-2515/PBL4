package model.bo;

import java.util.List;

import model.bean.User;
import model.dao.DAOUser;

public class UserBO {
	DAOUser userDAO = new DAOUser();
	
	public List<User> getAllUser() {
		return userDAO.getAllUser();
	}
	
	public User getUserByUserID(int id) {
		return userDAO.getUserByUserID(id);
	}
	
	public User getUserByMSSV(String id) {
		return userDAO.getUserByMSSV(id);
	}
		
	public void AddUser(User u) {
		userDAO.AddUser(u);
	}
	
	public void UpdateUser(User u) {
		userDAO.UpdateUser(u);
	}
	
	public void DelUser(int id) {
		userDAO.DelUser(id);
	}
}
