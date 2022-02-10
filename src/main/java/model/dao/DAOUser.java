package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.bean.User;

public class DAOUser {
	public List<User> getAllUser() {
        List<User> list = new ArrayList<>();
        String query = "select * from user";
        try {
        	Connection conn = DBContext.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6));
                user.setUserID(rs.getInt(1));
                list.add(user);
            }
            ps.close();
            conn.close();
        } catch (Exception e) {
        	
        }
        return list;
    }
	
	public User getLastUser() {
        String query = "select * from user order by UserID desc";
        User user = null;
        try {
        	Connection conn = DBContext.getConnection();
        	PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6));
                user.setUserID(rs.getInt(1));
            }
            ps.close();
            conn.close();
        } catch (Exception e) {
        }
        return user;
    }
    
    public User getUserByUserID(int id) {
        String query = "select * from user where UserID = ?";
        User user = null;
        try {
        	Connection conn = DBContext.getConnection();
        	PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user = new User(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6));
                user.setUserID(rs.getInt(1));
            }
            ps.close();
            conn.close();
        } catch (Exception e) {
        }
        return user;
    }
    
    public User getUserByMSSV(String id) {
        String query = "select * from user where MSSV = ?";
        User user = null;
        try {
        	Connection conn = DBContext.getConnection();
        	PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user = new User(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6));
                user.setUserID(rs.getInt(1));
            }
            ps.close();
            conn.close();
        } catch (Exception e) {
        }
        return user;
    }
    
    public void AddUser(User u) {
    	String query = "insert into user (Name, Email, Room, Mssv, RoleID) values(?,?,?,?,?)";
        try {
        	Connection conn = DBContext.getConnection();
        	PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, u.getName());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getRoom());
            ps.setString(4, u.getMssv());
            ps.setInt(5, u.getRoleID());
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
        	System.out.println(e.toString());
        }
    }
    
    public void UpdateUser(User u) {
    	String query = "update user set Name = ?, Email =?, Room =?, Mssv =?, RoleID =? where UserID =?";
        try {
        	Connection conn = DBContext.getConnection();
        	PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, u.getName());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getRoom());
            ps.setString(4, u.getMssv());
            ps.setInt(5, u.getRoleID());
            ps.setInt(6, u.getUserID());
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
        	System.out.println(e.toString());
        }
    }
    
    public void DelUser(int id) {
    	String query = "delete from user where UserID = ?";
        try {
        	Connection conn = DBContext.getConnection();
        	PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
        	System.out.println(e.toString());
        }
    }
}
