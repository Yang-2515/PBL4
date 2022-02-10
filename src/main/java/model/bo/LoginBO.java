package model.bo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

import model.bean.Login;
import model.bean.User;
import model.dao.DAOLogin;
import model.dao.DAOUser;

public class LoginBO {
	DAOLogin loginDAO = new DAOLogin();
	DAOUser userDAO = new DAOUser();
	
	public boolean isValidUser(String userName, String password){
		MessageDigest md;
		String pwdHashed = null;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] digest = md.digest();
			pwdHashed = DatatypeConverter.printHexBinary(digest).toLowerCase();
		} catch (NoSuchAlgorithmException e) {

		}
		return loginDAO.isExistUser(userName, pwdHashed);
	}
	
	public Login getLoginByUsername(String usr) {
		return loginDAO.getLoginByUsername(usr);
	}
	
	public Login getLoginByLoginID(int id) {
		return loginDAO.getLoginByLoginID(id);
	}
	
	public void AddLogin(Login u) {
		loginDAO.AddLogin(u);
	}
	
	public void UpdateLogin(Login u) {
		loginDAO.UpdateLogin(u);
	}
	
	public void DelLogin(int id) {
		loginDAO.DelLogin(id);
	}
	
	public Login getLoginByUserID(int id) {
		return loginDAO.getLoginByUserID(id);
	}
	
	public synchronized void  RegisterUser(Login lg, User user) {
		userDAO.AddUser(user);
		User usr = userDAO.getLastUser();
		lg.setUserID(usr.getUserID());
		loginDAO.AddLogin(lg);
	}
	
	public boolean CheckAccount(String username) {
		return loginDAO.CheckAccount(username);
	}
}
