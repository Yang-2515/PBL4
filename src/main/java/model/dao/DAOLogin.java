package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.bean.Login;

public class DAOLogin {
	Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Login> getAllLogin() {
        List<Login> list = new ArrayList<>();
        String query = "select * from login";
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
            	Login login = new Login(rs.getString(2),rs.getString(3),rs.getInt(4));
            	login.setLoginID(rs.getInt(1));
                list.add(login);
            }
            conn.close();
            ps.close();
        } catch (Exception e) {
        }
        return list;
    }
    
    public Login getLoginByLoginID(int id) {
        String query = "select * from login where LoginID = ?";
        Login login = null;
        try {
            conn = DBContext.getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                login = new Login(rs.getString(2),"",rs.getInt(4));
                login.setLoginID(id);
            }
            conn.close();
            ps.close();
        } catch (Exception e) {
        }
        return login;
    }
    
    public void AddLogin(Login u) {
    	String query = "insert into login (Username, Password, UserID) values(?,?,?)";
        try {
            conn = DBContext.getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, u.getUsername());
            ps.setString(2, u.getPassword());
            ps.setInt(3, u.getUserID());
            ps.executeUpdate();
            conn.close();
            ps.close();
        } catch (Exception e) {
        	System.out.println(e.toString());
        }
    }
    
    public void UpdateLogin(Login u) {
    	String query = "update login set Username = ?, Password=?, UserID=? where LoginID =?";
        try {
            conn = DBContext.getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setString(1, u.getUsername());
            ps.setString(2, u.getPassword());
            ps.setInt(3, u.getUserID());
            ps.setInt(4, u.getLoginID());
            ps.executeUpdate();
            conn.close();
            ps.close();
        } catch (Exception e) {
        	System.out.println(e.toString());
        }
    }
    
    public void DelLogin(int id) {
    	String query = "delete from login where LoginID = ?";
        try {
            conn = DBContext.getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
            conn.close();
            ps.close();
        } catch (Exception e) {
        	System.out.println(e.toString());
        }
    }
    
    public boolean isExistUser(String username, String password){
		boolean check = false;
		try{
			Connection con = DBContext.getConnection();
			String query = "select * from login where Username = ? and Password = ?";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, username);
			stmt.setString(2, password);
			ResultSet set = stmt.executeQuery();
			if (set.next()) check = true;
			stmt.close();
			con.close();
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
		return check;
	}
    
    public Login getLoginByUsername(String username) {
    	String query = "select * from login where Username = ?";
        Login login = null;
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                login = new Login(rs.getString(2),"",rs.getInt(4));
                login.setLoginID(rs.getInt(1));
            }
            conn.close();
            ps.close();
        } catch (Exception e) {
        }
        return login;
    }
    
    public Login getLoginByUserID(int id) {
    	String query = "select * from login where UserID = ?";
        Login login = null;
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                login = new Login(rs.getString(2),"",rs.getInt(4));
                login.setLoginID(rs.getInt(1));
            }
            conn.close();
            ps.close();
        } catch (Exception e) {
        }
        return login;
    }
    
    public boolean CheckAccount(String username) {
    	if(getLoginByUsername(username) != null) {
    		return true;
    	}
    	return false;
    }
}
